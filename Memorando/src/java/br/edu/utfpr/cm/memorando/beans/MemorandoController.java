package br.edu.utfpr.cm.memorando.beans;

import br.edu.utfpr.cm.memorando.conversores.AnexoConverter;
import br.edu.utfpr.cm.memorando.conversores.RemetenteConverter;
import br.edu.utfpr.cm.memorando.entidades.AnexoEntity;
import br.edu.utfpr.cm.memorando.entidades.MemorandoEntity;
import br.edu.utfpr.cm.memorando.entidades.RemetenteEntity;
import br.edu.utfpr.cm.memorando.jpa.AnexoFacade;
import br.edu.utfpr.cm.memorando.jpa.MemorandoFacade;
import br.edu.utfpr.cm.memorando.jpa.RemetenteFacade;
import br.edu.utfpr.cm.memorando.jsfutil.JsfUtil;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import org.primefaces.event.FileUploadEvent;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatch;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;
import org.apache.commons.io.filefilter.MagicNumberFileFilter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "memorandoController")
@SessionScoped
public class MemorandoController implements Serializable {

    private MemorandoEntity memorando;
    private AnexoEntity anexo;
    private MemorandoEntity memorandoSelecionado;
    private AnexoEntity anexoSelecionado;
    private StreamedContent file;
    private DataModel items = null;
    
    @EJB
    private MemorandoFacade ejbMemorandoFacade;
    @EJB
    private RemetenteFacade ejbRemetenteFacade;
    @EJB
    private AnexoFacade ejbAnexoFacade;

    public MemorandoController() {
        this.memorando = new MemorandoEntity();
        this.memorando.setAnexoList(new ArrayList<AnexoEntity>());

    }

    public AnexoEntity getAnexoSelecionado() {
        anexoSelecionado = new AnexoEntity();
        return anexoSelecionado;
    }

    public void setAnexoSelecionado(AnexoEntity anexoSelecionado) {
        this.anexoSelecionado = anexoSelecionado;
    }

    public MemorandoEntity getMemorandoSelecionado() {
        return memorandoSelecionado;
    }

    public void setMemorandoSelecionado(MemorandoEntity memorandoSelecionado) {
        this.memorandoSelecionado = memorandoSelecionado;
    }

    public MemorandoEntity getMemorando() {
        return memorando;
    }

    public void setMemorando(MemorandoEntity memorando) {
        this.memorando = memorando;
    }

    public MemorandoFacade getEjbMemorandoFacade() {
        return ejbMemorandoFacade;
    }

    public RemetenteFacade getEjbRemetenteFacade() {
        return ejbRemetenteFacade;
    }

    public AnexoFacade getEjbAnexoFacade() {
        return ejbAnexoFacade;
    }

    //Pega um arquivo e envia
    public void handleFileUpload(FileUploadEvent event) {
        /*Cada arquivo enviado tem que ser criado em um novo anexo*/
        this.anexo = new AnexoEntity();
        byte[] bFile = null;
        try {
            bFile = event.getFile().getContents();
            /*Pega o arquivo enviado e seta no atributo arquivo de anexo*/
            this.anexo.setArquivo(bFile);
            /*Pega o nome do arquivo e seta no atributo nomeArquivo do anexo*/
            this.anexo.setNomeArquivo(event.getFile().getFileName());
            this.memorando.getAnexoList().add(anexo);
            FacesMessage msg = new FacesMessage("Anexo ...", event.getFile().getFileName() + " Foi Anexado!!!.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            System.out.println("ERRO===>>>> " + e);
        }
    }

    public StreamedContent downloadAnexo() {
        try {
            /*Cria um InputStream com o campo byte[] */
            InputStream stream = new ByteArrayInputStream(this.anexoSelecionado.getArquivo());
            /*Classe da biblioteca jminemagic cria um parser*/
            Magic parser = new Magic();
            /*Pega o tipo de Mime do byte[]*/
            MagicMatch match = parser.getMagicMatch(this.anexoSelecionado.getArquivo());
            //Primeiro parametro é o stream, segundo é o tipo de arquivo, terceiro é o nome do arquivo
            file = new DefaultStreamedContent(stream, match.getMimeType(), this.getAnexoSelecionado().getNomeArquivo());
        } catch (MagicParseException ex) {
            Logger.getLogger(MemorandoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MagicMatchNotFoundException ex) {
            Logger.getLogger(MemorandoController.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (MagicException ex) {
            Logger.getLogger(MemorandoController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return this.file;
    }

    public String create() {
        try {
            getEjbMemorandoFacade().create(this.memorando);
            JsfUtil.addSuccessMessage("Memorando Criado");

            /*Inicializa novamente memorando e anexo e cria uma lista de anexo*/
            this.memorando = new MemorandoEntity();
            this.memorando.setAnexoList(new ArrayList<AnexoEntity>());

            return "Create";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Erro de Persistencia (MemorandoController)");
            return null;
        }
    }


    public List<MemorandoEntity> listarMemorandos() {
        return getEjbMemorandoFacade().findAll();
    }

    public List<RemetenteEntity> listarRemetentes() {
        return getEjbRemetenteFacade().findAll();
    }

    public List<AnexoEntity> listarAnexo() {
        return this.memorandoSelecionado.getAnexoList();
    }

    public Object getRemetenteConverter() {
        RemetenteConverter rc = new RemetenteConverter();
        rc.setRemetenteFacade(ejbRemetenteFacade);
        return rc;
    }

    public Object getAnexoConverter() {
        AnexoConverter rc = new AnexoConverter();
        rc.setAnexoFacade(ejbAnexoFacade);
        return rc;
    }
}

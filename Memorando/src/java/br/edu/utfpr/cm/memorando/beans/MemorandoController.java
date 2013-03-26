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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import org.primefaces.event.FileUploadEvent;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

@ManagedBean(name = "memorandoController")
@SessionScoped
public class MemorandoController implements Serializable {

    private MemorandoEntity memorando;
    private AnexoEntity anexo;
    
 
    private DataModel items = null;
    
    @EJB
    private MemorandoFacade ejbMemorandoFacade;
    
    @EJB
    private RemetenteFacade ejbRemetenteFacade;
    
    @EJB
    private AnexoFacade ejbAnexoFacade;
    
    
    public MemorandoController() {
        this.memorando = new MemorandoEntity();        
        this.anexo = new AnexoEntity();
        this.memorando.setAnexoList(new ArrayList<AnexoEntity>());         
    }

    public MemorandoEntity getMemorando() {
        return memorando;
    }

    public void setMemorando(MemorandoEntity memorando) {
        this.memorando = memorando;
    }

     public DataModel getItems() {
        return items;
    }

    public void setItems(DataModel items) {
        this.items = items;
    }

    public MemorandoFacade getEjbMemorandoFacade() {
        return ejbMemorandoFacade;
    }

    public void setEjbMemorandoFacade(MemorandoFacade ejbMemorandoFacade) {
        this.ejbMemorandoFacade = ejbMemorandoFacade;
    }

    public RemetenteFacade getEjbRemetenteFacade() {
        return ejbRemetenteFacade;
    }

    public void setEjbRemetenteFacade(RemetenteFacade ejbRemetenteFacade) {
        this.ejbRemetenteFacade = ejbRemetenteFacade;
    }

    public AnexoFacade getEjbAnexoFacade() {
        return ejbAnexoFacade;
    }

    public void setEjbAnexoFacade(AnexoFacade ejbAnexoFacade) {
        this.ejbAnexoFacade = ejbAnexoFacade;
    }
    
    //Pega um arquivo e envia
    public void handleFileUpload(FileUploadEvent event) {        
        byte[] bFile = null;
        try {
            System.out.println("ENTROUUUUUU===>>>> ");
            bFile = event.getFile().getContents();
            this.anexo.setArquivo(bFile);
            this.memorando.getAnexoList().add(anexo);
            FacesMessage msg = new FacesMessage("Anexo ...", event.getFile().getFileName() + " Foi Anexado!!!.");  
            FacesContext.getCurrentInstance().addMessage(null, msg);              
        } catch (Exception e) {
            System.out.println("ERRO===>>>> "+e);
        }        
    }
    

    public String create() {
        try {            
            getEjbMemorandoFacade().create(this.memorando);  
            JsfUtil.addSuccessMessage("Memorando Criado");
            this.memorando = new MemorandoEntity();
            this.anexo = new AnexoEntity();
            return "Create";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Erro de Persistencia (MemorandoController)");
            return null;
        }
    }

    public List<MemorandoEntity> listarMemorandos() {
        return getEjbMemorandoFacade().findAll();
    }   
    
    public List<RemetenteEntity> listarRemetentes(){
        return getEjbRemetenteFacade().findAll();
    }
    
    public List<AnexoEntity> listarAnexo(){
        return getEjbAnexoFacade().findAll();
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

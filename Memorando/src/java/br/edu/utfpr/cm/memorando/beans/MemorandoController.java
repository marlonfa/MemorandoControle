package br.edu.utfpr.cm.memorando.beans;

import br.edu.utfpr.cm.memorando.conversores.RemetenteConverter;
import br.edu.utfpr.cm.memorando.entidades.MemorandoEntity;
import br.edu.utfpr.cm.memorando.entidades.RemetenteEntity;
import br.edu.utfpr.cm.memorando.jpa.MemorandoFacade;
import br.edu.utfpr.cm.memorando.jpa.RemetenteFacade;
import br.edu.utfpr.cm.memorando.jsfutil.JsfUtil;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;

@ManagedBean(name = "memorandoController")
@SessionScoped
public class MemorandoController implements Serializable {

    private MemorandoEntity current;
    private DataModel items = null;
    @EJB
    private MemorandoFacade ejbFacade;
    @EJB
    private RemetenteFacade remetenteFacade;

    public MemorandoEntity getCurrent() {
        return current;
    }

    public void setCurrent(MemorandoEntity current) {
        this.current = current;
    }

    public MemorandoController() {
        this.current = new MemorandoEntity();
    }

    private MemorandoFacade getFacade() {
        return ejbFacade;
    }

     private RemetenteFacade getRemetenteFacade() {
        return remetenteFacade;
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage("Memorando Criado");
            this.current = new MemorandoEntity();
            return "Create";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Erro de Persistencia");
            return null;
        }
    }

    public List<MemorandoEntity> listarMemorandos() {
        return getFacade().findAll();
    }

    public Object getRemetenteConverter() {
        RemetenteConverter rc = new RemetenteConverter();
        rc.setRemetenteFacade(remetenteFacade);
        return rc;
    }
    
    public List<RemetenteEntity> listarRemetentes(){
        return getRemetenteFacade().findAll();
    }
}

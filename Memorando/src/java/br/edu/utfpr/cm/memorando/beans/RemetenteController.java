/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.memorando.beans;

import br.edu.utfpr.cm.memorando.entidades.RemetenteEntity;
import br.edu.utfpr.cm.memorando.jpa.RemetenteFacade;
import br.edu.utfpr.cm.memorando.jsfutil.JsfUtil;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;

/**
 *
 * @author marlon
 */
@ManagedBean(name = "remetenteController")
@SessionScoped
public class RemetenteController implements Serializable {
    
    private RemetenteEntity current;
    private DataModel items = null;
    @EJB
    private RemetenteFacade ejbFacade;
    
    public RemetenteEntity getCurrent() {
        return current;
    }

    public void setCurrent(RemetenteEntity current) {
        this.current = current;
    }

    public RemetenteController() {
        this.current = new RemetenteEntity();
    }
    
    private RemetenteFacade getFacade() {
        return ejbFacade;
    }

    public String create () {
        try {
            getFacade().create(current);           
            JsfUtil.addSuccessMessage("Remetente Criado");
            this.current = new RemetenteEntity();
            return "Create";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Erro de Persistencia");
            return null;
        }
    }
    
    public List<RemetenteEntity> listarRemetentes(){
        return getFacade().findAll();
    }
}

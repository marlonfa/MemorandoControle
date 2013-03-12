/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.memorando.beans;

import br.edu.utfpr.cm.memorando.entidades.AnexoEntity;
import br.edu.utfpr.cm.memorando.jpa.AnexoFacade;
import br.edu.utfpr.cm.memorando.jsfutil.JsfUtil;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;  

/**
 *
 * @author marlon
 */
@ManagedBean(name = "anexoController")
@SessionScoped
public class AnexoController implements Serializable{
    
    private AnexoEntity anexo;

    @EJB
    private AnexoFacade ejbAnexoFacade;
    
    public AnexoController(){
        this.anexo = new AnexoEntity();
    }

    public AnexoEntity getAnexo() {
        return anexo;
    }

    public void setAnexo(AnexoEntity anexo) {
        this.anexo = anexo;
    }

    public AnexoFacade getEjbAnexoFacade() {
        return ejbAnexoFacade;
    }

    public void setEjbAnexoFacade(AnexoFacade ejbAnexoFacade) {
        this.ejbAnexoFacade = ejbAnexoFacade;
    }
    
         
    public void handleFileUpload(FileUploadEvent event) {        
        byte[] bFile = null;
        try {
            bFile = event.getFile().getContents();
            getAnexo().setArquivo(bFile);
            FacesMessage msg = new FacesMessage("Anexo ...", event.getFile().getFileName() + " Foi Anexado!!!.");  
            FacesContext.getCurrentInstance().addMessage(null, msg);              
        } catch (Exception e) {
            System.out.println("ERRO===>>>> "+e);
        }        
    }
    
    public String create() {
        try {
            getEjbAnexoFacade().create(this.anexo);
            JsfUtil.addSuccessMessage("Anexo Enviado");
            this.anexo = new AnexoEntity();
            return "Create";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Erro de Persistencia (AnexoController)");
            return null;
        }
    }
        
}

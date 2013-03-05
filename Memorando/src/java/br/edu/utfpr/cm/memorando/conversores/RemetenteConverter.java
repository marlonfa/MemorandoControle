/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.memorando.conversores;

import javax.faces.convert.FacesConverter;
import br.edu.utfpr.cm.memorando.jpa.RemetenteFacade;
import br.edu.utfpr.cm.memorando.entidades.RemetenteEntity;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author marlon
 */


@FacesConverter(forClass = RemetenteEntity.class)
public class RemetenteConverter implements Converter{
    
    @EJB
    private RemetenteFacade ejbFacade;

    public void setRemetenteFacade(RemetenteFacade facade) {
        this.ejbFacade = facade;
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || value.equals("null")) {
            return null;
        }
        Object o  = ejbFacade.find(getKey(value));
        return ejbFacade.find(getKey(value));
    }

    Long getKey(String value) {
        java.lang.Long key;
        key = Long.valueOf(value);
        return key;
    }

    String getStringKey(java.lang.Long value) {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if ((object == null) || (object instanceof String)){
            return null;
        }
        if (object instanceof RemetenteEntity) {
            RemetenteEntity a = (RemetenteEntity) object;
            return getStringKey(a.getId());
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + RemetenteFacade.class.getName());
        }
    }
}

package br.edu.utfpr.cm.memorando.conversores;

import javax.faces.convert.FacesConverter;
import br.edu.utfpr.cm.memorando.jpa.RemetenteFacade;
import br.edu.utfpr.cm.memorando.entidades.RemetenteEntity;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@FacesConverter(forClass = RemetenteEntity.class)
public class RemetenteConverter implements Converter{
    
    @EJB
    private RemetenteFacade ejbFacade;

    public void setRemetenteFacade(RemetenteFacade facade) {
        this.ejbFacade = facade;
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if(value == null || value.length() == 0 || value.equals("null")){
            return null; 
        }
        return ejbFacade.find(new Long(value));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if ((value == null) || (value instanceof String)){
            return null;
        }        
        return ((RemetenteEntity)value).getId().toString();
    }
}
package br.edu.utfpr.cm.memorando.conversores;

import javax.faces.convert.FacesConverter;
import br.edu.utfpr.cm.memorando.jpa.AnexoFacade;
import br.edu.utfpr.cm.memorando.entidades.AnexoEntity;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@FacesConverter(forClass = AnexoEntity.class)
public class AnexoConverter implements Converter{
    
    @EJB
    private AnexoFacade ejbAnexoFacade;

    public void setAnexoFacade(AnexoFacade facade) {
        this.ejbAnexoFacade = facade;
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || value.equals("null")) {
            return null;
        }
        return ejbAnexoFacade.find(new Long(value));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if ((value == null) || (value instanceof String)){
            return null;
        }        
        return ((AnexoEntity)value).getId().toString();
    }
}
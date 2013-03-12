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
        Object o  = ejbAnexoFacade.find(getKey(value));
        return ejbAnexoFacade.find(getKey(value));
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
        if (object instanceof AnexoEntity) {
            AnexoEntity a = (AnexoEntity) object;
            return getStringKey(a.getId());
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + AnexoFacade.class.getName());
        }
    }
}





    
   


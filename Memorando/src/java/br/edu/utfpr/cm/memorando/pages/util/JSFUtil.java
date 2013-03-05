package br.edu.utfpr.cm.memorando.pages.util;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author reginaldo
 * @author marcelo
 */
public class JSFUtil {

    public static String ANEXO_LIST_ATTR = "anexoList";
    public static String ANEXOPA_LIST_ATTR = "anexoPAList";

    public static void ensureAddErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }

    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addErrorMessage(String msg, FacesMessage.Severity severity) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    /**
     *
     * @param msg - Mensagem a ser enviado ao formulário.
     * @param componentClientID - Client ID em que a mensagem será mostrada, no formato "formulario:idDoComponente".
     * O formulário deve ter um ID e o componente de ter um ID.
     */
    public static void addMessageToComponentClientID(String msg, String componentClientID) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(componentClientID, facesMsg);
    }

    /**
     *
     * @param msg - Mensagem a ser enviado ao formulário.
     * @param componentID - Id do componente em que a mensagem será mostrada. Não precisa ser um ID no formato "formulario:idDoComponente".
     * É necessário apenas indicar o ID do componente que o método irá buscar em toda a árvore DOM.
     */
    public static void addMessageToComponentID(String msg, String componentID) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        UIComponent mybutton = findComponent(FacesContext.getCurrentInstance().getViewRoot(), componentID);
        FacesContext.getCurrentInstance().addMessage(mybutton.getClientId(FacesContext.getCurrentInstance()), facesMsg);
    }

    private static UIComponent findComponent(UIComponent parent, String id) {
        if (id.equals(parent.getId())) {
            return parent;
        }
        Iterator<UIComponent> kids = parent.getFacetsAndChildren();
        while (kids.hasNext()) {
            UIComponent kid = kids.next();
            UIComponent found = findComponent(kid, id);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }

    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = JSFUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }

    public static Object getObjectFromRequestParameter(String attributeName) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getAttribute(attributeName);
    }

    public static Object getObjectFromSession(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
    }

    public static void dispatch(String s) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().dispatch(s);
        } catch (IOException ex) {
            Logger.getLogger(JSFUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void redirect(String s) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(s);
        } catch (IOException ex) {
            Logger.getLogger(JSFUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static HttpSession getSession(Boolean create) {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(create);
        return session;
    }

    /**
     * Pega o contexto atual da aplicação.
     * @return o contexto atual da aplicação.
     * @see javax.faces.context.FacesContext
     */
    public static FacesContext getContext() {
        return FacesContext.getCurrentInstance();
    }

    /**
     * Encontra um componente a partir da visão principal.
     * @param componentId id do componente a ser buscado.
     * @return retorna o componente com o id especificado caso ele exista ou
     * <code>null</code> caso contrário.
     */
    public static UIComponent findComponent(String componentId) {
        UIComponent component = UIViewRoot.getCurrentComponent(getContext());
        return component.findComponent(componentId);
    }

    public static void removeAnexosFromSession() {
        ((HttpSession) JSFUtil.getSession(Boolean.FALSE)).removeAttribute(ANEXO_LIST_ATTR);
        ((HttpSession) JSFUtil.getSession(Boolean.FALSE)).removeAttribute(ANEXOPA_LIST_ATTR);
    }

    /**
     * Obtem o nome do usuário logado na sessão.
     * @return uma <code>String</code> com o nome o do usuário.
     */
    public static String getUserFromSession() {
        String userName = "--";
        HttpSession session = getSession(Boolean.FALSE);
        if (session != null) {
            userName = (String) session.getAttribute("userFullName");
        }
        return userName;
    }
}

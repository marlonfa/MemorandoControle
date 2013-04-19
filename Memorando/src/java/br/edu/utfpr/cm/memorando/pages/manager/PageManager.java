package br.edu.utfpr.cm.memorando.pages.manager;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.edu.utfpr.cm.memorando.pages.util.JSFUtil;

/**
 * Esta classe é responsável por gerenciar as URLs e o fluxo das páginas
 * de toda a aplicação.
 * @author marcelo
 */
@ManagedBean(name = "pageManager")
@SessionScoped
public class PageManager implements Serializable {

    public PageManager() {
    }
    private String paginaAtiva = "./pages/MemorandoCreate.xhtml";    

    
    public String getPaginaAtiva() {
        return paginaAtiva;
    }

    public void setPaginaAtiva(String paginaAtiva) {
        this.paginaAtiva = paginaAtiva;
    }
    
    public void setPg1() {
        this.paginaAtiva = "./pages/MemorandoCreate.xhtml";
    }
    
    public void setPg2() {
        this.paginaAtiva = "./pages/RemetenteCreate.xhtml";
    }
    
    public void setPg3() {
        this.paginaAtiva = "./pages/MemorandoList.xhtml";
    }
    
    public void setPg4() {
        this.paginaAtiva = "./pages/RemetenteList.xhtml";
    }
    
    public void setPg5() {
        this.paginaAtiva = "./pages/AnexoList.xhtml";
    }
    
    public void setPg6() {
        this.paginaAtiva = "./pages/MemorandoView.xhtml";
    }
    
    public String getPg1() {
        return this.paginaAtiva;
    }
    
    public String getPg2() {
        return this.paginaAtiva;
    }
    
    public String getPg3() {
        return this.paginaAtiva;
    }
    public String getPg4() {
        return this.paginaAtiva;
    }
}

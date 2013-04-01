package br.edu.utfpr.cm.memorando.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
@Entity
@Table(name = "memorando")
public class MemorandoEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
       
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id")
    private Long id;

    @Column (name = "data") 
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data;
       
    @Column  (name = "nome_destinatario") 
    private String nomeDestinatario; 
    
    @Column  (name = "assunto") 
    private String assunto;
    
    @Column (name = "conteudo") 
    private String conteudo;  
    
    @JoinColumn(name = "memorando", referencedColumnName = "id")
    @OneToMany(cascade = CascadeType.ALL)
    private List<AnexoEntity> anexoList;
    
    @JoinColumn(name = "remetente_id", referencedColumnName="id", nullable=false)
    @ManyToOne(optional=false)
    private RemetenteEntity remetente; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        this.data = new Date();
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getNomeDestinatario() {
        return nomeDestinatario;
    }

    public void setNomeDestinatario(String nomeDestinatario) {
        this.nomeDestinatario = nomeDestinatario;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public List<AnexoEntity> getAnexoList() {
        return anexoList;
    }

    public void setAnexoList(List<AnexoEntity> anexoList) {
        this.anexoList = anexoList;
    }

    public RemetenteEntity getRemetente() {
        return remetente;
    }

    public void setRemetente(RemetenteEntity remetente) {
        this.remetente = remetente;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 31 * hash + (this.data != null ? this.data.hashCode() : 0);
        hash = 31 * hash + (this.nomeDestinatario != null ? this.nomeDestinatario.hashCode() : 0);
        hash = 31 * hash + (this.assunto != null ? this.assunto.hashCode() : 0);
        hash = 31 * hash + (this.conteudo != null ? this.conteudo.hashCode() : 0);
        hash = 31 * hash + (this.anexoList != null ? this.anexoList.hashCode() : 0);
        hash = 31 * hash + (this.remetente != null ? this.remetente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MemorandoEntity other = (MemorandoEntity) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.data != other.data && (this.data == null || !this.data.equals(other.data))) {
            return false;
        }
        if ((this.nomeDestinatario == null) ? (other.nomeDestinatario != null) : !this.nomeDestinatario.equals(other.nomeDestinatario)) {
            return false;
        }
        if ((this.assunto == null) ? (other.assunto != null) : !this.assunto.equals(other.assunto)) {
            return false;
        }
        if ((this.conteudo == null) ? (other.conteudo != null) : !this.conteudo.equals(other.conteudo)) {
            return false;
        }
        if (this.anexoList != other.anexoList && (this.anexoList == null || !this.anexoList.equals(other.anexoList))) {
            return false;
        }
        if (this.remetente != other.remetente && (this.remetente == null || !this.remetente.equals(other.remetente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MemorandoEntity{" + "id=" + id + ", data=" + data + ", nomeDestinatario=" + nomeDestinatario + ", assunto=" + assunto + ", conteudo=" + conteudo + ", anexoList=" + anexoList + ", remetente=" + remetente + '}';
    }

    

    

    

    
    
}

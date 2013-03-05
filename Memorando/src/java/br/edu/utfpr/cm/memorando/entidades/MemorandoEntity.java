/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.memorando.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author marlon
 */
@Entity
public class MemorandoEntity implements Serializable {
   
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data;
    
    @Column
    private String nomeDestinatario; 
    
    @Column
    private String assunto;
    
    @Column
    private String conteudo;
    
    @JoinColumn(name = "remetente_id", referencedColumnName="id", nullable=false)
    @ManyToOne(optional=false)
    private RemetenteEntity remetente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Date getData(){
        this.data = new Date();
        return data;
    }
    
    public void setData(Date data){
        this.data = data;
    }
    
    public String getNome(){
        return nomeDestinatario;
    }
    
    public void setNome(String nome){
        this.nomeDestinatario = nome;
    }
    
    public String getAssunto(){
        return assunto;
    }
    
    public void setAssunto(String assunto){
        this.assunto = assunto;
    }
    
    public String getConteudo(){
        return conteudo;
    }
    
    public void setConteudo(String conteudo){
        this.conteudo = conteudo;
    }
    
    public RemetenteEntity getRemetente(){
        return remetente;
    }
    
    public void setRemetente(RemetenteEntity remetente){
        this.remetente = remetente;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MemorandoEntity)) {
            return false;
        }
        MemorandoEntity other = (MemorandoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.utfpr.cm.memorando.entidades.MemorandoEntity[ id=" + id + " ]";
    }
    
}

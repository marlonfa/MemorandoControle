/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.memorando.entidades;

import java.io.Serializable;
import java.util.Arrays;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author marlon
 */
@Entity
@Table(name = "anexo")
public class AnexoEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id")
    private Long id;
    
    @Lob
    @Column(name="arquivo")
    private byte[] arquivo; 
    
    @Column(name = "nome_arquivo")
    private String nomeArquivo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 73 * hash + Arrays.hashCode(this.arquivo);
        hash = 73 * hash + (this.nomeArquivo != null ? this.nomeArquivo.hashCode() : 0);
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
        final AnexoEntity other = (AnexoEntity) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (!Arrays.equals(this.arquivo, other.arquivo)) {
            return false;
        }
        if ((this.nomeArquivo == null) ? (other.nomeArquivo != null) : !this.nomeArquivo.equals(other.nomeArquivo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AnexoEntity{" + "id=" + id + ", arquivo=" + arquivo + ", nomeArquivo=" + nomeArquivo + '}';
    }
    
    
   
    
    
}

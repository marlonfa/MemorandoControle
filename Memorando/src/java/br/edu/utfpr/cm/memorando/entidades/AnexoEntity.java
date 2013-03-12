/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.memorando.entidades;

import java.io.Serializable;
import java.util.Arrays;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;
    
    @Lob
    @Column(name="arquivo")
    private byte[] arquivo;    
    
    @ManyToOne
    @JoinColumn(name = "memorando_id", referencedColumnName = "id", nullable = false)
    private MemorandoEntity memorando;

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

    public MemorandoEntity getMemorando() {
        return memorando;
    }

    public void setMemorando(MemorandoEntity memorando) {
        this.memorando = memorando;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 23 * hash + Arrays.hashCode(this.arquivo);
        hash = 23 * hash + (this.memorando != null ? this.memorando.hashCode() : 0);
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
        if (this.memorando != other.memorando && (this.memorando == null || !this.memorando.equals(other.memorando))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AnexoEntity{" + "id=" + id + ", arquivo=" + arquivo + ", memorando=" + memorando + '}';
    }

    


    
}

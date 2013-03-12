/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.memorando.jpa;

import br.edu.utfpr.cm.memorando.entidades.AnexoEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marlon
 */
@Stateless
public class AnexoFacade extends AbstractFacade<AnexoEntity> {
    @PersistenceContext(unitName = "MemorandoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnexoFacade() {
        super(AnexoEntity.class);
    }
    
}

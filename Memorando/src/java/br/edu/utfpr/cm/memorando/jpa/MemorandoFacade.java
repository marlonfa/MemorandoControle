/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.memorando.jpa;

import br.edu.utfpr.cm.memorando.entidades.MemorandoEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marlon
 */
@Stateless
public class MemorandoFacade extends AbstractFacade<MemorandoEntity> {
    @PersistenceContext(unitName = "MemorandoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MemorandoFacade() {
        super(MemorandoEntity.class);
    }
    
}

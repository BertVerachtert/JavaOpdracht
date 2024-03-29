/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.dao;

import fact.it.www.entity.Tafel;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bertv
 */
@Stateless
public class TafelFacade extends AbstractFacade<Tafel> {

    @PersistenceContext(unitName = "2APPBI2_Verachtert_Bert_restaurant2019PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TafelFacade() {
        super(Tafel.class);
    }
    
}

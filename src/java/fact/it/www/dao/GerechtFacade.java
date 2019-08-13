/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.dao;

import fact.it.www.entity.Gerecht;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bertv
 */
@Stateless
public class GerechtFacade extends AbstractFacade<Gerecht> {

    @PersistenceContext(unitName = "2APPBI2_Verachtert_Bert_restaurant2019PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GerechtFacade() {
        super(Gerecht.class);
    }
    
}

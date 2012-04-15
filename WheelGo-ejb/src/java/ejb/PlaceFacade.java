/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Place;

/**
 *
 * @author mist
 */
@Stateless
public class PlaceFacade extends AbstractFacade<Place> implements PlaceFacadeLocal {
    @PersistenceContext(unitName = "WheelGo-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlaceFacade() {
        super(Place.class);
    }
    
}

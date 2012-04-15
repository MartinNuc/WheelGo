/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

/**
 *
 * @author mist
 */
import dto.TipDTO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mist
 */
@Stateless
public class TipFacade extends FactoryFacade {
    @PersistenceContext(unitName = "WheelGo-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipFacade() throws ClassNotFoundException {
        super(TipDTO.class);
    }
}



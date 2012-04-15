/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

/**
 *
 * @author mist
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import dto.PhotoDTO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mist
 */
@Stateless
public class PhotoFacade extends FactoryFacade {
    @PersistenceContext(unitName = "WheelGo-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PhotoFacade() throws ClassNotFoundException {
        super(PhotoDTO.class);
    }
}

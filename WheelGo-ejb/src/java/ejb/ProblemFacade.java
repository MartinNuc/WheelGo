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
import dto.ProblemDTO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mist
 */
@Stateless
public class ProblemFacade extends FactoryFacade {
    @PersistenceContext(unitName = "WheelGo-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProblemFacade() throws ClassNotFoundException {
        super(ProblemDTO.class);
    }
}


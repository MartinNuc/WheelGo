/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.LogDTO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mist
 */
@Stateless
public class LogFacade extends FactoryFacade {
    @PersistenceContext(unitName = "WheelGo-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LogFacade() throws ClassNotFoundException {
        super(LogDTO.class);
    }
}



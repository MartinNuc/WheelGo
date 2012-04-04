/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Roles;

/**
 *
 * @author mist
 */
@Stateless
public class RolesFacade extends AbstractFacade<Roles> {
    @PersistenceContext(unitName = "WheelGo-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolesFacade() {
        super(Roles.class);
    }
    
    public List<Roles> getRoles() {
        return (List<Roles>) em.createQuery("SELECT r FROM Roles r").getResultList();
    }
}

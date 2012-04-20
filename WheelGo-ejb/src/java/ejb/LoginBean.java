/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.security.Principal;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import model.User;

/**
 *
 * @author mist
 */
@Stateful
public class LoginBean implements LoginBeanLocal {

    @PersistenceContext(unitName = "WheelGo-ejbPU")
    private EntityManager em;
    private User user;

    @Resource
    SessionContext ctx;
    
    public LoginBean() {
    }

    @PostConstruct
    private void init() {

    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }
    
    @Override
    public User getUser() {
        Principal p = ctx.getCallerPrincipal();
        try {
            if (p == null || "ANONYMOUS".equals(p.getName())) {
                // zde budeme vracet anonymniho uzivatele na vytvareni hlaseni
                user = (User)em.createNamedQuery("getDefaultUser").getResultList().get(0);
                return user;
            }
            
            // registrovany uzivatel
            if (user == null)
                user = (User) em.createQuery("SELECT u FROM User u WHERE u.username=:login").setParameter("login", p.getName()).getSingleResult();
            return user;
        } catch (NoResultException nrex) {
            return null;
        }

    }

    @Override
    public void login(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

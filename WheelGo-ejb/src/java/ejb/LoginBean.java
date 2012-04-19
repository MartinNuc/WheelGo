/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
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

    public LoginBean() {
    }

    private void init() {
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public User getUser() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(User.class));
        List<User> users = em.createQuery(cq).getResultList();
        if (users.size() < 1)
            user = users.get(0);

        if(user == null) {
            throw new IllegalStateException("Neni udany zadny uzivatel!!!");
        }
        return user;
    }

    @Override
    public void login(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

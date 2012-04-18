/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
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
    
    public LoginBean()
    {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(User.class));
        List<User> users = em.createQuery(cq).getResultList();
        if (users.size() < 1)
            user = users.get(0);
    }            
    
    @Override
    public void setUser(User user)
    {
        this.user = user;
    }
    
    @Override
    public User getUser()
    {
        return user;
    }

    @Override
    public void login(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

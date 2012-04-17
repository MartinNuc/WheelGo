/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateful;
import model.User;

/**
 *
 * @author mist
 */
@Stateful
public class LoginBean implements LoginBeanLocal {

    private User user;
    
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

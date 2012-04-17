/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Local;
import model.User;

/**
 *
 * @author mist
 */
@Local
public interface LoginBeanLocal {
    public void setUser(User user);
    public User getUser();
    
    public void login(String username, String password);
}

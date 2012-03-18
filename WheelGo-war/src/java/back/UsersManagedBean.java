/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package back;

import ejb.UsersFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import model.Users;

/**
 *
 * @author mist
 */
@Named(value = "usersManagedBean")
@ApplicationScoped
public class UsersManagedBean {
    @EJB
    private UsersFacade usersFacade;

    /**
     * Creates a new instance of UsersManagedBean
     */
    public UsersManagedBean() {
    }
    
    public List<Users> getUsers()
    {
        return usersFacade.getUsers();
    }
}

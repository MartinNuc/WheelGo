/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package back;

import ejb.UsersFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Users;

/**
 *
 * @author mist
 */
//@Named(value = "usersManagedBean")
@ManagedBean(name="users")
@SessionScoped
public class UsersManagedBean implements Serializable {
    @EJB
    private UsersFacade usersFacade;
    
    private Users user = null;

    /**
     * Creates a new instance of UsersManagedBean
     */
    public UsersManagedBean() {
        System.out.println("Creating instance.");
    }

    public Users getUser() {
        return this.user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String newUser() {
       this.user = new Users();
       return "user";
    }

    public String saveUser() {
        usersFacade.create(user);
        return "users";
    }

    public String editUser(Users user) {
        this.user = user;
        return "user";
    }

    public void removeUser(Users user) {
        usersFacade.remove(user);
    }
    
    public List<Users> getUsers()
    {
        return usersFacade.getUsers();
    }
    
}

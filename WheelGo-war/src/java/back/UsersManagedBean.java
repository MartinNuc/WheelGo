/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package back;

import dto.UsersDTO;
import ejb.UsersFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
    
    public static final int STATE_ADD = 1;
    public static final int STATE_MODIFY = 2;
    private int state;
    
    private UsersDTO user = null;
    private String password = "";

    public String getPassword() {
        return "";
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Creates a new instance of UsersManagedBean
     */
    public UsersManagedBean() {
    }

    public UsersDTO getUser() {
        return this.user;
    }

    public void setUser(UsersDTO user) {
        this.user = user;
    }

    public String newUser() {
       this.user = new UsersDTO();
       state = STATE_ADD;
       password = "";
       return "user";
    }

    public String saveUser() {
        switch (state) {
            case STATE_MODIFY:
                usersFacade.editUser(user, password);
                break;
            case STATE_ADD:
                usersFacade.createUser(user, password);
                break;
        }
        state = 0;
        password = "";
        return "users";
    }

    public String editUser(UsersDTO user) {
        this.user = user;
        state = STATE_MODIFY;
        password = "";
        return "user";
    }

    public void removeUser(UsersDTO user) {
        usersFacade.remove(user);
    }
    
    public List<Object> getUsers()
    {
        return usersFacade.getAll();
    }
    
}

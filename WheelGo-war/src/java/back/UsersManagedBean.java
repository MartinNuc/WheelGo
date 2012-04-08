/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package back;

import dto.UserDTO;
import ejb.UserFacade;
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
    private UserFacade userFacade;
    
    public static final int STATE_ADD = 1;
    public static final int STATE_MODIFY = 2;
    private int state;
    
    private UserDTO user = null;
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

    public UserDTO getUser() {
        return this.user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String newUser() {
       this.user = new UserDTO();
       state = STATE_ADD;
       password = "";
       return "user";
    }

    public String saveUser() {
        switch (state) {
            case STATE_MODIFY:
                userFacade.editUser(user, password);
                break;
            case STATE_ADD:
                userFacade.createUser(user, password);
                break;
        }
        state = 0;
        password = "";
        return "users";
    }

    public String editUser(UserDTO user) {
        this.user = user;
        state = STATE_MODIFY;
        password = "";
        return "user";
    }

    public void removeUser(UserDTO user) {
        userFacade.remove(user);
    }
    
    public List<Object> getUsers()
    {
        return userFacade.getAll();
    }
    
}

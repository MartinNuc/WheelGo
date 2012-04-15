/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package back;

import dto.UserDTO;
import ejb.UserFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import wrappers.UserWrapper;

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
    
    //private UserDTO userDto = null;
    private UserWrapper user;
    private String password = "";
    
    private String messageForClient;

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
        user = new UserWrapper();
    }

    public UserWrapper getUser() {
        return this.user;
    }

    public void setUser(UserWrapper user) {
        this.user = user;
    }

    public String newUser() {
       this.user = new UserWrapper();
       state = STATE_ADD;
       password = "";
       return "user";
    }

    public String saveUser() {
        switch (state) {
            case STATE_MODIFY:
                userFacade.editUser(user.getDto(), password);
                break;
            case STATE_ADD:
                userFacade.createUser(user.getDto(), password);
                break;
        }
        state = 0;
        password = "";
        return "users";
    }

    public String editUser(UserWrapper user) {
        this.user = user;
        state = STATE_MODIFY;
        password = "";
        return "user";
    }

    public void removeUser(UserWrapper user) {
        userFacade.remove(user.getDto());
    }
    
    public List<UserWrapper> getUsers()
    {
        List<UserWrapper> list = new ArrayList<UserWrapper>();
        List<Object> allUsers = userFacade.getAll();
        if (allUsers != null)
            for (Object u : allUsers)
            {
                list.add(new UserWrapper((UserDTO) u));
            }
        return list;
    }
    
    public String checkPassword(UserWrapper user) {
        this.user = user;
        this.password = "";
        this.messageForClient = "";
        return "userCheckPassword";
    }
    
    public String checkPassword() { 
        if(userFacade.checkPassword(user.getDto(), password)) {
            messageForClient = "OK";
        } else {
            messageForClient = "NE";
        }
        return "userCheckPassword";
    }

    /**
     * @return the messageForClient
     */
    public String getMessageForClient() {
        return messageForClient;
    }
    
}

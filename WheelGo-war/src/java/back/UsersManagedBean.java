/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package back;

import dto.UserDTO;
import ejb.facades.interfaces.RoleFacadeLocal;
import ejb.facades.interfaces.UserFacadeLocal;
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
@ManagedBean(name = "users")
@SessionScoped
public class UsersManagedBean implements Serializable {

    @EJB
    public UserFacadeLocal userFacade;
    
    @EJB
    private RoleFacadeLocal roleFacade;
    
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
//        user = new UserWrapper(roleFacade);
    }

    public UserWrapper getUser() {
        return this.user;
    }

    public void setUser(UserWrapper user) {
        this.user = user;
    }

    public String newUser() {
        this.user = new UserWrapper(roleFacade);
        state = STATE_ADD;
        password = "";
        return "userModify";
    }

    public String saveUser() {
        switch (state) {
            case STATE_MODIFY:
                userFacade.edit(user.getDto(), password);
                break;
            case STATE_ADD:
                userFacade.create(user.getDto(), password);
                break;
        }
        state = 0;
        password = "";
        return "usersMainList";
    }

    public String editUser(UserWrapper user) {
        this.user = user;
        state = STATE_MODIFY;
        password = "";
        return "userModify";
    }

    public void removeUser(UserWrapper user) {
        userFacade.remove(user.getDto());
    }

    public List<UserWrapper> getUsers() {
        List<UserWrapper> list = new ArrayList<UserWrapper>();
        List<UserDTO> allUsers = userFacade.getAll();
        if (allUsers != null) {
            for (UserDTO u : allUsers) {
                list.add(new UserWrapper(u, roleFacade));
            }
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
        if (userFacade.checkPassword(user.getDto(), password)) {
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

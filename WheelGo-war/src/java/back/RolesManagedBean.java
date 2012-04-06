/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package back;

import ejb.RolesFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Roles;

/**
 *
 * @author mist
 */
//@Named(value = "rolesManagedBean")
@ManagedBean(name = "roles")
@SessionScoped
public class RolesManagedBean {

    public static final int ROLE_STATE_ADD = 1;
    public static final int ROLE_STATE_MODIFY = 2;
    @EJB
    private RolesFacade rolesFacade;
    private Roles role;
    private int roleState;

    /** Creates a new instance of RolesManagedBean */
    public RolesManagedBean() {
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String newRole() {
        role = new Roles();
        roleState = ROLE_STATE_ADD;
        return "roleModify";
    }

    public String saveRole() {
        switch (roleState) {
            case ROLE_STATE_MODIFY:
                rolesFacade.edit(role);
                break;
            case ROLE_STATE_ADD:
                rolesFacade.create(role);
                break;
        }

        return "rolesMainList";
    }

    public String editRole(Roles role) {
        this.role = role;
        roleState = ROLE_STATE_MODIFY;
        return "roleModify";
    }

    public void removeRole(Roles role) {
        rolesFacade.remove(role);
    }

    public List<Roles> getRoles() {
        return rolesFacade.getRoles();
    }
}

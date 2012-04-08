/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package back;

import dto.RoleDTO;
import ejb.RoleFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author mist
 */
//@Named(value = "rolesManagedBean")
@ManagedBean(name = "roles")
@SessionScoped
public class RolesManagedBean {

    public static final int STATE_ADD = 1;
    public static final int STATE_MODIFY = 2;
    @EJB
    private RoleFacade rolesFacade;
    private RoleDTO role;
    private int state;

    /** Creates a new instance of RolesManagedBean */
    public RolesManagedBean() {
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public String newRole() {
        role = new RoleDTO();
        state = STATE_ADD;
        return "roleModify";
    }

    public String saveRole() {
        switch (state) {
            case STATE_MODIFY:
                rolesFacade.edit(role);
                break;
            case STATE_ADD:
                rolesFacade.create(role);
                break;
        }   
        state = 0;
        return "rolesMainList";
    }

    public String editRole(RoleDTO role) {
        this.role = role;
        state = STATE_MODIFY;
        return "roleModify";
    }

    public void removeRole(RoleDTO role) {
        rolesFacade.remove(role);
    }

    public List<Object> getRoles() {
        return rolesFacade.getAll();
    }
}

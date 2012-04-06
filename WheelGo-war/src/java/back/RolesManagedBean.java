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
@ManagedBean(name="roles")
@SessionScoped
public class RolesManagedBean {
    @EJB
    private RolesFacade rolesFacade;
    
    private Roles role;
    
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
       return "roleModify";
    }

    public String saveRole() {
        rolesFacade.create(role);
        return "rolesMainList";
    }

    public String editRole(Roles role) {
        this.role = role;
        return "roleModify";
    }

    public void removeRole(Roles role) {
        rolesFacade.remove(role);
    }
    
    public List<Roles> getRoles()
    {
        return rolesFacade.getRoles();
    }

}

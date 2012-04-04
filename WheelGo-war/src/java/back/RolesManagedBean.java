/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package back;

import ejb.RolesFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import model.Roles;

/**
 *
 * @author mist
 */
//@Named(value = "rolesManagedBean")
@ManagedBean(name="roles")
@ApplicationScoped
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
       return "role";
    }

    public String saveRole() {
        rolesFacade.edit(role);
        return "roles";
    }

    public String editRole(Roles role) {
        this.role = role;
        return "role";
    }

    public void removeRole(Roles role) {
        rolesFacade.remove(role);
    }
    
    public List<Roles> getRoles()
    {
        return rolesFacade.getRoles();
    }

}

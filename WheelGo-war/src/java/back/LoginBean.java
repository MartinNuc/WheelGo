/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package back;


/**
 *
 * @author mist
 */
import dto.UserDTO;
import ejb.facades.interfaces.UserFacadeLocal;
import java.security.Principal;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name="login")
@RequestScoped
public class LoginBean {

    @EJB
    private UserFacadeLocal usersFacade;
    
    /** Creates a new instance of LoginBean */
    public LoginBean() {
    }

    public boolean isLoggedUser() {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("user");
    }


    public boolean isLoggedAdmin() {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("admin");
    }

    public String getLoginName() {
        Principal p = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        if (p == null) {return null;}
        return p.getName();
    }

    public String logout() {
        HttpSession sess = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        sess.invalidate();
        return "index";
    }

    public UserDTO getLoggedUser() {
        return usersFacade.getLoggedUser();
    }

}

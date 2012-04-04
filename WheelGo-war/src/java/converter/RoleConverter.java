/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import ejb.RolesFacade;
import ejb.UsersFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import model.Roles;

/**
 *
 * @author mist
 */
@FacesConverter(value = "roleConv")
public class RoleConverter implements Converter {
    private RolesFacade rolesFacade;
    //SchoolCenterLocal schoolCenter = lookupSchoolCenterLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return rolesFacade.find(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((Roles) value).getIdRoles());
    }

    private RolesFacade lookupRolesFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (RolesFacade) c.lookup("java:global/WheelGo/WheelGo-ejb/RolesFacade!WheelGo.ejb.AbstractFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}

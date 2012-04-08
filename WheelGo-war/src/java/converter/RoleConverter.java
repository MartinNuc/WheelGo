/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.RoleDTO;
import ejb.RoleFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author mist
 */
@FacesConverter(value = "roleConv")
public class RoleConverter implements Converter {
    private RoleFacade roleFacade = lookupRolesFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        RoleDTO out = (RoleDTO) roleFacade.find(Integer.parseInt(value));
        return out;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((RoleDTO) value).getIdRole());
    }

    private RoleFacade lookupRolesFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (RoleFacade) c.lookup("java:global/WheelGo/WheelGo-ejb/RoleFacade!ejb.RoleFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}

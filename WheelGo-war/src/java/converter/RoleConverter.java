/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.RoleDTO;
import ejb.facades.interfaces.RoleFacadeLocal;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author mist
 */
@ManagedBean(name = "RoleConverter")
@FacesConverter(value = "RoleConverter")
public class RoleConverter implements Converter {
    
    @EJB
    private RoleFacadeLocal roleFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        RoleDTO out = roleFacade.find(Integer.parseInt(value));
        System.out.println("out=" + out + " value=" + value);
        return out;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((RoleDTO) value).getIdRole());
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wrappers;

import dto.RoleDTO;
import dto.UserDTO;
import ejb.RoleFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author mist
 */
public class UserWrapper {
    private RoleFacade roleFacade = lookupRolesFacadeLocal();

    private Integer idUser;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    private String username;
    private String phoneId;
    
    private Integer idRole;
    private RoleDTO role;
    
    public UserWrapper()
    {
        
    }
    
    public UserWrapper(UserDTO userDto)
    {
        idUser = userDto.getIdUser();
        username = userDto.getUsername();
        phoneId = userDto.getPhoneId();
        idRole = userDto.getRole();
    }

    public RoleDTO getRole()
    {
        return (RoleDTO)roleFacade.find(idRole);
    }
    
    public void setRole(RoleDTO role)
    {
        idRole = role.getIdRole();
        this.role = role;
    }
    
    public UserDTO getDto()
    {
        UserDTO ret = new UserDTO();
        ret.setIdUser(idUser);
        ret.setPhoneId(phoneId);
        ret.setRole(role);
        ret.setUsername(username);
        return ret;
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

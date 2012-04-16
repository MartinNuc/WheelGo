/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wrappers;

import dto.RoleDTO;
import dto.UserDTO;
import ejb.facades.interfaces.RoleFacadeLocal;

/**
 *
 * @author mist
 */
public class UserWrapper {

    RoleFacadeLocal roleFacade;
    private Integer idUser;
    
    public UserWrapper(RoleFacadeLocal roleFacade) {
        this.roleFacade = roleFacade;
    }

    public UserWrapper(UserDTO userDto, RoleFacadeLocal roleFacade) {
        this.roleFacade = roleFacade;
        
        idUser = userDto.getIdUser();
        username = userDto.getUsername();
        phoneId = userDto.getPhoneId();
        idRole = userDto.getRole();
    }
    
    
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


    public RoleDTO getRole() {
        if (idRole == null) {
            return null;
        }

        return roleFacade.find(idRole);
    }

    public void setRole(RoleDTO role) {
        if (role == null) {
            this.role = null;
            this.idRole = null;
            return;
        }
        idRole = role.getIdRole();
        this.role = role;
    }

    public UserDTO getDto() {
        UserDTO ret = new UserDTO();
        ret.setIdUser(idUser);
        ret.setPhoneId(phoneId);
        if (role != null) {
            ret.setRole(role.getIdRole());
        } else {
            ret.setRole(idRole);
        }
        ret.setUsername(username);
        return ret;
    }

}

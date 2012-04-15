/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import model.Role;

/**
 *
 * @author mist
 */
public class UserDTO implements Serializable {
    private Integer idUser;
    private String username;

    @Override
    public String toString() {
        return username;
    }
    private String phoneId;
    private Integer role;

    public Integer getRole() {
        return role;
    }
    
    public void setRole(Role role) {
        this.role = role.getIdRole();
    }
    
    public void setRole(RoleDTO role) {
        this.role = role.getIdRole();
    }
    
    public void setRole(Integer role) {
        this.role = role;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserDTO other = (UserDTO) obj;
        if (this.idUser != other.idUser && (this.idUser == null || !this.idUser.equals(other.idUser))) {
            return false;
        }
        if ((this.username == null) ? (other.username != null) : !this.username.equals(other.username)) {
            return false;
        }
        if ((this.phoneId == null) ? (other.phoneId != null) : !this.phoneId.equals(other.phoneId)) {
            return false;
        }
        if (this.role != other.role && (this.role == null || !this.role.equals(other.role))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.idUser != null ? this.idUser.hashCode() : 0);
        hash = 89 * hash + (this.username != null ? this.username.hashCode() : 0);
        hash = 89 * hash + (this.phoneId != null ? this.phoneId.hashCode() : 0);
        hash = 89 * hash + (this.role != null ? this.role.hashCode() : 0);
        return hash;
    }
    
}

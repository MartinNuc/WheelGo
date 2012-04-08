/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import model.Roles;

/**
 *
 * @author mist
 */
public class UsersDTO implements Serializable {
    private Integer idUsers;
    private String username;

    @Override
    public String toString() {
        return username;
    }
    private String phoneId;
    private RolesDTO idRoles;

    public RolesDTO getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(RolesDTO idRoles) {
        this.idRoles = (RolesDTO)idRoles;
    }

    public void setIdRoles(Roles idRoles) {
        this.idRoles = (RolesDTO)DtoFactory.convertToDto(idRoles);
    }
    
    public Integer getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(Integer idUsers) {
        this.idUsers = idUsers;
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
        final UsersDTO other = (UsersDTO) obj;
        if (this.idUsers != other.idUsers && (this.idUsers == null || !this.idUsers.equals(other.idUsers))) {
            return false;
        }
        if ((this.username == null) ? (other.username != null) : !this.username.equals(other.username)) {
            return false;
        }
        if ((this.phoneId == null) ? (other.phoneId != null) : !this.phoneId.equals(other.phoneId)) {
            return false;
        }
        if (this.idRoles != other.idRoles && (this.idRoles == null || !this.idRoles.equals(other.idRoles))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.idUsers != null ? this.idUsers.hashCode() : 0);
        hash = 89 * hash + (this.username != null ? this.username.hashCode() : 0);
        hash = 89 * hash + (this.phoneId != null ? this.phoneId.hashCode() : 0);
        hash = 89 * hash + (this.idRoles != null ? this.idRoles.hashCode() : 0);
        return hash;
    }
    
}

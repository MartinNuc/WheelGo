/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author vlada
 */
public class RoleDTO implements Serializable {
    private String name;
    private String description;
    private Integer idRole;
    private Collection<Integer> users;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the idRole
     */
    public Integer getIdRole() {
        return idRole;
    }

    /**
     * @param idRole the idRole to set
     */
    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public Collection<Integer> getUsers() {
        return users;
    }

    public void setUsers(Collection<Integer> users) {
        this.users = users;
    }

    
    
    /**
     * Zajistuje spravny prevod role na jeji nazev, kdyz to treba zobrazujem v JSF
     */
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RoleDTO other = (RoleDTO) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        if (this.idRole != other.idRole && (this.idRole == null || !this.idRole.equals(other.idRole))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 17 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 17 * hash + (this.idRole != null ? this.idRole.hashCode() : 0);
        return hash;
    }
    
    
    
}

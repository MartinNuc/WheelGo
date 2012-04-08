/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author vlada
 */
public class RolesDTO implements Serializable {
    private String name;
    private String description;
    private Integer idRoles;

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
    public Integer getIdRoles() {
        return idRoles;
    }

    /**
     * @param idRole the idRole to set
     */
    public void setIdRoles(Integer idRole) {
        this.idRoles = idRole;
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
        final RolesDTO other = (RolesDTO) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        if (this.idRoles != other.idRoles && (this.idRoles == null || !this.idRoles.equals(other.idRoles))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 17 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 17 * hash + (this.idRoles != null ? this.idRoles.hashCode() : 0);
        return hash;
    }
    
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mist
 */
@Entity
@Table(name = "role")
public class Role extends AbstractModel {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRole", nullable = false)
    private Integer idRole;
    @Size(max = 32)
    @Column(name = "name", length = 32)
    private String name;
    @Size(max = 128)
    @Column(name = "description", length = 128)
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    @OrderBy("idUser")
    private Collection<User> usersCollection;

    public Role() {
    }

    public Role(Integer idRoles) {
        this.idRole = idRoles;
    }

    public Integer getId() {
        return idRole;
    }

    public void setId(Integer idRole) {
        this.idRole = idRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<User> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<User> usersCollection) {
        this.usersCollection = usersCollection;
    }    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dto.EntityFactory;
import dto.RolesDTO;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mist
 */
@Entity
@Table(name = "users")
@XmlRootElement
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsers", nullable = false)
    private Integer idUsers;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "username", nullable = false, length = 64)
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "passwd", nullable = false, length = 64)
    private String passwd;

    @Size(max = 64)
    @Column(name = "phoneId", length = 64)
    private String phoneId;
    @NotNull
    @JoinColumn(name = "idRoles", referencedColumnName = "idRoles", nullable = false)
    @ManyToOne(optional = false)
    private Roles idRoles;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsers")
    private Collection<Logs> logsCollection;

    public Users() {
    }

    public Users(Integer idUsers) {
        this.idUsers = idUsers;
    }

    public Users(Integer idUsers, String username, String passwd) {
        this.idUsers = idUsers;
        this.username = username;
        this.passwd = passwd;
    }

    public Integer getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(Integer idUsers) {
        this.idUsers = idUsers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId;
    }

    public Roles getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(RolesDTO idRoles) {
        this.idRoles = (Roles) EntityFactory.convertToEntity(idRoles);
    }
    
    public void setIdRoles(Roles idRoles) {
        this.idRoles = idRoles;
    }

    @XmlTransient
    public Collection<Logs> getLogsCollection() {
        return logsCollection;
    }

    public void setLogsCollection(Collection<Logs> logsCollection) {
        this.logsCollection = logsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsers != null ? idUsers.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.idUsers == null && other.idUsers != null) || (this.idUsers != null && !this.idUsers.equals(other.idUsers))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Users[ idUsers=" + idUsers + " ]";
    }
    
}

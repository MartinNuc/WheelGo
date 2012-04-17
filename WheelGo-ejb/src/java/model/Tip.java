/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mist
 */
@Entity
@Table(name = "tip")
@PrimaryKeyJoinColumn(name = "idTip")
@XmlRootElement
public class Tip extends Report implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "idTip", nullable = false)
    private Integer idTip;
    
    public Tip() {
    }
    
    public Tip(Report src) {
        super(src);
    }
    
    public Tip(Integer idTip) {
        this.idTip = idTip;
    }
    
    public Integer getIdTip() {
        return idTip;
    }
    
    public void setIdTip(Integer idTip) {
        this.idTip = idTip;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTip != null ? idTip.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tip)) {
            return false;
        }
        Tip other = (Tip) object;
        if ((this.idTip == null && other.idTip != null) || (this.idTip != null && !this.idTip.equals(other.idTip))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "model.Tip[ idTip=" + idTip + " ]";
    }
}

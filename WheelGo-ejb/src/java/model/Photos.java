/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mist
 */
@Entity
@Table(name = "photos")
@XmlRootElement
public class Photos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPhotos", nullable = false)
    private Integer idPhotos;
    @Size(max = 256)
    @Column(name = "url", length = 256)
    private String url;
    @JoinColumn(name = "idPhotos", referencedColumnName = "idReport", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Report report;

    public Photos() {
    }

    public Photos(Integer idPhotos) {
        this.idPhotos = idPhotos;
    }

    public Integer getIdPhotos() {
        return idPhotos;
    }

    public void setIdPhotos(Integer idPhotos) {
        this.idPhotos = idPhotos;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPhotos != null ? idPhotos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Photos)) {
            return false;
        }
        Photos other = (Photos) object;
        if ((this.idPhotos == null && other.idPhotos != null) || (this.idPhotos != null && !this.idPhotos.equals(other.idPhotos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Photos[ idPhotos=" + idPhotos + " ]";
    }
    
}

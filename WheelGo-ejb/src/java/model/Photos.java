/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.*;
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
@NamedQueries({
    @NamedQuery(name = "Photos.findAll", query = "SELECT p FROM Photos p"),
    @NamedQuery(name = "Photos.findByIdPhotos", query = "SELECT p FROM Photos p WHERE p.idPhotos = :idPhotos"),
    @NamedQuery(name = "Photos.findByUrl", query = "SELECT p FROM Photos p WHERE p.url = :url")})
public class Photos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPhotos", nullable = false)
    private Integer idPhotos;
    @Size(max = 256)
    @Column(name = "url", length = 256)
    private String url;
    @JoinColumn(name = "Report_idReport", referencedColumnName = "idReport", nullable = false)
    @ManyToOne(optional = false)
    private Report reportidReport;

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

    public Report getReportidReport() {
        return reportidReport;
    }

    public void setReportidReport(Report reportidReport) {
        this.reportidReport = reportidReport;
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

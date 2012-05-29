/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mist
 */
@Entity
@Table(name = "report")
@Inheritance(strategy = InheritanceType.JOINED)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name="getVisibleReports",
        query="SELECT r FROM Report r WHERE r.deleted = false")
})

public class Report implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Basic(optional = false)
    @Column(name = "idReport", nullable = false)
    private Integer idReport;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "name", nullable = false, length = 64)
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "latitude", nullable = false)
    private float latitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "longitude", nullable = false)
    private float longitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "deleted", nullable = false)
    private boolean deleted;
    @Size(max = 256)
    @Column(name = "describtion", length = 256)
    private String describtion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "report")
    private Collection<Photo> photosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "report")
    private Collection<Log> logsCollection;

    public Report() {
    }

    public Report(Report src) {
        setName(src.getName());
        setDescribtion(src.getDescribtion());
        setLatitude(src.getLatitude());
        setLongitude(src.getLongitude());
        setPhotos(src.getPhotos());
        setDate(src.getDate());
    }

    public Report(Integer idReport) {
        this.idReport = idReport;
    }

    public Report(Integer idReport, String name, Date date, float latitude, float longitude) {
        this.idReport = idReport;
        this.name = name;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getIdReport() {
        return idReport;
    }

    public void setIdReport(Integer idReport) {
        this.idReport = idReport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public Collection<Photo> getPhotos() {
        return photosCollection;
    }

    public void setPhotos(Collection<Photo> photos) {
        this.photosCollection = photos;
    }

    @XmlTransient
    public Collection<Log> getLogsCollection() {
        return logsCollection;
    }

    public void setLogsCollection(Collection<Log> logsCollection) {
        this.logsCollection = logsCollection;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Collection<Photo> getPhotosCollection() {
        return photosCollection;
    }

    public void setPhotosCollection(Collection<Photo> photosCollection) {
        this.photosCollection = photosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReport != null ? idReport.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.idReport == null && other.idReport != null) || (this.idReport != null && !this.idReport.equals(other.idReport))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Report[ idReport=" + idReport + " ]";
    }
}

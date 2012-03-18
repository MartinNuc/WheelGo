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
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r"),
    @NamedQuery(name = "Report.findByIdReport", query = "SELECT r FROM Report r WHERE r.idReport = :idReport"),
    @NamedQuery(name = "Report.findByName", query = "SELECT r FROM Report r WHERE r.name = :name"),
    @NamedQuery(name = "Report.findByDate", query = "SELECT r FROM Report r WHERE r.date = :date"),
    @NamedQuery(name = "Report.findByLatitude", query = "SELECT r FROM Report r WHERE r.latitude = :latitude"),
    @NamedQuery(name = "Report.findByLongitude", query = "SELECT r FROM Report r WHERE r.longitude = :longitude"),
    @NamedQuery(name = "Report.findByDescribtion", query = "SELECT r FROM Report r WHERE r.describtion = :describtion")})
public class Report implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
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
    @Size(max = 256)
    @Column(name = "describtion", length = 256)
    private String describtion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reportidReport")
    private Collection<Photos> photosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reportidReport")
    private Collection<Logs> logsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "report")
    private Collection<Problem> problemCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "report")
    private Collection<Place> placeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "report")
    private Collection<Tip> tipCollection;

    public Report() {
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

    @XmlTransient
    public Collection<Photos> getPhotosCollection() {
        return photosCollection;
    }

    public void setPhotosCollection(Collection<Photos> photosCollection) {
        this.photosCollection = photosCollection;
    }

    @XmlTransient
    public Collection<Logs> getLogsCollection() {
        return logsCollection;
    }

    public void setLogsCollection(Collection<Logs> logsCollection) {
        this.logsCollection = logsCollection;
    }

    @XmlTransient
    public Collection<Problem> getProblemCollection() {
        return problemCollection;
    }

    public void setProblemCollection(Collection<Problem> problemCollection) {
        this.problemCollection = problemCollection;
    }

    @XmlTransient
    public Collection<Place> getPlaceCollection() {
        return placeCollection;
    }

    public void setPlaceCollection(Collection<Place> placeCollection) {
        this.placeCollection = placeCollection;
    }

    @XmlTransient
    public Collection<Tip> getTipCollection() {
        return tipCollection;
    }

    public void setTipCollection(Collection<Tip> tipCollection) {
        this.tipCollection = tipCollection;
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

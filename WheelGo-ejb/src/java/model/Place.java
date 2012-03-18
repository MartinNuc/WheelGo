/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mist
 */
@Entity
@Table(name = "place")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Place.findAll", query = "SELECT p FROM Place p"),
    @NamedQuery(name = "Place.findByIdPlace", query = "SELECT p FROM Place p WHERE p.placePK.idPlace = :idPlace"),
    @NamedQuery(name = "Place.findByReportidReport", query = "SELECT p FROM Place p WHERE p.placePK.reportidReport = :reportidReport"),
    @NamedQuery(name = "Place.findByAccesibility", query = "SELECT p FROM Place p WHERE p.accesibility = :accesibility")})
public class Place implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PlacePK placePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "accesibility", nullable = false)
    private int accesibility;
    @JoinColumn(name = "Report_idReport", referencedColumnName = "idReport", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Report report;

    public Place() {
    }

    public Place(PlacePK placePK) {
        this.placePK = placePK;
    }

    public Place(PlacePK placePK, int accesibility) {
        this.placePK = placePK;
        this.accesibility = accesibility;
    }

    public Place(int idPlace, int reportidReport) {
        this.placePK = new PlacePK(idPlace, reportidReport);
    }

    public PlacePK getPlacePK() {
        return placePK;
    }

    public void setPlacePK(PlacePK placePK) {
        this.placePK = placePK;
    }

    public int getAccesibility() {
        return accesibility;
    }

    public void setAccesibility(int accesibility) {
        this.accesibility = accesibility;
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
        hash += (placePK != null ? placePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Place)) {
            return false;
        }
        Place other = (Place) object;
        if ((this.placePK == null && other.placePK != null) || (this.placePK != null && !this.placePK.equals(other.placePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Place[ placePK=" + placePK + " ]";
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author mist
 */
@Embeddable
public class PlacePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPlace", nullable = false)
    private int idPlace;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Report_idReport", nullable = false)
    private int reportidReport;

    public PlacePK() {
    }

    public PlacePK(int idPlace, int reportidReport) {
        this.idPlace = idPlace;
        this.reportidReport = reportidReport;
    }

    public int getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(int idPlace) {
        this.idPlace = idPlace;
    }

    public int getReportidReport() {
        return reportidReport;
    }

    public void setReportidReport(int reportidReport) {
        this.reportidReport = reportidReport;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPlace;
        hash += (int) reportidReport;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlacePK)) {
            return false;
        }
        PlacePK other = (PlacePK) object;
        if (this.idPlace != other.idPlace) {
            return false;
        }
        if (this.reportidReport != other.reportidReport) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.PlacePK[ idPlace=" + idPlace + ", reportidReport=" + reportidReport + " ]";
    }
    
}

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
public class TipPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idTip", nullable = false)
    private int idTip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Report_idReport", nullable = false)
    private int reportidReport;

    public TipPK() {
    }

    public TipPK(int idTip, int reportidReport) {
        this.idTip = idTip;
        this.reportidReport = reportidReport;
    }

    public int getIdTip() {
        return idTip;
    }

    public void setIdTip(int idTip) {
        this.idTip = idTip;
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
        hash += (int) idTip;
        hash += (int) reportidReport;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipPK)) {
            return false;
        }
        TipPK other = (TipPK) object;
        if (this.idTip != other.idTip) {
            return false;
        }
        if (this.reportidReport != other.reportidReport) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TipPK[ idTip=" + idTip + ", reportidReport=" + reportidReport + " ]";
    }
    
}

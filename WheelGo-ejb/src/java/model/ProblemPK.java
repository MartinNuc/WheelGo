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
public class ProblemPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idProblem", nullable = false)
    private int idProblem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Report_idReport", nullable = false)
    private int reportidReport;

    public ProblemPK() {
    }

    public ProblemPK(int idProblem, int reportidReport) {
        this.idProblem = idProblem;
        this.reportidReport = reportidReport;
    }

    public int getIdProblem() {
        return idProblem;
    }

    public void setIdProblem(int idProblem) {
        this.idProblem = idProblem;
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
        hash += (int) idProblem;
        hash += (int) reportidReport;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProblemPK)) {
            return false;
        }
        ProblemPK other = (ProblemPK) object;
        if (this.idProblem != other.idProblem) {
            return false;
        }
        if (this.reportidReport != other.reportidReport) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ProblemPK[ idProblem=" + idProblem + ", reportidReport=" + reportidReport + " ]";
    }
    
}

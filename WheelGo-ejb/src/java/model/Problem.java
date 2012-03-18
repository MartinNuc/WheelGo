/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mist
 */
@Entity
@Table(name = "problem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Problem.findAll", query = "SELECT p FROM Problem p"),
    @NamedQuery(name = "Problem.findByIdProblem", query = "SELECT p FROM Problem p WHERE p.problemPK.idProblem = :idProblem"),
    @NamedQuery(name = "Problem.findByReportidReport", query = "SELECT p FROM Problem p WHERE p.problemPK.reportidReport = :reportidReport"),
    @NamedQuery(name = "Problem.findByExpiration", query = "SELECT p FROM Problem p WHERE p.expiration = :expiration")})
public class Problem implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProblemPK problemPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expiration", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiration;
    @JoinColumn(name = "Report_idReport", referencedColumnName = "idReport", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Report report;

    public Problem() {
    }

    public Problem(ProblemPK problemPK) {
        this.problemPK = problemPK;
    }

    public Problem(ProblemPK problemPK, Date expiration) {
        this.problemPK = problemPK;
        this.expiration = expiration;
    }

    public Problem(int idProblem, int reportidReport) {
        this.problemPK = new ProblemPK(idProblem, reportidReport);
    }

    public ProblemPK getProblemPK() {
        return problemPK;
    }

    public void setProblemPK(ProblemPK problemPK) {
        this.problemPK = problemPK;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
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
        hash += (problemPK != null ? problemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Problem)) {
            return false;
        }
        Problem other = (Problem) object;
        if ((this.problemPK == null && other.problemPK != null) || (this.problemPK != null && !this.problemPK.equals(other.problemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Problem[ problemPK=" + problemPK + " ]";
    }
    
}

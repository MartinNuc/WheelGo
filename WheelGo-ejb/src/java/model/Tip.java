/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mist
 */
@Entity
@Table(name = "tip")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tip.findAll", query = "SELECT t FROM Tip t"),
    @NamedQuery(name = "Tip.findByIdTip", query = "SELECT t FROM Tip t WHERE t.tipPK.idTip = :idTip"),
    @NamedQuery(name = "Tip.findByReportidReport", query = "SELECT t FROM Tip t WHERE t.tipPK.reportidReport = :reportidReport")})
public class Tip implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TipPK tipPK;
    @JoinColumn(name = "Report_idReport", referencedColumnName = "idReport", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Report report;

    public Tip() {
    }

    public Tip(TipPK tipPK) {
        this.tipPK = tipPK;
    }

    public Tip(int idTip, int reportidReport) {
        this.tipPK = new TipPK(idTip, reportidReport);
    }

    public TipPK getTipPK() {
        return tipPK;
    }

    public void setTipPK(TipPK tipPK) {
        this.tipPK = tipPK;
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
        hash += (tipPK != null ? tipPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tip)) {
            return false;
        }
        Tip other = (Tip) object;
        if ((this.tipPK == null && other.tipPK != null) || (this.tipPK != null && !this.tipPK.equals(other.tipPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Tip[ tipPK=" + tipPK + " ]";
    }
    
}

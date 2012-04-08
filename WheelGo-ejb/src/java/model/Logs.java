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
@Table(name = "logs")
@XmlRootElement
public class Logs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLogs", nullable = false)
    private Integer idLogs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "operation", nullable = false)
    private int operation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumn(name = "idReport", referencedColumnName = "idReport", nullable = false)
    @ManyToOne(optional = false)
    private Report idReport;
    @JoinColumn(name = "idUsers", referencedColumnName = "idUsers", nullable = false)
    @ManyToOne(optional = false)
    private Users idUsers;

    public Logs() {
    }

    public Logs(Integer idLogs) {
        this.idLogs = idLogs;
    }

    public Logs(Integer idLogs, int operation, Date date) {
        this.idLogs = idLogs;
        this.operation = operation;
        this.date = date;
    }

    public Integer getIdLogs() {
        return idLogs;
    }

    public void setIdLogs(Integer idLogs) {
        this.idLogs = idLogs;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Report getIdReport() {
        return idReport;
    }

    public void setIdReport(Report idReport) {
        this.idReport = idReport;
    }

    public Users getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(Users idUsers) {
        this.idUsers = idUsers;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLogs != null ? idLogs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logs)) {
            return false;
        }
        Logs other = (Logs) object;
        if ((this.idLogs == null && other.idLogs != null) || (this.idLogs != null && !this.idLogs.equals(other.idLogs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Logs[ idLogs=" + idLogs + " ]";
    }
    
}

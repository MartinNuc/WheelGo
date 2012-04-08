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
@Table(name = "log")
@XmlRootElement
public class Log implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLog", nullable = false)
    private Integer idLog;
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
    private Report report;
    @JoinColumn(name = "idUser", referencedColumnName = "idUser", nullable = false)
    @ManyToOne(optional = false)
    private User user;

    public Log() {
    }

    public Log(Integer idLog) {
        this.idLog = idLog;
    }

    public Log(Integer idLog, int operation, Date date) {
        this.idLog = idLog;
        this.operation = operation;
        this.date = date;
    }

    public Integer getIdLog() {
        return idLog;
    }

    public void setIdLog(Integer idLog) {
        this.idLog = idLog;
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

    public Report getReport() {
        return report;
    }

    public void setReport(Report Report) {
        this.report = Report;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLog != null ? idLog.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Log)) {
            return false;
        }
        Log other = (Log) object;
        if ((this.idLog == null && other.idLog != null) || (this.idLog != null && !this.idLog.equals(other.idLog))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Logs[ idLogs=" + idLog + " ]";
    }
    
}

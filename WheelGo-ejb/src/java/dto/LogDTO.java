/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Date;
import model.Place;
import model.Problem;
import model.Tip;
import model.User;

/**
 *
 * @author mist
 */
public class LogDTO {
    private Integer idLog;
    private int operation;
    private Date date;
    private Integer report;
    private Integer user;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public Integer getReport() {
        return report;
    }

    public void setReport(Integer report) {
        this.report = report;
    }
    
    public void setReport(Problem report) {
        if (report == null)
            return;
        this.report = report.getIdReport();
    }
    
    public void setReport(Tip report) {
        if (report == null)
            return;
        this.report = report.getIdReport();
    }
    
    public void setReport(Place report) {
        if (report == null)
            return;
        this.report = report.getIdReport();
    }
    
    public void setReport(ProblemDTO report) {
        if (report == null)
            return;

        this.report = report.getIdReport();
    }

    public void setReport(TipDTO report) {
        if (report == null)
            return;

        this.report = report.getIdReport();
    }
    
    public void setReport(PlaceDTO report) {
        if (report == null)
            return;

        this.report = report.getIdReport();
    }
    
    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }
    
    public void setUser(User user) {
        if (user == null)
            return;
        this.user = user.getIdUser();
    }
    
    public void setUser(UserDTO user) {
        if (user == null)
            return;
        this.user = user.getIdUser();
    }
}

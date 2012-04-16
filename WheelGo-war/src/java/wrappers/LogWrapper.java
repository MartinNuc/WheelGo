/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wrappers;

import dto.LogDTO;
import dto.ReportDTO;
import dto.UserDTO;
import ejb.facades.interfaces.ReportFacadeLocal;
import ejb.facades.interfaces.UserFacadeLocal;
import java.util.Date;

/**
 *
 * @author mist
 */
public class LogWrapper {

    private UserFacadeLocal userFacade;
    private ReportFacadeLocal reportFacade;
    private Integer idLog;

    public Integer getIdLog() {
        return idLog;
    }

    public void setIdLog(Integer idLog) {
        this.idLog = idLog;
    }
    private Integer idUser;
    private UserDTO user;
    
    private Integer idReport;
    private ReportDTO report;
    
    private Date date;
    private Integer operation;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public ReportDTO getReport() {
        if (idReport == null) {
            return null;
        }
        return (ReportDTO) reportFacade.find(idReport);
    }

    public void setReport(ReportDTO report) {
        if (report == null) {
            this.report = null;
            this.idReport = null;
            return;
        }
        idReport = report.getIdReport();
        this.report = report;
    }

    public LogWrapper(LogDTO logDto, UserFacadeLocal uf, ReportFacadeLocal rf) {
        idLog = logDto.getIdLog();
        date = logDto.getDate();
        operation = logDto.getOperation();
        idReport = logDto.getReport();
        idUser = logDto.getUser();
        this.reportFacade = rf;
        this.userFacade = uf;
    }

    public UserDTO getUser() {
        if (idUser == null) {
            return null;
        }
        return (UserDTO) userFacade.find(idUser);
    }

    public void setUser(UserDTO user) {
        if (user == null) {
            this.user = null;
            this.idUser = null;
            return;
        }
        idUser = user.getIdUser();
        this.user = user;
    }

    public LogDTO getDto() {
        LogDTO ret = new LogDTO();
        ret.setIdLog(idLog);
        ret.setDate(date);
        ret.setUser(idUser);
        ret.setOperation(operation);
        ret.setReport(idReport);
        return ret;
    }
}

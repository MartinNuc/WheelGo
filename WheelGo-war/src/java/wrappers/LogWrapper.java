/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wrappers;

import dto.LogDTO;
import dto.ReportDTO;
import dto.UserDTO;
import ejb.facades.interfaces.UserFacadeLocal;
import java.util.Date;

/**
 *
 * @author mist
 */
public class LogWrapper {

    private UserFacadeLocal userFacade;
    private Integer idLog;

    
    public LogWrapper(UserFacadeLocal userFacade) {
        this.userFacade = userFacade;
    }
    
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

    public LogWrapper() {
    }

    public LogWrapper(LogDTO logDto) {
        idLog = logDto.getIdLog();
        date = logDto.getDate();
        operation = logDto.getOperation();
        idReport = logDto.getReport();
        idUser = logDto.getUser();
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
        return ret;
    }
}

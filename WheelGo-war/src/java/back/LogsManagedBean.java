/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package back;

import dto.LogDTO;
import ejb.facades.implementation.LogFacade;
import ejb.facades.implementation.ReportFacade;
import ejb.facades.implementation.UserFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import wrappers.LogWrapper;

/**
 *
 * @author mist
 */
@Named(value = "logsManagedBean")
@ManagedBean(name = "logs")
@SessionScoped
public class LogsManagedBean implements Serializable {
    @EJB
    private LogFacade logFacade;
    @EJB
    private UserFacade userFacade;
    @EJB
    private ReportFacade reportFacade;
    
    private LogDTO log;

    /** Creates a new instance of LogsManagedBean */
    public LogsManagedBean() {
    }
    
    public void removeLog(LogWrapper log) {
        logFacade.remove(log.getDto());
    }

    public List<LogWrapper> getLogs() {
        List<LogWrapper> list = new ArrayList<LogWrapper>();
        List<LogDTO> allLogs = logFacade.getAll();
        if (allLogs != null) {
            for (LogDTO l : allLogs) {
                list.add(new LogWrapper(l, userFacade, reportFacade));
            }
        }
        return list;
    }
}

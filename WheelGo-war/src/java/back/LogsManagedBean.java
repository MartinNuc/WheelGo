/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package back;

import dto.LogDTO;
import ejb.facades.interfaces.LogFacadeLocal;
import ejb.facades.interfaces.ReportFacadeLocal;
import ejb.facades.interfaces.UserFacadeLocal;
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
    private LogFacadeLocal logFacade;
    @EJB
    private UserFacadeLocal userFacade;
    @EJB
    private ReportFacadeLocal reportFacade;
    
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

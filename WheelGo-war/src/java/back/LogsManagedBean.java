/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package back;

import dto.LogDTO;
import ejb.LogFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author mist
 */
@Named(value = "logsManagedBean")
@ManagedBean(name = "logs")
@SessionScoped
public class LogsManagedBean implements Serializable {
    @EJB
    private LogFacade logsFacade;
    
    private LogDTO log;

    /** Creates a new instance of LogsManagedBean */
    public LogsManagedBean() {
    }
}

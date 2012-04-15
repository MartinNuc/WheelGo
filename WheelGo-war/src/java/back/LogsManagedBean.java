/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package back;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author mist
 */
@Named(value = "logsManagedBean")
@SessionScoped
public class LogsManagedBean implements Serializable {
    @EJB
    private LogFacade userFacade;
    
    public static final int STATE_ADD = 1;
    public static final int STATE_MODIFY = 2;
    private int state;
    
    //private UserDTO userDto = null;
    private LogDTO user;

    /** Creates a new instance of LogsManagedBean */
    public LogsManagedBean() {
    }
}

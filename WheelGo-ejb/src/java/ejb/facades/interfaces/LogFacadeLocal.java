/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.facades.interfaces;

import dto.LogDTO;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;

/**
 *
 * @author mist
 */
//@RolesAllowed({"admin"})
@Local
public interface LogFacadeLocal {

    void create(LogDTO log);
    //@RolesAllowed({"admin"})
    void edit(LogDTO log);
    //@RolesAllowed({"admin"})
    void remove(LogDTO log);
    LogDTO find(Object id);
    List<LogDTO> getAll();
    List<LogDTO> getRange(int[] range);
    int count();
    
}

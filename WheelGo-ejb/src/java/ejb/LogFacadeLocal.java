/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.LogDTO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mist
 */
@Local
public interface LogFacadeLocal {

    void create(LogDTO log);
    void edit(LogDTO log);
    void remove(LogDTO log);
    LogDTO find(Object id);
    List<LogDTO> getAll();
    List<LogDTO> getRange(int[] range);
    int count();
    
}

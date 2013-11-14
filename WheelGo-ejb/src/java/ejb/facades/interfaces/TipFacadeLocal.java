/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.facades.interfaces;

import dto.TipDTO;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;

/**
 *
 * @author mist
 */

//@RolesAllowed({"user"})
@Local
public interface TipFacadeLocal {

    void create(TipDTO tip);
    //@RolesAllowed({"admin"})
    void edit(TipDTO tip);
    //@RolesAllowed({"admin"})
    void remove(TipDTO tip);
    TipDTO find(Object id);
    List<TipDTO> getAll();
    List<TipDTO> getRange(int[] range);
    int count();
}

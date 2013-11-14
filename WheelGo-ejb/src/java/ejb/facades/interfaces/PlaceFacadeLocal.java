/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.facades.interfaces;

import dto.PlaceDTO;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;

/**
 *
 * @author mist
 */
//@RolesAllowed({"user"})
@Local
public interface PlaceFacadeLocal {

    void create(PlaceDTO place);
    //@RolesAllowed({"admin"})
    void edit(PlaceDTO place);
    //@RolesAllowed({"admin"})
    void remove(PlaceDTO place);

    PlaceDTO find(Object id);

    List<PlaceDTO> getAll();

    List<PlaceDTO> getRange(int[] range);

    int count();
    
}

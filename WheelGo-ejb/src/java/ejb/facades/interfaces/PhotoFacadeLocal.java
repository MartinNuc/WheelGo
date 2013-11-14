/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.facades.interfaces;

import dto.PhotoDTO;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;

/**
 *
 * @author mist
 */

//@RolesAllowed({"user"})
@Local
public interface PhotoFacadeLocal {

    void create(PhotoDTO photo);
    //@RolesAllowed({"admin"})
    void edit(PhotoDTO photo);
    //@RolesAllowed({"admin"})
    void remove(PhotoDTO photo);
    PhotoDTO find(Object id);
    List<PhotoDTO> getAll();
    List<PhotoDTO> getRange(int[] range);
    int count();
    
}

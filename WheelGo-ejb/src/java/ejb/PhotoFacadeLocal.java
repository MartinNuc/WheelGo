/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.PhotoDTO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mist
 */
@Local
public interface PhotoFacadeLocal {

    void create(PhotoDTO photo);
    void edit(PhotoDTO photo);
    void remove(PhotoDTO photo);
    PhotoDTO find(Object id);
    List<PhotoDTO> getAll();
    List<PhotoDTO> getRange(int[] range);
    int count();
    
}

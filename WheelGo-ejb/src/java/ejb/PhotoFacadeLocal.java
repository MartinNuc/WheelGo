/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Local;
import model.Photo;

/**
 *
 * @author mist
 */
@Local
public interface PhotoFacadeLocal {

    void create(Photo photo);

    void edit(Photo photo);

    void remove(Photo photo);

    Photo find(Object id);

    List<Photo> findAll();

    List<Photo> findRange(int[] range);

    int count();
    
}

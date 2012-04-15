/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Local;
import model.Place;

/**
 *
 * @author mist
 */
@Local
public interface PlaceFacadeLocal {

    void create(Place place);

    void edit(Place place);

    void remove(Place place);

    Place find(Object id);

    List<Place> findAll();

    List<Place> findRange(int[] range);

    int count();
    
}

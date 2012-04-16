/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.facades.interfaces;

import dto.PlaceDTO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mist
 */
@Local
public interface PlaceFacadeLocal {

    void create(PlaceDTO place);

    void edit(PlaceDTO place);

    void remove(PlaceDTO place);

    PlaceDTO find(Object id);

    List<PlaceDTO> getAll();

    List<PlaceDTO> getRange(int[] range);

    int count();
    
}

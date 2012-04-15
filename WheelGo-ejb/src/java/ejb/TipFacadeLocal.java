/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.TipDTO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mist
 */
@Local
public interface TipFacadeLocal {

    void create(TipDTO tip);
    void edit(TipDTO tip);
    void remove(TipDTO tip);
    TipDTO find(Object id);
    List<TipDTO> getAll();
    List<TipDTO> getRange(int[] range);
    int count();
}

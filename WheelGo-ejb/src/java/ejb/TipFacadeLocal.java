/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Local;
import model.Tip;

/**
 *
 * @author mist
 */
@Local
public interface TipFacadeLocal {

    void create(Tip tip);

    void edit(Tip tip);

    void remove(Tip tip);

    Tip find(Object id);

    List<Tip> findAll();

    List<Tip> findRange(int[] range);

    int count();
    
}

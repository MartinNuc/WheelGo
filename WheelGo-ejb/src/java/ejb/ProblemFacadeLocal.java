/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Local;
import model.Problem;

/**
 *
 * @author mist
 */
@Local
public interface ProblemFacadeLocal {

    void create(Problem problem);

    void edit(Problem problem);

    void remove(Problem problem);

    Problem find(Object id);

    List<Problem> findAll();

    List<Problem> findRange(int[] range);

    int count();
    
}

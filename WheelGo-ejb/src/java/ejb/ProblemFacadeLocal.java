/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.ProblemDTO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mist
 */
@Local
public interface ProblemFacadeLocal {

    void create(ProblemDTO problem);
    void edit(ProblemDTO problem);
    void remove(ProblemDTO problem);
    ProblemDTO find(Object id);
    List<ProblemDTO> getAll();
    List<ProblemDTO> getRange(int[] range);
    int count();
    
}

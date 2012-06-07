/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.facades.interfaces;

import dto.ProblemDTO;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;

/**
 *
 * @author mist
 */
@Local
@RolesAllowed({"user"})
public interface ProblemFacadeLocal {

    void create(ProblemDTO problem);
    @RolesAllowed({"admin"})
    void edit(ProblemDTO problem);
    @RolesAllowed({"admin"})
    void remove(ProblemDTO problem);
    ProblemDTO find(Object id);
    List<ProblemDTO> getAll();
    List<ProblemDTO> getRange(int[] range);
    int count();
    
}

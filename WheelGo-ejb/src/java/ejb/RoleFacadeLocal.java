/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.RoleDTO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mist
 */
@Local
public interface RoleFacadeLocal {

    void create(RoleDTO role);

    void edit(RoleDTO role);

    void remove(RoleDTO role);

    RoleDTO find(Object id);

    List<RoleDTO> getAll();

    List<RoleDTO> getRange(int[] range);

    int count();
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.facades.interfaces;

import dto.UserDTO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mist
 */
@Local
public interface UserFacadeLocal {
    public void create(UserDTO user, String password);
    public void edit(UserDTO data, String password);
    public void edit(UserDTO data);
    public void remove(UserDTO user);
    public UserDTO find(Integer id);
    public List<UserDTO> getAll();
    public List<UserDTO> getRange(int[] range);
    public boolean checkPassword(UserDTO user, String password);
    int count();
}

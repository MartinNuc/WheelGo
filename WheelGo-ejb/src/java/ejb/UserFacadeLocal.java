/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.UserDTO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mist
 */
@Local
interface UserFacadeLocal {
    public void createUser(UserDTO user, String password);
    public void setData(UserDTO data);
    public void editUser(UserDTO data, String password);
    public void remove(UserDTO user);
    public UserDTO find(Integer id);
    public List<UserDTO> getAll();
    public boolean checkPassword(UserDTO user, String password);
}

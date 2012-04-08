/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.EntityFactory;
import dto.UserDTO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.User;

/**
 *
 * @author mist
 */
@Stateless
public class UserFacade extends FactoryFacade {
    @PersistenceContext(unitName = "WheelGo-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() throws ClassNotFoundException {
        super(UserDTO.class);
    }
    
    public void editUser(UserDTO user, String password) {
        User userToMod = (User) EntityFactory.convertToEntity(user);
        userToMod.setPasswd(password);
        super.editEntity(userToMod);
    }
    
    public void createUser(UserDTO user, String password) {
        User userToAdd = (User) EntityFactory.convertToEntity(user);
        userToAdd.setPasswd(password);
        super.createEntity(userToAdd);
    }
    
    public void remove(UserDTO user) {
        User entity;
        entity = (User) EntityFactory.convertToEntity(user);
        entity.setPasswd("");
        getEntityManager().remove(getEntityManager().merge(entity));
    }
    
}

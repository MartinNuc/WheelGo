/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.EntityFactory;
import dto.UsersDTO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Users;

/**
 *
 * @author mist
 */
@Stateless
public class UsersFacade extends FactoryFacade {
    @PersistenceContext(unitName = "WheelGo-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() throws ClassNotFoundException {
        super(UsersDTO.class);
    }
    
    public void editUser(UsersDTO user, String password) {
        Users userToMod = (Users) EntityFactory.convertToEntity(user);
        userToMod.setPasswd(password);
        super.editEntity(userToMod);
    }
    
    public void createUser(UsersDTO user, String password) {
        Users userToAdd = (Users) EntityFactory.convertToEntity(user);
        userToAdd.setPasswd(password);
        super.createEntity(userToAdd);
    }
    
}

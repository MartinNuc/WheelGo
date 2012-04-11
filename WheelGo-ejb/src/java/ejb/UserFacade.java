/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.EntityFactory;
import dto.UserDTO;
import javax.ejb.EJB;
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

    @EJB
    private EncryptorBeanLocal encryptor;
    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() throws ClassNotFoundException {
        super(UserDTO.class);
    }
    
    public void editUser(UserDTO user, String password) {
        User userToMod = (User) EntityFactory.convertToEntity(user);
        userToMod.setPassword(encryptor.encryptPassword(password, user.getUsername()));
        super.editEntity(userToMod);
    }
    
    public void createUser(UserDTO user, String password) {
        User userToAdd = (User) EntityFactory.convertToEntity(user);
        userToAdd.setPassword(encryptor.encryptPassword(password, user.getUsername()));
        super.createEntity(userToAdd);
    }
    
    public void remove(UserDTO user) {
        User entity;
        entity = (User) EntityFactory.convertToEntity(user);
        entity.setPassword("");
        getEntityManager().remove(getEntityManager().merge(entity));
    }
    
    /**
     * Checks if passed password is correct.
     * 
     * @param user User who's password is being checked
     * @param password password in readable format in utf-8
     * @return true if password is ok, false otherwise
     */
    public boolean checkPassword(UserDTO user, String password) {
        User userToAdd = em.find(User.class, user.getIdUser());

        String encrypted = encryptor.encryptPassword(password, userToAdd.getUsername());
        if (userToAdd.getPassword().equals(encrypted))
            return false;
        else
            return true;
    }
}

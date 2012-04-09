/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.EntityFactory;
import dto.UserDTO;
import java.math.BigInteger;
import java.security.SecureRandom;
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
        userToMod.setPassword(password);
        super.editEntity(userToMod);
    }
    
    public void createUser(UserDTO user, String password) {
        User userToAdd = (User) EntityFactory.convertToEntity(user);
        
        SecureRandom random = new SecureRandom();
        
        userToAdd.setDynamicSeed(new BigInteger(20, random).toString(32));
        userToAdd.setPassword(encryptor.encryptPassword(password, userToAdd.getDynamicSeed()));
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
    public boolean checkPasswork(UserDTO user, String password) {
        User userToAdd = em.find(User.class, user.getIdUser());
        System.out.println("User=" + userToAdd);
        System.out.println("Enc=" + encryptor);
        System.out.println("password=" + password);
        System.out.println("orig.pass=" + userToAdd.getPassword());
        return userToAdd.getPassword().equals(encryptor.encryptPassword(password, userToAdd.getDynamicSeed()));
    }
}

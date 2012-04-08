/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.DtoFactory;
import dto.EntityFactory;
import dto.UsersDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Users;

/**
 *
 * @author mist
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {
    @PersistenceContext(unitName = "WheelGo-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
    
    public void remove(UsersDTO user) {
        super.remove(em.find(Users.class, user.getIdUsers()));
    }

    public void edit(UsersDTO user, String password) {
        //Users userToMod = em.find(Users.class, user.getIdUsers());
        Users userToMod = (Users) EntityFactory.convertToEntity(user);
        userToMod.setPasswd(password);
        super.edit(userToMod);
    }
    
    public void create(UsersDTO user, String password) {
        Users userToAdd = (Users) EntityFactory.convertToEntity(user);
        userToAdd.setPasswd(password);
        super.create(userToAdd);
    }    
    

    public List<UsersDTO> getUsers() {
        List<Users> users = super.findAll();
        List<UsersDTO> output = DtoFactory.convertArrayToDto(users);
        return output;
    }
    
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.facades.implementation;

import ejb.facades.interfaces.UserFacadeLocal;
import dto.UserDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Role;
import model.User;
import utils.EncryptorBeanLocal;

/**
 *
 * @author mist
 */
@Stateless
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal {
    @PersistenceContext(unitName = "WheelGo-ejbPU")
    private EntityManager em;

    @EJB
    private EncryptorBeanLocal encryptor;
        
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    @Override
    public void edit(UserDTO data, String password) {
        User toModify = em.find(User.class, data.getIdUser());
        toModify.setPassword(encryptor.encryptPassword(password, data.getUsername()));
        edit(data);
    }
    
    @Override
    public void edit(UserDTO data) {
        User toModify = em.find(User.class, data.getIdUser());
        
        toModify.setUsername(data.getUsername());
        toModify.setPhoneId(data.getPhoneId());
        toModify.setRole(em.find(Role.class, data.getRole()));
    }
    
    @Override
    public void create(UserDTO user, String password) {
        User userToAdd = toEntity(user);
        userToAdd.setPassword(encryptor.encryptPassword(password, user.getUsername()));
        getEntityManager().persist(userToAdd);
    }
    
    private User toEntity(UserDTO dto)
    {
        User entity = new User();
        entity.setIdUser(dto.getIdUser());
        entity.setPhoneId(dto.getPhoneId());
        entity.setRole(getEntityManager().find(Role.class, dto.getRole()));
        entity.setUsername(dto.getUsername());
        return entity;
    }
    
    @Override
    public void remove(UserDTO user) {
        User entity;
        entity = em.find(User.class, user.getIdUser());
        getEntityManager().remove(getEntityManager().merge(entity));
    }
    

    @Override
    public UserDTO find(Integer id) {
        User output = getEntityManager().find(User.class, id);
        return toDTO(output);
    }
    
    private UserDTO toDTO(User entity)
    {
        UserDTO dto = new UserDTO();
        dto.setIdUser(entity.getIdUser());
        dto.setPhoneId(entity.getPhoneId());
        dto.setRole(entity.getRole().getIdRole());
        dto.setUsername(entity.getUsername());
        return dto;
    }
    
    private List<UserDTO> toDTOs(List<User> entities)
    {
        List<UserDTO> dtos = new ArrayList<UserDTO>();
        for (User entity : entities)
        {
            dtos.add(toDTO(entity));
        }
        return dtos;
    }
    
    @Override
    public List<UserDTO> getAll()
    {
        try {
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
            List entities = getEntityManager().createQuery(cq).getResultList();
            return toDTOs(entities);
        } catch (SecurityException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public List<UserDTO> getRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(User.class));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return toDTOs(q.getResultList());
    }
    
    /**
     * Checks if passed password is correct.
     * 
     * @param user User who's password is being checked
     * @param password password in readable format in utf-8
     * @return true if password is ok, false otherwise
     */
    @Override
    public boolean checkPassword(UserDTO user, String password) {
        User userToAdd = em.find(User.class, user.getIdUser());

        String encrypted = encryptor.encryptPassword(password, userToAdd.getUsername());
        if (userToAdd.getPassword().equals(encrypted))
            return true;
        else
            return false;
    }

}

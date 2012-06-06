/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.facades.implementation;

import ejb.facades.interfaces.RoleFacadeLocal;
import dto.RoleDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Role;
import model.User;

/**
 *
 * @author mist
 */
@Stateless
public class RoleFacade extends AbstractFacade<Role> implements RoleFacadeLocal {

    @PersistenceContext(unitName = "WheelGo-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RoleFacade() {
        super(Role.class);
    }

    @Override
    public void edit(RoleDTO dto) {
        Role entity = em.find(Role.class, dto.getIdRole());
        entity.setDescription(dto.getDescription());
        entity.setId(dto.getIdRole());
        entity.setName(dto.getName());

        List<User> users = new ArrayList<User>();
        for (Integer logId : dto.getUsers()) {
            users.add(em.find(User.class, logId));
        }
        entity.setUsersCollection(users);
    }

    private Role toEntity(RoleDTO dto) {
        Role entity = new Role();
        entity.setDescription(dto.getDescription());
        entity.setId(dto.getIdRole());
        entity.setName(dto.getName());

        List<User> users = new ArrayList<User>();
        if (dto.getUsers() != null) {
            for (Integer logId : dto.getUsers()) {
                users.add(em.find(User.class, logId));
            }
        }
        entity.setUsersCollection(users);

        return entity;
    }

    @Override
    public void remove(RoleDTO Role) {
        Role entity;
        entity = em.find(Role.class, Role.getIdRole());
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    @Override
    public RoleDTO find(Object id) {
        Role output = getEntityManager().find(Role.class, id);
        return toDTO(output);
    }

    private RoleDTO toDTO(Role entity) {
        if (entity == null)
            return null;
        RoleDTO dto = new RoleDTO();
        dto.setIdRole(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setName(entity.getName());


        return dto;
    }

    private static List<RoleDTO> toDTOs(List<Role> entities) {
        List<RoleDTO> dtos = new ArrayList<RoleDTO>();
        for (Role entity : entities) {
            RoleDTO dto = new RoleDTO();
            dto.setDescription(entity.getDescription());
            dto.setIdRole(entity.getId());
            dto.setName(entity.getName());

            List<Integer> usersId = new ArrayList<Integer>();
            for (User user : entity.getUsersCollection()) {
                usersId.add(user.getId());
            }
            dto.setUsers(usersId);

            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<RoleDTO> getAll() {
        try {
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(Role.class));
            List entities = getEntityManager().createQuery(cq).getResultList();
            return toDTOs(entities);
        } catch (SecurityException ex) {
            Logger.getLogger(RoleFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void create(RoleDTO Role) {
        Role newRole = toEntity(Role);
        getEntityManager().persist(newRole);

    }

    @Override
    public List<RoleDTO> getRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Role.class));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return toDTOs(q.getResultList());
    }
}

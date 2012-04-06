/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.RolesDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Roles;

/**
 *
 * @author mist
 */
@Stateless
public class RolesFacade extends AbstractFacade<Roles> {
    @PersistenceContext(unitName = "WheelGo-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolesFacade() {
        super(Roles.class);
    }
    
    public void remove(RolesDTO role) {
        super.remove(em.find(Roles.class, role.getIdRole()));
    }

    public void edit(RolesDTO role) {
        Roles roleToMod = em.find(Roles.class, role.getIdRole());
        roleToMod.setName(role.getName());
        roleToMod.setDescription(role.getDescription());
    }
    
    public void create(RolesDTO role) {
        Roles roleToAdd = new Roles();
        roleToAdd.setName(role.getName());
        roleToAdd.setDescription(role.getDescription());
        
        super.create(roleToAdd);
    }    
    
    public List<RolesDTO> getRoles() {
        List<Roles> list = em.createQuery("SELECT r FROM Roles r ORDER by r.name").getResultList();
        List<RolesDTO> ret = new ArrayList<RolesDTO>();
        for(Roles role : list) {
            RolesDTO toBeAdded = new RolesDTO();
            toBeAdded.setIdRole(role.getIdRoles());
            toBeAdded.setName(role.getName());            
            toBeAdded.setDescription(role.getDescription());
            ret.add(toBeAdded);
        }
        
        return ret;
    }
}

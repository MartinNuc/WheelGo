/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.facades.implementation;

import dto.PhotoDTO;
import ejb.facades.interfaces.PhotoFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Photo;
import model.Report;

/**
 *
 * @author mist
 */
@Stateless
public class PhotoFacade extends AbstractFacade<Photo> implements PhotoFacadeLocal {
    @PersistenceContext(unitName = "WheelGo-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PhotoFacade() {
        super(Photo.class);
    }
    
@Override
    public void edit(PhotoDTO dto) {
        Photo entity = em.find(Photo.class, dto.getIdReport());
        
        entity.setId(dto.getIdPhoto());
        entity.setReport(em.find(Report.class, dto.getIdReport()));
        entity.setImage(dto.getImage());
    }
    
    private Photo toEntity(PhotoDTO dto)
    {
        Photo entity = new Photo();
        entity.setId(dto.getIdPhoto());
        entity.setReport(em.find(Report.class, dto.getIdReport()));
        entity.setImage(dto.getImage());        
        return entity;
    }
    
    @Override
    public void remove(PhotoDTO Photo) {
        Photo entity;
        entity = em.find(Photo.class, Photo.getIdReport());
        getEntityManager().remove(getEntityManager().merge(entity));
    }
    
    @Override
    public PhotoDTO find(Object id) {
        Photo output = getEntityManager().find(Photo.class, id);
        return toDTO(output);
    }
    
    private PhotoDTO toDTO(Photo entity)
    {
        if (entity == null)
            return null;
        PhotoDTO dto = new PhotoDTO();
        dto.setIdPhoto(entity.getId());
        dto.setIdReport(entity.getReport().getId());
        dto.setImage(entity.getImage());
        return dto;
    }
    
    private List<PhotoDTO> toDTOs(List<Photo> entities)
    {
        if (entities == null)
            return null;
        List<PhotoDTO> dtos = new ArrayList<PhotoDTO>();
        for (Photo entity : entities)
        {
            dtos.add(toDTO(entity));
        }
        return dtos;
    }
    
    @Override
    public List<PhotoDTO> getAll()
    {
        try {
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(Photo.class));
            List entities = getEntityManager().createQuery(cq).getResultList();
            return toDTOs(entities);
        } catch (SecurityException ex) {
            Logger.getLogger(PhotoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void create(PhotoDTO Photo) {
        Photo newPhoto = toEntity(Photo);
        getEntityManager().persist(newPhoto);

    }

    @Override
    public List<PhotoDTO> getRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Photo.class));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return toDTOs(q.getResultList());
    }
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.facades.implementation;

import ejb.facades.interfaces.TipFacadeLocal;
import dto.TipDTO;
import ejb.LoginBeanLocal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Log;
import model.Photo;
import model.Report;
import model.Tip;

/**
 *
 * @author mist
 */
@Stateless
public class TipFacade extends AbstractFacade<Tip> implements TipFacadeLocal {

    @PersistenceContext(unitName = "WheelGo-ejbPU")
    private EntityManager em;
    @EJB
    private LoginBeanLocal lb;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipFacade() {
        super(Tip.class);
    }

    @Override
    public void edit(TipDTO dto) {
        Tip entity = em.find(Tip.class, dto.getIdReport());
        entity.setDate(dto.getDate());
        entity.setDescribtion(dto.getDescribtion());
        entity.setId(dto.getIdReport());
        entity.setId(dto.getIdReport());
        entity.setLatitude(dto.getLatitude());
        entity.setDeleted(dto.isDeleted());

        List<Log> logs = new ArrayList<Log>();
        for (Integer logId : dto.getLogsCollection()) {
            logs.add(em.find(Log.class, logId));
        }
        entity.setLogsCollection(logs);

        entity.setLongitude(dto.getLongitude());
        entity.setName(dto.getName());

        List<Photo> photos = new ArrayList<Photo>();
        for (Integer photoId : dto.getPhotosCollection()) {
            photos.add(em.find(Photo.class, photoId));
        }
        entity.setPhotos(photos);
    }

    private Tip toEntity(TipDTO dto) {
        Tip entity = new Tip();
        entity.setDate(dto.getDate());
        entity.setDescribtion(dto.getDescribtion());
        entity.setId(dto.getIdReport());
        entity.setId(dto.getIdReport());
        entity.setLatitude(dto.getLatitude());
        entity.setDeleted(dto.isDeleted());

        List<Log> logs = new ArrayList<Log>();
        for (Integer logId : dto.getLogsCollection()) {
            logs.add(em.find(Log.class, logId));
        }
        entity.setLogsCollection(logs);

        entity.setLongitude(dto.getLongitude());
        entity.setName(dto.getName());

        List<Photo> photos = new ArrayList<Photo>();
        for (Integer photoId : dto.getPhotosCollection()) {
            photos.add(em.find(Photo.class, photoId));
        }
        entity.setPhotos(photos);

        return entity;
    }

    @Override
    public void remove(TipDTO tip) {
        Report entity = em.find(Report.class, tip.getIdReport());

        Log log = new Log();
        log.setDate(new Date());
        log.setOperation(3);
        log.setReport(entity);
        log.setUser(lb.getUser());
        em.persist(log);

        entity.setDeleted(true);
    }

    @Override
    public TipDTO find(Object id) {
        Tip output = getEntityManager().find(Tip.class, id);
        return toDTO(output);
    }

    private TipDTO toDTO(Tip entity) {
        if (entity == null) {
            return null;
        }
        TipDTO dto = new TipDTO();
        dto.setDate(entity.getDate());
        dto.setDescribtion(entity.getDescribtion());
        dto.setIdReport(entity.getId());
        dto.setLatitude(entity.getLatitude());
        dto.setDeleted(entity.isDeleted());

        List<Integer> logs = new ArrayList<Integer>();
        for (Log log : entity.getLogsCollection()) {
            logs.add(log.getId());
        }
        dto.setLogsCollection(logs);

        dto.setLongitude(entity.getLongitude());
        dto.setName(entity.getName());

        List<Integer> photos = new ArrayList<Integer>();
        for (Photo photo : entity.getPhotos()) {
            photos.add(photo.getId());
        }
        dto.setPhotosCollection(photos);
        return dto;
    }

    private List<TipDTO> toDTOs(List<Tip> entities) {
        List<TipDTO> dtos = new ArrayList<TipDTO>();
        for (Tip entity : entities) {
            dtos.add(toDTO(entity));
        }
        return dtos;
    }

    @Override
    public List<TipDTO> getAll() {
        try {
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tip.class));
            List entities = getEntityManager().createQuery(cq).getResultList();
            return toDTOs(entities);
        } catch (SecurityException ex) {
            Logger.getLogger(TipFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void create(TipDTO tip) {
        Tip newTip = toEntity(tip);
        getEntityManager().persist(newTip);

    }

    @Override
    public List<TipDTO> getRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Tip.class));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return toDTOs(q.getResultList());
    }
}

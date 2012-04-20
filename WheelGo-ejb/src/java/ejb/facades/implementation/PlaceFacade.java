/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.facades.implementation;

import ejb.facades.interfaces.PlaceFacadeLocal;
import dto.PlaceDTO;
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
import model.Place;
import model.Report;

/**
 *
 * @author mist
 */
@Stateless
public class PlaceFacade extends AbstractFacade<Place> implements PlaceFacadeLocal {
    @PersistenceContext(unitName = "WheelGo-ejbPU")
    private EntityManager em;

    @EJB
    private LoginBeanLocal lb;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlaceFacade() {
        super(Place.class);
    }
    
    @Override
    public void edit(PlaceDTO dto) {
        Place entity = em.find(Place.class, dto.getIdReport());
        entity.setAccesibility(dto.getAccesibility());
        entity.setDate(dto.getDate());
        entity.setDescribtion(dto.getDescribtion());
        entity.setIdReport(dto.getIdReport());
        entity.setIdPlace(dto.getIdReport());
        entity.setLatitude(dto.getLatitude());
        
        List<Log> logs = new ArrayList<Log>();
        for (Integer logId : dto.getLogsCollection())
            logs.add(em.find(Log.class, logId));
        entity.setLogsCollection(logs);
        
        entity.setLongitude(dto.getLongitude());
        entity.setName(dto.getName());
        
        List<Photo> photos = new ArrayList<Photo>();
        for (Integer photoId : dto.getPhotosCollection())
            photos.add(em.find(Photo.class, photoId));
        entity.setPhotos(photos);
    }
    
    private Place toEntity(PlaceDTO dto)
    {
        Place entity = new Place();
        entity.setDate(dto.getDate());
        entity.setAccesibility(dto.getAccesibility());
        entity.setDescribtion(dto.getDescribtion());
        entity.setIdReport(dto.getIdReport());
        entity.setIdPlace(dto.getIdReport());
        entity.setLatitude(dto.getLatitude());
        
        List<Log> logs = new ArrayList<Log>();
        for (Integer logId : dto.getLogsCollection())
            logs.add(em.find(Log.class, logId));
        entity.setLogsCollection(logs);
        
        entity.setLongitude(dto.getLongitude());
        entity.setName(dto.getName());
        
        List<Photo> photos = new ArrayList<Photo>();
        for (Integer photoId : dto.getPhotosCollection())
            photos.add(em.find(Photo.class, photoId));
        entity.setPhotos(photos);
        
        return entity;
    }
     
    @Override
    public PlaceDTO find(Object id) {
        Place output = getEntityManager().find(Place.class, id);
        return toDTO(output);
    }
    
    private PlaceDTO toDTO(Place entity)
    {
        PlaceDTO dto = new PlaceDTO();
        dto.setDate(entity.getDate());
        dto.setAccesibility(entity.getAccesibility());
        dto.setDescribtion(entity.getDescribtion());
        dto.setIdReport(entity.getIdReport());
        dto.setLatitude(entity.getLatitude());

        List<Integer> logs = new ArrayList<Integer>();
        for (Log log : entity.getLogsCollection())
            logs.add(log.getIdLog());
        dto.setLogsCollection(logs);

        dto.setLongitude(entity.getLongitude());
        dto.setName(entity.getName());

        List<Integer> photos = new ArrayList<Integer>();
        for (Photo photo : entity.getPhotos())
            photos.add(photo.getIdPhoto());
        dto.setPhotosCollection(photos);
        return dto;
    }
    
    private List<PlaceDTO> toDTOs(List<Place> entities)
    {
        List<PlaceDTO> dtos = new ArrayList<PlaceDTO>();
        for (Place entity : entities)
        {
            dtos.add(toDTO(entity));
        }
        return dtos;
    }
    
    @Override
    public List<PlaceDTO> getAll()
    {
        try {
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(Place.class));
            List entities = getEntityManager().createQuery(cq).getResultList();
            return toDTOs(entities);
        } catch (SecurityException ex) {
            Logger.getLogger(PlaceFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void create(PlaceDTO Place) {
        Place newPlace = toEntity(Place);
        getEntityManager().persist(newPlace);

    }

    @Override
    public List<PlaceDTO> getRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Place.class));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return toDTOs(q.getResultList());
    }

    @Override
    public void remove(PlaceDTO place) {
        Report entity = em.find(Report.class, place.getIdReport());
        
        Log log = new Log();
        log.setDate(new Date());
        log.setOperation(3);
        log.setReport(entity);
        log.setUser(lb.getUser());
        em.persist(log);
        
        entity.setDeleted(true);
    }
    
}

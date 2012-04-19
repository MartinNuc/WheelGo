/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.facades.implementation;

import dto.ReportDTO;
import ejb.LoginBeanLocal;
import ejb.facades.interfaces.ReportFacadeLocal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import model.Log;
import model.Photo;
import model.Report;
import model.User;

/**
 *
 * @author mist
 */
@Stateless
public class ReportFacade extends AbstractFacade<Report> implements ReportFacadeLocal {
    @PersistenceContext(unitName = "WheelGo-ejbPU")
    private EntityManager em;
    
    @EJB
    private LoginBeanLocal lb;
     
    private User user;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @PostConstruct
    private void init()
    {
        this.user = lb.getUser();
    }

    public ReportFacade() {
        super(Report.class);
    }
    
    public ReportFacade(User user) {
        super(Report.class);
    }
    
    @Override
    public void edit(ReportDTO dto) {
        Report entity = em.find(Report.class, dto.getIdReport());
        entity.setDate(dto.getDate());
        entity.setDescribtion(dto.getDescribtion());
        entity.setIdReport(dto.getIdReport());
        entity.setIdReport(dto.getIdReport());
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
    
    private Report toEntity(ReportDTO dto)
    {
        Report entity = new Report();
        entity.setDate(dto.getDate());
        entity.setDescribtion(dto.getDescribtion());
        entity.setIdReport(dto.getIdReport());
        entity.setIdReport(dto.getIdReport());
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
    public void remove(ReportDTO Report) {
        Report entity;
        entity = em.find(Report.class, Report.getIdReport());
        
        Log log = new Log();
        log.setDate(new Date());
        log.setOperation(3);
        log.setReport(entity);
        log.setUser(user);
        em.persist(log);
        
        entity.setDeleted(true);
    }
    
    @Override
    public ReportDTO find(Object id) {
        Report output = getEntityManager().find(Report.class, id);
        return toDTO(output);
    }
    
    private ReportDTO toDTO(Report entity)
    {
        ReportDTO dto = new ReportDTO();
        dto.setDate(entity.getDate());
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
    
    private List<ReportDTO> toDTOs(List<Report> entities)
    {
        List<ReportDTO> dtos = new ArrayList<ReportDTO>();
        for (Report entity : entities)
        {
            dtos.add(toDTO(entity));
        }
        return dtos;
    }
    
    
    @Override
    public List<ReportDTO> getAll()
    {       
        Query q = getEntityManager().createNamedQuery("getVisibleReports");
        return toDTOs(q.getResultList());
    }
    
    @Override
    public List<ReportDTO> getAllWithDeleted()
    {
        try {
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(Report.class));
            List entities = getEntityManager().createQuery(cq).getResultList();
            return toDTOs(entities);
        } catch (SecurityException ex) {
            Logger.getLogger(ReportFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void create(ReportDTO Report) {
        Report newReport = toEntity(Report);
        getEntityManager().persist(newReport);

    }

    @Override
    public List<ReportDTO> getRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Report.class));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return toDTOs(q.getResultList());
    }

    public List<ReportDTO> getArea(float latLowerBound, float latUpperBound, float longLowerBound, float longUpperBound) {
        return getArea(latLowerBound, latUpperBound, longLowerBound, longUpperBound, 500);
    }
    
    @Override
    public List<ReportDTO> getArea(float latLowerBound, float latUpperBound, float longLowerBound, float longUpperBound, int maxCount) {
        // Bez criteriaAPI:
        /*Query cq = getEntityManager().createQuery("select r from Report r where r.deleted=0 and r.latitude>=:arg1 and r.latitude<=:arg2 and "
                + "r.longitude>=:arg3 and r.longitude<=:arg4 ");
        cq.setParameter("arg1", latLowerBound);
        cq.setParameter("arg2", latUpperBound);
        cq.setParameter("arg3", longLowerBound);
        cq.setParameter("arg4", longUpperBound);
        cq.setMaxResults(maxCount);*/
        
        // s criteriaAPI:
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Report> criteriaQuery = criteriaBuilder.createQuery(Report.class);
        Root from = criteriaQuery.from(Report.class);
        Predicate predicate1 = criteriaBuilder.ge(from.get("latitude"), latLowerBound);
        Predicate predicate2 = criteriaBuilder.le(from.get("latitude"), latUpperBound);
        Predicate predicate3 = criteriaBuilder.ge(from.get("latitude"), longLowerBound);
        Predicate predicate4 = criteriaBuilder.le(from.get("latitude"), longUpperBound);
        Predicate predicate5 = criteriaBuilder.equal(from.get("deleted"), 0);
        criteriaQuery.where(criteriaBuilder.and(predicate1, predicate2, predicate3, predicate4, predicate5));
        
        TypedQuery<Report> query = em.createQuery(criteriaQuery);
        return toDTOs(query.getResultList());
    }
    
}

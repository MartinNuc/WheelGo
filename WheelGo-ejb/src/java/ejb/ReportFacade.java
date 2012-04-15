/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.ReportDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Log;
import model.Photo;
import model.Report;

/**
 *
 * @author mist
 */
@Stateless
public class ReportFacade extends AbstractFacade<Report> implements ReportFacadeLocal {
    @PersistenceContext(unitName = "WheelGo-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReportFacade() {
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
        getEntityManager().remove(getEntityManager().merge(entity));
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
    
}

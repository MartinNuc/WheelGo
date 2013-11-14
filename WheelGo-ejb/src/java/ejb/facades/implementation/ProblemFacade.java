/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.facades.implementation;

import dto.ProblemDTO;
import ejb.LoginBeanLocal;
import ejb.facades.interfaces.ProblemFacadeLocal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import model.Log;
import model.Photo;
import model.Problem;
import model.Report;

/**
 *
 * @author mist
 */
@Stateless
public class ProblemFacade extends AbstractFacade<Problem> implements ProblemFacadeLocal {

    @PersistenceContext(unitName = "WheelGo-ejbPU")
    private EntityManager em;
    @EJB
    private LoginBeanLocal lb;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProblemFacade() {
        super(Problem.class);
    }

    @Override
    public void edit(ProblemDTO dto) {
        Problem entity = em.find(Problem.class, dto.getIdReport());
        entity.setExpiration(dto.getExpirationDate());
        entity.setDescribtion(dto.getDescribtion());
    }

    private Problem toEntity(ProblemDTO dto) {
        Problem entity = new Problem();
        entity.setExpiration(dto.getExpirationDate());
        entity.setDate(dto.getDateDate());
        entity.setDescribtion(dto.getDescribtion());
        entity.setId(dto.getIdReport());
        entity.setLatitude(dto.getLatitude());
        entity.setDeleted(dto.isDeleted());

        if (dto.getLogsCollection() != null) {
            List<Log> logs = new ArrayList<Log>();
            for (Integer logId : dto.getLogsCollection()) {
                logs.add(em.find(Log.class, logId));
            }
            entity.setLogsCollection(logs);
        }

        entity.setLongitude(dto.getLongitude());
        entity.setName(dto.getName());

        if (dto.getPhotosCollection() != null) {
            List<Photo> photos = new ArrayList<Photo>();
            for (Integer photoId : dto.getPhotosCollection()) {
                photos.add(em.find(Photo.class, photoId));
            }
            entity.setPhotos(photos);
        }

        return entity;
    }

    @Override
    public ProblemDTO find(Object id) {
        Problem output = getEntityManager().find(Problem.class, id);
        return toDTO(output);
    }

    private ProblemDTO toDTO(Problem entity) {
        if (entity == null) {
            return null;
        }
        ProblemDTO dto = new ProblemDTO();
        dto.setExpiration(entity.getExpiration());
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

    private List<ProblemDTO> toDTOs(List<Problem> entities) {
        List<ProblemDTO> dtos = new ArrayList<ProblemDTO>();
        for (Problem entity : entities) {
            dtos.add(toDTO(entity));
        }
        return dtos;
    }

    @Override
    public List<ProblemDTO> getAll() {
        try {
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(Problem.class));
            List entities = getEntityManager().createQuery(cq).getResultList();
            return toDTOs(entities);
        } catch (SecurityException ex) {
            Logger.getLogger(ProblemFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void create(ProblemDTO problem) {
        Problem newProblem = toEntity(problem);
        newProblem.setDate(new Date());
        newProblem.setName("Problem");
        getEntityManager().persist(newProblem);
        getEntityManager().flush();
    }

    @Override
    public List<ProblemDTO> getRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Problem.class));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return toDTOs(q.getResultList());
    }

    @Override
    public void remove(ProblemDTO problem) {
        Report entity = em.find(Report.class, problem.getIdReport());

        Log log = new Log();
        log.setDate(new Date());
        log.setOperation(3);
        log.setReport(entity);
        log.setUser(lb.getUser());
        em.persist(log);

        entity.setDeleted(true);
    }
}

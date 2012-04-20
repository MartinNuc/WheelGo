/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.facades.implementation;

import dto.LogDTO;
import ejb.facades.interfaces.LogFacadeLocal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Log;
import model.Report;
import model.User;

/**
 *
 * @author mist
 */
@Stateless
public class LogFacade extends AbstractFacade<Log> implements LogFacadeLocal {

    @PersistenceContext(unitName = "WheelGo-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LogFacade() {
        super(Log.class);
    }

    @Override
    public void edit(LogDTO dto) {
        Log entity = em.find(Log.class, dto.getIdLog());

        entity.setDate(dto.getDate());
        entity.setIdLog(dto.getIdLog());
        entity.setOperation(dto.getOperation());
        entity.setReport(em.find(Report.class, dto.getReport()));
        entity.setUser(em.find(User.class, dto.getUser()));
    }

    private Log toEntity(LogDTO dto) {
        Log entity = new Log();
        entity.setDate(dto.getDate());
        entity.setIdLog(dto.getIdLog());
        entity.setOperation(dto.getOperation());
        entity.setReport(em.find(Report.class, dto.getReport()));
        entity.setUser(em.find(User.class, dto.getUser()));
        return entity;
    }

    @Override
    public void remove(LogDTO Log) {
        Log entity;
        entity = em.find(Log.class, Log.getIdLog());
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    @Override
    public LogDTO find(Object id) {
        Log output = getEntityManager().find(Log.class, id);
        return toDTO(output);
    }

    private LogDTO toDTO(Log entity) {
        if (entity == null)
            return null;
        LogDTO dto = new LogDTO();
        dto.setDate(entity.getDate());
        dto.setIdLog(entity.getIdLog());
        dto.setOperation(entity.getOperation());
        dto.setReport(entity.getReport().getIdReport());
        dto.setUser(entity.getUser().getIdUser());

        return dto;
    }

    private List<LogDTO> toDTOs(List<Log> entities) {
        List<LogDTO> dtos = new ArrayList<LogDTO>();
        for (Log entity : entities) {
            dtos.add(toDTO(entity));
        }
        return dtos;
    }

    @Override
    public List<LogDTO> getAll() {
        try {
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(Log.class));
            List entities = getEntityManager().createQuery(cq).getResultList();
            if (!entities.isEmpty()) {
                return toDTOs(entities);
            } else {
                return new LinkedList<LogDTO>();
            }
        } catch (SecurityException ex) {
            Logger.getLogger(LogFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void create(LogDTO Log) {
        Log newLog = toEntity(Log);
        getEntityManager().persist(newLog);

    }

    @Override
    public List<LogDTO> getRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Log.class));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return toDTOs(q.getResultList());
    }
}

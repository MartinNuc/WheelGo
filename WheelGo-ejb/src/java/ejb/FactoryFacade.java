/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import com.sun.xml.ws.xmlfilter.InvocationProcessingException;
import dto.DtoFactory;
import dto.EntityFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author mist
 */
public abstract class FactoryFacade {
    private Class entityClass;
    private Class dtoClass;

    public FactoryFacade(Class dto) throws ClassNotFoundException {
        String dtoClassName = dto.getSimpleName();
        String entityClassName = dtoClassName.substring(0, dtoClassName.length()-3);
        this.entityClass = Class.forName("model." + entityClassName);
        this.dtoClass = dto;
    }

    protected abstract EntityManager getEntityManager();

    public void create(Object dto) {
        Object entity;
        entity = EntityFactory.convertToEntity(dto);
        getEntityManager().persist(entity);
    }
    
    public void createEntity(Object entity) {
        getEntityManager().persist(entity);
    }

    public void edit(Object dto) {
        Object entity;
        entity = EntityFactory.convertToEntity(dto);
        getEntityManager().merge(entity);
    }

    public void editEntity(Object entity) {
        getEntityManager().merge(entity);
    }
    
    public void remove(Object dto) {
        Object entity;
        entity = EntityFactory.convertToEntity(dto);
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public Object find(Object id) {
        return getEntityManager().find(entityClass, id);
    }
    
    public List<Object> getAll()
    {
        return findAll();
    }

    public List<Object> findAll() {
        try {
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));

            List<Object> dtos = new ArrayList<Object>();
            Method add;
            add = List.class.getDeclaredMethod("add", Object.class);
            List entities = getEntityManager().createQuery(cq).getResultList();
            for (Object entity : entities)
                add.invoke(dtos, DtoFactory.convertToDto(entity));
            return dtos;
        } catch (InvocationProcessingException ex) {
            Logger.getLogger(FactoryFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(FactoryFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FactoryFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(FactoryFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(FactoryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
/*
    public List<Object> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Object> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }*/
    
    
}

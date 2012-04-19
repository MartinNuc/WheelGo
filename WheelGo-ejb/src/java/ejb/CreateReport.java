/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.*;

/**
 *
 * @author vlada
 */
@Stateful
public class CreateReport implements CreateReportLocal {
    @EJB
    private LoginBeanLocal loginBean;
    
    
    @PersistenceContext(unitName = "WheelGo-ejbPU")
    private EntityManager em;
    private Report instance;
    private User user;
    private int state = 0;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private void fillReport(String problemName,
            Date date, float latitude, float longitude) {

        instance.setPhotos(new ArrayList<Photo>());
        user = loginBean.getUser();
        instance.setName(problemName);
        instance.setDate(date);
        instance.setLatitude(latitude);
        instance.setLongitude(longitude);

    }
    
    

    public CreateReport() {
    }
    
    @PostConstruct
    private void init() {
        clear();
        this.user = loginBean.getUser();
        
    }
    
    private void createLog() {
        Log log = new Log();
        log.setUser(user);
        log.setOperation(1);
        log.setDate(new Date());
        log.setReport(instance);
        em.persist(log);
    }

    @Override
    public void clear() {
        instance = new Report();
        fillReport("", new Date(), 0, 0);

    }

    @Override
    public void preCreateProblem() {
        state = TYPE_PROBLEM_PRE;
        instance = new Problem(instance);

    }

    @Override
    public void createProblem() {
        if (state != TYPE_PROBLEM_PRE || !(instance instanceof Problem)) {
            throw new IllegalStateException("Attemp to save invalid problem.");
        }
        em.persist(instance);
        instance = em.merge(instance);
        
        createLog();

        state = TYPE_PROBLEM;
    }

    @Override
    public void createTip() {
        state = TYPE_TIP;
        instance = new Tip(instance);
        em.persist(instance);
        
        createLog();
                
        instance = em.merge(instance);
        
    }

    @Override
    public void createPlacePre() {
        state = TYPE_PLACE_PRE;
        instance = new Place(instance);
    }

    @Override
    public void createPlace() {
        if (state != TYPE_PLACE_PRE || !(instance instanceof Place)) {
            throw new IllegalStateException("Attemp to save invalid problem.");
        }
        em.persist(instance);
        instance = em.merge(instance);

        createLog();
        
        state = TYPE_PLACE;
    }

    @Override
    public Date getExpiration() {
        if (state != TYPE_PROBLEM_PRE && state != TYPE_PROBLEM) {
            throw new IllegalStateException("Attemp to set expiriation on invalid problem.");
        }
        return ((Problem) instance).getExpiration();
    }

    @Override
    public void setExpiration(Date expiration) {
        if (state != TYPE_PROBLEM_PRE && state != TYPE_PROBLEM) {
            throw new IllegalStateException("Attemp to set expiriation on invalid problem.");
        }
        ((Problem) instance).setExpiration(expiration);
    }

    @Override
    public int getAccesibility() {
        if (state != TYPE_PLACE && state != TYPE_PLACE_PRE) {
            throw new IllegalStateException("Attemp to set accesibility on invalid problem.");
        }
        return ((Place) instance).getAccesibility();
    }

    @Override
    public void setAccesibility(int accesibility) {
        if (state != TYPE_PLACE && state != TYPE_PLACE_PRE) {
            throw new IllegalStateException("Attemp to set accesibility on invalid problem.");
        }
        ((Place) instance).setAccesibility(accesibility);
    }

    @Override
    public void addPhoto(byte data[], String url) {
        Photo photo = new Photo();
        photo.setUrl(url);
        photo.setImage(data);
        photo.setReport(instance);
        
        instance.getPhotos().add(photo);
        instance = em.merge(instance);
        
    }

    @Override
    public void cancelReport() {
        em.remove(em.merge(instance));
    }

    @Override
    public String getName() {
        return instance.getName();
    }

    @Override
    public void setName(String name) {
        instance.setName(name);
    }

    @Override
    public String getDescription() {
        return instance.getDescribtion();
    }

    @Override
    public void setDescription(String description) {
        instance.setDescribtion(description);
    }

    @Override
    public Date getDate() {
        return instance.getDate();
    }

    @Override
    public void setDate(Date date) {
        instance.setDate(date);
    }

    @Override
    public int getState() {
        return state;
    }
    
    @Override
    public void setLatitude(float latitude) {
        instance.setLatitude(latitude);
    }
    
    @Override
    public float getLatitude() {
        return instance.getLatitude();
    }
    
    @Override
    public void setLongitude(float longitude) {
        instance.setLongitude(longitude);
    }
    
    @Override
    public float getLongitude() {
        return instance.getLongitude();
    }    
    
}

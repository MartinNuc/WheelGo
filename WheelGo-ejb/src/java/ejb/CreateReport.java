/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.UserDTO;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Photo;
import model.Place;
import model.Problem;
import model.Report;
import model.Tip;
import model.User;

/**
 *
 * @author vlada
 */
@Stateful
public class CreateReport implements CreateReportLocal {
    
    @PersistenceContext(unitName = "WheelGo-ejbPU")
    private EntityManager em;
    
    private Report instance;
    private User user;
    private int state = 0;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private void fillReport(UserDTO user, String problemName,
            Date date, float latitude, float longitude) {

        instance.setPhotos(new ArrayList<Photo>());
        if(user!=null) {
            this.user = em.find(User.class, user.getIdUser());
        }
        instance.setName(problemName);
        instance.setDate(date);
        instance.setLatitude(latitude);
        instance.setLongitude(longitude);

    }

    public CreateReport() {
        instance = new Report();
        fillReport( null, "", new Date(), 0, 0);
    }
    
    @Override
    public void createProblem(UserDTO user, String problemName,
            Date date, float latitude, float longitude) {
        state = TYPE_PROBLEM;
        instance = new Problem();
        fillReport(user, problemName, date, latitude, longitude);
    }

    @Override
    public void createTip(UserDTO user, String problemName,
            Date date, float latitude, float longitude) {
        state = TYPE_TIP;
        instance = new Tip();
        fillReport(user, problemName, date, latitude, longitude);
    }

    @Override
    public void createPlace(UserDTO user, String problemName,
            Date date, float latitude, float longitude) {
        state = TYPE_PLACE;
        instance = new Place();
        fillReport(user, problemName, date, latitude, longitude);
    }

    @Override
    public void setExpiration(Date expiration) {
        if (state != TYPE_PROBLEM) {
            throw new IllegalStateException("Attemp to set expiriation on invalid problem.");
        }
        ((Problem) instance).setExpiration(expiration);
    }
    
    @Override
    public void setAccesibility(int accesibility) {
        if (state != TYPE_PLACE) {
            throw new IllegalStateException("Attemp to set expiriation on invalid problem.");
        }
        ((Place) instance).setAccesibility(accesibility);
    }
    
    @Override
    public void addPhoto(byte data[],String url) {
        Photo photo = new Photo();
        photo.setUrl(url);
        photo.setPictureData(data);
        
        instance.getPhotos().add(photo);
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

}

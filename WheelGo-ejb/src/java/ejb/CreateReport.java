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

    private static final int TYPE_TIP = 1;
    private static final int TYPE_PLACE = 2;
    private static final int TYPE_PROBLEM = 3;
    @PersistenceContext(unitName = "WheelGo-ejbPU")
    private EntityManager em;
    private Report report;
    private User user;
    private int state = 0;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private void fillReport(Report instance, UserDTO user, String problemName,
            Date date, float latitude, float longitude) {

        instance.setPhotos(new ArrayList<Photo>());
        this.user = em.find(User.class, user.getIdUser());
        instance.setName(problemName);
        instance.setDate(date);
        instance.setLatitude(latitude);
        instance.setLongitude(longitude);

    }

    @Override
    public void createProblem(UserDTO user, String problemName,
            Date date, float latitude, float longitude) {
        state = TYPE_PROBLEM;
        fillReport(new Problem(), user, problemName, date, latitude, longitude);
    }

    public void createTip(UserDTO user, String problemName,
            Date date, float latitude, float longitude) {
        state = TYPE_TIP;
        fillReport(new Tip(), user, problemName, date, latitude, longitude);
    }

    public void createPlace(UserDTO user, String problemName,
            Date date, float latitude, float longitude) {
        state = TYPE_PLACE;
        fillReport(new Place(), user, problemName, date, latitude, longitude);
    }

    public void setExpiration(Date expiration) {
        if (state != TYPE_PROBLEM) {
            throw new IllegalStateException("Attemp to set expiriation on invalid problem.");
        }
        ((Problem) report).setExpiration(expiration);
    }
    
    public void setAccesibility(int accesibility) {
        if (state != TYPE_PLACE) {
            throw new IllegalStateException("Attemp to set expiriation on invalid problem.");
        }
        ((Place) report).setAccesibility(accesibility);
    }
    
    public void addPhoto(byte data[],String url) {
        Photo photo = new Photo();
        photo.setUrl(url);
        photo.setPictureData(data);
        
        report.getPhotos().add(photo);
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.UserDTO;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author vlada
 */

@Local
public interface CreateReportLocal {
    public static final int TYPE_UNSPEC = 0;

    
    public static final int TYPE_PLACE = 2;
    public static final int TYPE_PLACE_PRE = 5;
    
    public static final int TYPE_TIP = 1;
    
    public static final int TYPE_PROBLEM = 3;
    public static final int TYPE_PROBLEM_PRE = 4;
    
    public static final int PHOTO = 6;

    void clear();
    
    void addPhoto(byte[] data, String url);

    void createPlacePre();
    void createPlace();

    void preCreateProblem();
    void createProblem();

    void createTip();

    Date getDate();
    void setDate(Date date);

    String getDescription();
    void setDescription(String description);

    String getName();
    void setName(String name);

    int getState();

    int getAccesibility();
    void setAccesibility(int accesibility);
    
    Date getExpiration();
    void setExpiration(Date expiration);
    
}

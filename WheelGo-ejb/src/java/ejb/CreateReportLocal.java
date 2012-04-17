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
    int TYPE_PLACE = 2;
    int TYPE_PROBLEM = 3;
    int TYPE_TIP = 1;
    int TYPE_UNSPEC = 0;

    void addPhoto(byte[] data, String url);

    void createPlace(UserDTO user, String problemName, Date date, float latitude, float longitude);

    void createProblem(UserDTO user, String problemName, Date date, float latitude, float longitude);

    void createTip(UserDTO user, String problemName, Date date, float latitude, float longitude);

    Date getDate();
    void setDate(Date date);

    String getDescription();
    void setDescription(String description);

    String getName();
    void setName(String name);

    int getState();

    void setAccesibility(int accesibility);

    void setExpiration(Date expiration);
    
}

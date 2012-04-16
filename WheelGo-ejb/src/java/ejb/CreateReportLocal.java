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

    void createProblem(UserDTO user, String problemName,
            Date date, float latitude, float longitude);
}

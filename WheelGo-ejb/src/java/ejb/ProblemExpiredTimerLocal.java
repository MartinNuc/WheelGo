/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.Date;
import javax.ejb.Local;
import javax.ejb.Timer;
import model.Problem;

/**
 *
 * @author vlada
 */
@Local
public interface ProblemExpiredTimerLocal {

    void problemExpired(Timer timer);

    void initializeTimer(Date expiration, Problem problem);
    
}

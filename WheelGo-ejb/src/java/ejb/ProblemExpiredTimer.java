/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Problem;

/**
 *
 * @author vlada
 */
@Stateless
public class ProblemExpiredTimer implements ProblemExpiredTimerLocal {
    
    @PersistenceContext(unitName = "WheelGo-ejbPU")
    private EntityManager em;

    @Resource
    private SessionContext sessionContext;

    @Timeout
    @Override
    public void problemExpired(Timer timer) {
        Problem problem = (Problem)timer.getInfo();
        problem = em.merge(problem);
        problem.setDeleted(true);
        System.out.println("Problem " + problem + " is deleted.");
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void initializeTimer(Date expiration, Problem problem) {
        TimerService timerService = sessionContext.getTimerService();
        timerService.createTimer(expiration, problem);
        System.out.println("Timer was set to " + expiration + " to delete problem " + problem);
    }

    public void persist(Object object) {
        em.persist(object);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Report;

/**
 *
 * @author mist
 */
@Stateless
public class ReportFacade extends AbstractFacade<Report> implements ReportFacadeLocal {
    @PersistenceContext(unitName = "WheelGo-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReportFacade() {
        super(Report.class);
    }
    
}

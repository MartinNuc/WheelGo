/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package back;

import dto.ReportDTO;
import dto.RoleDTO;
import ejb.facades.interfaces.ReportFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author mist
 */
@Named(value = "reportsManagedBean")
@RequestScoped
@ManagedBean(name = "reports")
public class ReportsManagedBean {

    @EJB
    private ReportFacadeLocal reportFacade;
    
    
    private ReportDTO report;

    /**
     * Creates a new instance of ReportsManagedBean
     */
    public ReportsManagedBean() {
    }
    
    public List<ReportDTO> getReports() {
        return reportFacade.getAll();
    }
    
    public void removeReport(ReportDTO report) {
        reportFacade.remove(report);
    }
}

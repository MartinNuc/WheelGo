/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package back;

import dto.ReportDTO;
import ejb.facades.interfaces.ReportFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

/**
 *
 * @author mist
 */
@Named(value = "reportsManagedBean")
@RequestScoped
@ManagedBean(name = "reports")
public class ReportsManagedBean {

    float lowLatitude=0;
    float upLatitude=1;
    float lowLongitude=0;
    float upLongitude=1;
    
    @EJB
    private ReportFacadeLocal reportFacade;
    
    /**
     * Creates a new instance of ReportsManagedBean
     */
    public ReportsManagedBean() {
    }

    public List<ReportDTO> getReports() {
        return reportFacade.getAll();
    }
    
    public List<ReportDTO> getReportsArea() {
        return reportFacade.getArea(lowLatitude, upLatitude, lowLongitude, upLongitude, 100);
    }
    
    public void removeReport(ReportDTO report) {
        reportFacade.remove(report);
    }

    public float getLowLatitude() {
        return lowLatitude;
    }

    public void setLowLatitude(float lowLatitude) {
        this.lowLatitude = lowLatitude;
    }

    public float getLowLongitude() {
        return lowLongitude;
    }

    public void setLowLongitude(float lowLongitude) {
        this.lowLongitude = lowLongitude;
    }

    public float getUpLatitude() {
        return upLatitude;
    }

    public void setUpLatitude(float upLatitude) {
        this.upLatitude = upLatitude;
    }

    public float getUpLongitude() {
        return upLongitude;
    }

    public void setUpLongitude(float upLongitude) {
        this.upLongitude = upLongitude;
    }
    
    public String updateCoordinates()
    {
        return "reportAreaView";
    }
        
}

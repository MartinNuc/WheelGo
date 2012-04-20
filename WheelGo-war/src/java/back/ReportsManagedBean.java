/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package back;

import dto.PhotoDTO;
import dto.ReportDTO;
import ejb.facades.interfaces.LogFacadeLocal;
import ejb.facades.interfaces.PhotoFacadeLocal;
import ejb.facades.interfaces.ReportFacadeLocal;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import wrappers.ReportWrapper;

/**
 *
 * @author mist
 */
@Named(value = "reportsManagedBean")
@RequestScoped
@ManagedBean(name = "reports")
public class ReportsManagedBean {

    float lowLatitude = 0;
    float upLatitude = 1;
    float lowLongitude = 0;
    float upLongitude = 1;
    @EJB
    private ReportFacadeLocal reportFacade;
    @EJB
    private PhotoFacadeLocal photoFacade;
    @EJB
    private LogFacadeLocal logFacade;

    /**
     * Creates a new instance of ReportsManagedBean
     */
    public ReportsManagedBean() {
    }

    public List<ReportWrapper> getReports() {
        List<ReportWrapper> list = new ArrayList<ReportWrapper>();
        List<ReportDTO> allReports = reportFacade.getAll();
        if (allReports != null) {
            for (ReportDTO r : allReports) {
                list.add(new ReportWrapper(r, photoFacade, logFacade));
                System.out.println("Reports.photos=" + r.getPhotosCollection());
            }
        }
        return list;
    }

    public List<ReportWrapper> getReportsArea() {
        List<ReportWrapper> list = new ArrayList<ReportWrapper>();
        List<ReportDTO> allReports = reportFacade.getArea(lowLatitude, upLatitude, lowLongitude, upLongitude, 100);
        if (allReports != null) {
            for (ReportDTO r : allReports) {
                list.add(new ReportWrapper(r, photoFacade, logFacade));
            }
        }
        return list;
    }

    public void removeReport(ReportWrapper report) {
        reportFacade.remove(report.getDto());
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

    public String updateCoordinates() {
        return "reportAreaView";
    }

    /**
     * TODO: Ani jeden zpusob vykreslovani fotky nefunguje.
     */
    public StreamedContent drawPhoto(Integer photoId) throws IOException {
        PhotoDTO p = photoFacade.find(photoId);
        if (p != null) {
            return new DefaultStreamedContent(new ByteArrayInputStream(p.getImage()), "image/jpeg");
        } else {
            return null;
        }
    }

    public StreamedContent drawPhoto2() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String photoId = externalContext.getRequestParameterMap().get("photo_id");
        if (photoId == null || photoId.equals("")) {
            return null;
        } else {
            int parsedId = Integer.parseInt(photoId);
            if (parsedId < 0) {
                return null;
            }
            PhotoDTO p = photoFacade.find(parsedId);
            return new DefaultStreamedContent(new ByteArrayInputStream(p.getImage()), "image/png");
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wrappers;

import dto.LogDTO;
import dto.PhotoDTO;
import dto.ReportDTO;
import ejb.facades.interfaces.LogFacadeLocal;
import ejb.facades.interfaces.PhotoFacadeLocal;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.imageio.ImageIO;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author mist
 */
public class ReportWrapper {
        
    private PhotoFacadeLocal photoFacade;
    private LogFacadeLocal logFacade;
    
    private Integer idReport;
    private String name;
    private Date date;
    private float latitude;
    private float longitude;
    private String describtion;
    private Collection<Integer> photosCollection;
    private Collection<PhotoDTO> photos;
    private Collection<Integer> logsCollection;
    private Collection<LogDTO> logs;

    public ReportWrapper(ReportDTO report,PhotoFacadeLocal photoFacade, LogFacadeLocal logFacade) {
        this.photoFacade = photoFacade;
        this.logFacade = logFacade;
        
        idReport = report.getIdReport();
        name = report.getName();
        date = report.getDate();
        latitude = report.getLatitude();
        longitude = report.getLongitude();
        describtion = report.getDescribtion();
        
        photosCollection = report.getPhotosCollection();
        logsCollection = report.getLogsCollection();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public Integer getIdReport() {
        return idReport;
    }

    public void setIdReport(Integer idReport) {
        this.idReport = idReport;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public Collection<LogDTO> getLogs() {
        if (logsCollection == null) {
            return null;
        }
        Collection<LogDTO> output = new ArrayList<LogDTO>();
        while (logs.iterator().hasNext())
            output.add(logFacade.find(logs.iterator().next()));
        return output;
    }

    public void setLogs(Collection<LogDTO> logs) {
        if (logs == null) {
            this.logs = null;
            this.logsCollection = null;
            return;
        }
        logsCollection = new ArrayList<Integer>();
        while (logs.iterator().hasNext())
            logsCollection.add(logs.iterator().next().getIdLog());
        
        this.logs = logs;
    }

    public Collection<Integer> getLogsCollection() {
        return logsCollection;
    }

    public void setLogsCollection(Collection<Integer> logsCollection) {
        this.logsCollection = logsCollection;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<PhotoDTO> getPhotos() {
        if (photosCollection == null) {
            return null;
        }
        Collection<PhotoDTO> output = new ArrayList<PhotoDTO>();
        while (photos.iterator().hasNext())
            output.add(photoFacade.find(photos.iterator().next()));
        return output;
    }

    public void setPhotos(Collection<PhotoDTO> photos) {
        if (photos == null) {
            this.photos = null;
            this.photosCollection = null;
            return;
        }
        photosCollection = new ArrayList<Integer>();
        while (photos.iterator().hasNext())
            photosCollection.add(photos.iterator().next().getIdPhoto());
        
        this.photos = photos;
    }

    public Collection<Integer> getPhotosCollection() {
        return photosCollection;
    }

    public void setPhotosCollection(Collection<Integer> photosCollection) {
        this.photosCollection = photosCollection;
    }
    
    public ReportDTO getDto() {
        ReportDTO dto = new ReportDTO();
        dto.setDate(date);
        dto.setDescribtion(describtion);
        dto.setIdReport(idReport);
        dto.setLatitude(latitude);
        dto.setLongitude(longitude);
        dto.setLogsCollection(logsCollection);
        dto.setPhotosCollection(photosCollection);
        dto.setName(name);
        return dto;
    }
    
    public StreamedContent getFirstPhoto() throws IOException {
        
        if (photosCollection == null || photosCollection.isEmpty()) {
            return null;
        }
        PhotoDTO p = photoFacade.find(photosCollection.iterator().next());
        //StreamedContent graphicText = new DefaultStreamedContent(new ByteArrayInputStream(p.getImage()), "image/jpeg");   
        StreamedContent graphicText;  
            BufferedImage bufferedImg = new BufferedImage(100, 25, BufferedImage.TYPE_INT_RGB);  
            Graphics2D g2 = bufferedImg.createGraphics();  
            g2.drawString("This is a text", 0, 10);  
            ByteArrayOutputStream os = new ByteArrayOutputStream();  
            ImageIO.write(bufferedImg, "png", os);  
            graphicText = new DefaultStreamedContent(new ByteArrayInputStream(os.toByteArray()), "image/png"); 
        return graphicText;
    }
    
    public boolean hasPhoto()
    {
        if (photosCollection != null && photosCollection.isEmpty() == false)
            return true;
        else
            return false;
    }
}

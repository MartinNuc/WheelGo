/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author mist
 */
public class ReportDTO implements Serializable
{
    private Integer idReport;
    private String name;
    private long date;
    private double latitude;
    private double longitude;
    private String description;
    private Collection<Integer> photosCollection;
    private Collection<Integer> logsCollection;
    private boolean deleted;
    
    /**
     * 0 - non defined
     * 1 - problem
     * 2 - tip
     * 3 - place
     */
    private int type; 
    

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getDate()
    {
        return this.date;
    }
    
    public Date getDateDate() {
        Date ret = new Date(this.date);
        return ret;
    }

    public void setDate(Date date) {
        this.date = date.getTime();
    }
    
    public void setDate(long date)
    {
        this.date = date;
    }

    public String getDescribtion() {
        return description;
    }

    public void setDescribtion(String describtion) {
        this.description = describtion;
    }


    public Integer getIdReport() {
        return idReport;
    }

    public void setIdReport(Integer idReport) {
        this.idReport = idReport;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Collection<Integer> getLogsCollection() {
        return logsCollection;
    }

    public void setLogsCollection(Collection<Integer> logsCollection) {
        this.logsCollection = logsCollection;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Integer> getPhotosCollection() {
        return photosCollection;
    }

    public void setPhotosCollection(Collection<Integer> photosCollection) {
        this.photosCollection = photosCollection;
    }

    /**
     * @return the deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * @param deleted the deleted to set
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    
}

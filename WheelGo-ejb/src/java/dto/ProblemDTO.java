/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import anotations.DtoConnection;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author mist
 */
public class ProblemDTO implements Serializable
{
    /* problem */
    private Date expiration;
    
    /* report */
    private Integer idReport;
    private String name;
    private Date date;
    private float latitude;
    private float longitude;
    private String describtion;
    @DtoConnection(entity = "model.Photo")
    private Collection<Integer> photosCollection;
    @DtoConnection(entity = "model.Log")
    private Collection<Integer> logsCollection;

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

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
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

    public Collection<Integer> getPhotosCollection() {
        return photosCollection;
    }

    public void setPhotosCollection(Collection<Integer> photosCollection) {
        this.photosCollection = photosCollection;
    }
}

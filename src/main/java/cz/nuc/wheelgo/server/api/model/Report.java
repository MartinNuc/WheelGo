package cz.nuc.wheelgo.server.api.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: mist
 * Date: 13.11.13
 * Time: 18:05
 */
@Entity
public abstract class Report {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    protected Long id;
    @Basic(optional = false)
    @Size(min = 1, max = 64)
    @Column(name = "name", nullable = false, length = 64)
    protected String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected java.util.Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "latitude", nullable = false)
    protected double latitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "longitude", nullable = false)
    protected double longitude;
    @Size(max = 256)
    @Column(name = "description", length = 256)
    protected String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mist
 */
@Entity
@Table(name = "photo")
@XmlRootElement
public class Photo extends AbstractModel {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPhoto", nullable = false)
    private Integer idPhoto;
    @Size(max = 256)
    @Column(name = "url", length = 256)
    private String url;
    @JoinColumn(name = "idReport", referencedColumnName = "idReport", nullable = false)
    @ManyToOne(optional = 
            false)
    private Report report;
    @Lob
    @Basic
    @Column(columnDefinition = "BLOB NOT NULL")
    private byte[] image;

    public Photo() {
    }

    public Photo(Integer idPhoto) {
        this.idPhoto = idPhoto;
    }

    @Override
    public Integer getId() {
        return idPhoto;
    }

    public void setId(Integer idPhoto) {
        this.idPhoto = idPhoto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }


    /**
     * @return the pictureData
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * @param image the pictureData to set
     */
    public void setImage(byte[] image) {
        this.image = image;
    }
}

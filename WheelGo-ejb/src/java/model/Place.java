/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mist
 */
@Entity
@Table(name = "place")
@PrimaryKeyJoinColumn(name="idPlace")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Place.findAll", query = "SELECT p FROM Place p"),
    @NamedQuery(name = "Place.findByAccesibility", query = "SELECT p FROM Place p WHERE p.accesibility = :accesibility")})
public class Place extends Report implements Serializable {
    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Column(name = "idPlace", nullable = false)
    private Integer idPlace;

    public Integer getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(Integer idPlace) {
        this.idPlace = idPlace;
    }
    @Basic(optional = false)
    @NotNull
    @Column(name = "accesibility", nullable = false)
    private int accesibility;

    public Place() {
    }


    public int getAccesibility() {
        return accesibility;
    }

    public void setAccesibility(int accesibility) {
        this.accesibility = accesibility;
    }

}

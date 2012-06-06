/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mist
 */
@Entity
@Table(name = "tip")
@PrimaryKeyJoinColumn(name = "idTip")
@XmlRootElement
public class Tip extends Report implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "idTip", nullable = false)
    private Integer idTip;
    
    public Tip() {
    }
    
    public Tip(Report src) {
        super(src);
    }
    
    public Tip(Integer idTip) {
        this.idTip = idTip;
    }
    
    @Override
    public Integer getId() {
        return idTip;
    }
    
    public void setId(Integer idTip) {
        this.idTip = idTip;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mist
 */
@Entity
@Table(name = "problem")
@PrimaryKeyJoinColumn(name="idProblem")
@XmlRootElement
public class Problem extends Report implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(name = "idProblem", nullable = false)
    private Integer idProblem;
    @Column(name = "expiration")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiration;

    public Problem() {
    }
    
    public Problem(Report src) {
        super(src);
    }

    public Problem(Integer idProblem) {
        this.idProblem = idProblem;
    }

    public Integer getId() {
        return idProblem;
    }

    public void setId(Integer idProblem) {
        this.idProblem = idProblem;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }    
}

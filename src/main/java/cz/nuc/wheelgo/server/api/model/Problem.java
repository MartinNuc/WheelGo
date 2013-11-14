package cz.nuc.wheelgo.server.api.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: mist
 * Date: 13.11.13
 * Time: 18:04
 */
@Entity
public class Problem extends Report {

    /**
     * Default value included to remove warning. Remove or modify at will. *
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "expiration")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiration;

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
}

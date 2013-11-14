/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author mist
 */
public class ProblemDTO extends ReportDTO  implements Serializable 
{
    /* problem */
    private long expiration;

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }

    public Date getExpirationDate() {
        Date ret = new Date(expiration);
        return ret;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration.getTime();
    }
    
    public ProblemDTO()
    {
        setType(ReportType.PROBLEM);
    }

}

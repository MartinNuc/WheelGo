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
public class ProblemDTO extends ReportDTO
{
    /* problem */
    private Date expiration;

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

}

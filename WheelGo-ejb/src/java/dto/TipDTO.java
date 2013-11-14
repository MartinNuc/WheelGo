/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author mist
 */
public class TipDTO extends ReportDTO  implements Serializable {    
    public TipDTO()
    {
        setType(ReportType.TIP);
    }
}

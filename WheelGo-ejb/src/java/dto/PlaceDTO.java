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
public class PlaceDTO extends ReportDTO  implements Serializable  {
  
    /* place */
    private int accesibility;
    
    public int getAccesibility() {
        return accesibility;
    }

    public void setAccesibility(int accesibility) {
        this.accesibility = accesibility;
    }  
    
    public PlaceDTO()
    {
        setType(ReportType.PLACE);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.facades.interfaces;

import dto.ReportDTO;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;

/**
 *
 * @author mist
 */
@Local
public interface ReportFacadeLocal {

    void create(ReportDTO report);
    //@RolesAllowed({"admin"})
    void edit(ReportDTO report);
    //@RolesAllowed({"admin"})
    void remove(ReportDTO report);
    ReportDTO find(Object id);
    List<ReportDTO> getAll();
    List<ReportDTO> getAllWithDeleted();
    List<ReportDTO> getRange(int[] range);
    int count();
    
    List<ReportDTO> getArea(double latLowerBound, double latUpperBound, double longLowerBound, double longUpperBound, int maxCount);

}

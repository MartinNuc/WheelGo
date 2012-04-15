/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.ReportDTO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mist
 */
@Local
public interface ReportFacadeLocal {

    void create(ReportDTO report);
    void edit(ReportDTO report);
    void remove(ReportDTO report);
    ReportDTO find(Object id);
    List<ReportDTO> getAll();
    List<ReportDTO> getRange(int[] range);
    int count();
    
}

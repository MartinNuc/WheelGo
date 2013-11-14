/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.facades.interfaces;

import dto.Location;
import java.util.List;
import javax.ejb.Local;
import navigation.Node;

/**
 *
 * @author mist
 */
@Local
public interface NavigationFacadeLocal {

    List<Node> navigate(Location from, Location to);
    
}

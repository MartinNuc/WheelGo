/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package navigation;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import dto.Location;
import ejb.facades.interfaces.NavigationFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author mist
 */
@Stateless
@LocalBean
public class NavigationFacade implements NavigationFacadeLocal {

    public List<Node> navigate(Location from, Location to) {
        List<Node> ret = new ArrayList<Node>();
        
        Client client = Client.create();
        WebResource resource = client.resource("http://localhost:8888/api/navigate");
        // Get response as String
        String string = resource.path("1")
            .accept(MediaType.APPLICATION_XML)
                .get(String.class);
        System.out.println(string);
        
        Node node = new Node();
        node.setLatitude(from.getLatitude());
        node.setLongitude(from.getLongitude());
        node.setDescription("Start");
        ret.add(node);
        
        node = new Node();
        node.setLatitude(49.968312376930594);
        node.setLongitude(14.386311516233087);
        node.setDescription("Bezte pesky");
        ret.add(node);
        
        node = new Node();
        node.setLatitude(49.968399376930504);
        node.setLongitude(14.386388516233187);
        node.setDescription("Pokracujte na kole");
        ret.add(node);        
        
        node = new Node();
        node.setLatitude(to.getLatitude());
        node.setLongitude(to.getLongitude());
        node.setDescription("Cil");
        ret.add(node);
        
        return ret;
    }

    

}

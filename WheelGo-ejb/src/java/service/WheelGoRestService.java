/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ejb.facades.interfaces.NavigationFacadeLocal;
import ejb.facades.interfaces.ProblemFacadeLocal;
import ejb.facades.interfaces.ReportFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author mist
 */
@Path("/hello")
@Stateless()
public class WheelGoRestService {
    
    @EJB
    ReportFacadeLocal reports;
    @EJB
    ProblemFacadeLocal problems; 
    @EJB
    NavigationFacadeLocal navigation;
    
    @GET
    @Produces("text/plain")
    public String hello()
    {
        return "ahoj";
    }
    /*
    public ProblemDTO getProblemDetail(Integer id) {
        return problems.find(id);
    }
    
    public List<ReportDTO> getReportsAround(Location location, double radius)
    {
        return reports.getArea(location.getLatitude()-radius, location.getLatitude()+radius, location.getLongitude()-radius, location.getLongitude()+radius, 50);
    }
    
    public Integer addProblem(ProblemDTO problem) {
        problems.create(problem);
        return 0;
    }
    
    public List<Node> navigate(Location from, Location to)
    {
        return navigation.navigate(from, to);
    }*/
    
}

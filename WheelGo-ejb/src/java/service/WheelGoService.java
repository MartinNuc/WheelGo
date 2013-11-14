/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dto.Location;
import dto.PhotoDTO;
import dto.PlaceDTO;
import dto.ProblemDTO;
import dto.ReportDTO;
import dto.TipDTO;
import ejb.facades.interfaces.NavigationFacadeLocal;
import ejb.facades.interfaces.PhotoFacadeLocal;
import ejb.facades.interfaces.PlaceFacadeLocal;
import ejb.facades.interfaces.ProblemFacadeLocal;
import ejb.facades.interfaces.ReportFacadeLocal;
import ejb.facades.interfaces.TipFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import navigation.Node;

/**
 *
 * @author mist
 */
@WebService(serviceName = "WheelGoService")
@Stateless()
public class WheelGoService {

    @EJB
    ReportFacadeLocal reports;
    @EJB
    ProblemFacadeLocal problems;  
    @EJB
    TipFacadeLocal tips;  
    @EJB
    PlaceFacadeLocal places;  
    @EJB
    PhotoFacadeLocal photos;  
    @EJB NavigationFacadeLocal navigation;
    
    @WebMethod
    public ReportDTO getReport(Integer id) {
        return reports.find(id);
    }
    
    @WebMethod
    public ProblemDTO getProblemDetail(Integer id) {
        return problems.find(id);
    }
    
    @WebMethod
    public TipDTO getTipDetail(Integer id) {
        return tips.find(id);
    }
    
    @WebMethod
    public PlaceDTO getPlaceDetail(Integer id) {
        return places.find(id);
    }
    
    @WebMethod
    public List<ReportDTO> getReportsAround(Location location, double radius)
    {
        return reports.getArea(location.getLatitude()-radius, location.getLatitude()+radius, location.getLongitude()-radius, location.getLongitude()+radius, 50);
    }
    
    @WebMethod
    public Integer addProblem(ProblemDTO problem) {
        problems.create(problem);
        return 0;
    }
    
    @WebMethod
    public Integer addTip(TipDTO tip) {
        tips.create(tip);
        return 0;
    }
    
    @WebMethod
    public Integer addPlace(PlaceDTO place) {
        places.create(place);
        return 0;
    }
    
    @WebMethod
    public PhotoDTO getImage(int id)
    {
        return photos.find(id);
    }
    
    @WebMethod
    public Integer deleteSpot(int id)
    {
        ReportDTO r = reports.find(id);
        reports.remove(r);
        return 0;
    }

    @WebMethod
    public Integer editProblem(ProblemDTO edited)
    {
        problems.edit(edited);
        return 0;
    }
    
    @WebMethod
    public Integer editTip(TipDTO edited)
    {
        tips.edit(edited);
        return 0;
    }
    
    @WebMethod
    public Integer editPlace(PlaceDTO edited)
    {
        places.edit(edited);
        return 0;
    }
    
    @WebMethod
    public List<Node> navigate(Location from, Location to)
    {
        return navigation.navigate(from, to);
    }
}

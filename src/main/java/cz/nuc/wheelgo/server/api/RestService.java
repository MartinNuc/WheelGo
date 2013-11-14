package cz.nuc.wheelgo.server.api;

import com.google.gson.Gson;
import cz.nuc.wheelgo.server.api.dto.Location;
import cz.nuc.wheelgo.server.api.dto.NavigationParameters;
import cz.nuc.wheelgo.server.api.dto.ProblemDto;
import cz.nuc.wheelgo.server.api.dto.Route;
import cz.nuc.wheelgo.server.api.model.Problem;
import cz.nuc.wheelgo.server.api.model.ReportRepository;
import org.jboss.resteasy.annotations.providers.jaxb.json.BadgerFish;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mist
 * Date: 10.10.13
 * Time: 3:19
 */
@Path("/api")
@Stateless
public class RestService {

    Gson gson = new Gson();

    @Inject
    WheelGoFacade facade;

    @Inject
    ReportRepository reportRepository;

    @GET
    @Produces("application/json")
    @Path("hello")
    @BadgerFish
    public String hello() {
        return "Ahoj";
    }

    @POST
    @Path("/navigate")
    @Produces("application/json")
    @BadgerFish
    public Route navigate(NavigationParameters navigationParameters)
    {
        List<Problem> problemsToAvoid = new LinkedList<Problem>();
        for(ProblemDto p : navigationParameters.problemsToAvoid)
        {
            problemsToAvoid.add(reportRepository.findProblemById(p.id));
        }
        return facade.navigate(new Location(navigationParameters.latitudeFrom, navigationParameters.longitudeFrom),
                new Location(navigationParameters.latitudeTo, navigationParameters.longitudeTo), problemsToAvoid);
    }

    @GET
    @Path("/problem")
    @Produces("application/json")
    @Consumes("application/json")
    @BadgerFish
    public List<ProblemDto> getProblemsAround(@QueryParam("lat") double latitude, @QueryParam("long") double longitude, @QueryParam("radius") double radius)
    {
        List<ProblemDto> problems = new LinkedList<ProblemDto>();
        for(Problem p : facade.getProblemsAround(latitude, longitude, radius))
        {
            problems.add(new ProblemDto(p));
        }

        return problems;
    }

    @POST
    @Path("problem")
    @Produces("application/json")
    @Consumes("application/json")
    @BadgerFish
    public boolean addProblem(ProblemDto problemDto)
    {
        Problem problem = new Problem();
        problem.setName(problemDto.name);
        problem.setExpiration(new Date(problemDto.expirationTimestamp));
        problem.setDate(new Date(problemDto.reportedTimestamp));
        problem.setDescription(problemDto.description);
        problem.setLatitude(problemDto.latitude);
        problem.setLongitude(problemDto.longitude);
        facade.createProblem(problem);
        return true;
    }
}

package cz.nuc.wheelgo.server.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cz.nuc.wheelgo.server.api.dto.*;
import cz.nuc.wheelgo.server.api.model.Problem;
import cz.nuc.wheelgo.server.api.model.ReportRepository;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mist
 * Date: 13.11.13
 * Time: 18:08
 */
@Stateless
public class WheelGoFacade {

    @Inject
    EntityManager em;

    @Inject
    ReportRepository reportRepository;

    public Route navigate(Location from, Location to, List<Problem> problemsToAvoid) {

        Route result = new Route();
        List<NavigationNode> route;

        // let google find path
        Integer resultId = null;
        try {
            ClientRequest request = new ClientRequest(
                    "http://1.wheelgo-navigator.appspot.com/api/navigate");
            request.queryParameter("latFrom", from.latitude);
            request.queryParameter("longFrom", from.longitude);
            request.queryParameter("latTo", to.latitude);
            request.queryParameter("longTo", to.longitude);
            List<Location> locationsToAvoid = new LinkedList<Location>();
            for(Problem p : problemsToAvoid)
            {
                locationsToAvoid.add(new Location(p.getLatitude(), p.getLongitude()));
            }
            Gson gson = new Gson();
            //request.formParameter("avoid", gson.toJson(locationsToAvoid));
            request.accept("application/json");
            request.body("application/json", gson.toJson(locationsToAvoid));
            ClientResponse<Integer> response = request.post(Integer.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            System.out.println("Output from Server .... " + response.getEntity());
            resultId = response.getEntity();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // get calculated path
        String jsonResponse;
        if (resultId == null) {
            return null;
        }
        try {
            // try 5 times
            ClientResponse<String> response = null;
            for (int i = 0; i < 5; i++) {
                Thread.sleep(2000);
                ClientRequest request = new ClientRequest(
                        "http://1.wheelgo-navigator.appspot.com/api/getResult");
                request.queryParameter("id", resultId);
                request.accept("application/json");
                JAXBContext jaxbContext = JAXBContext.newInstance(JaxbList.class);
                Unmarshaller um = jaxbContext.createUnmarshaller();
                response = request.get(String.class);
                if (response.getStatus() == 200) {
                    // found path
                    break;
                }
            }
            if (response == null) {
                return null;
            }
            System.out.println("Output from Server .... " + response.getEntity());
            Type listType = new TypeToken<List<NavigationNode>>() {
            }.getType();
            Gson gson = new Gson();
            result.path = gson.fromJson(response.getEntity(), listType);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // go through path and add nearby problems
        if (result.path == null)
        {
            return null;
        }

        HashMap<Long, ProblemDto> problems = new HashMap<Long, ProblemDto>();
        for (NavigationNode node : result.path) {
            for (Problem problem : getProblemsAround(node.latitude, node.longitude, 0.0006)) {
                problems.put(problem.getId(), new ProblemDto(problem));
            }
        }
        result.problems = new LinkedList<ProblemDto>(problems.values());

        return result;
    }

    public void createProblem(Problem problem) {
        em.joinTransaction();
        problem.setDate(new Date());
        em.persist(problem);
        em.flush();
    }

    public List<Problem> getProblemsAround(double latitude, double longitude, double radius) {
        return reportRepository.findProblemsAround(latitude - radius, latitude + radius, longitude - radius, longitude + radius);
    }
}

package cz.nuc.wheelgo.server.api.dto;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mist
 * Date: 13.11.13
 * Time: 18:11
 */
public class Route {
    public List<ProblemDto> problems = new LinkedList<ProblemDto>();
    public List<NavigationNode> path = new LinkedList<NavigationNode>();
}

package cz.nuc.wheelgo.server.api.dto;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mist
 * Date: 14.11.13
 * Time: 17:23
 */
public class NavigationParameters {
    public List<ProblemDto> problemsToAvoid;
    public double latitudeFrom;
    public double longitudeFrom;
    public double latitudeTo;
    public double longitudeTo;

}

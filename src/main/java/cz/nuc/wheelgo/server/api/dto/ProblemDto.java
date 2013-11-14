package cz.nuc.wheelgo.server.api.dto;

import cz.nuc.wheelgo.server.api.model.Problem;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: mist
 * Date: 13.11.13
 * Time: 18:12
 */
public class ProblemDto {
    public long id;
    public String description;
    public double latitude;
    public double longitude;
    public String name;
    public long expirationTimestamp;
    public long reportedTimestamp;

    public ProblemDto()
    {}

    public ProblemDto(Problem problem) {
        this.expirationTimestamp = problem.getExpiration().getTime();
        this.reportedTimestamp = problem.getDate().getTime();
        this.id = problem.getId();
        this.description = problem.getDescription();
        this.latitude = problem.getLatitude();
        this.longitude = problem.getLongitude();
        this.name = problem.getName();
    }
}

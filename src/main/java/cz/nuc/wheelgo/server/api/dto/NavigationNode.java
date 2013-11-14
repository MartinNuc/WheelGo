package cz.nuc.wheelgo.server.api.dto;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created with IntelliJ IDEA.
 * User: mist
 * Date: 13.11.13
 * Time: 18:09
 */
@XmlRootElement(name = "NavigationNode", namespace="")
@XmlType
public class NavigationNode {
    public double latitude;
    public double longitude;
    public String description;
    public int bearing = 0;
}

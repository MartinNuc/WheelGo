/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package navigation;

/**
 *
 * @author mist
 */
public class Node {
    private double latitude;
    private double longitude;
    private String description;
    private int bearing=0;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBearing() {
        return bearing;
    }

    public void setBearing(int bearing) {
        this.bearing = bearing;
    }
    
    
}

/*
    *Shon Bennett
    *Moon.java
    *This is the Moon class that acts a blueprint for making Moon objects
    *CMSC 255 Sect 901
    *December 2 2021
 */
public class Moon {
    //instance variables
    private String name;
    private double radius;
    private double density;
    private double distance;

    /**
     * Moon Default Constructor
     */
    public Moon(){
        name = "";
        radius = 0.0;
        density = 0.0;
        distance = 0.0;
    }

    /**
     *
     * @param newName
     * @param newRadius
     * @param newDensity
     * @param newDistance
     *
     * Paramterized Constructor
     */
    public Moon(String newName, double newRadius, double newDensity, double newDistance){
        name = newName;
        radius = newRadius;
        density = newDensity;
        distance = newDistance;
    }

    /**
     *
     * @return name instance variable
     */
    public String getName(){
        return name;
    }

    /**
     *
     * @return radius instance variable
     */
    public double getRadius(){
        return radius;
    }

    /**
     *
     * @return density instance variable
     */
    public double getDensity(){
        return density;
    }

    /**
     *
     * @return distance instance variable
     */
    public double getDistance(){
        return distance;
    }

    /**
     *
     * @param newName --- set name instance variable to newName
     */
    public void setName(String newName){
        name = newName;
    }

    /**
     *
     * @param newRadius --- set radius instance variable to newRadius parameter
     */
    public void setRadius(double newRadius){
        radius = newRadius;
    }

    /**
     *
     * @param newDensity --- set density instance variable to newDensity parameter
     */
    public void setDensity(double newDensity){
        density = newDensity;
    }

    /**
     *
     * @param newDistance --- set distance instance variable to newDistance parameter
     */
    public void setDistance(double newDistance){
        distance = newDistance;
    }

    @Override
    /**
     * returns a String describing the Moon object
     */
    public String toString(){
        String returnRadius = String.format("%.2f", radius);
        String returnDensity = String.format("%.2f", density);
        String returnDistance = String.format("%.2f", distance);
        return name + " " + returnRadius + " " + returnDensity + " " + returnDistance;
    }

}

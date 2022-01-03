/*
 * Development.java
 *
 * This is the Development class file, which is used to model developments full of houses.
 *
 * Shon Bennett
 * November 11, 2021
 * CMSC 255 Sect 901
 */
package Project6;

import java.util.ArrayList;

public class Development{
    /**
     * Development class instance variables
     */
    //instance variables (private data)
    private String name;
    private String location;
    private int yearEst;
    private int numLots;
    private ArrayList<House> houses;

    /**
     * Development class default constructor --- sets the name and location instance variables to an empty String,  yearEst and numLots to 0
     * and creates an empty House ArrayList for the houses instance variable
     */
    public Development(){
        name = "";
        location = "";
        yearEst = 0;
        numLots = 0;
        houses = new ArrayList<House>();
    }

    /**
     *
     * @param aName
     * @param aLocation
     * @param aYearEst
     * @param aNumLots
     *
     * Parameterized Development constructor
     */
    public Development(String aName, String aLocation, int aYearEst, int aNumLots){
        name = aName;
        location = aLocation;
        yearEst = aYearEst;
        numLots = aNumLots;
        houses = new ArrayList<House>();
    }

    /**
     *
     * @return the name instance variable
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return the location instance variable
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @return the yearEst instance variable
     */
    public int getYearEst() {
        return yearEst;
    }

    /**
     *
     * @return the numLots instance variable
     */
    public int getNumLots() {
        return numLots;
    }

    /**
     *
     * @param newName
     * set name instance variable to newName
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     *
     * @param newLocation
     * set location instance variable to newLocation
     */
    public void setLocation(String newLocation) {
        location = newLocation;
    }

    /**
     *
     * @param newYearEst
     * set yearEst instance variable to newYearEst
     */
    public void setYearEst(int newYearEst) {
        yearEst = newYearEst;
    }

    /**
     *
     * @param newNumLots
     * set numLots instance variable to newNumLots
     */
    public void setNumLots(int newNumLots) {
        numLots = newNumLots;
    }

    /**
     *
     * @param houseObj
     * adds houseObj to houses instance variable's House ArrayList
     */
    public void addHouse(House houseObj){
        houses.add(houseObj);
    }

    /**
     *
     * @return the number of Houses associated with a Development object
     */
    public int getNumHouses() {
        //this is supposed to be a for each loop that traverses ArrayList and counts amount of
        //objs in list via a count variable
        int count = 0;
        for (int i = 0; i < houses.size(); i++) {
            count++;
        }
        return count;
    }

    /**
     *
     * @return the House ArrayList store in houses
     */
    public ArrayList<House> getHouses(){
        return houses;
    }

    /**
     *
     * @return a String describing the Development object
     */
    @Override
    public String toString() {

        String developmentString = "";
        //for each loop to call each house object with a toString() method
        for (int i = 0; i < houses.size(); i++) {
           developmentString += houses.get(i).toString();
        }
        //returns the Development's header information and the House toString method of each House stored in the houses ArrayList
        return name + "\n" + location + "\n" + yearEst + "\n" + numLots + "\n" + "Houses:\n" + developmentString;
    }

}

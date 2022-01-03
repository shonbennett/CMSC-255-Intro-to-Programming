/*
 * House.java
 *
 * This is the House class file, which is used to create House objects. This class also has accessor and mutator methods
 * for all the instance variables, as well as a toString method that describes the House object
 *
 * Shon Bennett
 * November 11, 2021
 * CMSC 255 Sect 901
 */

package Project6;


public class House {
    /**
     * House class instance variables
     */
    private String owner;
    private int lotNumber;
    private double squareFootage;

    //these instance variables are enumerated types
    private Bedrooms bedrooms;
    private Baths baths;
    private Color color;

    /**
     * Default constructor of House class
     */
    public House(){
        owner = "";
        lotNumber = 1;
        squareFootage = 500.00;
        bedrooms = Bedrooms.ONE_BEDROOM;
        baths = Baths.ONE;
        color = Color.WHITE;

    }

    /**
     *
     * @param anOwner
     * @param aLotNumber
     * @param aSquareFootage
     * @param aBedrooms
     * @param aBaths
     * @param aColor
     *
     * Parameterized House constructor
     */
    public House(String anOwner, int aLotNumber, double aSquareFootage, Bedrooms aBedrooms, Baths aBaths, Color aColor){
        owner = anOwner;
        lotNumber = aLotNumber;
        squareFootage = aSquareFootage;
        bedrooms = aBedrooms;
        baths = aBaths;
        color = aColor;
    }

    /**
     *
     * @return owner instance variable
     */
    public String getOwner(){
        return owner;
    }

    /**
     *
     * @param newOwner
     * set owner instance variable to newOwner param
     */
    public void setOwner(String newOwner){
        owner = newOwner;
    }

    /**
     *
     * @return the lotNumber instance variable
     */
    public int getLotNumber() {
        return lotNumber;
    }

    /**
     *
     * @return the squareFootage instance variable
     */
    public double getSquareFootage() {
        return squareFootage;
    }

    /**
     *
     * @return the bedrooms instance variable
     */
    public Bedrooms getBedrooms() {
        return bedrooms;
    }

    /**
     *
     * @return the baths instance variable
     */
    public Baths getBaths() {
        return baths;
    }

    /**
     *
     * @return the color instance variable
     */
    public Color getColor() {
        return color;
    }

    /**
     *
     * @param newLotNumber
     * set lotNumber instance varaible to newLotNumber param
     */
    public void setLotNumber(int newLotNumber) {
        lotNumber = newLotNumber;
    }

    /**
     *
     * @param newSquareFootage
     * set squareFootage instance variable to newSquareFootage param
     */
    public void setSquareFootage(double newSquareFootage) {
        squareFootage = newSquareFootage;
    }

    /**
     *
     * @param newBedroom
     * set the bedrooms instance variable to the newBedroom param
     */
    public void setBedrooms(Bedrooms newBedroom) {
        bedrooms = newBedroom;
    }

    /**
     *
     * @param newBathroom
     * set baths instance variable to newBathroom param
     */
    public void setBaths(Baths newBathroom) {
        baths = newBathroom;
    }

    /**
     *
     * @param newColor
     * set color instance variable to newColor param
     */
    public void setColor(Color newColor) {
        color = newColor;
    }

    /**
     *
     * @return String describing the House object
     */
    @Override
    //TA Brianna taught me another way to use formatted Strings via the String class' .format() method
    //with the .format() method, we can actually store formatted Strings into variables
    public String toString() {
        String returnString = String.format("%.2f", squareFootage);
        return "\n\t" + owner + "\n\t" + lotNumber + "\n\t" + returnString + "\n\t" + bedrooms + "\n\t" + baths + "\n\t" + color + "\n";
    }

}

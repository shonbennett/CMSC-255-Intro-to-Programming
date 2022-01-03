/*
    *Shon Bennett
    *MoonStudy.java
    *This is the MoonStudy java file, which opens files, makes Moon objects, finds means using findMean(),
     finds highest values with findHighValue() and findMeanMoon(), and finds lowest moons via findLowestMoons(),
     and deals with PrintWriter objects via three outputToFile methods
    *CMSC 255 Sect 901
    *December 2 2021
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class MoonStudy {

    public static void main(String[] args){

        //create input and output files
        File input = new File(args[0]);
        File output = new File(args[1]);
        ArrayList<String> stringArray = new ArrayList<String>();

        //we must place the openFile method in a try/catch
        try{
            stringArray = openFile(input);
            System.out.println("Input file correct");
        } catch (Exception e) {
            System.out.println("Incorrect input filename");
        }

        //call MoonStudy's methods using results from openFile stored in stringArray
       ArrayList<Moon> moonList = createObjects(stringArray);
       findMean(moonList, MoonAttributes.RADIUS);
       findMeanMoon(moonList, MoonAttributes.DISTANCE, 999);
    }

    public static ArrayList<String> openFile(File inputFile) throws FileNotFoundException {
        Scanner input = new Scanner(inputFile);
        ArrayList<String> stringArray = new ArrayList<String>();
        while (input.hasNextLine()) {
            //adds the line as a single String element in the String ArrayList
            stringArray.add(input.nextLine());
        }
        return stringArray;

    }

    public static ArrayList<Moon> createObjects(ArrayList<String> lines) {

        //ArrayList<String[]> tempArray = new ArrayList<String[]>();
        ArrayList<Moon> moonList = new ArrayList<Moon>();

        for (int index = 0; index < lines.size(); index++) {
            String[] tempArray = lines.get(index).split("\t");

            //create a new Moon obj that we can add to the moonList Moon ArrayList we will return at the end of this method
            Moon moonObj = new Moon();
            moonObj.setName(tempArray[0]);

            //TEST IF SECOND ELEMENT IS VALID RADIUS
            try {
                if (Double.parseDouble(tempArray[1]) >= 0.0){
                    moonObj.setRadius(Double.parseDouble(tempArray[1]));
                }
            } catch (NumberFormatException exception) {
                double newRadius = 0.0;
                moonObj.setRadius(newRadius);
            }


            //TEST IF THIRD ELEMENT IS A VALID DENSITY
            try {
                if (Double.parseDouble(tempArray[2]) >= 0.0){
                    moonObj.setDensity(Double.parseDouble(tempArray[2]));
                }
            } catch (NumberFormatException exception) {
                double newDensity = 0.0;
                moonObj.setDensity(newDensity);
            }

            //TEST IS FOURTH ELEMENT IS A VALID DISTANCE
            try {
                if (Double.parseDouble(tempArray[3]) >= 0.0){
                    moonObj.setDistance(Double.parseDouble(tempArray[3]));
                }
            } catch (NumberFormatException exception) {
                double newDistance = 0.0;
                moonObj.setDistance(newDistance);
            }

            //adds the moonObj Moon to the ArrayList of moons we will return
            moonList.add(moonObj);
        }
        return moonList;


    }


    public static double findMean(ArrayList<Moon> moons, MoonAttributes attribute) {

        double average = 0;
        int count = 0;
	/*
		create if statement to see which attribute we will be assessing. Then
		within each if statement we have a for loop to grab the attribute of
		each Moon object in the moons ArrayList

*/
        if (attribute == MoonAttributes.RADIUS) {

            for (int i = 0; i < moons.size(); i++) {
                average += moons.get(i).getRadius();
                count++;
            }
        } else if (attribute == MoonAttributes.DENSITY) {
            for (int i = 0; i < moons.size(); i++) {
                average += moons.get(i).getDensity();
                count++;
            }
        } else if (attribute == MoonAttributes.DISTANCE) {
            for (int i = 0; i < moons.size(); i++) {
                average += moons.get(i).getDistance();
                count++;
            }
        }
        average = average / count;
        return average;


    }

    public static double findHighValue(ArrayList<Moon> moons, MoonAttributes attribute) {

        /*
	If statements set up to see which attribute we are testing. Then inside there is a for loop that checks for the highest max
*/
        double max = 0.0;
        if (attribute == MoonAttributes.RADIUS) {
            max = moons.get(0).getRadius();
            for (int i = 0; i < moons.size(); i++) {
                if (moons.get(i).getRadius() > max) {
                    max = moons.get(i).getRadius();
                }
            }
        } else if (attribute == MoonAttributes.DENSITY) {
            max = moons.get(0).getDensity();
            for (int i = 0; i < moons.size(); i++) {
                if (moons.get(i).getDensity() > max) {
                    max = moons.get(i).getDensity();
                }
            }
        } else if (attribute == MoonAttributes.DISTANCE) {
            max = moons.get(0).getDistance();
            for (int i = 0; i < moons.size(); i++) {
                if (moons.get(i).getDistance() > max) {
                    max = moons.get(i).getDistance();
                }
            }

        }
        return max;
    }

    public static Moon findMeanMoon(ArrayList<Moon> moons, MoonAttributes attribute, double meanValue) {
        //create meanMoon Moon variable that will be returned at the end of this method
        Moon meanMoon = new Moon();


        ArrayList<Double> tempArray = new ArrayList<Double>();
        tempArray.add(meanValue);
        double closestValue = tempArray.get(0);

        //series of if statements to determine which MoonAttribute we are about to deal with
        if (attribute == MoonAttributes.RADIUS) {
            for (int i = 0; i < moons.size(); i++) {
                tempArray.add(moons.get(i).getRadius());
            }
        } else if (attribute == MoonAttributes.DENSITY) {
            for (int i = 0; i < moons.size(); i++) {
                tempArray.add(moons.get(i).getDensity());
            }
        } else if (attribute == MoonAttributes.DISTANCE) {
            for (int i = 0; i < moons.size(); i++) {
                tempArray.add(moons.get(i).getDistance());
            }
        }

        //this calls the Collection class' sort method (which is given to us via Java API),
        // which will sort the entire array into ascending order
        //the Collection class' sort method sorts the tempArray into ascending order
        Collections.sort(tempArray);

        //now we will find the location of the meanValue
        int meanValueLocation = 0;
        for (int i = 0; i < tempArray.size(); i++) {
            if (tempArray.get(i) == meanValue) {
                //meanValueLocation = tempArray.get(0);
                meanValueLocation = i;
            }
        }

        //now we will compare the number to left and right and see which is closer to meanValue
        double leftNumber = tempArray.get(0);
        if (meanValueLocation - 1 >= 0) {
            leftNumber = tempArray.get(meanValueLocation - 1);
        }

        double rightNumber = 0.0;
        if (meanValueLocation + 1 < tempArray.size()) {
            rightNumber = tempArray.get(meanValueLocation + 1);
        }

        if ((rightNumber - meanValue) < (meanValue - leftNumber)){
            closestValue = rightNumber;
        } else {
            closestValue = leftNumber;
        }


        //based on attribute we first chose, we will search the moon object that
        // matches the closestValue in the selected attribute, and return it
        if (attribute == MoonAttributes.RADIUS) {
            for (int i = 0; i < moons.size(); i++) {
                if (moons.get(i).getRadius() == closestValue) {
                    //return moons.get(i);
                    meanMoon = moons.get(i);
                }
            }
        }
        else if (attribute == MoonAttributes.DENSITY) {
                for (int i = 0; i < moons.size(); i++) {
                    if (moons.get(i).getDensity() == closestValue) {
                        //return moons.get(i);
                        meanMoon = moons.get(i);
                    }
                }
            } else if (attribute == MoonAttributes.DISTANCE) {
                for (int i = 0; i < moons.size(); i++) {
                    if (moons.get(i).getDistance() == closestValue) {
                        //return moons.get(i);
                        meanMoon = moons.get(i);
                    }
                }

            }
        return meanMoon;

        }

    public static ArrayList<Moon> findLowestMoons(ArrayList<Moon> moons, double value, MoonAttributes attribute){

        ArrayList<Moon> leastMoons = new ArrayList<Moon>();

        //we use a series of if statements to determine the appropriate MoonAttribute to use
        //and then we find the lowest value within the given attributes from moon objects
        if (attribute == MoonAttributes.RADIUS){
            for(int i = 0; i < moons.size(); i++){
                if (moons.get(i).getRadius() < value){
                    leastMoons.add(moons.get(i));
                }
            }
        }
        else if (attribute == MoonAttributes.DENSITY){
            for(int i = 0; i < moons.size(); i++){
                if (moons.get(i).getDensity() < value){
                    leastMoons.add(moons.get(i));
                }
            }
        }
        else if (attribute == MoonAttributes.DISTANCE){
            for(int i = 0; i < moons.size(); i++){
                if (moons.get(i).getDistance() < value){
                    leastMoons.add(moons.get(i));
                }
            }
        }
        return leastMoons;


    }


    //THESE ARE THE OUTPUT METHODS

    //OUTPUT FOR MOON ARRAYLISTS
    public static void outputToFile(String outputMessage, ArrayList<Moon> moons, PrintWriter out){

        out.print(outputMessage);

            for (int i = 0; i < moons.size() - 1; i++){

                out.print(moons.get(i).toString() + " ");

            }
            out.println(moons.get(moons.size() - 1).toString() + " ");
            out.println("");
            out.print("");

    }

    //OUTPUT FOR MOON OBJECTS
    public static void outputToFile(String outputMessage, Moon moon, PrintWriter out){

        out.println(outputMessage + moon.toString());
        out.println("");
        out.print("");


    }

    //OUTPUT FOR DOUBLE VALUES
    public static void outputToFile(String outputMessage, double value, PrintWriter out){

        String formattedDouble = String.format("%.2f", value);
        out.println(outputMessage + formattedDouble);
        out.println("");
        out.print("");

    }


}





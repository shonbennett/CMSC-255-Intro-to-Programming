/*
 * MoonSearch.java
 *
 * This program takes in multiple arrays, and determines the average and highest radius of the moons,
 * average density of the moons, and lowest distance from the moons. This program also determines the highest and lowest moons.
 *
 *
 * Shon Bennett
 * October 17, 2021
 * CMSC 255 Sect 901
 */
import java.util.Scanner;
public class MoonSearch {
    public static void main(String[] args){
        //Scanner object is created in order to recieve user inputs later in the program
        Scanner input = new Scanner(System.in);
        //the calcAvg method is called, using the moonRadii array as an argument for the method
        double[] moonRadii = {11.3,6.2,68.9,33.6,71.2,26.8,255.9,47.4};
        double radiiAvg = calcAvg(moonRadii);
        System.out.printf("The average radius is: %,.1f",radiiAvg);
        System.out.println();

        //the calcAvg method is called again. This time the density array is used as the argument.
        double[] density = {1.8,1.4,14.2,33.3,16.4,68.1,121.3,38.2};
        double densityAvg = calcAvg(density);
        System.out.printf("The average density is: %,.1f", densityAvg);
        System.out.println();

        //the findHighValue method is called, using the moonRadii array as the parameter.
        double highestRadius = findHighValue(moonRadii);
        System.out.printf("The highest radius is: %,.1f", highestRadius);
        System.out.println();

        //the findLeastValue method is called, using the distance array as the argument.
        double[] distance = {3.7,23.4,550391.6,227894.9,778893.6,143323.5,287223.5,449655.1};
        double lowestDistance = findLeastValue(distance);
        System.out.printf("The lowest distance is: %,.1f", lowestDistance);
        System.out.println();

        //the findHighestTwo method is called, using the moon String array and moonRadii double array as arguements.
        String[] moon = {"Phobus","Deimos","Adrastea","Aitne","Amalthea","Ananke","Aoede","Arche"};
        System.out.printf("The highest two moons for radii are:");
        System.out.println();
        findHighestTwo(moon, moonRadii);

        //The findLowestTwo method is called, using the moon String array and density double array as parameters.
        System.out.printf("The lowest two moons for density are:");
        System.out.println();
        findLowestTwo(moon, density);

        //the user is prompted to input a moon String value. This input is stored in the moonName variable
        //which will become an argument in the findMoon method once it is called.
        System.out.println("Enter a moon:");
        String moonName = input.nextLine();
        findMoon(moon,moonName);
    }
    //end of main method


    //calcAvg --> all elements in the array is added to the total variable as the array is traversed. the total variable is then divided by the array length
    //and stored in the average variable, which is returned at the end of the method.
    public static double calcAvg(double[] values){
        double total = 0;
        for (int i =0; i < values.length; i++){
            total += values[i];
        }
        double average = total / values.length;
        return average;
    }

    /*
    * findHighValue --> this method traverses the double array to find the highest value.
    * the first element in the array is set as the largest in the beginning. each element
    * is then compared to largest. if an element is larger than largest, largest is
    * set to the element. when we finish traversing the array, we will have the largest value, which
    * will be returned at the end.
    * */
    public static double findHighValue(double[] values){
        double largest = values[0];
        for (int i = 0; i < values.length; i++){
            if (values[i] > largest){
                largest = values[i];
            }
        }
        return largest;
    }

    /*
        findLeastValue--> traverses the entire array for the smallest value. the first element in
        the array is set as least in the beginning of the method. each element is then
        compared to least; if the element is smaller than least, it is set as least. in the end of the method,
        the smallest value is returned
     */
    public static double findLeastValue(double[] values){
        double least = values[0];
        for (int i = 0; i < values.length; i++){
            if (values[i] < least){
                least = values[i];
            }
        }
        return least;
    }

    /*
        findHighestTwo --> this method traverses the double[] values array passed in, and finds the
        two largest values. the index of these two values are stored in variables indexOfLargest and
        indexOfSecondLargest. the indexes are then used to return the corresponding names in the
        names String array
     */
   public static String[] findHighestTwo(String[] names, double[] values){
        double largest = values[0];
        double secondLargest = values[0];
        int indexOfLargest = 0;
        int indexOfSecondLargest = 0;

       for (int i = 0; i < values.length; i++) {
           //if the current element is largest than largest, this element become the new largest. current largest becomes the second largest
           if (values[i] > largest) {
               secondLargest = largest;
               indexOfSecondLargest = indexOfLargest;
               largest = values[i];
               indexOfLargest = i;

           }
           //if the current element is larger than the second largest, but smaller than the largest, this element becomes the new second largest element
           else if (values[i] > secondLargest && values[i] < largest) {
               secondLargest = values[i];
               indexOfSecondLargest = i;
           }
       }
        //create a new array to store the two largest elements
       String[] greatestMoons = {names[indexOfLargest], names[indexOfSecondLargest]};

       System.out.println(greatestMoons[0]);
       System.out.println("\t" + greatestMoons[1]);

       return greatestMoons;
   }


    /*
        findLowestTwo --> this method traverses the double[] values array to find the two smallest values.
        the indexes of these two smallest values are stored in indexOfSmall and indexOfSmallest. these two
        index variables are used to return the corresponding moon names from the names String array
     */
   public static String[] findLowestTwo(String[] names, double[] values){ //findLeastTwo
        double smallest = values[0];
        double small = values[0];
        int indexOfSmall = 0;
        int indexOfSmallest = 0;

       for (int i = 0; i < values.length; i++){
           //if the current element is smaller than the smallest, it becomes the new smallest. the current smallest becomes the second-smallest value, which is stored in small
           if (values[i] < smallest){
               small = smallest;
               indexOfSmall = indexOfSmallest;
               smallest = values[i];
               indexOfSmallest = i;
           }
           //if the current element is smaller than the second-smallest element, but smaller than the current smallest value, the element becomes the new second-smallest value,
           //which is stored in small
           else if(values[i] < small && values[i] > smallest) {
               small = values[i];
               indexOfSmall = i;
           }
       }

       //create a new array to store the two smallest elements
       String[] smallMoons = {names[indexOfSmallest], names[indexOfSmall]};
       System.out.println(smallMoons[0]);
       System.out.println("\t" + smallMoons[1]);
       return smallMoons;


   }

   /*
    findMoon --> the find moon method traverses the names String array to see if any element has the same value as
    moon. if an element is found that has the same value as moon, moonFound is set to true, a print statment
    stating the moon is in the array is printed, and the method comes to an end
    */
    public static boolean findMoon(String[] names, String moon){
        boolean moonFound = false;
        for (int i = 0; i < names.length; i++){
            //if moon is equal to the current element, moonFound becomes true
            if (moon.compareTo(names[i]) == 0) {
                moonFound = true;
                System.out.println(moon + " is a moon in the array.");
                return moonFound;
            }
            else {
                moonFound = false;

            }

        }
        System.out.printf("%s is not a moon in the array.",moon);
        return moonFound; //here in case moonFound never becomes true
    }
}

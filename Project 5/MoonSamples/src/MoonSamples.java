/*
 * MoonSamples.java
 *
 * This is a program that helps Martians analyze Mars' soil. This program contains the methods getElements (places elements in an array),
 * getSamples (places all the values in string in a 2D array), searchForLife (searches for all methods that could support life),
 * searchHighestElements (returns the two elements that have the highest amounts), and searchHighestSample (returns the sample that has the highest
 * amount for an element).
 *
 * Shon Bennett
 * October 28, 2021
 * CMSC 255 Sect 901
 */

package MoonSamples;

import java.util.Arrays;

public class MoonSamples {
    public static void main(String[] args){
        String elements = "carbon-dioxide,magnesium,sodium,potassium,chloride,water";
        String samples = "8.3,4.5,6.7,2.3,12.5,4.5<>3.9,1.8,34.7,23.5,1.2,14.3<>6.7,7.4,1.5,18.4,7.2,23.7<>23.4,5.6,2.9,18.5,39.5,18.2<>15.4,5.3,27.4,9.8,3.8,27.4";

        String[] results = getElements(elements);

        double[][] tempSamples = getSamples(samples);

        int[] life = searchForLife(tempSamples);
        System.out.println("The samples that contain life are " + Arrays.toString(life));

        searchHighestElements(tempSamples, results, 1);
        System.out.println("The highest elements for sample 1 are " + searchHighestElements(tempSamples, results, 1));

        int highSample = searchHighestSample(tempSamples, results, "water");
        System.out.println("The sample with the highest value of the element water is " + highSample);
    }

    //this method places all the elements in the elements string into a 1D array
    public static String[] getElements(String inputElementString){
        /* use the split method to pull all the commas. where each comma once was, we break the string, and turn it into an element of an array.
        the array made from the split method is stored in elementsArray **/
        String[] elementsArray = inputElementString.split(",");
        return elementsArray;

    }

    //this method creates a 2D array out of the sample string
    public static double[][] getSamples(String inputSamplesString) {
        //the sample string is turned into a 1D array via the split method, and stored into tempArray
        String[] tempArray = inputSamplesString.split(",|<>"); // | acts as an "or" in this statement

        //we create a new 2D array called compositionArray that we will store the val7ues of tempArray in
        double[][] compositionArray = new double [5][6];

        //the index variable will be used for getting each number in the tempArray as we transfer the elements into compositionArray
        int index = 0;

        //these row and column variables will be used in the while loop ahead as we place each element into the 2D array
        int row = 0;
        int column = 0;

        // as long as the column and row variables are less than the compositionArray's row/column lengths, and the
        // index variable is less than the temporary array's length, we will continue adding elements
        while (column < 6 && row < 5 && index < tempArray.length){
            compositionArray[row][column] = Double.parseDouble(tempArray[index]); //parse method turns String to double value
            column++;
            index++;

            //this if statement is our "reset" that allows us to continue adding elements. once the column variable reaches its end,
            //we set it equal to zero. then we add 1 to the row variable, so we can add to the next available row
            if (column == compositionArray[row].length){
                column = 0;
                row++;
            }
        }
        return compositionArray;
    }

    //this method tells you which soils are suitable for life
    public static int[] searchForLife(double [][] samples){
        //index used to add to int array we will return at method’s end
        int index = 0;

        //array used before we switch it to final array
        int[] tempLifeArray = new int[samples.length];

        //count variable used to determine length of final int array we will return
        int count = 0;

        //this for loop goes through each row, and places its elements in the supportsLife formula (which is stored in the supportsLife
        //variable.
        for (int row = 0; row < samples.length; row++) {
            double supportsLife = (8 * samples[row][0]) + (2 * samples[row][1]) + samples[row][2] + (4 * samples[row][3]) + samples[row][4] + (5 * samples[row][5]);

            /*if the value stored in supportsLife is greater than 300, we know it is capable of supporting life.
            we will then make room for the row in the new array by adding it to the tempLifeArray (the temporary array
            used to store values until we have our final supportsLife array) and increasing our index and count variables **/
            if (supportsLife >= 300){
                int lifeRow = row + 1;
                tempLifeArray[index] = lifeRow;
                index++;
                count++;
            }
        }

        int[] lifeArray = new int[count];

        //for loop to switch tempLifeArray elements to our final lifeArray
        for (int i = 0;  i < lifeArray.length; i++) {
            lifeArray[i] = tempLifeArray[i];
        }
        return lifeArray;
    }

    //this method returns the two elements that have the highest amount for the indicated sample
    public static String searchHighestElements(double [][] samples, String[] elements, int sampleNum) {
        sampleNum = sampleNum - 1;
        //set a default highest and second highest. Set vars aside for their indexes also
        double highest = samples[sampleNum][0];
        int highestRow = sampleNum;
        int highestColumn = 0;
        double secondHighest = samples[sampleNum][1];
        int secondHighestRow = sampleNum;
        int secondHighestColumn = 1;

        //this for loop is used to compare values
        for(int column = 0; column < samples[sampleNum].length; column++){
            if (samples[sampleNum][column] > highest){
                //move highest and its indexes to secondHighest vars
                secondHighest = highest;
                secondHighestColumn = highestColumn;
                secondHighestRow = sampleNum;

                //make current element being traversed the highest
                highest = samples[sampleNum][column];
                highestRow = sampleNum;
                highestColumn = column;
            }
            else if (samples[sampleNum][column] > secondHighest && samples[sampleNum][column] < highest){
                //make current element second highest
                secondHighest = samples[sampleNum][column];
                secondHighestColumn = column;
                secondHighestRow = sampleNum;
            }
        }
        //create array to return the string elements that correspond with the numbers
        String highestElements = elements[highestColumn] + " and " + elements[secondHighestColumn];
        return highestElements;
    }

    //this method returns sample number with highest amount of an element
    public static int searchHighestSample(double [][] samples, String[] elements, String element) {
        /* we will use this for loop to get index of element name in 1d array.
        this elementColumn index variable will be used for the 2d array column we will be checking
        if the element is non-existent, then we will keep elementColumn as -1 **/
        int elementColumn = -1;
        int highestSampleIndex = 0;
        for (int i = 0; i < elements.length; i++) {
            if (element.compareTo(elements[i]) == 0){
                elementColumn = i;
            }

        }

        /* use this for loop to check each row in the column. After checking, we will have the highest number's sample number stored in highestSampleIndex
        this for loop will only occur if elementColumn is not -1 (which means the element exists)
        if elementColumn is -1, then **/
        if(elementColumn != -1) {

            for (int row = 0; row < samples.length; row++) {
                if (samples[row][elementColumn] > samples[highestSampleIndex][elementColumn]) {
                    highestSampleIndex = row;
                }
            }
            //must add one to highestSampleIndex variable so we get the actual sample number of the greatest element
            highestSampleIndex = highestSampleIndex + 1;

        }else if (elementColumn == -1){
            highestSampleIndex = -1;
        }
        return highestSampleIndex;
    }
}



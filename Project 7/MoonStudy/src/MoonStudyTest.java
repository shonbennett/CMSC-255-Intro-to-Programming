
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.Timeout;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.*;

import static org.junit.Assert.*;

@SuppressWarnings("rawtypes")
public class MoonStudyTest {


    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void MoonAttributesTest() {
        try {
            MoonAttributes.valueOf("RADIUS");
            MoonAttributes.valueOf("DENSITY");
            MoonAttributes.valueOf("DISTANCE");
        }
        catch(IllegalArgumentException e) {
            fail(e.getLocalizedMessage());
        }
        assertEquals("When looking at the number of values in the MoonAttributes enum, we",3,MoonAttributes.values().length);
    }

    @Test
    public void MoonStudy_GoodData_openFileTest() throws IOException {
        // Invoke method
        ArrayList<String> actual = MoonStudy.openFile(generateGoodInputFile());
        // Check results
        testStringArray("When checking the String array returned from the openFile method given a file with valid input data, we",generateGoodInputStringArray(),actual);
    }

    @Test
    public void MoonStudy_GoodData_createObjectsTest(){
        ArrayList<Moon> actual = MoonStudy.createObjects(generateGoodInputStringArray());
        testMoonArray("When checking the ArrayList returned from the createObjects method given data generated from MoonGoodData.txt, we",generateGoodInputArrayList(),actual);
    }

    @Test
    public void MoonStudy_GoodData_findMeanTest(){
        double actual = MoonStudy.findMean(generateGoodInputArrayList(),MoonAttributes.RADIUS);
        assertEquals("When checking the return value from the findMean method given data generated from MoonGoodData.txt and the RADIUS attribute, we",65.1625,actual,0.01);

        actual = MoonStudy.findMean(generateGoodInputArrayList(),MoonAttributes.DENSITY);
        assertEquals("When checking the return value from the findMean method given data generated from MoonGoodData.txt and the DENSITY attribute, we",36.8375,actual,0.01);

        actual = MoonStudy.findMean(generateGoodInputArrayList(),MoonAttributes.DISTANCE);
        assertEquals("When checking the return value from the findMean method given data generated from MoonGoodData.txt and the DISTANCE attribute, we",304676.1625,actual,0.01);
    }

    @Test
    public void MoonStudy_GoodData_findHighValueTest(){
        double actual = MoonStudy.findHighValue(generateGoodInputArrayList(),MoonAttributes.RADIUS);
        assertEquals("When checking the return value from the findHighValue method given data generated from MoonGoodData.txt and the RADIUS attribute, we",255.9,actual,0.01);

        actual = MoonStudy.findHighValue(generateGoodInputArrayList(),MoonAttributes.DENSITY);
        assertEquals("When checking the return value from the findHighValue method given data generated from MoonGoodData.txt and the DENSITY attribute, we",121.3,actual,0.01);

        actual = MoonStudy.findHighValue(generateGoodInputArrayList(),MoonAttributes.DISTANCE);
        assertEquals("When checking the return value from the findHighValue method given data generated from MoonGoodData.txt and the DISTANCE attribute, we",778893.6,actual,0.01);
    }

    @Test
    public void MoonStudy_GoodData_findMeanMoonTest(){
        Moon actual = MoonStudy.findMeanMoon(generateGoodInputArrayList(),MoonAttributes.RADIUS,65.1625);
        Moon expected = generateGoodInputArrayList().get(2);

        assertTrue("When checking the return value from the findMeanMoon method given data generated from MoonGoodData.txt, the RADIUS attribute, and the average of radii, we expected " + expected + " but was " + actual,MoonIsEqual(expected,actual));

        actual = MoonStudy.findMeanMoon(generateGoodInputArrayList(),MoonAttributes.DISTANCE,304676.1625);
        expected = generateGoodInputArrayList().get(6);

        assertTrue("When checking the return value from the findMeanMoon method given data generated from MoonGoodData.txt, the DISTANCE attribute, and the average of distance, we expected " + expected + " but was " + actual,MoonIsEqual(expected,actual));

        actual = MoonStudy.findMeanMoon(generateGoodInputArrayList(),MoonAttributes.DENSITY,36.8375);
        expected = generateGoodInputArrayList().get(7);

        assertTrue("When checking the return value from the findMeanMoon method given data generated from MoonGoodData.txt, the DENSITY attribute, and the average of density, we expected " + expected + " but was " + actual,MoonIsEqual(expected,actual));
    }

    @Test
    public void MoonStudy_GoodData_findLowestMoonsTest(){
        ArrayList<Moon> actual = MoonStudy.findLowestMoons(generateGoodInputArrayList(),65.1625,MoonAttributes.RADIUS);
        ArrayList<Moon> expected = generateGoodInputArrayList();
        expected.remove(6);expected.remove(4);expected.remove(2);

        testMoonArray("When checking the ArrayList returned from the findLowestMoons method given data generated from MoonGoodData.txt, the RADIUS attribute, and the average of radii, we",expected,actual);

        actual = MoonStudy.findLowestMoons(generateGoodInputArrayList(),304676.1625,MoonAttributes.DISTANCE);
        expected = generateGoodInputArrayList();
        expected.remove(7);expected.remove(4);expected.remove(2);

        testMoonArray("When checking the ArrayList returned from the findLowestMoons method given data generated from MoonGoodData.txt, the DISTANCE attribute, and the average of distance, we",expected,actual);

        actual = MoonStudy.findLowestMoons(generateGoodInputArrayList(),36.8375,MoonAttributes.DENSITY);
        expected = generateGoodInputArrayList();
        expected.remove(7);expected.remove(6);expected.remove(5);

        testMoonArray("When checking the ArrayList returned from the findLowestMoons method given data generated from MoonGoodData.txt, the DENSITY attribute, and the average of density, we",expected,actual);


    }

    @Test
    public void MoonStudy_GoodData_writeOutMoonArrayListTest() throws IOException {
        File outputFile = folder.newFile("bar.txt");
        PrintWriter outWriter = new PrintWriter(outputFile);
        ArrayList<Moon> outputData = generateGoodInputArrayList();
        String expected = "Hello World: Phobus 11.30 1.80 3.70 Deimos 6.20 1.40 23.40 Adrastea 68.90 14.20 550391.60 Aitne 33.60 33.30 227894.90 Amalthea 71.20 16.40 778893.60 Ananke 26.80 68.10 143323.50 Aoede 255.90 121.30 287223.50 Arche 47.40 38.20 449655.10 ";
        String message = "Hello World: ";
        // Invoke method
        MoonStudy.outputToFile(message,outputData,outWriter);
        outWriter.flush();outWriter.close();
        // Check results
        assertTrue("Output file does not exist", outputFile.exists());
        Scanner outputScan = new Scanner(outputFile);

        int i = 0;

        assertEquals("When checking the output file produced by outputToFile given an ArrayList<Moon> of good data, at line " +(i+1)+ " we",expected,outputScan.nextLine());

        assertEquals("When checking the output file produced by outputToFile given an ArrayList<Moon> of good data, at line 2 we", "", outputScan.nextLine());

//        while (outputScan.hasNextLine()){
//            String expectedString = "";
//            if(i < expected.size()){
//                expectedString = expected.get(i).toString();
//            }
//            assertEquals("When checking the output file produced by outputToFile given an ArrayList<Moon> of good data, at line " +(i+2)+ " we",expectedString,outputScan.nextLine().trim());
//            i++;
//        }
        outputScan.close();
    }

    @Test
    public void MoonStudy_GoodData_writeOutDoubleDataTest() throws IOException {
        File outputFile = folder.newFile("bar.txt");
        PrintWriter outWriter = new PrintWriter(outputFile);
        String message = "This is a message: ";
        // Invoke method
        MoonStudy.outputToFile(message,100.7564829,outWriter);
        MoonStudy.outputToFile(message,256.7544829,outWriter);
        outWriter.flush();outWriter.close();
        // Check results
        assertTrue("Output file does not exist", outputFile.exists());
        Scanner outputScan = new Scanner(outputFile);
        int i = 1;
        try {
            assertEquals("When checking the output file produced by outputToFile given a String message and double value, at line 1 we", message + "100.76", outputScan.nextLine());i++;
            assertEquals("When checking the output file produced by outputToFile given a String message and double value, at line 2 we", "", outputScan.nextLine());i++;

            assertEquals("When checking the output file produced by calling outputToFile a second time given a String message and double value, at line 3 we", message + "256.75", outputScan.nextLine());i++;
            assertEquals("When checking the output file produced by calling outputToFile a second time given a String message and double value, at line 4 we", "", outputScan.nextLine());
        }
        catch (NoSuchElementException e){
            fail("Line " + i + " does not exist but it should");
        }

        outputScan.close();
    }

    @Test
    public void MoonStudy_GoodData_writeOutMoonDataTest() throws IOException {
        File outputFile = folder.newFile("bar.txt");
        PrintWriter outWriter = new PrintWriter(outputFile);
        String message = "This is a message: ";
        // Invoke method
        MoonStudy.outputToFile(message,new Moon("Phobus",11.3,1.8,3.7),outWriter);
        MoonStudy.outputToFile(message,new Moon("Deimos",6.2,1.4,23.4),outWriter);
        outWriter.flush();outWriter.close();
        // Check results
        assertTrue("Output file does not exist", outputFile.exists());
        Scanner outputScan = new Scanner(outputFile);
        int i = 1;
        try {
            assertEquals("When checking the output file produced by outputToFile given a String message and double value, at line 1 we", message + "Phobus 11.30 1.80 3.70", outputScan.nextLine());i++;
            assertEquals("When checking the output file produced by outputToFile given a String message and double value, at line 2 we", "", outputScan.nextLine());i++;

            assertEquals("When checking the output file produced by calling outputToFile a second time given a String message and double value, at line 3 we", message + "Deimos 6.20 1.40 23.40", outputScan.nextLine());i++;
            assertEquals("When checking the output file produced by calling outputToFile a second time given a String message and double value, at line 4 we", "", outputScan.nextLine());
        }
        catch (NoSuchElementException e){
            fail("Line " + i + " does not exist but it should");
        }

        outputScan.close();
    }

    @Test
    public void MoonStudy_GoodData_mainMethod(){
        try{
            File outputFile = folder.newFile("bar.txt");
            MoonStudy.main(new String[]{generateGoodInputFile().getAbsolutePath(),outputFile.getAbsolutePath()});

            // Check results
            assertTrue("Output file does not exist", outputFile.exists());
            Scanner outputScan = new Scanner(outputFile);
            int i = 1;
            try {
                assertEquals("When checking the output file produced by calling the main method given good data, at line 1 we", "The mean of radii is: 65.16", outputScan.nextLine());i++;
                assertEquals("When checking the output file produced by calling the main method given good data, at line 2 we", "", outputScan.nextLine());i++;

                assertEquals("When checking the output file produced by calling the main method given good data, at line 3 we", "The highest density value is: 121.30", outputScan.nextLine());i++;
                assertEquals("When checking the output file produced by calling the main method given good data, at line 4 we", "", outputScan.nextLine());i++;

                assertEquals("When checking the output file produced by calling the main method given good data, at line 5 we", "The moon closest to the mean is: Adrastea 68.90 14.20 550391.60", outputScan.nextLine());i++;
                assertEquals("When checking the output file produced by calling the main method given good data, at line 6 we", "", outputScan.nextLine());i++;

                assertEquals("When checking the output file produced by calling the main method given good data, at line 7 we", "The moons below the mean value of radii are: Phobus 11.30 1.80 3.70 Deimos 6.20 1.40 23.40 Aitne 33.60 33.30 227894.90 Ananke 26.80 68.10 143323.50 Arche 47.40 38.20 449655.10 ", outputScan.nextLine());i++;
                assertEquals("When checking the output file produced by calling the main method given good data, at line 8 we", "", outputScan.nextLine());i++;

            }
            catch (NoSuchElementException e){
                fail("Line " + i + " does not exist but it should");
            }

            outputScan.close();

        } catch (IOException e) { e.printStackTrace(); }

    }




    @Test
    public void MoonStudy_BadData_openFileTest() throws IOException {
        // Invoke method
        ArrayList<String> actual = MoonStudy.openFile(generateBadInputFile());
        // Check results
        testStringArray("When checking the String array returned from the openFile method given a file with invalid input data, we",generateBadInputStringArray(),actual);
    }

    @Test
    public void MoonStudy_BadData_createObjectsTest(){
        ArrayList<Moon> actual = MoonStudy.createObjects(generateBadInputStringArray());
        testMoonArray("When checking the ArrayList returned from the createObjects method given data generated from MoonBadData.txt, we",generateBadInputArrayList(),actual);
    }

    @Test
    public void MoonStudy_BadData_findMeanTest(){
        double actual = MoonStudy.findMean(generateBadInputArrayList(),MoonAttributes.RADIUS);
        assertEquals("When checking the return value from the findMean method given data generated from MoonBadData.txt and the RADIUS attribute, we",24.275,actual,0.01);

        actual = MoonStudy.findMean(generateBadInputArrayList(),MoonAttributes.DENSITY);
        assertEquals("When checking the return value from the findMean method given data generated from MoonBadData.txt and the DENSITY attribute, we",15.5125,actual,0.01);

        actual = MoonStudy.findMean(generateBadInputArrayList(),MoonAttributes.DISTANCE);
        assertEquals("When checking the return value from the findMean method given data generated from MoonBadData.txt and the DISTANCE attribute, we",304673.2375,actual,0.01);
    }

    @Test
    public void MoonStudy_BadData_findHighValueTest(){
        double actual = MoonStudy.findHighValue(generateBadInputArrayList(),MoonAttributes.RADIUS);
        assertEquals("When checking the return value from the findHighValue method given data generated from MoonBadData.txt and the RADIUS attribute, we",68.9,actual,0.01);

        actual = MoonStudy.findHighValue(generateBadInputArrayList(),MoonAttributes.DENSITY);
        assertEquals("When checking the return value from the findHighValue method given data generated from MoonBadData.txt and the DENSITY attribute, we",68.1,actual,0.01);

        actual = MoonStudy.findHighValue(generateBadInputArrayList(),MoonAttributes.DISTANCE);
        assertEquals("When checking the return value from the findHighValue method given data generated from MoonBadData.txt and the DISTANCE attribute, we",778893.6,actual,0.01);
    }

    @Test
    public void MoonStudy_BadData_findMeanMoonTest(){
        Moon actual = MoonStudy.findMeanMoon(generateBadInputArrayList(),MoonAttributes.RADIUS,24.275);
        Moon expected = generateBadInputArrayList().get(5);

        assertTrue("When checking the return value from the findMeanMoon method given data generated from MoonBadData.txt, the RADIUS attribute, and the average of radii, we expected " + expected + " but was " + actual,MoonIsEqual(expected,actual));

        actual = MoonStudy.findMeanMoon(generateBadInputArrayList(),MoonAttributes.DISTANCE,304673.2375);
        expected = generateBadInputArrayList().get(6);

        assertTrue("When checking the return value from the findMeanMoon method given data generated from MoonBadData.txt, the DISTANCE attribute, and the average of distance, we expected " + expected + " but was " + actual,MoonIsEqual(expected,actual));

        actual = MoonStudy.findMeanMoon(generateBadInputArrayList(),MoonAttributes.DENSITY,15.5125);
        expected = generateBadInputArrayList().get(4);

        assertTrue("When checking the return value from the findMeanMoon method given data generated from MoonBadData.txt, the DENSITY attribute, and the average of density, we expected " + expected + " but was " + actual,MoonIsEqual(expected,actual));
    }

    @Test
    public void MoonStudy_BadData_findLowestMoonsTest(){
        ArrayList<Moon> actual = MoonStudy.findLowestMoons(generateBadInputArrayList(),24.275,MoonAttributes.RADIUS);
        ArrayList<Moon> expected = generateBadInputArrayList();
        expected.remove(7);expected.remove(5);expected.remove(3);expected.remove(2);

        testMoonArray("When checking the ArrayList returned from the findLowestMoons method given data generated from MoonBadData.txt, the RADIUS attribute, and the average of radii, we",expected,actual);

        actual = MoonStudy.findLowestMoons(generateBadInputArrayList(),304673.2375,MoonAttributes.DISTANCE);
        expected = generateBadInputArrayList();
        expected.remove(7);expected.remove(4);expected.remove(2);

        testMoonArray("When checking the ArrayList returned from the findLowestMoons method given data generated from MoonBadData.txt, the DISTANCE attribute, and the average of distance, we",expected,actual);

        actual = MoonStudy.findLowestMoons(generateBadInputArrayList(),15.5125,MoonAttributes.DENSITY);
        expected = generateBadInputArrayList();
        expected.remove(7);expected.remove(5);expected.remove(4);

        testMoonArray("When checking the ArrayList returned from the findLowestMoons method given data generated from MoonBadData.txt, the DENSITY attribute, and the average of density, we",expected,actual);

    }

    @Test
    public void MoonStudy_BadData_writeOutMoonArrayListTest() throws IOException {
        File outputFile = folder.newFile("bar.txt");
        PrintWriter outWriter = new PrintWriter(outputFile);
        ArrayList<Moon> outputData = generateBadInputArrayList();
        String expected = "Hello World: Phobus 11.30 0.00 3.70 Deimos 6.20 1.40 0.00 Adrastea 68.90 0.00 550391.60 Aitne 33.60 0.00 227894.90 Amalthea 0.00 16.40 778893.60 Ananke 26.80 68.10 143323.50 Aoede 0.00 0.00 287223.50 Arche 47.40 38.20 449655.10 ";
        String message = "Hello World: ";
        // Invoke method
        MoonStudy.outputToFile(message,outputData,outWriter);
        outWriter.flush();outWriter.close();
        // Check results
        assertTrue("Output file does not exist", outputFile.exists());
        Scanner outputScan = new Scanner(outputFile);

        int i = 0;

        assertEquals("When checking the output file produced by outputToFile given an ArrayList<Moon> of bad data, at line " +(i+1)+ " we",expected,outputScan.nextLine());

        assertEquals("When checking the output file produced by outputToFile given an ArrayList<Moon> of bad data, at line 2 we", "", outputScan.nextLine());

//        while (outputScan.hasNextLine()){
//            String expectedString = "";
//            if(i < expected.size()){
//                expectedString = expected.get(i).toString();
//            }
//            assertEquals("When checking the output file produced by outputToFile given an ArrayList<Moon> of good data, at line " +(i+2)+ " we",expectedString,outputScan.nextLine().trim());
//            i++;
//        }
        outputScan.close();
    }

    @Test
    public void MoonStudy_BadData_mainMethod(){
        try{
            File outputFile = folder.newFile("bar.txt");
            MoonStudy.main(new String[]{generateBadInputFile().getAbsolutePath(),outputFile.getAbsolutePath()});

            // Check results
            assertTrue("Output file does not exist", outputFile.exists());
            Scanner outputScan = new Scanner(outputFile);
            int i = 1;
            try {
                assertEquals("When checking the output file produced by calling the main method given bad data, at line 1 we", "The mean of radii is: 24.28", outputScan.nextLine());i++;
                assertEquals("When checking the output file produced by calling the main method given bad data, at line 2 we", "", outputScan.nextLine());i++;

                assertEquals("When checking the output file produced by calling the main method given bad data, at line 3 we", "The highest density value is: 68.10", outputScan.nextLine());i++;
                assertEquals("When checking the output file produced by calling the main method given bad data, at line 4 we", "", outputScan.nextLine());i++;

                assertEquals("When checking the output file produced by calling the main method given bad data, at line 5 we", "The moon closest to the mean is: Ananke 26.80 68.10 143323.50", outputScan.nextLine());i++;
                assertEquals("When checking the output file produced by calling the main method given bad data, at line 6 we", "", outputScan.nextLine());i++;

                assertEquals("When checking the output file produced by calling the main method given bad data, at line 7 we", "The moons below the mean value of radii are: Phobus 11.30 0.00 3.70 Deimos 6.20 1.40 0.00 Amalthea 0.00 16.40 778893.60 Aoede 0.00 0.00 287223.50 ", outputScan.nextLine());i++;
                assertEquals("When checking the output file produced by calling the main method given bad data, at line 8 we", "", outputScan.nextLine());i++;

            }
            catch (NoSuchElementException e){
                fail("Line " + i + " does not exist but it should");
            }

            outputScan.close();

        } catch (IOException e) { e.printStackTrace(); }

    }




    private File generateGoodInputFile() throws IOException {
        final String INPUT_FILENAME = "foo.txt";
        File inputFile = folder.newFile(INPUT_FILENAME);
        PrintWriter write = new PrintWriter(inputFile);
        write.print("Phobus\t11.3\t1.8\t3.7\n" +
                "Deimos\t6.2\t1.4\t23.4\n" +
                "Adrastea\t68.9\t14.2\t550391.6\n" +
                "Aitne\t33.6\t33.3\t227894.9\n" +
                "Amalthea\t71.2\t16.4 \t778893.6\n" +
                "Ananke\t26.8\t68.1\t143323.5\n" +
                "Aoede\t255.9\t121.3\t287223.5\n" +
                "Arche\t47.4\t38.2\t449655.1");
        write.close();
        return inputFile;
    }

    private ArrayList<String> generateGoodInputStringArray(){
        String[] array = {"Phobus\t11.3\t1.8\t3.7",
                "Deimos\t6.2\t1.4\t23.4",
                "Adrastea\t68.9\t14.2\t550391.6",
                "Aitne\t33.6\t33.3\t227894.9",
                "Amalthea\t71.2\t16.4 \t778893.6",
                "Ananke\t26.8\t68.1\t143323.5",
                "Aoede\t255.9\t121.3\t287223.5",
                "Arche\t47.4\t38.2\t449655.1"};
        ArrayList<String> toReturn = new ArrayList<>();
        Collections.addAll(toReturn, array);
        return toReturn;
    }

    private ArrayList<Moon> generateGoodInputArrayList(){
        Moon[] temp = {
                createMoon("Phobus",11.3,1.8,3.7),
                createMoon("Deimos",6.2,1.4,23.4),
                createMoon("Adrastea",68.9,14.2,550391.6),
                createMoon("Aitne",33.6,33.3,227894.9),
                createMoon("Amalthea",71.2,16.4,778893.6),
                createMoon("Ananke",26.8,68.1,143323.5),
                createMoon("Aoede",255.9,121.3,287223.5),
                createMoon("Arche",47.4,38.2,449655.1)
        };

        return new ArrayList<>(Arrays.asList(temp));
    }


    private File generateBadInputFile() throws IOException {
        final String INPUT_FILENAME = "foo.txt";
        File inputFile = folder.newFile(INPUT_FILENAME);
        PrintWriter write = new PrintWriter(inputFile);
        write.print("Phobus\t11.3\t1.f8\t3.7\n" +
                "Deimos\t6.2\t1.4\t23e.4\n" +
                "Adrastea\t68.9\t-14.2\t550391.6\n" +
                "Aitne\t33.6\trte.f\t227894.9\n" +
                "Amalthea\t-71.2\t16.4 \t778893.6\n" +
                "Ananke\t26.8\t68.1\t143323.5\n" +
                "Aoede\t2o5.9\t12u1.3\t287223.5\n" +
                "Arche\t47.4\t38.2\t449655.1");
        write.close();
        return inputFile;
    }

    private ArrayList<String> generateBadInputStringArray(){
        String[] array = {"Phobus\t11.3\t1.f8\t3.7",
                "Deimos\t6.2\t1.4\t23e.4",
                "Adrastea\t68.9\t-14.2\t550391.6",
                "Aitne\t33.6\trte.f\t227894.9",
                "Amalthea\t-71.2\t16.4 \t778893.6",
                "Ananke\t26.8\t68.1\t143323.5",
                "Aoede\t2o5.9\t12u1.3\t287223.5",
                "Arche\t47.4\t38.2\t449655.1"};
        ArrayList<String> toReturn = new ArrayList<>();
        Collections.addAll(toReturn, array);
        return toReturn;
    }

    private ArrayList<Moon> generateBadInputArrayList(){
        Moon[] temp = {
                createMoon("Phobus", 11.3, 0, 3.7),
                createMoon("Deimos", 6.2, 1.4, 0),
                createMoon("Adrastea", 68.9, 0, 550391.6),
                createMoon("Aitne", 33.6, 0, 227894.9),
                createMoon("Amalthea", 0, 16.4, 778893.6),
                createMoon("Ananke", 26.8, 68.1, 143323.5),
                createMoon("Aoede", 0, 0, 287223.5),
                createMoon("Arche", 47.4, 38.2, 449655.1)
        };

        return new ArrayList<>(Arrays.asList(temp));
    }


    private void testMoonArray(String message, ArrayList expected, ArrayList actual){
        assertEquals(message + " looked at the size and ",expected.size(),actual.size());

        for(int i = 0; i < expected.size(); i++) {
            if (!MoonIsEqual(expected.get(i), actual.get(i))) {
                assertEquals(message, expected, actual);
            }
        }
    }

    private void testStringArray(String message, ArrayList expected, ArrayList actual){
        assertEquals(message + " looked at the size and ",expected.size(),actual.size());

        for(int i = 0; i < expected.size(); i++) {
            if (!expected.get(i).equals(actual.get(i))) {
                assertEquals(message, expected, actual);
            }
        }
    }

    private boolean MoonIsEqual(Object o1, Object o2){
        Class c = o1.getClass();
        try {
            Field o1FieldName = c.getDeclaredField("name");
            o1FieldName.setAccessible(true);
            Object o1Name = o1FieldName.get(o1);

            Field o2FieldName = c.getDeclaredField("name");
            o2FieldName.setAccessible(true);
            Object o2Name = o2FieldName.get(o2);

            Field o1FieldRadius = c.getDeclaredField("radius");
            o1FieldRadius.setAccessible(true);
            Object o1Radius = o1FieldRadius.get(o1);

            Field o2FieldRadius = c.getDeclaredField("radius");
            o2FieldRadius.setAccessible(true);
            Object o2Radius = o2FieldRadius.get(o2);

            Field o1FieldValue = c.getDeclaredField("density");
            o1FieldValue.setAccessible(true);
            Object o1Value = o1FieldValue.get(o1);

            Field o2FieldValue = c.getDeclaredField("density");
            o2FieldValue.setAccessible(true);
            Object o2Value = o2FieldValue.get(o2);

            Field o1FieldDistance = c.getDeclaredField("distance");
            o1FieldDistance.setAccessible(true);
            Object o1Distance = o1FieldDistance.get(o1);

            Field o2FieldDistance = c.getDeclaredField("distance");
            o2FieldDistance.setAccessible(true);
            Object o2Distance = o2FieldDistance.get(o2);

            return  o1Name.equals(o2Name) &&
                    o1Radius.equals(o2Radius) &&
                    o1Value.equals(o2Value) &&
                    o1Distance.equals(o2Distance);


        } catch (NoSuchFieldException e) {
            fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
        } catch (Exception e) {
            fail("Something weird went wrong");
        }

        return false;
    }

    private Moon createMoon(String aName, double aRadius, double aDensity, double aDistance){
        Moon testMoon = new Moon();
        Class c = testMoon.getClass();

        try {
            Field name = c.getDeclaredField("name");
            name.setAccessible(true);
            name.set(testMoon, aName);

            Field radius = c.getDeclaredField("radius");
            radius.setAccessible(true);
            radius.set(testMoon, aRadius);

            Field value = c.getDeclaredField("density");
            value.setAccessible(true);
            value.set(testMoon, aDensity);

            Field distance = c.getDeclaredField("distance");
            distance.setAccessible(true);
            distance.set(testMoon, aDistance);


        } catch (Exception e) {
            fail(e.toString());
        }

        return testMoon;
    }


}

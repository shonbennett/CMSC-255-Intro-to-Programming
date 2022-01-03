
import Project6.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import static org.junit.Assert.*;

@SuppressWarnings("rawtypes")
public class DevelopmentTest {


    @Test
    public void Development_instanceCountTest(){
        Development testDevelopment = new Development();
        Class c = testDevelopment.getClass();
        try {
            assertEquals(
                    "You must only have the instance variables specified. When looking at the number of instance variables we",
                    5, c.getDeclaredFields().length);
        }
        catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    @Test
    public void Development_instanceVariablesTest(){
        Development testDevelopment = new Development();
        instanceVariablePrivate("location",testDevelopment);
        instanceVariablePrivate("name",testDevelopment);
        instanceVariablePrivate("yearEst",testDevelopment);
        instanceVariablePrivate("numLots",testDevelopment);
        instanceVariablePrivate("houses",testDevelopment);

        instanceVariableStatic("location",testDevelopment);
        instanceVariableStatic("name",testDevelopment);
        instanceVariableStatic("yearEst",testDevelopment);
        instanceVariableStatic("numLots",testDevelopment);
        instanceVariableStatic("houses",testDevelopment);

        instanceVariableCorrectType("location",String.class,testDevelopment);
        instanceVariableCorrectType("name",String.class,testDevelopment);
        instanceVariableCorrectType("yearEst",int.class,testDevelopment);
        instanceVariableCorrectType("numLots",int.class,testDevelopment);
        instanceVariableCorrectType("houses",ArrayList.class,testDevelopment);
    }

    @Test
    public void Development_defaultConstructorTest() {
        Development testDevelopment = new Development();

        testVariable("location",testDevelopment,"","When checking the value of location we");
        testVariable("name",testDevelopment,"","When checking the value of name we");
        testVariable("yearEst",testDevelopment,0,"When checking the value of yearEst we");
        testVariable("numLots",testDevelopment,0,"When checking the value of numLots we");
        testVariable("houses",testDevelopment,new ArrayList<>(),"When checking the value of Houses we");

    }

    @Test
    public void Development_parameterizedConstructorTest() {
        Development testDevelopment = new Development("Red & Blue","25 Pallet Dr",1996,2);

        testVariable("location",testDevelopment,"25 Pallet Dr","When checking the value of location we");
        testVariable("name",testDevelopment,"Red & Blue","When checking the value of name we");
        testVariable("yearEst",testDevelopment,1996,"When checking the value of yearEst we");
        testVariable("numLots",testDevelopment,2,"When checking the value of numLots we");
        testVariable("houses",testDevelopment,new ArrayList<>(),"When checking the value of houses we");

    }

    @Test
    public void Development_getLocationTest() {
        ArrayList<House> someHouses = new ArrayList<>();
        Development testDevelopment = createDevelopment("Richmond, VA","Claire Sparks",2021,15,someHouses);
        assertEquals("With a Development object who's location instance variable is Richmond, VA, when calling getLocation we","Richmond, VA",testDevelopment.getLocation());
    }

    @Test
    public void Development_setLocationTest() {
        ArrayList<House> someHouses = new ArrayList<>();
        Development testDevelopment = createDevelopment("Richmond, VA","Claire Sparks",2021,15,someHouses);

        testDevelopment.setLocation("Boston, MA");
        testVariable("location",testDevelopment,"Boston, MA","After calling Development's setLocation method with an argument of Boston, MA, for the value of location we");
    }

    @Test
    public void Development_getNameTest() {
        ArrayList<House> someHouses = new ArrayList<>();
        Development testDevelopment = createDevelopment("Richmond, VA","Claire Sparks",2021,15,someHouses);
        assertEquals("With a Development object who's name instance variable is Claire Sparks, when calling getLocation we","Claire Sparks",testDevelopment.getName());
    }

    @Test
    public void Development_setNameTest() {
        ArrayList<House> someHouses = new ArrayList<>();
        Development testDevelopment = createDevelopment("Richmond, VA","Claire Sparks",2021,15,someHouses);

        testDevelopment.setName("Samus Aran");
        testVariable("name",testDevelopment,"Samus Aran","After calling Development's setName method with an argument of Samus Aran, for the value of name we");
    }

    @Test
    public void Development_getYearEstTest() {
        ArrayList<House> someHouses = new ArrayList<>();
        Development testDevelopment = createDevelopment("Richmond, VA","Claire Sparks",2021,15,someHouses);
        assertEquals("With a Development object who's yearEst instance variable is 2021, when calling getYearEst we",2021,testDevelopment.getYearEst());
    }

    @Test
    public void Development_setYearEstTest() {
        ArrayList<House> someHouses = new ArrayList<>();
        Development testDevelopment = createDevelopment("Richmond, VA","Claire Sparks",2021,15,someHouses);

        testDevelopment.setYearEst(2017);
        testVariable("yearEst",testDevelopment,2017,"After calling Development's setYearEst method with an argument of 2017, for the value of yearEst we");
    }

    @Test
    public void Development_getNumLotsTest() {
        ArrayList<House> someHouses = new ArrayList<>();
        Development testDevelopment = createDevelopment("Richmond, VA","Claire Sparks",2021,15,someHouses);
        assertEquals("With a Development object who's numLots instance variable is 15, when calling getNumLots we",15,testDevelopment.getNumLots());
    }

    @Test
    public void Development_setNumLotsTest() {
        ArrayList<House> someHouses = new ArrayList<>();
        Development testDevelopment = createDevelopment("Richmond, VA","Claire Sparks",2021,15,someHouses);

        testDevelopment.setNumLots(30);
        testVariable("numLots",testDevelopment,30,"After calling Development's setNumLots method with an argument of 30, for the value of numLots we");
    }

    @Test
    public void Development_getHousesTest() {
        ArrayList<House> someHouses = new ArrayList<>();
        someHouses.add(createHouse("Oak",151,3000.50,Bedrooms.THREE_BEDROOM,Baths.TWO,Color.BLUE));
        someHouses.add(createHouse("Elm",251,330.50,Bedrooms.STUDIO,Baths.ONE,Color.BRICK));

        ArrayList<House> expectedHouses = new ArrayList<>();
        expectedHouses.add(createHouse("Oak",151,3000.50, Bedrooms.THREE_BEDROOM, Baths.TWO, Color.BLUE));
        expectedHouses.add(createHouse("Elm",251,330.50,Bedrooms.STUDIO,Baths.ONE,Color.BRICK));

        Development testDevelopment = createDevelopment("Richmond, VA","Claire Sparks",2021,15,someHouses);
        testHouseArray("With a Development object who's houses instance variable is has two elements, when calling getHouses we",expectedHouses,testDevelopment.getHouses());
    }

    @Test
    public void Development_getNumHousesTest() {
        ArrayList<House> someHouses = new ArrayList<>();
        someHouses.add(createHouse("Oak",151,3000.50,Bedrooms.THREE_BEDROOM,Baths.TWO,Color.BLUE));
        someHouses.add(createHouse("Elm",251,330.50,Bedrooms.STUDIO,Baths.ONE,Color.BRICK));
        Development testDevelopment = createDevelopment("Richmond, VA","Claire Sparks",2021,15,someHouses);
        assertEquals("With a Development object who's Houses instance variable is has two elements, when calling getNumHouses we",2,testDevelopment.getNumHouses());
    }

    @Test
    public void Development_addHouseOneHouseTest() {
        ArrayList<House> someHouses = new ArrayList<>();
        ArrayList<House> expectedHouse = new ArrayList<>();
        expectedHouse.add(createHouse("Lucy",804,900.50,Bedrooms.ONE_BEDROOM,Baths.TWO,Color.GREEN));
        Development testDevelopment = createDevelopment("Richmond, VA","Claire Sparks",2021,15,someHouses);
        testDevelopment.addHouse(createHouse("Lucy",804,900.50,Bedrooms.ONE_BEDROOM,Baths.TWO,Color.GREEN));

        testVariable("houses",testDevelopment,expectedHouse,"With a Development object who's houses instance variable had zero elements, then calling addHouse once, when checking the houses instance variable we");
    }

    @Test
    public void Development_addHouseFourHousesTest() {
        ArrayList<House> someHouses = new ArrayList<>();
        ArrayList<House> expectedHouse = new ArrayList<>();
        expectedHouse.add(createHouse("Lucy",804,900.50,Bedrooms.ONE_BEDROOM,Baths.TWO,Color.GREEN));
        expectedHouse.add(createHouse("Julia",1740,540.50,Bedrooms.THREE_BEDROOM,Baths.THREE,Color.GRAY));
        expectedHouse.add(createHouse("Phoebe",405,600.50,Bedrooms.STUDIO,Baths.TWO,Color.GREEN));
        expectedHouse.add(createHouse("Stella",515,700.50,Bedrooms.ONE_BEDROOM,Baths.TWO,Color.BLUE));


        Development testDevelopment = createDevelopment("Richmond, VA","Claire Sparks",2021,15,someHouses);
        testDevelopment.addHouse(createHouse("Lucy",804,900.50,Bedrooms.ONE_BEDROOM,Baths.TWO,Color.GREEN));
        testDevelopment.addHouse(createHouse("Julia",1740,540.50,Bedrooms.THREE_BEDROOM,Baths.THREE,Color.GRAY));
        testDevelopment.addHouse(createHouse("Phoebe",405,600.50,Bedrooms.STUDIO,Baths.TWO,Color.GREEN));
        testDevelopment.addHouse(createHouse("Stella",515,700.50,Bedrooms.ONE_BEDROOM,Baths.TWO,Color.BLUE));


        testVariable("houses",testDevelopment,expectedHouse,"With a Development object who's houses instance variable had zero elements, then calling addHouse four times, when checking the houses instance variable we");
    }

    @Test
    public void Customer_toStringTest() {
        ArrayList<House> someHouse = new ArrayList<>();
        someHouse.add(createHouse("Phoebe",405,600.50,Bedrooms.STUDIO,Baths.TWO,Color.GREEN));
        Development testDevelopment = createDevelopment("Richmond, VA","Claire Sparks",2021,15,someHouse);


        assertEquals(  "Claire Sparks\n" +
                "Richmond, VA\n" +
                "2021\n" +
                "15\n" +
                "Houses:\n" +
                "\n" +
                "\tPhoebe\n" +
                "\t405\n" +
                "\t600.50\n" +
                "\tSTUDIO\n" +
                "\tTWO\n" +
                "\tGREEN\n", testDevelopment.toString());


        someHouse = new ArrayList<>();

        someHouse.add(createHouse("Lucy",804,900.50,Bedrooms.ONE_BEDROOM,Baths.TWO,Color.GREEN));
        someHouse.add(createHouse("Julia",1740,540.50,Bedrooms.THREE_BEDROOM,Baths.THREE,Color.GRAY));
        someHouse.add(createHouse("Phoebe",405,600.50,Bedrooms.STUDIO,Baths.TWO,Color.GREEN));
        someHouse.add(createHouse("Stella",515,700.50,Bedrooms.ONE_BEDROOM,Baths.TWO,Color.BLUE));

        testDevelopment = createDevelopment("Albuquerque, NM","Claire Sparks",1995,45,someHouse);

        assertEquals(  "Claire Sparks\n" +
                "Albuquerque, NM\n" +
                "1995\n" +
                "45\n" +
                "Houses:\n" +
                "\n" +
                "\tLucy\n" +
                "\t804\n" +
                "\t900.50\n" +
                "\tONE_BEDROOM\n" +
                "\tTWO\n" +
                "\tGREEN\n" +
                "\n" +
                "\tJulia\n" +
                "\t1740\n" +
                "\t540.50\n" +
                "\tTHREE_BEDROOM\n" +
                "\tTHREE\n" +
                "\tGRAY\n" +
                "\n" +
                "\tPhoebe\n" +
                "\t405\n" +
                "\t600.50\n" +
                "\tSTUDIO\n" +
                "\tTWO\n" +
                "\tGREEN\n" +
                "\n" +
                "\tStella\n" +
                "\t515\n" +
                "\t700.50\n" +
                "\tONE_BEDROOM\n" +
                "\tTWO\n" +
                "\tBLUE\n", testDevelopment.toString());
    }

    private Development createDevelopment(String aLocation, String anName, int aYearEst, int aNumLots, ArrayList anHouses){
        Development testDevelopment = new Development();
        Class c = testDevelopment.getClass();

        try {
            Field location = c.getDeclaredField("location");
            location.setAccessible(true);
            location.set(testDevelopment, aLocation);

            Field name = c.getDeclaredField("name");
            name.setAccessible(true);
            name.set(testDevelopment, anName);

            Field yearEst = c.getDeclaredField("yearEst");
            yearEst.setAccessible(true);
            yearEst.set(testDevelopment, aYearEst);

            Field numLots = c.getDeclaredField("numLots");
            numLots.setAccessible(true);
            numLots.set(testDevelopment, aNumLots);

            Field Houses = c.getDeclaredField("houses");
            Houses.setAccessible(true);
            Houses.set(testDevelopment, anHouses);

        } catch (Exception e) {
            fail(e.toString());
        }

        return testDevelopment;
    }

    private House createHouse(String anOwner, int aLotNumber, double aSquareFootage, Bedrooms aBedrooms, Baths aBaths, Color aColor){
        House testHouse = new House();
        @SuppressWarnings("rawtypes")
        Class c = testHouse.getClass();

        try {
            Field owner = c.getDeclaredField("owner");
            owner.setAccessible(true);
            owner.set(testHouse, anOwner);

            Field lotNumber = c.getDeclaredField("lotNumber");
            lotNumber.setAccessible(true);
            lotNumber.set(testHouse, aLotNumber);

            Field squareFootage = c.getDeclaredField("squareFootage");
            squareFootage.setAccessible(true);
            squareFootage.set(testHouse, aSquareFootage);

            Field bedrooms = c.getDeclaredField("bedrooms");
            bedrooms.setAccessible(true);
            bedrooms.set(testHouse, aBedrooms);

            Field baths = c.getDeclaredField("baths");
            baths.setAccessible(true);
            baths.set(testHouse, aBaths);

            Field Color = c.getDeclaredField("color");
            Color.setAccessible(true);
            Color.set(testHouse, aColor);

        } catch (Exception e) {
            fail(e.toString());
        }

        return testHouse;
    }

    private void instanceVariablePrivate(String aField, Object testObject) {
        Class c = testObject.getClass();
        try {
            c.getDeclaredField(aField);

            assertTrue("You must make your instance variables private.", Modifier.isPrivate(c.getDeclaredField(aField).getModifiers()));

        } catch (NoSuchFieldException e) {
            fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
        } catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    private void instanceVariableStatic(String aField, Object testObject) {
        Class c = testObject.getClass();
        try {
            c.getDeclaredField(aField);

            assertFalse("Your instance variables must NOT be static.", Modifier.isStatic(c.getDeclaredField(aField).getModifiers()));

        } catch (NoSuchFieldException e) {
            fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
        } catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    private void instanceVariableCorrectType(String aField, Class<?> aClass,  Object testObject) {
        Class c = testObject.getClass();
        try {
            c.getDeclaredField(aField);

            assertEquals("You must make the speed instance variable of type"+ aClass.toString() +".", aClass, c.getDeclaredField(aField).getType());

        } catch (NoSuchFieldException e) {
            fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
        } catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    private void testVariable(String aField, Object testObject, Object expected, String message){
        Class c = testObject.getClass();
        try {
            Field field = c.getDeclaredField(aField);
            field.setAccessible(true);
            Object fieldValue = field.get(testObject);

            if(expected == null){
                assertNull(message,fieldValue);
            }
            //If class is a double we have a special Junit assert to run
            else if(expected.getClass().equals(Double.class)){
                double doubleFieldValue = (double) fieldValue;
                double doubleExpected = (double) expected;
                assertEquals(message, doubleExpected, doubleFieldValue, .01);
            }
            //Array of some kind yay
            else if(expected.getClass().isArray()){

            }
            else if(expected.getClass().equals(ArrayList.class)){
                //CUSTOM FOR PROJECT6TESTS!!!
                testHouseArray(message,(ArrayList) expected, (ArrayList) fieldValue);
            }
            else{
                assertEquals(message, expected, fieldValue);
            }

        }
        catch (Exception e) {
            fail(e.toString());
        }
    }

    private void testHouseArray(String message, ArrayList expected, ArrayList actual){
        assertEquals(message + " looked at the size and ",expected.size(),actual.size());

        for(int i = 0; i < expected.size(); i++) {
            if (!HouseIsEqual(expected.get(i), actual.get(i))) {
                assertEquals(message + " looked at index "+i+" and ", expected, actual);
            }
        }
    }

    private boolean HouseIsEqual(Object o1, Object o2){
        Class c = o1.getClass();
        try {
            Field o1FieldOwner = c.getDeclaredField("owner");
            o1FieldOwner.setAccessible(true);
            Object o1Owner = o1FieldOwner.get(o1);

            Field o2FieldOwner = c.getDeclaredField("owner");
            o2FieldOwner.setAccessible(true);
            Object o2Owner = o2FieldOwner.get(o2);

            Field o1FieldSquareFootage = c.getDeclaredField("squareFootage");
            o1FieldSquareFootage.setAccessible(true);
            Object o1squareFootage = o1FieldSquareFootage.get(o1);

            Field o2FieldSquareFootage = c.getDeclaredField("squareFootage");
            o2FieldSquareFootage.setAccessible(true);
            Object o2squareFootage = o2FieldSquareFootage.get(o2);

            Field o1FieldLotNumber = c.getDeclaredField("lotNumber");
            o1FieldLotNumber.setAccessible(true);
            Object o1lotNumber = o1FieldLotNumber.get(o1);

            Field o2FieldLotNumber = c.getDeclaredField("lotNumber");
            o2FieldLotNumber.setAccessible(true);
            Object o2lotNumber = o2FieldLotNumber.get(o2);

            Field o1FieldBedrooms = c.getDeclaredField("bedrooms");
            o1FieldBedrooms.setAccessible(true);
            Object o1Bedrooms = o1FieldBedrooms.get(o1);

            Field o2FieldBedrooms = c.getDeclaredField("bedrooms");
            o2FieldBedrooms.setAccessible(true);
            Object o2Bedrooms = o2FieldBedrooms.get(o2);

            Field o1FieldBaths = c.getDeclaredField("baths");
            o1FieldBaths.setAccessible(true);
            Object o1Baths = o1FieldBaths.get(o1);

            Field o2FieldBaths = c.getDeclaredField("baths");
            o2FieldBaths.setAccessible(true);
            Object o2Baths = o2FieldBaths.get(o2);

            Field o1FieldColor = c.getDeclaredField("color");
            o1FieldColor.setAccessible(true);
            Object o1Color = o1FieldColor.get(o1);

            Field o2FieldColor = c.getDeclaredField("color");
            o2FieldColor.setAccessible(true);
            Object o2Color = o2FieldColor.get(o2);

            return  o1Owner.equals(o2Owner) &&
                    o1squareFootage.equals(o2squareFootage) &&
                    o1lotNumber.equals(o2lotNumber) &&
                    o1Bedrooms.equals(o2Bedrooms) &&
                    o1Baths.equals(o2Baths) &&
                    o1Color.equals(o2Color);


        } catch (NoSuchFieldException e) {
            fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
        } catch (Exception e) {
            fail("Something weird went wrong");
        }

        return false;
    }

}

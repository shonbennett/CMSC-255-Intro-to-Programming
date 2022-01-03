
import Project6.Baths;
import Project6.Bedrooms;
import Project6.Color;
import Project6.House;
import org.junit.Test;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class HouseTest {

    @Test
    public void House_instanceCountTest(){
        House testHouse = new House();
        @SuppressWarnings("rawtypes")
        Class c = testHouse.getClass();
        try {
            assertEquals(
                    "You must only have the instance variables specified. When looking at the number of instance variables we",
                    6, c.getDeclaredFields().length);
        }
        catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    @Test
    public void House_instanceVariablesTest(){
        House testHouse = new House();
        instanceVariablePrivate("owner",testHouse);
        instanceVariablePrivate("squareFootage",testHouse);
        instanceVariablePrivate("lotNumber",testHouse);
        instanceVariablePrivate("bedrooms",testHouse);
        instanceVariablePrivate("baths",testHouse);
        instanceVariablePrivate("color",testHouse);

        instanceVariableStatic("owner",testHouse);
        instanceVariableStatic("squareFootage",testHouse);
        instanceVariableStatic("lotNumber",testHouse);
        instanceVariableStatic("bedrooms",testHouse);
        instanceVariableStatic("baths",testHouse);
        instanceVariableStatic("color",testHouse);

        instanceVariableCorrectType("owner", String.class,testHouse);
        instanceVariableCorrectType("squareFootage", double.class,testHouse);
        instanceVariableCorrectType("lotNumber", int.class,testHouse);
        instanceVariableCorrectType("bedrooms", Bedrooms.class,testHouse);
        instanceVariableCorrectType("baths", Baths.class,testHouse);
        instanceVariableCorrectType("color", Color.class,testHouse);
    }

    @Test
    public void House_defaultConstructorTest() {
        House testHouse = new House();

        testVariable("owner",testHouse,"","When checking the value of owner we");
        testVariable("squareFootage",testHouse,500.00,"When checking the value of squareFootage we");
        testVariable("lotNumber",testHouse,1,"When checking the value of lotNumber we");
        testVariable("bedrooms",testHouse,Bedrooms.ONE_BEDROOM,"When checking the value of bedrooms we");
        testVariable("baths",testHouse,Baths.ONE,"When checking the value of baths we");
        testVariable("color",testHouse,Color.WHITE,"When checking the value of Color we");

    }

    @Test
    public void House_parameterizedConstructorTest() {
        House testHouse = new House("Zach Whitten",85,750.50,Bedrooms.TWO_BEDROOM,Baths.ONE,Color.GRAY);

        testVariable("owner",testHouse,"Zach Whitten","When checking the value of owner we");
        testVariable("squareFootage",testHouse,750.50,"When checking the value of squareFootage we");
        testVariable("lotNumber",testHouse,85,"When checking the value of lotNumber we");
        testVariable("bedrooms",testHouse,Bedrooms.TWO_BEDROOM,"When checking the value of bedrooms we");
        testVariable("baths",testHouse,Baths.ONE,"When checking the value of baths we");
        testVariable("color",testHouse,Color.GRAY,"When checking the value of Color we");

    }

    @Test
    public void House_getOwnerTest() {
        House testHouse = createHouse("Johnny",1968,900.05,Bedrooms.ONE_BEDROOM,Baths.THREE,Color.BRICK);
        assertEquals("With an House object who's owner instance variable is Johnny, when calling getOwner we","Johnny",testHouse.getOwner());
    }

    @Test
    public void House_setLocationNumberTest() {
        House testHouse = createHouse("Johnny",1968,900.05,Bedrooms.ONE_BEDROOM,Baths.THREE,Color.BRICK);
        testHouse.setOwner("Cash");
        testVariable("owner",testHouse,"Cash","After calling House's setOwner method with an argument of \"Cash\", for the value of owner we");
    }

    @Test
    public void House_getLotNumberTest() {
        House testHouse = createHouse("Carrie",1340,3000.50,Bedrooms.THREE_BEDROOM,Baths.TWO,Color.GRAY);
        assertEquals("With an House object who's lotNumber instance variable is 340.05, when calling getLotNumber we",1340,testHouse.getLotNumber(),.001);
    }

    @Test
    public void House_setLotNumberTest() {
        House testHouse = createHouse("Carrie",1340,3000.50,Bedrooms.THREE_BEDROOM,Baths.TWO,Color.GRAY);
        testHouse.setLotNumber(800);
        testVariable("lotNumber",testHouse,800,"After calling House's setLotNumber method with an argument of 2750.25, for the value of lotNumber we");
    }

    @Test
    public void House_getSquareFootageTest() {
        House testHouse = createHouse("Brandon",600,1300.50,Bedrooms.ONE_BEDROOM,Baths.ONE,Color.GREEN);
        assertEquals("With an House object who's squareFootage instance variable is 1300.50, when calling getSquareFootage we",1300.50,testHouse.getSquareFootage(),.001);
    }

    @Test
    public void House_setSquareFootageTest() {
        House testHouse = createHouse("Brandon",600,1300.50,Bedrooms.ONE_BEDROOM,Baths.ONE,Color.GREEN);
        testHouse.setSquareFootage(550.75);
        testVariable("squareFootage",testHouse,550.75,"After calling House's setSquareFootage method with an argument of 550.75, for the value of squareFootage we");
    }

    @Test
    public void House_getBedroomsTest() {
        House testHouse = createHouse("Dave",2002,750.50,Bedrooms.TWO_BEDROOM,Baths.ONE,Color.YELLOW);
        assertEquals("With an House object who's bedrooms instance variable is TWO_BEDROOM, when calling getBedrooms we",Bedrooms.TWO_BEDROOM,testHouse.getBedrooms());
    }

    @Test
    public void House_setBedroomsTest() {
        House testHouse = createHouse("Dave",2002,750.50,Bedrooms.TWO_BEDROOM,Baths.ONE,Color.YELLOW);
        testHouse.setBedrooms(Bedrooms.STUDIO);
        testVariable("bedrooms",testHouse,Bedrooms.STUDIO,"After calling House's setBedrooms method with an argument of Bedrooms.STUDIO, for the value of bedrooms we");
    }

    @Test
    public void House_getBathsTest() {
        House testHouse = createHouse("Dave",2002,750.50,Bedrooms.TWO_BEDROOM,Baths.ONE,Color.YELLOW);
        assertEquals("With an House object who's baths instance variable is Baths.ONE, when calling getBaths we",Baths.ONE,testHouse.getBaths());
    }

    @Test
    public void House_setBathsTest() {
        House testHouse = createHouse("Dave",2002,750.50,Bedrooms.TWO_BEDROOM,Baths.ONE,Color.YELLOW);
        testHouse.setBaths(Baths.TWO);
        testVariable("baths",testHouse,Baths.TWO,"After calling House's setBaths method with an argument of Bedrooms.STUDIO, for the value of baths we");
    }

    @Test
    public void House_getColorTest() {
        House testHouse = createHouse("Dave",2002,750.50,Bedrooms.TWO_BEDROOM,Baths.ONE,Color.YELLOW);
        assertEquals("With an House object who's Color instance variable is Color.YELLOW, when calling getBaths we",Color.YELLOW,testHouse.getColor());
    }

    @Test
    public void House_setColorTest() {
        House testHouse = createHouse("Dave",2002,750.50,Bedrooms.TWO_BEDROOM,Baths.ONE,Color.YELLOW);
        testHouse.setColor(Color.BLUE);
        testVariable("color",testHouse,Color.BLUE,"After calling House's setColor method with an argument of Color.BLUE, for the value of color we");
    }

    @Test
    public void House_toStringTest() {
        House testHouse = createHouse("Jack",5,5000.50,Bedrooms.STUDIO,Baths.ONE,Color.GREEN);
        assertEquals(  "\n\tJack\n" +
                "\t5\n" +
                "\t5000.50\n" +
                "\tSTUDIO\n" +
                "\tONE\n" +
                "\tGREEN\n", testHouse.toString());

        testHouse = createHouse("Rostam",444,404.40,Bedrooms.ONE_BEDROOM,Baths.ONE,Color.WHITE);
        assertEquals(  "\n" +
                "\tRostam\n" +
                "\t444\n" +
                "\t404.40\n" +
                "\tONE_BEDROOM\n" +
                "\tONE\n" +
                "\tWHITE\n", testHouse.toString());
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
        @SuppressWarnings("rawtypes")
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
        @SuppressWarnings("rawtypes")
        Class c = testObject.getClass();
        try {
            c.getDeclaredField(aField);

            assertEquals("Your instance variables must NOT be static.", false,
                    Modifier.isStatic(c.getDeclaredField(aField).getModifiers()));

        } catch (NoSuchFieldException e) {
            fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
        } catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    private void instanceVariableCorrectType(String aField, Class<?> aClass,  Object testObject) {
        @SuppressWarnings("rawtypes")
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
        @SuppressWarnings("rawtypes")
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

            }
            else{
                assertEquals(message, expected, fieldValue);
            }

        }
        catch (Exception e) {
            fail(e.toString());
        }
    }
}



import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import static org.junit.Assert.*;

@SuppressWarnings({"rawtypes"})
public class MoonTest {

    @Test
    public void Moon_instanceCountTest(){
        Moon testMoon = new Moon();
        Class c = testMoon.getClass();
        try {
            assertEquals(
                    "You must only have the instance variables specified. When looking at the number of instance variables we",
                    4, c.getDeclaredFields().length);
        }
        catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    @Test
    public void Moon_instanceVariablesTest(){
        Moon testMoon = new Moon();
        instanceVariablePrivate("name",testMoon);
        instanceVariablePrivate("radius",testMoon);
        instanceVariablePrivate("density",testMoon);
        instanceVariablePrivate("distance",testMoon);

        instanceVariableStatic("name",testMoon);
        instanceVariableStatic("radius",testMoon);
        instanceVariableStatic("density",testMoon);
        instanceVariableStatic("distance",testMoon);

        instanceVariableCorrectType("name", String.class,testMoon);
        instanceVariableCorrectType("radius", double.class,testMoon);
        instanceVariableCorrectType("density", double.class,testMoon);
        instanceVariableCorrectType("distance", double.class,testMoon);
    }

    @Test
    public void Moon_defaultConstructorTest() {
        Moon testMoon = new Moon();

        testVariable("name",testMoon,"","When checking the value of name we",0);
        testVariable("radius",testMoon,0.0,"When checking the value of radius we",0);
        testVariable("density",testMoon,0.0,"When checking the value of density we",0);
        testVariable("distance",testMoon,0.0,"When checking the value of distance we",0);
    }

    @Test
    public void Moon_parameterizedConstructorTest() {
        Moon testMoon = new Moon("Lysithea",1171700,12214.0,550390.0);

        testVariable("name",testMoon,"Lysithea","When checking the value of name we",0);
        testVariable("radius",testMoon,1171700.0,"When checking the value of radius we",0);
        testVariable("density",testMoon,12214.0,"When checking the value of density we",0);
        testVariable("distance",testMoon,550390.0,"When checking the value of distance we",0);

    }

    @Test
    public void Moon_toStringTest() {
        Moon testMoon = createMoon("Lysithea",1171700,12214.0,550390.0);
        assertEquals(  "Lysithea 1171700.00 12214.00 550390.00", testMoon.toString());

        testMoon = createMoon("Thebe",221900,23434.16,550390.0);
        assertEquals(  "Thebe 221900.00 23434.16 550390.00", testMoon.toString());
    }

    /*
    @Test
    public void Moon_equalsTest() {
        //new String() is not redundant, it is used to get around String interning.
        Moon testMoon = createMoon(new String("Campbell’s Soup Cans"),new String("Andy Warhol"),1204214.0,new String("1962 W 53rd St"));
        Moon testMoonClone = createMoon(new String("Campbell’s Soup Cans"),new String("Andy Warhol"),1204214.0,new String("1962 W 53rd St"));
        Moon testMoonOtherClone = createMoon(new String("Campbell’s Soup Cans"),new String("Andy Warhol"),1204214.0,new String("1962 W 53rd St"));
        Moon otherMoon = createMoon(new String("Autumn Rhythm"),new String("Jackson Pollock"),2343454.0,new String("1950 W 53rd St"));
        Moon thirdMoon = createMoon(new String("Infinity Mirror Room"),new String("Yayoi Kusama"),1204214.0,new String("1965 W 53rd St"));


        Class c = testMoon.getClass();
        //Test if equals takes a parameter of type City and fail them if it does
        try {
            c.getMethod("equals",Moon.class);
            fail("Moon's equals method should have a single parameter of type Object");
        } catch (NoSuchMethodException ignored) {}

        try {
            Method f = c.getMethod("equals",Object.class);

            //Test given null
            try {
                Object nullReference = null;
                assertFalse("When calling Moon's equals method with an argument of null, we", (boolean) f.invoke(testMoon, nullReference));
            }
            catch (Exception e){
                fail("When calling Moon's equals method with an argument of null, we got an exception. Try using the Debugger to figure out the issue! Place a debug point at the start of your equals method and debug this test case!");
            }

            //Test given a non state object
            try{
                assertFalse("When calling Moon's equals method with a non Moon argument, we", (boolean) f.invoke(testMoon,"NotAnMoon") );
            }
            catch (Exception e){
                fail("When calling Moon's equals method with a non Moon argument, we got an exception. Try using the Debugger to figure out the issue! Place a debug point at the start of your equals method and debug this test case!");
            }

            //Test given itself //reflexive
            try {
                assertTrue("When calling Moon's equals method with an argument of itself, we", (boolean) f.invoke(testMoon, testMoon));
            }
            catch (Exception e){
                fail("When calling Moon's equals method with an argument of itself, we got an exception. Try using the Debugger to figure out the issue! Place a debug point at the start of your equals method and debug this test case!");
            }

            //Test symmetric (x.equals(y) == y.equals(x)
            try{
                assertTrue("Symmetric test. Expected x.equals(y) == y.equals(x), was",(boolean) f.invoke(testMoon,testMoonClone) && (boolean) f.invoke(testMoonClone,testMoon));
            }
            catch (Exception e){
                fail("When calling Moon's equals method with a symmetric test. We expected x.equals(y) == y.equals(x) but we got an exception. Try using the Debugger to figure out the issue! Place a debug point at the start of your equals method and debug this test case!");
            }

            //Test transitive (x.equals(y) == y.equals(z) == x.equals(z))
            try{
                assertTrue("Transitive test. Expected x.equals(y) == y.equals(z) == x.equals(z), was",
                        (boolean) f.invoke(testMoon,testMoonClone) ==
                                (boolean) f.invoke(testMoonClone,testMoonOtherClone) ==
                                (boolean) f.invoke(testMoon,testMoonOtherClone)
                );
            }
            catch (Exception e){
                fail("When calling Moon's equals method with a transitive test. We expected x.equals(y) == y.equals(z) == x.equals(z) but we got an exception. Try using the Debugger to figure out the issue! Place a debug point at the start of your equals method and debug this test case!");
            }

            //Test non equivalent objects
            try{
                assertFalse("When calling Moon's equals method with an argument of a logically different Moon object, we",(boolean) f.invoke(testMoon,otherMoon) && (boolean) f.invoke(testMoon,thirdMoon));
            }
            catch (Exception e){
                fail("When calling Moon's equals method with an argument of a logically different Moon object, we got an exception. Try using the Debugger to figure out the issue! Place a debug point at the start of your equals method and debug this test case!");
            }

        } catch (NoSuchMethodException e) {
            fail("Something weird went wrong. " + e.toString());
        }
    }
    */

    @Test
    public void Moon_getNameTest() {
        Moon testMoon = createMoon("Metis",128000,442214.25,550390.00);
        assertEquals("With an Moon object who's name instance variable is Metis, when calling getName we","Metis",testMoon.getName());
    }

    @Test
    public void Moon_setNameTest() {
        Moon testMoon = createMoon("Metis",128000,442214.25,550390.00);
        testMoon.setName("Mneme");
        testVariable("name",testMoon,"Mneme","After calling Moon's setName method with an argument of Mneme, for the value of name we",0);
    }

    @Test
    public void Moon_getRadiusTest() {
        Moon testMoon = createMoon("Metis",128000,442214.25,550390.00);
        assertEquals("With an Moon object who's radius instance variable is 128000, when calling getRadius we",128000,testMoon.getRadius(),.001);
    }

    @Test
    public void Moon_setRadiusTest() {
        Moon testMoon = createMoon("Metis",128000,442214.25,550390.00);
        testMoon.setRadius(95000);
        testVariable("radius",testMoon,95000.0,"After calling Moon's setRadius method with an argument of 95000, for the value of radius we",0);
    }

    @Test
    public void Moon_getDensityTest() {
        Moon testMoon = createMoon("Metis",128000,442214.25,550390.00);
        assertEquals("With an Moon object who's density instance variable is 442214.25, when calling getDensity we",442214.25,testMoon.getDensity(),.001);
    }

    @Test
    public void Moon_setDensityTest() {
        Moon testMoon = createMoon("Metis",128000,442214.25,550390.00);
        testMoon.setDensity(150245.45);
        testVariable("density",testMoon,150245.45,"After calling Moon's setDensity method with an argument of 150245.45, for the value of density we",0);
    }

    @Test
    public void Moon_getDistanceTest() {
        Moon testMoon = createMoon("Metis",128000,442214.25,550390.00);
        assertEquals("With an Moon object who's distance instance variable is 550390.00, when calling getDistance we",550390.00,testMoon.getDistance(),.001);
    }

    @Test
    public void Moon_setDistanceTest() {
        Moon testMoon = createMoon("Metis",128000,442214.25,550390.00);
        testMoon.setDistance(225390.00);
        testVariable("distance",testMoon,225390.00,"After calling Moon's setDistance method with an argument of 225390.00, for the value of distance we",0);
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

            assertEquals("Your instance variables must NOT be static.", false,
                    Modifier.isStatic(c.getDeclaredField(aField).getModifiers()));

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

    private void testVariable(String aField, Object testObject, Object expected, String message, int descendantLevel){
        Class c = testObject.getClass();

        for(int i = 0; i < descendantLevel; i++){
            c = c.getSuperclass();
        }

        try {
            Field field = c.getDeclaredField(aField);
            field.setAccessible(true);
            Object fieldDensity = field.get(testObject);

            if(expected == null){
                assertNull(message,fieldDensity);
            }
            //If class is a double we have a special Junit assert to run
            else if(expected.getClass().equals(Double.class)){
                double doubleFieldDensity = (double) fieldDensity;
                double doubleExpected = (double) expected;
                assertEquals(message, doubleExpected, doubleFieldDensity, .001);
            }
            //Array of some kind yay
            else if(expected.getClass().isArray()){

            }
            else if(expected.getClass().equals(ArrayList.class)){

            }
            else{
                assertEquals(message, expected, fieldDensity);
            }

        }
        catch (Exception e) {
            fail(e.toString());
        }
    }
    
}

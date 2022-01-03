

import org.junit.*;
import org.junit.rules.Timeout;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;

public class MoonSamplesTest {


    private final double[][] givenSamples = {{8.3, 4.5, 6.7, 2.3, 12.5, 4.5}, {3.9, 1.8, 34.7, 23.5, 1.2, 14.3}, {6.7, 7.4, 1.5, 18.4, 7.2, 23.7}, {23.4, 5.6, 2.9, 18.5, 39.5, 18.2}, {15.4, 5.3, 27.4, 9.8, 3.8, 27.4}};
    private final String[] givenElements = {"carbon-dioxide","magnesium","sodium","potassium","chloride","water"};

    private final double[][] unknownSamples = {{29.3, 2.5, 5.7, 5.3, 10.5, 14.5}, {5.9, 2.8, 3.7, 23.9, 16.2, 14.1}, {16.7, 17.4, 11.5, 1.4, 17.2, 3.7}, {23.2, 15.6, 2.2, 1.5, 29.5, 28.3}, {12.4, 15.3, 20.4, 19.8, 3.8, 17.4}};

    private final double[][] actualSamples = {{0,0,0,0,0,0,0,20.4,0,0,0,10.5,6.3,9.5,0,0,0,0,0,16.7,0,20.6,0,0,0,2.4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,10.3,0,0,0,3.5,8.9,2.7,0,0,0,0,0,6.7,0,2.1,0,0,0,20.4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,22.6,0,0,0,8.2,9.3,10.5,0,0,0,0,0,12.6,0,15.2,0,0,0,9.6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,26.5,0,0,0,10.3,16.3,8.7,0,0,0,0,0,26.8,0,10.3,0,0,0,7.7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,22.4,0,0,0,14.6,9.2,3.7,0,0,0,0,0,19.3,0,27.6,0,0,0,3.4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,28.8,0,0,0,14.6,8.3,12.2,0,0,0,0,0,8.7,0,15.9,0,0,0,5.4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};

    private final String[] actualElements = {"Hydrogen","Helium","Lithium","Beryllium","Boron","Carbon","Nitrogen","Oxygen","Fluorine","Neon","Sodium","Magnesium","Aluminium","Silicon","Phosphorus","Sulfur","Chlorine","Argon","Potassium","Calcium","Scandium","Titanium","Vanadium","Chromium","Manganese","Iron","Cobalt","Nickel","Copper","Zinc","Gallium","Germanium","Arsenic","Selenium","Bromine","Krypton","Rubidium","Yttrium","Zirconium","Niobium","Molybdenum","Technetium","Ruthenium","Rhodium","Palladium","Silver","Cadmium","Indium","Tin","Antimony","Tellurium","Iodine","Xenon","Caesium","Barium","Lanthanum","Cerium","Praseodymium","Neodymium","Promethium","Samarium","Europium","Gadolinium","Terbium","Dysprosium","Holmium","Erbium","Thulium","Ytterbium","Lutetium","Hafnium","Tantalum","Tungsten","Rhenium","Osmium","Iridium","Platinum","Gold","Mercury","Thallium","Lead","Bismuth","Polonium","Astatine","Radon","Francium","Radium","Actinium","Thorium","Protactinium","Uranium","Neptunium","Plutonium","Americium","Curium","Berkelium","Californium","Einsteinium","Fermium","Mendelevium","Nobelium","Lawrencium","Rutherfordium","Dubnium","Seaborgium","Bohrium","Hassium","Meitnerium","Darmstadtium","Roentgenium","Copernicium","Nihonium","Flerovium","Moscovium","Livermorium","Tennessine","Oganesson"};


    @Test
    public void getElementsGivenDataTest(){
        String[] expected = givenElements;
        Assert.assertArrayEquals( expected, MoonSamples.getElements("carbon-dioxide,magnesium,sodium,potassium,chloride,water"));
    }

    @Test
    public void getElementsUnknownDataTest(){
        String[] expected = actualElements;
        Assert.assertArrayEquals( expected, MoonSamples.getElements("Hydrogen,Helium,Lithium,Beryllium,Boron,Carbon,Nitrogen,Oxygen,Fluorine,Neon,Sodium,Magnesium,Aluminium,Silicon,Phosphorus,Sulfur,Chlorine,Argon,Potassium,Calcium,Scandium,Titanium,Vanadium,Chromium,Manganese,Iron,Cobalt,Nickel,Copper,Zinc,Gallium,Germanium,Arsenic,Selenium,Bromine,Krypton,Rubidium,Yttrium,Zirconium,Niobium,Molybdenum,Technetium,Ruthenium,Rhodium,Palladium,Silver,Cadmium,Indium,Tin,Antimony,Tellurium,Iodine,Xenon,Caesium,Barium,Lanthanum,Cerium,Praseodymium,Neodymium,Promethium,Samarium,Europium,Gadolinium,Terbium,Dysprosium,Holmium,Erbium,Thulium,Ytterbium,Lutetium,Hafnium,Tantalum,Tungsten,Rhenium,Osmium,Iridium,Platinum,Gold,Mercury,Thallium,Lead,Bismuth,Polonium,Astatine,Radon,Francium,Radium,Actinium,Thorium,Protactinium,Uranium,Neptunium,Plutonium,Americium,Curium,Berkelium,Californium,Einsteinium,Fermium,Mendelevium,Nobelium,Lawrencium,Rutherfordium,Dubnium,Seaborgium,Bohrium,Hassium,Meitnerium,Darmstadtium,Roentgenium,Copernicium,Nihonium,Flerovium,Moscovium,Livermorium,Tennessine,Oganesson"));
    }


    @Test
    public void getSamplesGivenDataTest(){
        double[][] expected = givenSamples;
        Assert.assertArrayEquals( expected, MoonSamples.getSamples("8.3,4.5,6.7,2.3,12.5,4.5<>3.9,1.8,34.7,23.5,1.2,14.3<>6.7,7.4,1.5,18.4,7.2,23.7<>23.4,5.6,2.9,18.5,39.5,18.2<>15.4,5.3,27.4,9.8,3.8,27.4"));
    }

    @Test
    public void getSamplesUnknownDataTest(){
        double[][] expected = actualSamples;
        Assert.assertArrayEquals( expected, MoonSamples.getSamples("0,0,0,0,0,0,0,20.4,0,0,0,10.5,6.3,9.5,0,0,0,0,0,16.7,0,20.6,0,0,0,2.4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0<>0,0,0,0,0,0,0,10.3,0,0,0,3.5,8.9,2.7,0,0,0,0,0,6.7,0,2.1,0,0,0,20.4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0<>0,0,0,0,0,0,0,22.6,0,0,0,8.2,9.3,10.5,0,0,0,0,0,12.6,0,15.2,0,0,0,9.6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0<>0,0,0,0,0,0,0,26.5,0,0,0,10.3,16.3,8.7,0,0,0,0,0,26.8,0,10.3,0,0,0,7.7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0<>0,0,0,0,0,0,0,22.4,0,0,0,14.6,9.2,3.7,0,0,0,0,0,19.3,0,27.6,0,0,0,3.4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0<>0,0,0,0,0,0,0,28.8,0,0,0,14.6,8.3,12.2,0,0,0,0,0,8.7,0,15.9,0,0,0,5.4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0"));
    }


    @Test
    public void searchForLifeGivenDataTest(){

        int[] expected = {4,5};
        Assert.assertArrayEquals( expected, MoonSamples.searchForLife(givenSamples));
    }

    @Test
    public void searchForLifeUnknownDataTest(){

        int[] expected = {1,4,5};
        Assert.assertArrayEquals( expected, MoonSamples.searchForLife(unknownSamples));
    }

    @Test
    public void searchHighestElementsGivenDataTest(){

        Assert.assertEquals("When checking the result of searchHighestElements for sample #1, we","chloride and carbon-dioxide",MoonSamples.searchHighestElements(givenSamples,givenElements,1));

        Assert.assertEquals("When checking the result of searchHighestElements for sample #3, we","water and potassium",MoonSamples.searchHighestElements(givenSamples,givenElements,3));
    }

    @Test
    public void searchHighestElementsUnknownDataTest(){

        Assert.assertEquals("When checking the result of searchHighestElements for sample #1, we","Titanium and Oxygen",MoonSamples.searchHighestElements(actualSamples,actualElements,1));

        Assert.assertEquals("When checking the result of searchHighestElements for sample #3, we","Oxygen and Titanium",MoonSamples.searchHighestElements(actualSamples,actualElements,3));
    }


    @Test
    public void searchHighestSampleGivenDataTest(){

        Assert.assertEquals("When checking the result of searchHighestSample for magnesium, we",3,MoonSamples.searchHighestSample(givenSamples,givenElements,"magnesium"));

        Assert.assertEquals("When checking the result of searchHighestSample for water, we",5,MoonSamples.searchHighestSample(givenSamples,givenElements,"water"));

        Assert.assertEquals("When checking the result of searchHighestSample for folsom (a non-existent element), we",-1,MoonSamples.searchHighestSample(givenSamples,givenElements,"folsom"));
    }

    @Test
    public void searchHighestSampleUnknownDataTest(){

        Assert.assertEquals("When checking the result of searchHighestSample for Oxygen, we",6,MoonSamples.searchHighestSample(actualSamples,actualElements,"Oxygen"));

        Assert.assertEquals("When checking the result of searchHighestSample for Titanium, we",5,MoonSamples.searchHighestSample(actualSamples,actualElements,"Titanium"));

        Assert.assertEquals("When checking the result of searchHighestSample for folsom (a non-existent element), we",-1,MoonSamples.searchHighestSample(actualSamples,actualElements,"folsom"));
    }


}

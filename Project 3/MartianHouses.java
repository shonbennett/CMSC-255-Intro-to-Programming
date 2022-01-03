
/*
 * MartianHouses.java
 *
 * This program takes the user's name, and the side length of a house. The side length is then used to calculate the surface area and cost. The name, surface area and cost is
 * then printed out. The user can enter multiple houses, or just one house if they would like.
 *
 * Shon Bennett
 * September 30, 2021
 * CMSC 255 Section 901
 */
import java.util.Scanner;
public class MartianHouses {
	public static void main(String[] args){
		
		//scanner object created
		Scanner input = new Scanner (System.in);
		//String variable answer is set to yes. The variable answer will be sued in the while loop boolean condition
		String answer = "yes";
		
		//this while loop runs as long as answer equals anything other than "no"
		while (answer.compareTo("no") != 0){ 
			//user prompted to enter their name. String variable name takes in the user's name
			System.out.println("Enter the settler's name: ");
			String name = input.nextLine();
			
			//the user is prompted to enter the house side lengths. the side length is taken in through the variable s
			System.out.println("Enter the length of a side of the house: ");
			double s = input.nextDouble();
			
			//side length taken in through variable s is used to calculate the floor and roof area. floor and roof area has the same 
			//formula, so I used floorRoofArea to calculate them both.
			double floorRoofArea = 2 * Math.pow(s,2) * (1 + Math.pow(2, .5));
			
			//outer-walls area is calculated using the variable s
			double outerWallsArea = 8 * 12 * s;
			
			//surface area is calcularted by adding the floor, roof and outer-walls area. I used the floorRoofArea variable twice
			//in this equation since florRoofArea was used to calculate both the floor and roof area.
			double surfaceArea = floorRoofArea + floorRoofArea + outerWallsArea;
			
			//totalCost multiplies the surfaceArea by 14.50 to find the cost of the house
			double totalCost = 14.50 * surfaceArea; 
			
			//results are printed using the printf formatting method
			System.out.printf("%s has a house surface area of %,.2f and cost of $%,.2f\n", name, surfaceArea, totalCost);
			
			//the user is asked if they would like to enter another hosue
			System.out.println("Would you like to enter another house? Enter no to exit.");
			
			//this blank input line is here so we can avoid the error of catching incorrect input. The user then enters another String input
			//that is stored in variable answer. As long as the input is not "no", the loop will run again.
			input.nextLine();
			answer = input.nextLine().trim();
		} 
	}
}
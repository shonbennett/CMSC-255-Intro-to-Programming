/*
 * MartianMoney.java
 *
 * This program uses dimensional analysis to convert US dollar amounts to Martian currency. The US dollar amount can either be randomly
   generated by the computer, or inputted by the user. 
 * Shon Bennett
 * September 16, 2021
 * CMSC 255 Section 901
 */
import java.util.Scanner;
public class MartianMoney {
	public static void main(String[] args){
		//create scanner object
		Scanner input = new Scanner(System.in);
		//prompt user to input 1 or 2. 1 will randomlly generate a number, 2 will allow them to enter their own number
		System.out.println("Would you like to randomly enter a dollar amount (1) or enter it yourself (2)?"); 
		int inputtedAmount = input.nextInt();
		
		
		//if 1 was entered, the number is randomly generated and then converted 
		if (inputtedAmount == 1){
			int dollarAmount = (int)((Math.random() * 9999921) + 80); //how do i use math random? it says that it is declared incorrectly
			
			//display randomly generated number to user
			System.out.println(dollarAmount);
			
			//dollarAmount is converted to tintinas. tintina is then converted back to US dollars and named tintinaToDollars.
			//leftoverAmount is created by subtracted the tintinaToDollars from dollarAmount. the leftoverAmount will be key to converting the remaining amount to Martian currency
			int tintina = dollarAmount / 80;
			int tintinaToDollars = tintina * 80;
			double leftoverAmount = dollarAmount - tintinaToDollars; 
			
			//the leftoverAmount is converted to sutton. the sutton is then converted back to the US dollar amount and named suttonToDollars.
			//suttonToDollars is subtracted from leftoverAmount. 
			double sutton =leftoverAmount / 80 * 5; 
			sutton = (int)sutton;
			double suttonToDollars = (int)(sutton / 5 * 80); 
			leftoverAmount -= suttonToDollars; 
			
			//leftoverAmount is converted from US dollars to knorr. the knorr is then converted back to the US dollar amount, and then subtracted from leftoverAmount
			double knorr = (int)(leftoverAmount / 80 * 5 * 2);  
			double knorrToDollars = (int)(knorr / 2 / 5 * 80);
			leftoverAmount -= knorrToDollars;
			
			//the remaing amount of US dollars, which is represented by leftoverAmount, is converted to wernicke. the wernicke amount is converted back to US dollars and 
			//subtracted from the leftoverAmount
			double wernicke = (int)(leftoverAmount / 80 * 5 * 2 * 8);
			double wernickeToDollars = (int)(wernicke / 8 / 2 / 5 * 80);
			leftoverAmount -= wernickeToDollars;
			
			//The conversions are done. Now, we print out the randomly generated amount in Martian currency. use a series of if statements that prints out the conversions only if greater than 0
		System.out.print(dollarAmount + " is ");
		
			if (tintina > 0){
				System.out.print(tintina + " tintina ");
			}
			if (sutton > 0){
				System.out.print((int)sutton + " sutton ");
			}
			if (knorr > 0){
				System.out.print((int)knorr + " knorr ");
			}
			if (wernicke > 0){
				System.out.print((int)wernicke + " wernicke");
			}
			}
			
			//this is a totally different route. if input equals 2, then we prompt the user to enter dollar amount
		else if (inputtedAmount == 2){
			System.out.println("Enter a dollar amount between $80 and $10,000,000.");
			int dollarAmount = input.nextInt();
			
			//if dollarAmount is in range, the convert it into Martian Money
			if (dollarAmount >= 80 && dollarAmount <= 10000000){
				
			//dollarAmount is converted to tintinas. tintina is then converted back to US dollars and named tintinaToDollars.
			//leftoverAmount is created by subtracted the tintinaToDollars from dollarAmount. the leftoverAmount will be key to converting the remaining amount to Martian currency
			int tintina = dollarAmount / 80;  
			int tintinaToDollars = tintina * 80;
			double leftoverAmount = dollarAmount - tintinaToDollars; 
			
			//the leftoverAmount is converted to sutton. the sutton is then converted back to the US dollar amount and named suttonToDollars.
			//suttonToDollars is subtracted from leftoverAmount. 
			double sutton =leftoverAmount / 80 * 5; 
			sutton = (int)sutton;
			double suttonToDollars = (int)(sutton / 5 * 80); 
			leftoverAmount -= suttonToDollars; 
			
			//leftoverAmount is converted from US dollars to knorr. the knorr is then converted back to the US dollar amount, and then subtracted from leftoverAmount
			double knorr = (int)(leftoverAmount / 80 * 5 * 2); 
			double knorrToDollars = (int)(knorr / 2 / 5 * 80);
			leftoverAmount -= knorrToDollars;
			
			//the remaing amount of US dollars, which is represented by leftoverAmount, is converted to wernicke. the wernicke amount is converted back to US dollars and 
			//subtracted from the leftoverAmount
			double wernicke = (int)(leftoverAmount / 80 * 5 * 2 * 8); 
			double wernickeToDollars = (int)(wernicke / 8 / 2 / 5 * 80);
			leftoverAmount -= wernickeToDollars;
			
		//print out user amount in Martian currency. use a series of if statements that prints out the conversions if greater than 0
		System.out.print(dollarAmount + " is ");
		
			if (tintina > 0){
				System.out.print(tintina + " tintina ");
			}
			if (sutton > 0){
				System.out.print((int)sutton + " sutton ");
			}
			if (knorr > 0){
				System.out.print((int)knorr + " knorr ");
			}
			if (wernicke > 0){
				System.out.print((int)wernicke + " wernicke");
			}
			}
			
			//if an amount is entered that is not in range not between 80 and 10000000, print "incorrect input"
			else {
				System.out.println("Incorrect input");
			}
			
		}
			//if the input was anything other than 1 or 2, the program will output "Incorrect input"
		else {
			System.out.println("Incorrect input");
		}
	}
	
}


		
		
		
		
		
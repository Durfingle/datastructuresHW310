import java.util.Scanner;
/**
 * Program #6a
 * @description This program will take a user input number and print
 * 				the descending numbers in order.
 * 
 * CS108-3
 * @date 04/06/2017
 * @author Juan Pina-Sanz
 *
 */
public class Prog6a {

	public static void main(String Args[]){
		System.out.println("Program 6, Juan Pina-Sanz, sccs0549");
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		countdown(n);
		scan.close();
	}
	
	/**
	 * The countdown method is a recursive method that prints the values of
	 * the input n. The method then calls itself on a value of n-1. 
	 * The method will stop when the value of n is less than or equal to 1 printing
	 * 1 as the final value. 
	 * @param n An integer value to be counted down and printed.
	 */
	public static void countdown(int n){
		
		if(n >= 1){
			System.out.print(n + " ");
			countdown(n-1);
		}
	}
}
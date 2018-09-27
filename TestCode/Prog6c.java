
import java.util.Scanner;
/**
 * Program #6c
 * @description This Class utilizes a recursive method to calculate the 
 * 				exponent product of two user input values.
 * 
 * CS108-3
 * @date 04/06/2017
 * @author Juan Pina-Sanz
 *
 */
public class Prog6c {

	public static void main(String Args[]){
		System.out.println("Program 6, Juan Pina-Sanz, sccs0549");
		Scanner scan = new Scanner(System.in);
		double n = scan.nextDouble();
		int x = scan.nextInt();
		System.out.printf("%.3f", power(n,x));
		scan.close();
	}
	/**
	 * The method, power, is a recursive method that takes in two inputs,
	 * a double and an int and applies the int as an exponent power to the double.
	 * Using recursion the method will continue to call itself applying the value, n,
	 * to itself until it finally calculates the correct number.
	 * @param n the first value is the base number, this number can be a double.
	 * @param x the second value is the power that the base value is being raised by.
	 * @return This method will return a double.
	 */
	public static double power(double n, int x){

		if( x == 0){
			return 1;
		}else{
			if( x == 1){
				return n;
			}else{
				if( x > 0){
					return n*power(n, x-1);
				}
			}
		}
		return 0;
	}
	
}

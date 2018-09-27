import java.util.Scanner;

/**
 * Program #6b
 * @description This program will take a user input integer and print
 * 				the number in reverse order.
 * 
 * CS108-3
 * @date 04/06/2017
 * @author Juan Pina-Sanz
 *
 */
public class Prog6b {

	public static void main(String Args[]){
		System.out.println("Program 6, Juan Pina-Sanz, sccs0549");
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		reverse(n);
		scan.close();
	}
	/**
	 * The reverse method is a recursive method that will take the int
	 * value, n, and print a number that is the reverse of the input number.
	 * @param n An integer value to be printed in reverse
	 */
	public static void reverse(int n){
		if (n < 10){
			System.out.println(n);
		}else{
			System.out.print(n % 10);
			reverse(n/10);
		}
	}
}

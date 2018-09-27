import java.util.Scanner;
import java.lang.StringBuilder;

/**
 * Program #6d
 * @description This Class is meant for calculating the factorial of a
 * 				user input and then printing the product of each value.
 * 
 * CS108-3
 * @date 04/06/2017
 * @author Juan Pina-Sanz
 *
 */
public class Prog6d {

	static StringBuilder txt = new StringBuilder(" ");
	static int n;

	public static void main(String Args[]){
		System.out.println("Program 6, Juan Pina-Sanz, sccs0549");
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		//fact(txt, n);
		printFactorial(txt, n);
		scan.close();
	}

	/**
	 * The factorial method is a recursive method that takes as input an
	 * integer and produces the factorial product of that integer
	 * @param i Any int type value
	 * @return This method will return the factorial product of the 
	 * 			argument as an int.
	 */
	public static int factorial(int i){

		if (i == 1){
			return 1;
		}	

		if (i >= 1){
			return i*factorial(i-1);
		}

		return 0;
	}

	/**
	 * The printFactorial method will print the factorial results in the 
	 * format shown in the rubric.
	 * @param s The method utilizes a StringBuilder object to append and delete 
	 * 			the spaces going into the print method
	 * @param i The i parameter is an int. This int is a value meant to be pulled 
	 * 			from the factorial method.
	 */
	public static void printFactorial(StringBuilder s, int i){

		if(i >= 1){
			System.out.printf("%sfactorial(%d)\n", s,i);
			s = s.append("   ");
			printFactorial(s, i-1);
		}

		if(i == 1){
			System.out.printf("%sreturn %d\n", s, i);
			s = s.delete(s.indexOf(" "),3);
			System.out.printf("%sreturn %d*%d = %d \n", s, i, i, i);
			s = s.delete(s.indexOf(" "),3);
		}

		if(i > n+1){
			System.out.printf("%sreturn %d*%d = %d \n ", s, i, factorial(i-1), factorial(i));
			s = s.delete(s.indexOf(" "), 3);
		}

	}

}

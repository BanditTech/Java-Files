import java.util.Scanner;

public class Input {
	public static void main(String[] args) {
		Scanner keys = new Scanner(System.in);
		System.out.println("\nWhat is your input?");
		String input = keys.nextLine();
		System.out.println(response(input));
		keys.close();
	}

	public static String response(String input) {
		return "Omg, " + input + "... Thats hilarious!";
	}
}

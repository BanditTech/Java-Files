import java.util.ArrayList;

public class Arrays {
	public static void main(String[] args) {
		String[] clients = { "Jim", "Joe", "Bob", "Mary" };
		ArrayList<String> clientsList = new ArrayList<>();
		for (String client : clients) {
			clientsList.add(client);
		}
		System.out.println("\n" + clientsList.size());
	}
}

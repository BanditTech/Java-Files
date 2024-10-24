import java.util.ArrayList;
import java.util.Scanner;

// Class representing a User
class User {
	protected String username;
	protected String password;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public boolean validatePassword(String password) {
		return this.password.equals(password);
	}

	public void manageAccount() {
		System.out.println("Standard user: " + username + " can manage account information.");
	}
}

// Class representing an Admin (inherits from User)
class Admin extends User {
	public Admin(String username, String password) {
		super(username, password);
	}

	@Override
	public void manageAccount() {
		System.out.println("Admin user: " + username + " can perform maintenance functions.");
		System.out.println("1. View all users");
		System.out.println("2. Delete user");
		System.out.println("3. Log out");
	}
}

// Class managing the list of Accounts
class UserManager {
	private ArrayList<User> users = new ArrayList<>();
	private Scanner scanner = new Scanner(System.in);

	public void start() {
		ensureDefaultAdmin();
		boolean running = true;
		while (running) {
			System.out.println("Welcome to User Manager\n\n");
			System.out.println("1. Log in");
			System.out.println("2. Create an account");
			System.out.println("3. Exit");
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // consume newline character
			clearScreen();
			switch (choice) {
				case 1:
					login();
					break;
				case 2:
					createAccount();
					break;
				case 3:
					running = false;
					break;
				default:
					System.out.println("Invalid option. Please try again.");
			}
		}
	}

	// Check if there is no admin and create a default admin if necessary
	private void ensureDefaultAdmin() {
		boolean adminExists = false;
		for (User user : users) {
			if (user instanceof Admin) {
				adminExists = true;
				break;
			}
		}

		if (!adminExists) {
			System.out.println("Creating default admin account. (username: admin, password: admin123)");
			createAccount("admin", "admin123", true);
		}
	}

	private void login() {
		System.out.print("Enter username: ");
		String username = scanner.nextLine();
		System.out.print("Enter password: ");
		String password = scanner.nextLine();

		for (User user : users) {
			if (user.getUsername().equals(username) && user.validatePassword(password)) {
				if (user instanceof Admin) {
					adminMenu((Admin) user);
				} else {
					userMenu(user);
				}
				return;
			}
		}
		System.out.println("Invalid username or password.");
	}

	private void createAccount() {
		System.out.print("Enter new username: ");
		String username = scanner.nextLine();
		System.out.print("Enter new password: ");
		String password = scanner.nextLine();
		createAccount(username, password);
	}

	public void createAccount(String username, String password) {
		createAccount(username, password, false);
	}

	private void createAccount(String username, String password, boolean Admin) {
		if (Admin) {
			users.add(new Admin(username, password));
			System.out.println("Admin account created: " + username);
		} else {
			users.add(new User(username, password));
			System.out.println("Standard user account created: " + username);
		}
	}

	private void userMenu(User user) {
		boolean loggedIn = true;
		while (loggedIn) {
			System.out.println("\nWelcome, " + user.getUsername());
			System.out.println("1. Manage account");
			System.out.println("2. Log out");
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // consume newline

			switch (choice) {
				case 1:
					user.manageAccount();
					break;
				case 2:
					loggedIn = false;
					System.out.println("Logged out.");
					break;
				default:
					System.out.println("Invalid option. Please try again.");
			}
		}
	}

	private void adminMenu(Admin admin) {
		boolean loggedIn = true;
		while (loggedIn) {
			System.out.println("\nAdmin: " + admin.getUsername());
			admin.manageAccount();
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // consume newline

			switch (choice) {
				case 1:
					viewAllUsers();
					break;
				case 2:
					deleteUser();
					break;
				case 3:
					loggedIn = false;
					System.out.println("Logged out.");
					break;
				default:
					System.out.println("Invalid option. Please try again.");
			}
		}
	}

	private void viewAllUsers() {
		System.out.println("Listing all users:");
		for (User user : users) {
			System.out.println("- " + user.getUsername());
		}
	}

	private void deleteUser() {
		System.out.print("Enter the username of the user to delete: ");
		String username = scanner.nextLine();
		users.removeIf(user -> user.getUsername().equals(username));
		System.out.println("User " + username + " deleted (if they existed).");
	}

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}

// main method insertion point
public class UserManager_demo {
	public static void main(String[] args) {
		System.out.println("");
		UserManager accountManager = new UserManager();
		accountManager.createAccount("user1", "password");
		accountManager.createAccount("user2", "password");
		accountManager.createAccount("user3", "password");
		accountManager.createAccount("user4", "password");
		accountManager.start();
	}
}
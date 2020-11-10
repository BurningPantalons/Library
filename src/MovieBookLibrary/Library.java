package MovieBookLibrary;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Library {

	private LibraryManager itemLibrary;

	public Library(String movBookPath) {
		try {
			itemLibrary = new LibraryManager(movBookPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		boolean running = true;
		itemLibrary.readParseFile();
		Scanner scanner = new Scanner(System.in);
		System.out.print("Hello user!\nWelcome to the Library!");
		String userInput;

		while (running) {
			System.out.printf("\nEnter command:\nFor help, write help + enter.\n>");
			userInput = scanner.nextLine();
			Command command = parseCommand(userInput);

			if (command == Command.QUIT) {
				handleQuitCommand();
			} else if (command == Command.UNKNOWN) {
				printUnknownCommand();
				continue;
			}

			if (command == Command.REGISTER) {
				System.out.print(
						"\nWhat kind of item do you want to register?\nfor Book, press (b) + enter\nfor Movie, press (m) + enter.");
				try {
					String bookOrMovie = scanner.nextLine();
					itemLibrary.register(bookOrMovie);
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
			}

			if (command == Command.DEREGISTER) {
				itemLibrary.searchLibrary("Deregister");
			}

			if (command == Command.CHECKOUT) {
				itemLibrary.searchLibrary("Checkout");
			}

			if (command == Command.CHECKIN) {
				itemLibrary.searchLibrary("Checkin");
			}

			if (command == Command.HELP) {
				printHelp();
			}

			if (command == Command.LIST) {
				itemLibrary.list();
			}

			if (command == Command.INFO) {
				itemLibrary.searchLibrary("Info");
			}

		}
		//scanner.close();
	}

	public static Command parseCommand(String userInput) {
		String command = userInput;
		switch (command) {

		case "register":
		case "r":
			return Command.REGISTER;

		case "deregister":
		case "delete":
			return Command.DEREGISTER;

		case "checkout":
		case "out":
			return Command.CHECKOUT;

		case "checkin":
		case "in":
			return Command.CHECKIN;

		case "help":
		case "h":
			return Command.HELP;

		case "list":
			return Command.LIST;

		case "info":
			return Command.INFO;

		case "quit":
		case "q":
		case "exit":
			return Command.QUIT;
		default:
			return Command.UNKNOWN;
		}

	}

	public static void printUnknownCommand() {
		System.out.print("\nUnknown command, please try again");
		return;
	}

	public static void printHelp() {
		System.out.print(
				"\nBasic commands:\n-Register(register) - Create a new library item\n-Deregister(deregister) - Delete a library item");
		System.out.print("\n-Checkin(checkin) - Return a borrowed item\n-Checkout(checkout) - Borrow an item");
		System.out.print(
				"\n-List(list) - list all items in library\n-Info(info) - display more details about a specific item");
		System.out.print(
				"\nFor more details on alternate Command shortcuts, read the ReadMe file\nPress q to quit application.");

	}

	public static void handleQuitCommand() {
		System.out.print("\nExiting program.");
		System.exit(0);
	}

	public static void main(String[] args) throws IOException {
		
		Library lib = new Library("MovieBookLibrary.txt");
		lib.start();

	}

}

package MovieBookLibrary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class Library {

	private IntLibrary<Item> library;

	public Library(String movBookPath) {
		try {
			library = new LibraryManager(movBookPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		boolean running = true;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Hello user!\nWelcome to the Library!");
		System.out.println("Enter command:\nFor help, write help + enter.");

		while (running) {

			String userInput = scanner.nextLine();
			Command command = parseCommand(userInput);

			if (command == Command.QUIT) {
				handleQuitCommand();
			} else if (command == Command.UNKNOWN) {
				printUnknownCommand();
				continue;
			}

			if (command == Command.REGISTER) {
				System.out.println(
						"What kind of item do you want to register?\nfor Book, press (b) + enter\nfor Movie, press (m) + enter.");

				try {
					String bookOrMovie = scanner.next();
					library.register(bookOrMovie);

				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
			}

			if (command == Command.DEREGISTER) {
				library.searchLibrary("Deregister");
			}

			if (command == Command.CHECKOUT) {
				library.searchLibrary("Checkout");
			}

			if (command == Command.CHECKIN) {
				library.searchLibrary("Checkin");
			}

			if (command == Command.HELP) {
				printHelp();
				continue;
			}

			if (command == Command.LIST) {
				library.list();
			}

			if (command == Command.INFO) {
				library.searchLibrary("Info");
			}

		}
		scanner.close();
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
		System.out.println("Unknown command, please try again");
		return;
	}

	public static void printHelp() {
		System.out.println(
				"Basic commands:\n-Register(register) - Create a new library item\n-Deregister(deregister) - Delete a library item");
		System.out.println("\n-Checkin(checkin) - Return a borrowed item\n-Checkout(checkout) - Borrow an item");
		System.out.println(
				"\n-List(list) - list all items in library\n-Info(info) - display more details about a specific item");
		System.out.println(
				"\nFor more details on alternate Command shortcuts, read the ReadMe file\nPress q to quit application.");

	}

	public static void handleQuitCommand() {
		System.out.print("Exiting program.");
		System.exit(0);
	}

	public static void main(String[] args) throws IOException {

		Library lib = new Library("MovieBookLibrary.csv");
		lib.start();

	}

}

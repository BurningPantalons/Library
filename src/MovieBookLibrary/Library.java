package MovieBookLibrary;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Library {

	private IntLibrary<Item> library;

	public Library(String movBookPath) {
		try {
			library = new LibraryManager(movBookPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("No file found at " + movBookPath);
			System.out.println("Exiting");
			System.exit(0);
		}

	}

	public static void main(String[] args) {
		Library lib = new Library("MovieBookLibrary.csv");
		lib.start();

	}

	public void start() {
		boolean running = true;
		Scanner scanner = new Scanner(System.in);
		while (running) {

			String userInput = scanner.nextLine();
			Command command = parseCommand(userInput);

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
				System.out.println("Deregister item:\nfor Book, press (b) + enter\nfor Movie, press (m) + enter.");

				try {
					String bookOrMovie = scanner.next(); // antingen gör man två metoder som i register, eller så gör
															// man en metod som letar i bägge listorna. förmodligen det
															// andra alternativet.
					if (bookOrMovie.equals("b")) {
						System.out.println("Enter book id you wish to remove");

						System.exit(0);
					} else if (bookOrMovie.equals("m")) {
						System.out.println("Enter movie id you wish to remove");

						System.exit(0);
					} else {
						printUnknownCommand();
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
			}

			if (command == Command.CHECKOUT) {
			}

			if (command == Command.CHECKIN) {
			}

			if (command == Command.HELP) {
				printHelp();
				continue;
			}

			if (command == Command.LIST) {

			}

			if (command == Command.INFO) {
			}

			if (command == Command.QUIT) {
				quit();
			}

			if (command == Command.UNKNOWN) {
				printUnknownCommand();
				continue;
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
		System.out.println("helptext");
	}

	public static void quit() {
		System.out.print("Exiting program.");
		System.exit(0);
	}

}

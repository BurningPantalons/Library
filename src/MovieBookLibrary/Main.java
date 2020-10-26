package MovieBookLibrary;

import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(System.in);

		while (true) {

			String userInput = scanner.nextLine();
			Command command = parseCommand(userInput);

			if (command == Command.REGISTER) {
				System.out.println(
						"What kind of item do you want to register?\nfor Book, press (b) + enter\nfor Movie, press (m) + enter.");

				try {
					String bookOrMovie = scanner.next();
					if (bookOrMovie.equals("b")) {
						System.out.println("you are creating a new book.");
						System.exit(0);
					} else if (bookOrMovie.equals("m")) {
						System.out.println("you are creating a new movie.");
						System.exit(0);
					} else {
						printUnknownCommand();
						continue;
					}
				} catch (Exception e) {
					return;
				}
			}

			if (command == Command.DEREGISTER) {
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
			scanner.close();
		}

		/*
		 * läsa in csv.filer när programmet startar parseCommand som tar reda på rätt
		 * kommando och kallar på metod switch för Book(b) och Movie(m) list() som visar
		 * en lista på Böcker och Filmer och ifall dom är utlånade (till specificerad
		 * kund)
		 */

	}

	public static Command parseCommand(String userInput) {
		String command = userInput;
		switch (command) {

		case "register":
			return Command.REGISTER;

		case "deregister":
			return Command.DEREGISTER;

		case "checkout":
			return Command.CHECKOUT;

		case "checkin":
			return Command.CHECKIN;

		case "help":
			return Command.HELP;

		case "list":
			return Command.LIST;

		case "info":
			return Command.INFO;

		case "quit":
		case "q":	
			return Command.QUIT;
		}
		return Command.UNKNOWN;
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

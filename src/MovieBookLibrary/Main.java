package MovieBookLibrary;

import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		
		
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
					if (bookOrMovie.equals("b")) {
						regBook();
					} else if (bookOrMovie.equals("m")) {
						regMovie();
					} else {
						printUnknownCommand();
						continue;
					}
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
						System.out.println("Deleting book");
						
						System.exit(0);
					} else if (bookOrMovie.equals("m")) {
						System.out.println("Deleting movie");
						
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
				printList();
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
		case "r":
			return Command.REGISTER;

		case "deregister":
			return Command.DEREGISTER;

		case "checkout":
			return Command.CHECKOUT;

		case "checkin":
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
			return Command.QUIT;
		}
		return Command.UNKNOWN;
	}

	public static void regBook() {
		System.out.println("Book: enter id, title, value(sek), author, pages.");
		// Scanner bParameter = new Scanner(System.in);
		ArrayList<Book> bookList = new ArrayList<Book>();
		bookList.add(new Book(0, null, 0, null, 0));

		// System.exit(0);
		// bParameter.close();

	}

	public static void regMovie() {
		System.out.println("Movie: enter id, title, value(sek), rating, runtime.");
		// Scanner mParameter = new Scanner(System.in);
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		movieList.add(new Movie(0, null, 0, 0, 0));
		// System.exit(0);
		// mParameter.close();
	}

	public static void deleteItem() {
		/*
		 * läser i både bookList och movieList efter id.nr
		 * och tar bort det ur arraylistan
		 */
	}
	
	public static void printList() {
		/* 
		 * Skriver ut arraylistorna med variabel-namn: + värde
		 */
		
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

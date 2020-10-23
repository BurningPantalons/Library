package MovieBookLibrary;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			
			String userInput = scanner.nextLine();
	        Command command = parseCommand(userInput);

			if (command == Command.QUIT) {
				System.exit(0);
			}
			scanner.close();
		}
		
		/*läsa in csv.filer när programmet startar
		 * parseCommand som tar reda på rätt kommando och kallar på metod
		 * switch för Book(b) och Movie(m)
		 * list() som visar en lista på Böcker och Filmer och ifall dom är utlånade (till specificerad kund)
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
			return Command.QUIT;
		}
		return null;
	}
}

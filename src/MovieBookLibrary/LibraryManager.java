package MovieBookLibrary;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManager {
	static List<Item> libraryList = new ArrayList<Item>(); // listan som kallas på i LIST-kommandot, läser in både Movie
															// och Book och skriver ut det i en lång lista
	static List<Book> bookList = new ArrayList<Book>();
	static List<Movie> movieList = new ArrayList<Movie>();

//	static String Not_Available;
//	static String In_Stock;
//	static String Does_Not_Exist;

//	checkout	<artikelnummer>	
//	Startar en dialog i konsolen som låter användaren ange ett namn och ett telefonnummer 
//	för en kund. Lånar ut produkten med artikelnummer <artikelnummer> till en denna kund. 
//	Efter detta finns inte produkten länge i lager.
//
//	checkin	<artikelnummer>	Tar tillbaka en utlånad produkt med artikelnummer <artikelnummer>. 
//	Efter detta finns produkten i lager.

//	info <artikelnummer>	Skriver ut alla egenskaper hos produkten med artikelnummer
//	<artikelnummer>
//	
//	list inga argument	Skriver ut artikelnummer, titel och largerstatus (i lager eller utlånad) 
//	för alla registrerade produkter i systemet. Om en produkt är utlånad till en kund skall 
//	kundens namn och telefonnummer visas.

	public static void regBook() {
		System.out.println("Book: enter id, title, value(sek), author, pages.");
		Scanner scanner = new Scanner(System.in);
		String userInput = scanner.nextLine();
		String[] arguments = userInput.split(", ");
		int id = 0, value = 0, nPages = 0;
		String title = null, author = null;
		
		
		try {
			id = Integer.parseInt(arguments[0]);
			title = arguments[1];
			value = Integer.parseInt(arguments[2]);
			author = arguments[3];
			nPages = Integer.parseInt(arguments[4]);
		} catch (Exception e) {
			System.out.println("Failed to parse all attributes for a book.\nTry again.\n");
			regBook();
		}
		Book book = new Book(id, title, value, author, nPages);
		System.out.println(book);
		bookList.add(book);
		return;
	}
//	public static void regBook() { //försöker efterlikna oscars exempel här
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("Book:\nEnter id:\n>");
//		String userInput = scanner.next();
//		String[] tempBook = new String[id, title, value, author, nPages];
//		try {
//		int id = Integer.toString(userInput);
//		System.out.println("\nEnter title:\n>");
//		title = userInput;
//		System.out.println("\nEnter value:\n>");
//		value = Integer.parseInt(userInput);
//		System.out.println("\nEnter author:\n>");
//		author = userInput;
//		System.out.println("\nEnter number of pages:\n>");
//		nPages = Integer.parseInt(userInput);
//		} catch (Exception e) {
//			System.out.println("Failed to parse all attributes for a book.\nTry again.\n>");
//			regBook();
//		}
//		
//		Book book = new Book(id, title, value, author, nPages);
//		System.out.println(book);
//		bookList.add(book);
//		// System.exit(0);
//		return;
//	}

	public static void regMovie() {
		System.out.println("Movie: enter id, title, value(sek), rating, runtime.");
		Scanner scanner = new Scanner(System.in);
		String userInput = scanner.nextLine();
		String[] arguments = userInput.split(", ");
		int id = 0, value = 0, runtime = 0;
		String title = null;
		float rating = 0f;

		try {
			id = Integer.parseInt(arguments[0]);
			title = arguments[1];
			value = Integer.parseInt(arguments[2]);
			rating = Float.parseFloat(arguments[3]);
			runtime = Integer.parseInt(arguments[4]);
		} catch (Exception e) {
			System.out.println("Failed to parse all attributes for a movie.\nTry again.\n");
			regMovie();
		}
		Movie movie = new Movie(id, title, value, rating, runtime);
		System.out.println(movie);
		movieList.add(movie);

		return;
	}

//	private String changeItemState(){
//		if (){ //här ska man kunna ändra böcker/filmer till "utlånade" eller "in stock"
//			
//		}
//		else {
//			return "The item does not exist in the library.";
//		}
//	}

//Funktion för utskrift av csv.

	public static void ListWriter() {

		try {

			PrintWriter moviePrintWriter = new PrintWriter("Movies.csv");
			moviePrintWriter.println(Movie.getMovieCsvHeaderString());
			for (Movie movie : movieList) {
				String csvRecord = movie.movieCsvRecord();
				moviePrintWriter.println(csvRecord);

			}
			moviePrintWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			PrintWriter bookPrintWriter = new PrintWriter("Books.csv");
			bookPrintWriter.println(Book.getBookCsvHeaderString());
			for (Book book : bookList) {
				String csvRecord = book.bookCsvRecord();
				bookPrintWriter.println(csvRecord);

			}
			bookPrintWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

/*
 * uppdaterar csv-filerna under körtid - PrintWriter uppdaterar csv-filerna vid
 * programavslut - flush
 */

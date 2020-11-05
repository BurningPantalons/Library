package MovieBookLibrary;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManager implements IntLibrary<Item> {

	private List<Item> libraryList = new ArrayList<Item>();
	private String movBookPath;

	public LibraryManager(String movBookPath) throws FileNotFoundException {
		this.movBookPath = movBookPath;
		libraryList = parseItems(movBookPath);
	}

	static String State_Not_Available, State_In_Stock, State_Does_Not_Exist, State_Already_Registered;

	@Override
	public void register(String argument) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter id:\n>");
		int id = Integer.parseInt(sc.next());
		System.out.println("Enter title:\n>");
		String title = sc.next();
		System.out.println("Enter value (Sek):\n>");
		int value = Integer.parseInt(sc.next());
		if (argument.equals("b")) {
			System.out.println("Enter Author:\n>");
			String author = sc.next();
			System.out.println("Enter number of pages:\n>");
			int nPages = Integer.parseInt(sc.next());
			libraryList.add(new Book(id, title, value, author, nPages));
			writeItems();
		} else if (argument.equals("m")) {
			System.out.println("Enter Imdb rating:\n>");
			float rating = Float.parseFloat(sc.next());
			System.out.println("Enter runtime (mins):\n>");
			int runtime = Integer.parseInt(sc.next());
			libraryList.add(new Movie(id, title, value, rating, runtime));
			writeItems();
		} else {
			System.out.println(
					"Arguments not valid.\nPlease try again.\nFor book, press (b) + enter.\nFor movie, press (m) + enter.");
			String newArg = sc.next();
			register(newArg);
		}

	}

	private List<Item> parseItems(String movBookPath) throws FileNotFoundException {

		FileReader reader = new FileReader(movBookPath);
		Scanner scanner = new Scanner(reader);

		// Read the movie from CSV
		List<Item> movBookLibrary = new ArrayList<Item>();
		scanner.nextLine(); // skip header line
		while (scanner.hasNextLine()) {
			String csvRecord = scanner.nextLine();
			for (Item movie : movBookLibrary) {
				Movie.parseMovie(csvRecord);
				libraryList.add(movie);
			}
			scanner.nextLine();
			for (Item book : movBookLibrary) {
				Book.parseBook(csvRecord);
				libraryList.add(book);

			}
		
		}
		scanner.close();

		return null;

	}

	private void writeItems() {

		try {
			PrintWriter printWriter = new PrintWriter(movBookPath);
			printWriter.println(Movie.getCsvHeaderString());
			for (Item movie : libraryList) {
				String csvRecordM = movie.CsvRecord();
				printWriter.println(csvRecordM);
				System.out.printf("All Movies successfully written to file.\nProceeding with books.\n");
				printWriter.println(Book.getCsvHeaderString());
				for (Item book : libraryList) {
					String csvRecordB = book.CsvRecord();
					printWriter.println(csvRecordB);
					System.out.println("All Book successfully written to file.\n");
				}
			}
			printWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	private void searchLibrary(String argument) {
		int index;
		try {
			index = Integer.parseInt(argument);
			for (Item movie : libraryList) {
				
				
			}
				
		}catch (Exception e) {
			System.out.println("Failed to parse index from arguments.");
			return;
		}
		
		
	}

	@Override
	public void checkout(String argument) {
	}
	/*
	 * deklarera variablen metod som itererar genom listan efter id - SÖKMETOD
	 * skickar index borrowed = true customer name, nr
	 */

	@Override
	public void checkin(String argument) {
		/*
		 * deklarera variablen metod som itererar genom listan efter id - SÖKMETOD
		 * skickar index borrowed = true customer name, nr
		 */
	}

	@Override
	public void info() {

	}

	@Override
	public void list() {

	}

	@Override
	public void deregister(String argument) {
		/*
		 * deklarera variablen metod som itererar genom listan efter id - SÖKMETOD
		 * skickar index borrowed = true customer name, nr
		 */
	}

}
//public static void regBook() {
//System.out.println("Book: enter id, title, value(sek), author, pages.");
//Scanner scanner = new Scanner(System.in);
//String userInput = scanner.nextLine();
//String[] arguments = userInput.split(", ");
//int id = 0, value = 0, nPages = 0;
//String title = null, author = null;
//
//try {
//	id = Integer.parseInt(arguments[0]);
//	title = arguments[1];
//	value = Integer.parseInt(arguments[2]);
//	author = arguments[3];
//	nPages = Integer.parseInt(arguments[4]);
//} catch (Exception e) {
//	System.out.println("\nFailed to parse all attributes for a book.\nTry again.");
//	//regBook();
//}
//Book book = new Book(id, title, value, author, nPages);
//System.out.println(book);
//libraryList.add(book);
//System.out.println(bookList.toString());
//scanner.close();
//return;
//}
//
//public static void regMovie() {
//System.out.println("Movie: enter id, title, value(sek), rating, runtime.");
//Scanner scanner = new Scanner(System.in);
//String userInput = scanner.nextLine();
//String[] arguments = userInput.split(", ");
//int id = 0, value = 0, runtime = 0;
//String title = null;
//float rating = 0f;
//
//try {
//	id = Integer.parseInt(arguments[0]);
//	title = arguments[1];
//	value = Integer.parseInt(arguments[2]);
//	rating = Float.parseFloat(arguments[3]);
//	runtime = Integer.parseInt(arguments[4]);
//} catch (Exception e) {
//	System.out.println("Failed to parse all attributes for a movie.\nTry again.\n");
//	//regMovie();
//}
//Movie movie = new Movie(id, title, value, rating, runtime);
//System.out.println(movie);
//libraryList.add(movie);
//scanner.close();
//return;
//}

//public static void ListWriter() {
//
//try {
//
//	PrintWriter moviePrintWriter = new PrintWriter("Movies.csv");
//	moviePrintWriter.println(Movie.getMovieCsvHeaderString());
//	for (Movie movie : libraryList) {
//		String csvRecord = movie.movieCsvRecord();
//		moviePrintWriter.println(csvRecord);
//
//	}
//	moviePrintWriter.close();
//} catch (FileNotFoundException e) {
//	e.printStackTrace();
//}
//try {
//	PrintWriter bookPrintWriter = new PrintWriter("Books.csv");
//	bookPrintWriter.println(Book.getBookCsvHeaderString());
//	for (Book book : libraryList) {
//		String csvRecord = book.bookCsvRecord();
//		bookPrintWriter.println(csvRecord);
//
//	}
//	bookPrintWriter.close();
//} catch (FileNotFoundException e) {
//	e.printStackTrace();
//}
//}
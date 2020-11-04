package MovieBookLibrary;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManager implements IntLibrary{
	static List<Item> libraryList = new ArrayList<Item>(); // listan som kallas på i LIST-kommandot, läser in både Movie
															// och Book och skriver ut det i en lång lista
	static List<Book> bookList = new ArrayList<Book>();
	static List<Movie> movieList = new ArrayList<Movie>();

	static String State_Not_Available, State_In_Stock, State_Does_Not_Exist, State_Already_Registered;

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
		System.out.println(bookList.toString());
		scanner.close();
		return;
	}

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
		scanner.close();
		return;
	}

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



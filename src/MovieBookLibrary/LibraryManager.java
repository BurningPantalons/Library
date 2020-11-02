package MovieBookLibrary;

import java.util.ArrayList;


enum ItemState {
	
	IN_STOCK,
	BORROWED,
	DO_NOT_EXIST,
	
}

public class LibraryManager {

	/*
	 * sköter alla utlåningar/inlämningar updaterar bookList och movieList
	 * 
	 * 
	 */
	ArrayList<Book> bookList = new ArrayList<Book>();
	ArrayList<Movie> movieList = new ArrayList<Movie>();

	public static void regBook() {
		System.out.println("Book: enter id, title, value(sek), author, pages.");
		// Scanner bParameter = new Scanner(System.in);

		// System.exit(0);

	}

	public static void regMovie() {
		System.out.println("Movie: enter id, title, value(sek), rating, runtime.");
		// Scanner mParameter = new Scanner(System.in);

		// System.exit(0);

	}
}

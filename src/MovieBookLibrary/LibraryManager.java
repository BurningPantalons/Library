package MovieBookLibrary;

import java.util.ArrayList;

enum ItemState {

	IN_STOCK, BORROWED, DO_NOT_EXIST,

}

public class LibraryManager extends Main{

	/*
	 * sköter alla utlåningar/inlämningar updaterar bookList och movieList
	 * 
	 * 
	 */
	ArrayList<Book> bookList = new ArrayList<Book>();
	ArrayList<Movie> movieList = new ArrayList<Movie>();

	public static void addBook(Book book){
		
	
	}
}

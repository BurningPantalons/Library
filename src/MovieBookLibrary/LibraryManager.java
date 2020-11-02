package MovieBookLibrary;

import java.util.ArrayList;
import java.util.List;

enum ItemState {

	IN_STOCK, BORROWED, DO_NOT_EXIST,

}

public class LibraryManager extends Main{

	/*
	 * sköter alla utlåningar/inlämningar updaterar bookList och movieList
	 * 
	 * 
	 */
	List<Book> bookList = new ArrayList<Book>();
	List<Movie> movieList = new ArrayList<Movie>();

//	public static <E> void add(E product) {
//		
//		if (product = book) {
//			bookList.add(product);
//		}
//	
//	}
}

package MovieBookLibrary;
import java.util.ArrayList;

public class Books extends Item {

	private String author;
	private int pages;
	
	public ArrayList bookList = new ArrayList();

	
	public void register(String author, int pages) {
		
		setAuthor(author);
		setPages(pages);
	}

	Books() { // id, title, value, author, pages

	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}
	// arraylists
}

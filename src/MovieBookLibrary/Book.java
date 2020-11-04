package MovieBookLibrary;

public class Book extends Item {

	private String author;
	private int nPages;
	
	//public static final int type = 0;

	public Book(int id, String title, int value, String author, int pages) {
		super(id, title, value);
		setAuthor(author);
		setnPages(pages);
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public int getnPages() {
		return nPages;
	}


	public void setnPages(int nPages) {
		this.nPages = nPages;
	}


	@Override
	public String toString() {
		return String.format("Id: %d, Title: %s, Value(sek): %dkr, Author: %s, Pages: %d", id, title, value, author,
				nPages);
	}

	public String bookCsvRecord() {
		return String.format("%d,%s,%d,%s,%d", id, title, value, author, nPages);
	}

	public static String getBookCsvHeaderString() {
		return "id,title,value (Sek),author,number_of_pages";
	}
}

package MovieBookLibrary;

public class Book extends Item {

	private String author;
	private int nPages;

	public Book(int id, String title, int value, String author, int pages) {
		super(id, title, value);
		setAuthor(author);
		setnPages(pages);
		this.typeIdentifier = TYPE_BOOK;
		this.stateIdentifier = STATE_IN_STOCK;
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
	public String getTypeIdentifier() {
		return TYPE_BOOK;
	}

	@Override
	public String toString() {
		return String.format("%s Id: %d, Title: %s, Value(sek): %dkr, Author: %s, Pages: %d", typeIdentifier, id, title,
				value, author, nPages);
	}

	public String CsvRecord() {
		return String.format("%s,%d,%s,%d,%s,%d", typeIdentifier, id, title, value, author, nPages);
	}

	public static String getCsvHeaderString() {
		return "type,id,title,value (Sek),author,number_of_pages";
	}

}

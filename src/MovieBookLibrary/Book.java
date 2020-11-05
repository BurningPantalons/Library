package MovieBookLibrary;

public class Book extends Item {

	private String author;
	private int nPages;

	// public static final int type = 0;

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

	@Override
	public String CsvRecord() {
		return String.format("%d,%s,%d,%s,%d", id, title, value, author, nPages);
	}

	public static String getCsvHeaderString() {
		return "id,title,value (Sek),author,number_of_pages";
	}

	public static Book parseBook(String csvRecord) {
		String[] values = csvRecord.split(",");
		int id = Integer.parseInt(values[0]);
		String title = values[1];
		int value = Integer.parseInt(values[2]);
		String author = values[3];
		int nPages = Integer.parseInt(values[4]);
		return new Book(id, title, value, author, nPages);
	}

}

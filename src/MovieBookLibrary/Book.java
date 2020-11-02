package MovieBookLibrary;

import java.util.*;

public class Book extends Item {

	private String author;
	private int nPages;

	public Book(int id, String title, int value, String author, int pages) {
		super(id, title, value);
		setAuthor(author);
		setPages(pages);
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPages() {
		return nPages;
	}

	public void setPages(int pages) {
		this.nPages = pages;
	}

}

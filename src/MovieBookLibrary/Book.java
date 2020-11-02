package MovieBookLibrary;

import java.util.*;

public class Book extends Item {

	private String author;
	private int nPages;

	public Book(int id, String title, int value, String author, int pages) {
		super(id, title, value);
		this.author = author;
		this.nPages = pages;
	}

	public String getAuthor() {
		return author;
	}

	public int getPages() {
		return nPages;
	}
	
	@Override
	public String toString() {
		return String.format("Id: %d, Title: %s, Value(sek): %dkr, Author: %s, Pages: %d", id, title, value, author, nPages);
	}
}

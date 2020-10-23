package MovieBookLibrary;

public class Books extends Item {

	private String author;
	private int pages;
	
	Books(){ //id, title, value, author, pages
		
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
	public void setLength(int pages) {
		this.pages = pages;
	}
	//arraylist
}

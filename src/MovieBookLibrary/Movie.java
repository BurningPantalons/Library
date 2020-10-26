package MovieBookLibrary;

public class Movie extends Item {

	private float rating;
	private int length;
	
	public Movie(int id, String title, int value, float rating, int length) {
		super(id, title, value);
		setRating(rating);
		setLength(length);
	}
	
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	//Arraylist
}

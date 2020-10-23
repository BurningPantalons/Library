package MovieBookLibrary;

public class Movies extends Item {

	private float rating;
	private int length;
	
	Movies(){ //id, title, value, rating, length
		
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

package MovieBookLibrary;

public class Movie extends Item {

	private float rating;
	private int runtime;
	
	public Movie(int id, String title, int value, float rating, int runtime) {
		super(id, title, value);
		setRating(rating);
		setRuntime(runtime);
	}
	
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public int getRuntime() {
		return runtime;
	}
	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}
	//Arraylist
}

package MovieBookLibrary;

public class Movie extends Item {

	private float rating;
	private int runtime;
	
	public Movie(int id, String title, int value, float rating, int runtime) {
		super(id, title, value);
		this.rating = rating;
		this.runtime = runtime;
	}
	
	public float getRating() {
		return rating;
	}
	
	public int getRuntime() {
		return runtime;
	}
	
}

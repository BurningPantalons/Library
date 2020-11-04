package MovieBookLibrary;

public class Movie extends Item {

	private float rating;
	private int runtime;
	
	//public static final int type = 1;

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


	@Override
	public String toString() {
		return String.format("Id: %d, Title: %s, Value(sek): %dkr, Rating: %.2f, Runtime: %dmin", id, title, value,
				rating, runtime);
	}

	public String movieCsvRecord() {
		return String.format("%d,%s,%d,%.2f,%d", id, title, value, rating, runtime);
	}

	public static String getMovieCsvHeaderString() {
		return "id,title,value (Sek),imdb_rating,runtime (minutes)";
	}
}

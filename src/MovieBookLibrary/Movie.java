package MovieBookLibrary;

public class Movie extends Item {

	private float rating;
	private int runtime;

	public Movie(int id, String title, int value, float rating, int runtime) {
		super(id, title, value);
		setRating(rating);
		setRuntime(runtime);
		this.typeIdentifier = TYPE_MOVIE;
		this.stateIdentifier = STATE_IN_STOCK;
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
		return String.format("%s Id: %d, Title: %s, Value(sek): %dkr, Rating: %.2f, Runtime: %dmin", typeIdentifier, id,
				title, value, rating, runtime);
	}

	@Override
	public String getTypeIdentifier() {
		return TYPE_MOVIE;
	}

	@Override
	public String CsvRecord() {
		return String.format("%s,%d,%s,%d,%.2f,%d,%s,%s,%s", typeIdentifier, id, title, value, rating, runtime, stateIdentifier, customerName, customerNumber);
	}

	public static String getCsvHeaderString() {
		return "Movie,Id,Title,Value,Imdb rating,Runtime (minutes),state,customerName,customerNumber";
	}

}

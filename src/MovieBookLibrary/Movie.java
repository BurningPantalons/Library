package MovieBookLibrary;

public class Movie extends Item {

	private float rating;
	private int runtime;

	// public static final int type = 1;

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

	@Override
	public String CsvRecord() {
		return String.format("%d,%s,%d,%.2f,%d", id, title, value, rating, runtime);
	}

	public static String getCsvHeaderString() {
		return "id,title,value (Sek),imdb_rating,runtime (minutes)";
	}

	public static Movie parseMovie(String csvRecord) {
		String[] values = csvRecord.split(",");
		int id = Integer.parseInt(values[0]);
		String title = values[1];
		int value = Integer.parseInt(values[2]);
		float rating = Float.parseFloat(values[3]);
		int runtime = Integer.parseInt(values[4]);
		return new Movie(id, title, value, rating, runtime);
	}
}

package MovieBookLibrary;

public abstract class Item {

	protected int id;
	protected String title;
	protected int value;

	
	public Item(int id, String title, int value) {
		setId(id);
		setTitle(title);
		setValue(value);	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}


}

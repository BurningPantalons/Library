package MovieBookLibrary;

public abstract class Item implements ItemAttributes {

	protected int id;
	protected String title;
	protected int value;
	protected String customerName;
	protected String customerNumber;
	protected String typeIdentifier = null;
	protected String stateIdentifier = null;

	public Item(int id, String title, int value) {
		setId(id);
		setTitle(title);
		setValue(value);
		setCustomerName(EMPTY_STRING);
		setCustomerNumber(EMPTY_STRING);
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String CsvRecord() {
		return null;
	}

	public String getTypeIdentifier() {
		return typeIdentifier;
	}

	public void setItemState(String stateIdentifier) {
		this.stateIdentifier = stateIdentifier;
	}

	public String getItemState() {
		return stateIdentifier;
	}

}

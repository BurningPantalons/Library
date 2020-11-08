package MovieBookLibrary;

public interface ItemAttributes {

	
	
	final String EMPTY_STRING = "";
	final String TYPE_BOOK = "(Book)";
	final String TYPE_MOVIE = "(Movie)";
	
	final String STATE_NOT_AVAILABLE = "(borrowed)";
	final String STATE_IN_STOCK = "(in stock)"; 
	final String STATE_DOES_NOT_EXIST = "Item does not exist."; 
	final String STATE_ALREADY_REGISTERED = "Id already exists in library,\n new registration with same Id not posssible.";
	
	final boolean EXIST = true;

	
}

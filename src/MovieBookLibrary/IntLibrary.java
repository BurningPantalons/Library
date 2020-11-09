package MovieBookLibrary;

public interface IntLibrary<T> {

	abstract void register(String argument);

	abstract void deregister(int index);

	abstract void checkout(int Id);	

	abstract void checkin(int Id);	
	
	abstract void info(int Id); 
	
	abstract void list();

	abstract void searchLibrary(String string);

}

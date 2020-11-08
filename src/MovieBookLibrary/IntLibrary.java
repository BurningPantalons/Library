package MovieBookLibrary;

public interface IntLibrary<T> {

	abstract void register(String argument);

	abstract void deregister(int index);

	abstract void checkout(String argument);	

	abstract void checkin(String argument);	
	
	abstract void info(String argument); 
	
	abstract void list();

}

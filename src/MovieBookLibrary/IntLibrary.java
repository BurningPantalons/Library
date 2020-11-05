package MovieBookLibrary;

public interface IntLibrary<T> {

	abstract void checkout(String argument);	
	//<artikelnummer>	
//	Startar en dialog i konsolen som låter användaren ange ett namn och ett telefonnummer 
//	för en kund. Lånar ut produkten med artikelnummer <artikelnummer> till en denna kund. 
//	Efter detta finns inte produkten länge i lager.

	abstract void checkin(String argument);	
//	<artikelnummer>	Tar tillbaka en utlånad produkt med artikelnummer <artikelnummer>. 
//	Efter detta finns produkten i lager.

	abstract void info(); //	<artikelnummer>
	//Skriver ut alla egenskaper hos produkten med artikelnummer
	//	<artikelnummer>
	
	abstract void list();
//	inga argument	Skriver ut artikelnummer, titel och largerstatus (i lager eller utlånad) 
//	för alla registrerade produkter i systemet. Om en produkt är utlånad till en kund skall 
//	kundens namn och telefonnummer visas.
//	
	abstract void deregister(String argument);
	
	abstract void register(String argument);

	
	
}

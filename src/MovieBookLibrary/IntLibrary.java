package MovieBookLibrary;

public interface IntLibrary {

	public static void checkout() {}	
	//<artikelnummer>	
//	Startar en dialog i konsolen som låter användaren ange ett namn och ett telefonnummer 
//	för en kund. Lånar ut produkten med artikelnummer <artikelnummer> till en denna kund. 
//	Efter detta finns inte produkten länge i lager.

	public static void checkin() {}	
//	<artikelnummer>	Tar tillbaka en utlånad produkt med artikelnummer <artikelnummer>. 
//	Efter detta finns produkten i lager.

	public static void info() {} 
//	<artikelnummer>	Skriver ut alla egenskaper hos produkten med artikelnummer
//	<artikelnummer>
	
	public static void list() {}
//	inga argument	Skriver ut artikelnummer, titel och largerstatus (i lager eller utlånad) 
//	för alla registrerade produkter i systemet. Om en produkt är utlånad till en kund skall 
//	kundens namn och telefonnummer visas.
//	
	public static void deregister() {}
	
}

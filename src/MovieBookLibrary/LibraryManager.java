package MovieBookLibrary;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.apache.commons.csv.*;

public class LibraryManager extends Exceptions implements IntLibrary<Item>, ItemAttributes {

	private List<Item> libraryList = new ArrayList<Item>();
	private String movBookPath;

	public LibraryManager(String movBookPath) throws FileNotFoundException {
		try {
			writeCsvFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.movBookPath = movBookPath;
		parseItems(movBookPath);
	}

	@Override
	public void register(String argument) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter id:\n");
		int id = 0;

		try {
			id = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		System.out.print("Enter title:");
		String title = null;
		try {
			title = sc.nextLine();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		System.out.print("\nEnter value (Sek):");
		int value = 0;
		try {
			value = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		if (argument.equals("b")) {
			System.out.println("Enter Author:");
			String author = null;
			try {
				author = sc.nextLine();
			} catch (NoSuchElementException e) {
				e.printStackTrace();

			}
			System.out.println("Enter number of pages:");
			int nPages = 0;
			try {
				nPages = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			libraryList.add(new Book(id, title, value, author, nPages));
			try {
				writeItems();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		} else if (argument.equals("m")) {
			System.out.println("Enter Imdb rating:");
			float rating = 0.0f;
			try {
				rating = Float.parseFloat(sc.next());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			System.out.println("Enter runtime (mins):");
			int runtime = 0;
			try {
				runtime = Integer.parseInt(sc.next());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			libraryList.add(new Movie(id, title, value, rating, runtime));
			try {
				writeItems();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		} else {
			System.out.println(
					"Arguments not valid.\nPlease try again.\nFor book, press (b) + enter.\nFor movie, press (m) + enter.");
			String newArg = sc.next();
			register(newArg);
			return;
		}

	}

	private List<Item> parseItems(String movBookPath) throws FileNotFoundException {

		FileReader reader = new FileReader(movBookPath);
		Scanner scanner = new Scanner(reader);
		List<Item> fileList = new ArrayList<Item>();

		if (movBookPath.isBlank()) {
			System.out.println("Could not find any information on items to parse.\nProceeding to start application.");
			scanner.close();
			return null;
		}
		try {
			while (scanner.hasNextLine()) {
				for (Item item : fileList) {
					String csvRecord = scanner.nextLine();
					if (csvRecord.contentEquals(Movie.getCsvHeaderString())
							|| csvRecord.contentEquals(Book.getCsvHeaderString())) {
						scanner.nextLine();
					} else if (item.getTypeIdentifier().contentEquals("(Book)")) {
						Book.parseBook(csvRecord);
						libraryList.add(item);
					} else if (item.getTypeIdentifier().contentEquals("(Movie)"))
						Movie.parseMovie(csvRecord);
					libraryList.add(item);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("parseItems");
		}
		scanner.close();
		//list();
		return fileList;
	}

	private void writeCsvFile() throws IOException {
		CSVPrinter pW = new CSVPrinter(new FileWriter("MovieBookLibrary.csv"), CSVFormat.DEFAULT);
		String bookHeader = Book.getCsvHeaderString();
		String movieHeader = Movie.getCsvHeaderString();
		pW.printRecord(bookHeader);
		pW.printRecord(movieHeader);
		return;
	}

	private void writeItems() throws IOException {
		CSVPrinter pW = new CSVPrinter(new FileWriter("MovieBookLibrary.csv"), CSVFormat.DEFAULT);

		try {
			for (Item item : libraryList) {
				if (item.getTypeIdentifier().contentEquals("(Book)")) {
					pW.printRecord(item);
				} else if (item.getTypeIdentifier().contentEquals("(Movie)")) {
					pW.printRecord(item);
				} else {

				}
			}
			System.out.printf("All items successfully written to file.\n");
			pW.close();
			list();
			return;
		} catch (IOException e) {
			System.out.println("writeItems");
			e.printStackTrace();
			return;
		}

	}

	private int searchLibrary(String argument) {
		try {
			int Id = Integer.parseInt(argument);
			for (int i = 0; i > libraryList.size();) {
				Item item = libraryList.get(i);
				i++;
				if (Id == item.getId()) {
					return Id;
				} else {
					System.out.println("Item Id does not exist.\n");
					return 0;
				}
			}

		} catch (Exception e) {
			System.out.println("Failed to parse index from argument.\nTry again");
			return 0;
		}
		return 0;
	}

	@Override
	public void checkout(String argument) {
		Scanner customerSc = new Scanner(System.in);
		String Id = argument;
		searchLibrary(Id);
		if (libraryList.contains(Id) == EXIST) {
			String customerName, customerNumber;
			System.out.println("Enter name for person borrowing this item:");
			customerName = customerSc.nextLine();
			System.out.println("Enter number for person borrowing this item:");
			customerNumber = customerSc.nextLine();

		}

	}
	/*
	 * deklarera variablen metod som itererar genom listan efter id - SÖKMETOD
	 * skickar index borrowed = true customer name, nr
	 */

	@Override
	public void checkin(String argument) {
		String Id = argument;
		searchLibrary(Id);
		/*
		 * deklarera variablen metod som itererar genom listan efter id - SÖKMETOD
		 * skickar index borrowed = true, ta bort customer name, nr
		 */
	}

	@Override
	public void deregister(int index) {
		int Id = index;
		searchLibrary(Integer.toString(Id));
		libraryList.remove(Id);
		System.out.println("Item removed from library.");
		return;
	}

	@Override
	public void info(String argument) {
		String Id = argument;
		searchLibrary(Id);

		return;
	}

	@Override
	public void list() {
		if (libraryList.isEmpty() == true) {
			System.out.println("The list is empty.");
			System.out.println("Enter command:\nFor help, write help + enter.");
			return;
		} else {
			for (Item item : libraryList) {
				if (item.stateIdentifier == STATE_IN_STOCK) {
					System.out.printf("%s,%d,%s,%s",item.typeIdentifier, item.id, item.title, STATE_IN_STOCK);
				} else if (item.stateIdentifier == STATE_NOT_AVAILABLE) {
					System.out.printf("%s,%d,%s,\nBorrowed by: %s, %s",item.typeIdentifier, item.id, item.title, item.customerName,
							item.customerNumber);
				}
			}
			System.out.println("Enter command:\nFor help, write help + enter.");
			return;
		}
	}

}

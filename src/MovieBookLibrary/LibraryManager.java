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
			sc.close();
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
			sc.close();
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

		if (movBookPath.isBlank()) {
			System.out.println("Could not find any information on items to parse.\nProceeding to start application.");
			scanner.close();
			return null;
		} else
			try {
				while (scanner.hasNextLine()) {
					String csvRecord = scanner.nextLine();
					String[] recordValues = csvRecord.split(",");
					if (csvRecord.equals(Movie.getCsvHeaderString()) || csvRecord.equals(Book.getCsvHeaderString())) {
						scanner.nextLine();
					} else if (recordValues[0].equals(TYPE_BOOK)) {						
						int id = Integer.parseInt(recordValues[1]);
						String title = recordValues[2];
						int value = Integer.parseInt(recordValues[3]);
						String author = recordValues[4];
						int nPages = Integer.parseInt(recordValues[5]);
						Book book = new Book(id, title, value, author, nPages);
						libraryList.add(book);
					} else if (recordValues[0].equals(TYPE_MOVIE)) {						
						int id = Integer.parseInt(recordValues[1]);
						String title = recordValues[2];
						int value = Integer.parseInt(recordValues[3]);
						float rating = Float.parseFloat(recordValues[4]);
						int runtime = Integer.parseInt(recordValues[5]);
						Movie movie = new Movie(id, title, value, rating, runtime);
						libraryList.add(movie);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("parseItems");
			}
		scanner.close();
		return null;
	}

	private void createCsvFile() throws IOException {
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
				if (item.getTypeIdentifier().equals(TYPE_BOOK)) {
					pW.printRecord(item);
				} else if (item.getTypeIdentifier().equals(TYPE_MOVIE)) {
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

	public void searchLibrary(String argument) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Id number:\n>");
		int Id = Integer.parseInt(sc.next());

		for (int i = 0; i > libraryList.size();) {
			Item item = libraryList.get(i);
			if (Integer.valueOf(item.getId()) == Id) {
				if (argument.equals("Info")) {
					int Index = libraryList.indexOf(item);
					info(Index);
				}
				else if (argument.equals("Checkin")) {
					int Index = libraryList.indexOf(item);
					checkin(Index);
				}
				else if (argument.equals("Checkout")) {
					int Index = libraryList.indexOf(item);
					checkin(Index);
				}
				else if (argument.equals("Deregister")) {
					int Index = libraryList.indexOf(item);
					deregister(Index);
				}
				
			} else if (libraryList.contains(Id) != EXIST) {
				System.out.println("Item Id does not exist.\n");
				return;
			} else {
				System.out.println("Failed to parse index from argument.\nTry again");
				return;

			}
			i++;
		}
		System.out.println("searchLibrary-end");
		return;
	}

	@Override
	public void checkout(int Id) {
		int index = Id;
		Item item = libraryList.get(index);
		if (item.stateIdentifier == STATE_NOT_AVAILABLE) {
			System.out.println("Item already borrowed out");
			return;
		} else {
			System.out.println("checkout-borrow");
			Scanner customerSc = new Scanner(System.in);
			String customerName, customerNumber;
			System.out.println("Enter name for person borrowing this item:");
			customerName = customerSc.nextLine();
			System.out.println("Enter number for person borrowing this item:");
			customerNumber = customerSc.nextLine();
			item.stateIdentifier = STATE_NOT_AVAILABLE;
			item.setCustomerName(customerName);
			item.setCustomerNumber(customerNumber);
			System.out.println("Item borrowed to: " + customerName + " number: " + customerNumber);
			customerSc.close();
			return;
		}
	}

	@Override
	public void checkin(int Id) {
		int index = Id;
		Item item = libraryList.get(index);
		if (item.stateIdentifier != STATE_NOT_AVAILABLE) {
			System.out.println("Item is already in stock.");
			return;
		} else {
			item.stateIdentifier = STATE_IN_STOCK;
			item.setCustomerName(null);
			item.setCustomerNumber(null);
			System.out.println("Item returned to the library.");
			return;
		}

	}

	@Override
	public void deregister(int index) {
		int deleteId = index;
		Item deleteItem = libraryList.get(deleteId);
		libraryList.remove(deleteItem);
		System.out.println("Item removed from library.");
		return;
	}

	@Override
	public void info(int Id) {
		int index = Id;
		Item item = libraryList.get(index);
		if (item.stateIdentifier == STATE_IN_STOCK) {
			item.toString();
			System.out.println("info-in-stock");
			return;
		} else if (item.stateIdentifier == STATE_NOT_AVAILABLE) {
			System.out.printf(
					item.toString() + "Borrowed by: " + item.getCustomerName() + "number: " + item.getCustomerNumber());
			System.out.println("info-borrowed");
			return;
		}
		return;
	}

	@Override
	public void list() throws NullPointerException {
		try {
			if (libraryList.isEmpty()) {
				System.out.println("The list is empty.");
				System.out.println("Enter command:\nFor help, write help + enter.");
				return;
			} else {
				for (Item item : libraryList) {
					if (item.stateIdentifier == STATE_IN_STOCK) {
						System.out.printf("%s,%d,%s,%s\n", item.typeIdentifier, item.id, item.title, STATE_IN_STOCK);
					} else if (item.stateIdentifier == STATE_NOT_AVAILABLE) {
						System.out.printf("%s,%d,%s,\nBorrowed by: %s, %s\n", item.typeIdentifier, item.id, item.title,
								item.customerName, item.customerNumber);
					}
				}
				System.out.println("Enter command:\nFor help, write help + enter.\n");
				return;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			return;
		}
	}

}

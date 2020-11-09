package MovieBookLibrary;

import java.io.File;
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
	private Scanner sc = new Scanner(System.in);
	
	public LibraryManager(String movBookPath) throws FileNotFoundException {
		this.movBookPath = movBookPath;

	}

	@Override
	public void register(String argument) {
		
		System.out.print("\nEnter id:");
		int id = 0;

		try {
			id = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		System.out.print("\nEnter title:");
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
			System.out.print("\nEnter Author:");
			String author = null;
			try {
				author = sc.nextLine();
			} catch (NoSuchElementException e) {
				e.printStackTrace();

			}
			System.out.print("\nEnter number of pages:");
			int nPages = 0;
			try {
				nPages = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			libraryList.add(new Book(id, title, value, author, nPages));
			//sc.close();
			try {
				writeItems();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		} else if (argument.equals("m")) {
			System.out.print("\nEnter Imdb rating:");
			float rating = 0.0f;
			try {
				rating = Float.parseFloat(sc.nextLine());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			System.out.print("\nEnter runtime (mins):");
			int runtime = 0;
			try {
				runtime = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			libraryList.add(new Movie(id, title, value, rating, runtime));
			//sc.close();
			try {
				writeItems();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		} else {
			System.out.print(
					"\nArguments not valid.\nPlease try again.\nFor book, press (b) + enter.\nFor movie, press (m) + enter.");
			String newArg = sc.nextLine();
			register(newArg);
			return;
		}

	}

	public void readParseFile() {
		try {
			Scanner scanner = new Scanner(new File(movBookPath));

			if (movBookPath.isBlank()) {
				System.out
						.print("\nCould not find any information on items to parse.\nProceeding to start application.");
				scanner.close();
				return;
			} else
				while (scanner.hasNextLine()) {
					String csvRecord = scanner.nextLine();
					String[] recordValues = csvRecord.split(",");
					if (csvRecord.equals(Movie.getCsvHeaderString()) || csvRecord.equals(Book.getCsvHeaderString())) {
						scanner.nextLine();
					}
					if (recordValues[0].equals(TYPE_BOOK)) {
						int id = Integer.parseInt(recordValues[1]);
						String title = recordValues[2];
						int value = Integer.parseInt(recordValues[3]);
						String author = recordValues[4];
						int nPages = Integer.parseInt(recordValues[5]);
						Book book = new Book(id, title, value, author, nPages);
						libraryList.add(book);
					}
					if (recordValues[0].equals(TYPE_MOVIE)) {
						int id = Integer.parseInt(recordValues[1]);
						String title = recordValues[2];
						int value = Integer.parseInt(recordValues[3]);
						float rating = Float.parseFloat(recordValues[4]);
						int runtime = Integer.parseInt(recordValues[5]);
						Movie movie = new Movie(id, title, value, rating, runtime);
						libraryList.add(movie);
					}

					// System.out.println("parseItems");
				}
			//scanner.close();
			return;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void writeItems() throws IOException {
		CSVPrinter pW = new CSVPrinter(new FileWriter(movBookPath), CSVFormat.DEFAULT);
		String bookHeader = Book.getCsvHeaderString();
		String movieHeader = Movie.getCsvHeaderString();
		pW.printRecord(bookHeader);
		pW.printRecord(movieHeader);
		try {
			for (Item item : libraryList) {
				if (item.getTypeIdentifier().equals(TYPE_BOOK)) {
					Book book = (Book) item;
					pW.printRecord(item.getTypeIdentifier(), item.getId(), item.getTitle(), item.getValue(),
							book.getAuthor(), book.getnPages());
				} else if (item.getTypeIdentifier().equals(TYPE_MOVIE)) {
					Movie movie = (Movie) item;
					pW.printRecord(item.getTypeIdentifier(), item.getId(), item.getTitle(), item.getValue(),
							movie.getRating(), movie.getRuntime());
				}
			}
			System.out.printf("\nAll items successfully written to file.");
			pW.close();
			return;
		} catch (IOException e) {
			System.out.print("\nwriteItems-fel");
			e.printStackTrace();
			return;
		}

	}

	public void searchLibrary(String argument) {
		System.out.println("Enter Id number:\n>");
		int Id = Integer.parseInt(sc.next());

		for (int i = 0; i > libraryList.size();) {
			Item item = libraryList.get(i);
			if (Integer.valueOf(item.getId()) == Id) {
				if (argument.equals("Info")) {
					int Index = libraryList.indexOf(item);
					info(Index);
				}
				if (argument.equals("Checkin")) {
					int Index = libraryList.indexOf(item);
					checkin(Index);
				}
				if (argument.equals("Checkout")) {
					int Index = libraryList.indexOf(item);
					checkin(Index);
				}
				if (argument.equals("Deregister")) {
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
			String customerName, customerNumber;
			System.out.println("Enter name for person borrowing this item:");
			customerName = sc.nextLine();
			System.out.println("Enter number for person borrowing this item:");
			customerNumber = sc.nextLine();
			item.stateIdentifier = STATE_NOT_AVAILABLE;
			item.setCustomerName(customerName);
			item.setCustomerNumber(customerNumber);
			System.out.println("Item borrowed to: " + customerName + " number: " + customerNumber);
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

			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			return;
		}
	}

}

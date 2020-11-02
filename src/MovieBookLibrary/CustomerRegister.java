package MovieBookLibrary;

import java.util.HashMap;
import java.util.Map;

public class CustomerRegister {

	Map<String, Integer> register = new HashMap<String, Integer>();
	String name = null;
	int phone = 0;
	
	public void addCustomer(String[] arguments) {
		try {
			name = arguments[0];
			phone = Integer.parseInt(arguments[1]);
		} catch (Exception e) {
			System.out.println("Failed to parse arguments correctly.");
		}
		register.put(arguments[0], Integer.parseInt(arguments[1]));
	}
	
	public String customerCsvRecord() {
		return String.format("%s,%d", name,phone);
	}

	public static String getCustomerCsvHeaderString() {
		return "Customer_Name,Customer_PhoneNr";
	}

//	private String name;
//	private int phoneNo;
//	
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public int getPhoneNo() {
//		return phoneNo;
//	}
//	public void setPhoneNo(int phoneNo) {
//		this.phoneNo = phoneNo;
//	}

	// HASHMAP String, Int

}

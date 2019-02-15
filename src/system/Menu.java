package system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Menu {
	
	
	/* Set<Category> categorySet = new HashSet<Category>();


	
		
		//add item to hashset
		public void addCategory(Category category) {
			categorySet.add(category);
	}
		// delete item from hashset
		public void deleteCategory(Category category) {
			categorySet.remove(category);
		}
*/

		//create the hashset
	static Map<String, MenuItem>  menuItems = new HashMap<String, MenuItem>();
			

			
			//add item to hashset
			public static void addItem(String name, MenuItem menuItem) {
				menuItems.put(name,menuItem);
		}
			// delete item from hashset
			public static void deleteItem(String name) {
				menuItems.remove(name);
			}

			

	
public static MenuItem getItem(String name ) {
	return menuItems.get(name);
}
	
	


public void readFile(String filename) {
	try {
		Scanner inputFromFile = new Scanner(new File(filename));
		
		  //checks if there is a line in the scanner 
		while (inputFromFile.hasNextLine()) {
			
			// reads the line and assign the data read from the line to a local variable
			String datainLine = inputFromFile.nextLine();
			
			// Checks if the line has any data, if no data exists in the line then stop the reading and close the file
			if (datainLine.length() != 0) {
				
				//Send the read data from a line for further processing to a function called processDataInLine
				processLine(datainLine);
			}
		}
		inputFromFile.close(); //close the file
		
	} 
	catch (FileNotFoundException fnf) {
		System.out.println("File not found ");
	}
}
/* find menu item by description
public MenuItem getMenuItem(String name) {
	return menuItems.get(name.hashCode())
	
}*/

private Category translateCategory(String test) {
	if(test == "hot drink") {
		return Category.HOTDRINK;
	}
	
	else if (test == "cold drink") {
		return Category.COLDDRINK;
	}
	
	else if (test == "Pastries") {
		return Category.PASTRIES;
	}
	
	else { 
		return Category.SANDWICH;
	}
	
	
	}

public void processLine(String line) {
String itemNumber = "";
String itemPrice = "";
try {
	String parts [] = line.split(",");
	String itemName = parts [1];
	itemNumber = parts [0];
	
	Category category = translateCategory(parts[2]);
	
	
	itemPrice = parts [3];
	BigDecimal Price = new BigDecimal(itemPrice);
	
	
	//BigDecimal Price = BigDecimal.parse (itemPrice);
	int No = Integer.parseInt(itemNumber);
}
	finally{}
	
}



// write text to a file 
	public void writeToFile(String filename, String report) {
		FileWriter fw;
		try {
			fw = new FileWriter(filename);
			fw.write("The report\n");
			fw.write(report);
			fw.close();
		}
		catch(FileNotFoundException fnf) {
			System.out.println(filename + "Not found");
			System.exit(0);
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
			System.exit(1);
		}
	}	
		//print to a file 
		public void printToFile(String filename) throws IOException {
			PrintWriter pw;
			try { 
				pw = new PrintWriter(filename);
				pw.print("The report\n" );
				pw.close();
			}
			catch(FileNotFoundException pfn){
				System.out.print(filename + "Not found");
				System.exit(2);
				
			}
		}
		
		// read stock csv 
		public void readStock() {
			Menu stock = new Menu();
			// declares file path if file is located in diff location not in the same folder
			String filename = "StockItems.csv";
			// reads items from file 
			stock.readFile(filename);
			
		
		 
					
		
		}
		
/*			
		// get in a table 
//		public String getTableOfItemsInMenu() {
//			String report = "Item Number    Item Name   Category   Price \n";
//			for (MenuItem i: ) {
//				report += String.format("%-8s", i.getNumber());
//				report += String.format("%-17s", i.getName());
//				report += String.format("%-10", i.getCategory());
				report += String.format("%-10s", i.getPrice());
				report += "\n";
			
				}
			return report;


}*/
		}
		

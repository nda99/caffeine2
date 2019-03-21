package model;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.*;

public class Menu {

    static private Map<String, MenuItem>  menuItems = new HashMap<String, MenuItem>();
    static private String menuFile;



    /**
     * add item to hashset
     */
    public static void addItem(String name, MenuItem menuItem) {
        menuItems.put(name,menuItem);
    }
    /**
     * delete item from hashset
     * using name 
     */
    public static void deleteItem(String name) {
        menuItems.remove(name);
    }

    /**
     * find item using name
     */
    public static MenuItem getItem(String name) {
        return menuItems.get(name);
    }
    
    /**
     * read file
     * @throws FileNotFoundException
     * @throws IOException
     */
	static public void readFile(String filename) throws FileNotFoundException,IOException{
		Menu.menuFile = filename;

		Scanner inputFromFile = new Scanner(new File(filename));

		// checks if there is a line in the scanner
		while (inputFromFile.hasNextLine()) {

			// reads the line and assign the data read from the line to a local variable
			String datainLine = inputFromFile.nextLine();

			// Checks if the line has any data, if no data exists in the line then stop the
			// reading and close the file
			if (datainLine.length() != 0) {

				// Send the read data from a line for further processing to a function called
				// processDataInLine
				processLine(datainLine);
			}
		}
		inputFromFile.close(); // close the file

	}
/** 
 * translates category from enum
 */
    static public Category translateCategory(String test) {
        if(test.equals("Hot drink")) {
            return Category.HOTDRINK;
        }

        else if (test.equals("Cold drink")) {
            return Category.COLDDRINK;
        }

        else if (test.equals("Pastries")) {
            return Category.PASTRIES;
        }

        else {
            return Category.SANDWICH;
        }
    }

    static public String translateCatToString(Category cat) {

        if(cat.equals(Category.HOTDRINK)){
            return "Hot drink";
        }
        if(cat.equals(Category.COLDDRINK)){
            return "Cold drink";
        }
        if(cat.equals(Category.SANDWICH)){
            return "Sandwich";
        }
        else {
            return "Pastries";
        }
    }

    /**
     * shows how file that is read in is processed
     * @param line
     */
    static public void processLine(String line) {
        String itemNumber = "";
        String itemPrice = "";
        try {
            String parts [] = line.split(",");
            String itemName = parts [1];
            itemNumber = parts [0];

            Category category = translateCategory(parts[2]);


            itemPrice = parts [3];
            double Price = Double.parseDouble(parts[3]);


            int No = Integer.parseInt(itemNumber);
            int quantity = Integer.parseInt(parts[4]);
            //	public MenuItem(int itemNumber, String itemName, Category itemCategory, double price ) {

            MenuItem item = new MenuItem(No,itemName,category,Price, quantity);
            menuItems.put(itemName, item);
        }
        finally{}

    }


    /**
     * how the program can write to a file or report
     */
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
    
    /**
     * how the program prints to file 
     * returns menu item
     */
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

    static public Map<String, MenuItem> getMenuMap(){
        return Menu.menuItems;
    }

    /**
     * Update the file to record the number of points, lazy version, erases the previous file and write it again.
     * This method should always be used before finishing the program to save the changes.
     * @return true if it succeeded, false else
     */
    static public boolean updateFile(){
        String backUpFile = "MenuTempBackup.csv";
        // Copying previous customer file before erasing it
        copyFiles(Menu.menuFile, backUpFile);
        //deleting old customer file
        new File(Menu.menuFile).delete();

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(Menu.menuFile), "utf-8"))) {
            String newline = System.getProperty("line.separator");

            //fill new file with current data in the Hashmap
            for (HashMap.Entry<String, MenuItem> menuItem : menuItems.entrySet()) {
                MenuItem miTemp = menuItem.getValue();
                writer.write( miTemp.getNumber() + "," + miTemp.getName() + "," + translateCatToString(miTemp.getCategory())
                        + "," + miTemp.getPrice() + "," + miTemp.getQuantity() + newline);
            }

            writer.flush();
            writer.close();
        }catch (IOException e){
            copyFiles(backUpFile, Menu.menuFile);
            new File(backUpFile).delete();
            e.printStackTrace();
            return false;
        }

        //delete backup if successful
        new File(backUpFile).delete();
        return true;
    }

    /**
     * Simple copy function used in the updateFile method to create a backup file
     * @param source
     * @param dest
     */
    static void copyFiles(String source, String dest){
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destChannel = new FileOutputStream(dest).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            sourceChannel.close();
            destChannel.close();
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Return an array list of items belonging to the required category
     * @param category category wanted
     * @return
     */
    static public ArrayList<MenuItem> getAllFromCategory(String category){

        ArrayList<MenuItem> items = new ArrayList<>();
        for (HashMap.Entry<String, MenuItem> menuItem : menuItems.entrySet()){
            MenuItem miTemp = menuItem.getValue();
            if (translateCatToString(miTemp.getCategory()).equals(category)) {

                items.add(miTemp);
            }
        }
        return items;
    }

}
		

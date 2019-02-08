package system;


public class MainGUI{
	public static void main(String[] args) {
		Menu menu = new Menu();
		// declares file path if file is located in diff location not in the same folder
		String filename = "menuItems.csv";
		// reads items from file 
		menu.readFile(filename);
		
		// report
		//System.out.println();
	 
				
	}


}

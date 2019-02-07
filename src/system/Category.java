package system;
import java.util.*;

public class Category {
	
	private String category;
	
	//create the hashset
	private HashSet<MenuItem> menuItems;
		

		
		//add item to hashset
		public void addItem(MenuItem menuItem) {
			menuItems.add(menuItem);
	}
		// delete item from hashset
		public void deleteItem(MenuItem menuItem) {
			menuItems.remove(menuItem);
		}

		
		// get category
	public String getCategory() {
		
		return category;
	}
		
		
}

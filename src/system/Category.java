package system;
import java.util.*;

public class Category {
	private String category;
		//create the hashset
		private HashSet<MenuItem> menuItems;
		
		public Category() {
			menuItems = new HashSet<MenuItem>();
		}
		
		// adding all the categories into the hashset
	
	/** Category.add(HotDrink);
	Category.add(ColdDrink);
	Category.add(Pastries);
	Category.add(Sandwiches);
	
	 for (MenuItem i : Category) {
		System.out.println(i.getName() + i.hashCode());
	} */
	
	// constructor to create objects with categories 
	

	
		//add item
		public void add(MenuItem menuItem) {
			menuItems.add(menuItem);
	}
		// delete item
		public void remove(MenuItem menuItem) {
			menuItems.remove(menuItem);
		}

		
		// get category
	public String getCategory() {
		
		return category;
	}
	// compare categories
	public int compareTo(Category other) {
		return category.compareTo(other.category);
	}
	
		//if same name find 
		public boolean equals (Object other) {
			if (other instanceof Category) {
				Category otherCategory = (Category)other;
				if (category.equals (otherCategory.category))
					return true;
			}
			return false;
			
		
	}
		//finding hash code 
		public int hashCode() { 
			return category.hashCode();
	
	}
		
		
}

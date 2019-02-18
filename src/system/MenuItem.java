package system;
import java.math.BigDecimal;



public class MenuItem  implements Comparable<MenuItem> {
	// holds a list of items 
	
	private int number;
	private String name;
	private Category category;
	private double price;


	// constructor 
	public MenuItem(int itemNumber, String itemName, Category itemCategory, double price ) {
		this.number = itemNumber;
		this.name = itemName;
		this.category = itemCategory;
		this.price = price;
		}
	 //compare item categories 
	public int compareTo (MenuItem other) {
		return name.compareTo(other.name);
	}
	//if same name find 
	//@Override
	public boolean equals (Object other) {
		if (other instanceof MenuItem) {
			MenuItem otherItem = (MenuItem)other;
			if (name.equals (otherItem.name) 
				&& number == otherItem.number 
				&& category.equals (otherItem.category) 
				&& price == otherItem.price )
				return true;
		}
		return false;
		}
	//finding hash code 
			public int hashCode() { 
				return name.hashCode();
			}
	
	// return item number 
	public int getNumber() {
		return number;
	}
	
	// return item name 
	public String getName() {
		return name;
	}
	
	// return price 
	public double getPrice() {
		return price;
	}
	
	// return category
	public Category getCategory() {
		return category;
	}
	
	
	// get full details
	public String toString() {
		return getName();
		//return String.format("Item number " + getNumber() +", name " + getName() + " is a " + getCategory() + ".\n" + getName() + " costs " + getPrice() );
	
	}
	
	
	//set item number
	public void setNumber(int number) {
		this.number = number;
	}
	
	//set item name
	public void setName(String name) {
		this.name = name;
	}
	
	//set item price
	public void setPrice(double price) {
		this.price = price;
	}
	
	//set item category
	public void setCategory(Category category) {
		this.category = category;
		
	}
	
	
}
	

package system;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;


public class MenuItem  implements Comparable<MenuItem> {
	// holds a list of items 
	
	private int number;
	private String name;
	private Category category;
	private BigDecimal price;


	// constructor 
	public MenuItem(int itemNumber, String itemName, Category itemCategory, BigDecimal itemPrice ) {
		this.number = itemNumber;
		this.name = itemName;
		this.category = itemCategory;
		this.price = itemPrice;
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
				&& price.equals (otherItem.price) )
				return true;
		}
		return false;
		}
	//finding hash code 
			public int hashCode() { 
				return category.hashCode();
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
	public BigDecimal getPrice() {
		return price;
	}
	
	// return category
	public Category getCategory() {
		return category;
	}
	
	
	// get full details
	public String getFullDetails() {
		return String.format("Item number " + getNumber() +", name " + getName() + " is a " + category.getCategory() + ".\n" + getName() + " costs " + getPrice() );
	
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
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	//set item category
	public void setCategory(Category category) {
		this.category = category;
	}
}
	

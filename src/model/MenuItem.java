package model;

import exceptions.*;


public class MenuItem  implements Comparable<MenuItem> {
	// holds a list of items 
	
	private int number;
	private String name;
	private Category category;
	private double price;
	private int quantity;


	/**
	 *  constructor 
	 */
	public MenuItem(int itemNumber, String itemName, Category itemCategory, double price, int quantity ) {
		this.number = itemNumber;
		this.name = itemName;
		this.category = itemCategory;
		this.price = price;
		this.quantity = quantity;
		}
	 /**
	  * compare item categories 
	  */
	public int compareTo (MenuItem other) {
		return name.compareTo(other.name);
	}
	//if same name find 
	@Override
	public boolean equals (Object other) {
		if (other instanceof MenuItem) {
			MenuItem otherItem = (MenuItem)other;
			if (name.equals (otherItem.name) 
				&& number == otherItem.number 
				&& category.equals (otherItem.category) 
				&& price == otherItem.price )
				return true ;
		}
		
		return false;
		}
	
	@Override
	//finding hash code 
			public int hashCode() { 
				return (int) number * name.hashCode() * category.hashCode();
			}
	
	
	
	/**
	 *  return item number 
	 * @return number
	 */
	public int getNumber() {
		return number;
	}
	
	/**
	 *  return item name 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 *  return price 
	 * @return price
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 *  return category
	 * @return category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * return quantity
	 * @return quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 *  get full details
	 */
	public String toString() {
		return getName();
		//return String.format("Item number " + getNumber() +", name " + getName() + " is a " + getCategory() + ".\n" + getName() + " costs " + getPrice() );
	
	}
	
	
	/**
	 * set item number
	 * @param number
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	
	/**
	 * set item name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * set item price
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * set item category
	 * @param category
	 */
	public void setCategory(Category category) {
		this.category = category;
		
	}

	/**
	 * set quantity
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void decreaseQuantity(int dec) throws NotEnoughStockException {
		if (dec > this.quantity) {
			throw new NotEnoughStockException(this.quantity, this.name);
		} else {
			this.quantity -= dec;
		}
	}

	public void increaseQuantity(int inc){
	    this.quantity += inc;
    }
}
	

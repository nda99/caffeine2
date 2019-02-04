package system;
import java.sql.Timestamp;
import java.util.HashMap;

public class Order {
	
	private int orderID;
	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public HashMap<String, Integer> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(HashMap<String, Integer> orderItems) {
		this.orderItems = orderItems;
	}

	private Timestamp time; 
	//private Customer customer;
	private double total;
	private HashMap<String,Integer> orderItems;
	
	public static void main(String[] args) {
		System.out.println("Hello World");
	}
	
	/**
	 * Add items order hashmap memory
	 * @param item containing item name
	 * @param quantity containing how many of this item have been ordered 
	 */
	public void addItem(String item, int quantity ) {
		orderItems.put(item, quantity);
	}
	
	/**
	 * Removes an item from the order (just subtract one if multiple)
	 * @param item Name of item
	 */
	public void deleteItem(String item) {
		int quantity = orderItems.get(item);
		if (quantity>0) {
			orderItems.put(item, quantity - 1) ;
		}
	}
	
	/**
	 * Calculate order total price with discount voucher
	 * @param voucher
	 * @return order total price
	 */
	public double calculateTotal(String voucher) {
		double discount = validateDiscount(voucher);
		return discount;
	}
	
	/**
	 * Calculate order total price when no discount voucher is provided
	 * @return order total price
	 */
	public double calculateTotal() {
		double price = 0;
		return price;
	}
	
	/**
	 * Checks if discount voucher is valid
	 * @param voucher
	 * @return discount percentage
	 */
	private double validateDiscount(String voucher){
		double discount = 0;
		return discount;
	}
	
}

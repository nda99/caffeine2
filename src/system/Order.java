package system;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Order {
	public Set<Integer> a;
	private int orderID;
	private Timestamp time; 
	//private Customer customer;
	private double total;
	Map<String,Integer> orderItems = new HashMap<String,Integer>();
	
	public Order(Timestamp t) {
		time = t;
	}
	
	public Order() {
		
	}
	
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

	public Map<String, Integer> getOrderItems() {
		return orderItems;
	}


	public static void main(String[] args) {
		System.out.println("Hello World");
		StaffGUI sgui = new StaffGUI();
	}

	/**
	 * Add items order hashmap memory
	 * @param item containing item name
	 * @param quantity containing how many of this item have been ordered 
	 */
	public void addItem(String item, Integer quantity ) {
		this.orderItems.put(item, quantity);
	}
	
	/**
	 * Removes an item from the order (just subtract one if multiple)
	 * @param item Name of item
	 */
	public void deleteItem(String item) {
		
		if (orderItems.get(item)!=null) {
			this.orderItems.put(item, orderItems.get(item) - 1) ;
		}
	}
	
	/**
	 * Calculate order total price with discount voucher and special offers
	 * @param voucher
	 * @return order total price
	 */
	public double calculateTotal(String voucher) {
		double discount = (1-validateDiscount(voucher));
		
		// Order 2 Mochas and get a FREE COOKIE!!!
		if(orderItems.get("Mocha")>=2) {
			if(orderItems.get("Cookie")==null) {
				orderItems.put("Cookie", 1);				
			}
			else {
				orderItems.put("Cookie", orderItems.get("Cookie")+1);
			}
		}
			
		// Order a brownie and  Mochas and get a FREE COOKIE!!!
		if(orderItems.get("Mocha")>=2) {
			if(orderItems.get("Cookie")==null) {
				orderItems.put("Cookie", 1);				
			}
			else {
				orderItems.put("Cookie", orderItems.get("Cookie")+1);
			}
		}
		
		
		return discount*calculateTotal();
	}
	
	/**
	 * Calculate order total price when no discount voucher is provided
	 * @return order total price
	 */
	public double calculateTotal() {
		double price = 1;
		return price;
	}
	
	/**
	 * Checks if discount voucher is valid
	 * @param voucher
	 * @return discount percentage
	 */
	private double validateDiscount(String voucher){
		double discount = 0.2;
		return discount;
	}
	
	public String getInvoice() {
		String item = "Latte";
		return(String.format("%s           x%d \n %f", item, orderItems.get(item),calculateTotal()));
	}
	
	public String getInvoice(String voucher) {
		String item = "";
		for (Map.Entry m: orderItems.entrySet()) {
		item = item + String.format("%s           x%d\n", m.getKey().toString(),m.getValue());
	}
		return(String.format("%s \nTotal: %2.2f\n", item, calculateTotal(voucher)));
	}
	
	
	public boolean equals(Order o) {
		if (o.getTime().equals(this.getTime())) {
			return true;
		}
		return false;
	}
	
	public int compareTo(Order o) {
		return time.compareTo(o.getTime());
	}
	
	public int hashCode() {
		
		return time.toString().hashCode();
	}
	
	public String toString() {
		String items = "";
		for (Map.Entry m: orderItems.entrySet()) {
			items = items + String.format(" %s(x%d) ", m.getKey().toString(),m.getValue());
		}
		return (String.format("Time: %s Items:%s", time.toString(), items));
	}
}

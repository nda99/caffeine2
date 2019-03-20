package model;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import exceptions.*;

public class Order {
	private Timestamp time;
	private String customer = "Anonymous";
	private double total;
	private double discounts;
	private boolean processed = false;
	private boolean validated = false;
	private boolean redeemed = false;
	private boolean queued = false;
	Map<MenuItem,Integer> orderItems = new HashMap<MenuItem,Integer>();
	
	private ActivityLog log = ActivityLog.getInstance();
	
	/**
	 * Constructs Order object from a Timestamp
	 * @param t Timestamp from Order
	 */
	public Order(Timestamp t) {
		this.time = t;
	}
	
	/**
	 * Constructs order object from ArrayList of MenuItems
	 * @param items ArrayList provided by MenuGUI
	 */
	public Order(ArrayList<MenuItem> items){
		Date date= new Date();
		long t = date. getTime();
		Timestamp ts = new Timestamp(t);
		time = ts;
		for(MenuItem item : items) {
			if(!orderItems.containsKey(item)) {
				orderItems.put(item, 1);
				this.total = total + item.getPrice();
			}
			else {
				orderItems.put(item, orderItems.get(item) + 1);
				this.total = total + item.getPrice();
			}
		}
		log.logInfo("Order " + time  + " has been placed");
	}
	
	public Order() {
		
	}
	

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public double getTotal() {
		return total=0.0;
	}

	public void setTotal(double total) {
		this.total = total;
	}
//DONT FORGET TO CHANGE STRING TO MENUITEM


	public Map<MenuItem, Integer> getOrderItems() {
		return orderItems;
	}

	public double getDiscount() {
		return discounts;
	}
	
	public int getItemQuantity(MenuItem i) {
		return orderItems.get(i);
	}
	
	public int getItemQuantity(String i) {
		return orderItems.get(Menu.getItem(i));
	}
	
	public String getCustomer() {
		return customer;
	}
	
	public void setCustomer(String c) {
		customer = c;
	}
	/**
	 * Add items order hashmap memory and computes their total price (given a MenuItem)
	 * @param item containing MenuItem object
	 * @param quantity containing how many of this item have been ordered 
	 */
	public void addItem(MenuItem item, Integer quantity ) {
		if (item != null) {
			this.orderItems.put(item, quantity);
			this.total = total + item.getPrice() * quantity;
		}

	}
	
	
	/**
	 *  Add items order hashmap memory and computes their total price (given the item name)
	 * @param item containing String of item name
	 * @param quantity containing how many of this item have been ordered
	 */
	public void addItem(String item, Integer quantity ) {
		if (Menu.getItem(item) != null) {
			this.orderItems.put(Menu.getItem(item), quantity);
			this.total = total + Menu.getItem(item).getPrice()* quantity;
		}
	}
	
	
	/**
	 * Removes an item from the order (just subtract one if multiple)
	 * @param item Name of item
	 */
	public void deleteItem(MenuItem item) {
		
		if (orderItems.get(item)!=null) {
			this.orderItems.put(item, orderItems.get(item) - 1) ;
		}
	}
	
	/**
	 * Calculate order total price with discount voucher and special offers
	 * @param voucher Voucher to be entered by customer
	 * @return order total price
	 */
	public double calculateTotal(String voucher) {
		double discount = (1-validateDiscount(voucher));
		double offer = 0.0;
		
		
		//MEAL DEAL: COLD DRINK + SANDWICH + PASTRY = �5.99
		boolean cold=false, sand=false, pastry=false;
		double cPrice=0.0, sPrice=0.0, pPrice=0.0;
		for (Map.Entry m: this.orderItems.entrySet()) {
			if(Menu.getItem(m.getKey().toString()).getCategory()==Category.COLDDRINK) {
				cold=true;
				cPrice = Menu.getItem(m.getKey().toString()).getPrice();
			}
			if(Menu.getItem(m.getKey().toString()).getCategory()==Category.SANDWICH) {
				sand=true;
				sPrice = Menu.getItem(m.getKey().toString()).getPrice();
			}

			if(Menu.getItem(m.getKey().toString()).getCategory()==Category.PASTRIES) {
				pPrice = Menu.getItem(m.getKey().toString()).getPrice();
				pastry=true;
			}
		}
		
		if(cold && sand && pastry) {
			if(pPrice + cPrice + sPrice < 0) {
				offer = discount*(pPrice + cPrice + sPrice) - 5.99;
			}
		}
		
		// Order 2 Mochas and get a FREE COOKIE!!!
		if (orderItems.get(Menu.getItem("Mocha")) != null && !validated) {
			if (orderItems.get(Menu.getItem("Mocha")) >= 2) {
				if (orderItems.get(Menu.getItem("Cookie")) == null) {
					orderItems.put(Menu.getItem("Cookie"), 1);
				} else {
					orderItems.put(Menu.getItem("Cookie"), orderItems.get(Menu.getItem("Cookie")) + 1);
				}
			}
		}
		
		// Order a brownie and get your Latte for half price!!!
		if (orderItems.get(Menu.getItem("brownie")) != null ) {
			if (orderItems.get(Menu.getItem("brownie")) >= 1) {
				if (orderItems.get(Menu.getItem("Latte")) != null) {
					offer = offer + (double) Menu.getItem("Latte").getPrice()*0.5;
				}
			}
		}
		
		validated=true;
		discounts = (1-discount)*total + offer;
		return discount*total - offer;
	}
	
	/**
	 * Calculate order total price when no discount voucher is provided
	 * @return order total price
	 */
	public double calculateTotal() {
		double offer = 0.0;
		
		
		//MEAL DEAL: COLD DRINK + SANDWICH + PASTRY = �5.99
		boolean cold=false, sand=false, pastry=false;
		double cPrice=0.0, sPrice=0.0, pPrice=0.0;
		
		for (Map.Entry<MenuItem,Integer> m: orderItems.entrySet()) {


			if(Menu.getItem(m.getKey().toString()).getCategory().equals(Category.COLDDRINK)){
				cold=true;
				cPrice = Menu.getItem(m.getKey().toString()).getPrice();
			}
			if(Menu.getItem(m.getKey().toString()).getCategory()==Category.SANDWICH) {
				sand=true;
				sPrice = Menu.getItem(m.getKey().toString()).getPrice();
			}

			if(Menu.getItem(m.getKey().toString()).getCategory()==Category.PASTRIES) {
				pPrice = Menu.getItem(m.getKey().toString()).getPrice();
				pastry=true;
			}
		}
		
		if(cold && sand && pastry) {

			if(pPrice + cPrice + sPrice < 0) {
				offer = pPrice + cPrice + sPrice - 5.99;
			}
		}
		
		// Order a brownie and get your Latte for half price!!!
		if (orderItems.get(Menu.getItem("brownie")) != null) {
			if (orderItems.get(Menu.getItem("brownie")) >= 1) {
				if (orderItems.get(Menu.getItem("Latte")) != null) {
					offer = offer + (double) Menu.getItem("Latte").getPrice()*0.5;
				}
			}
		}
		
		// Order 2 Mochas and get a FREE COOKIE!!!
		if (orderItems.get(Menu.getItem("Mocha")) != null && !validated) {
			if (orderItems.get(Menu.getItem("Mocha")) >= 2) {
				if (orderItems.get(Menu.getItem("Cookie")) == null) {
					orderItems.put(Menu.getItem("Cookie"), 1);
				} else {
					orderItems.put(Menu.getItem("Cookie"), orderItems.get(Menu.getItem("Cookie")) + 1);
				}
			}
		}
		
		validated=true;
		discounts = offer;
		return total - offer;
	}
	
	/**
	 * Gets discount from customer's voucher or create a new LoyalCustomer
	 * 
	 * @param voucher
	 * @return discount percentage
	 */
	private double validateDiscount(String voucher) {
		AllCustomers customers = null;
		int points = 0;
		double discount = 0.0;

		try {
			customers = new AllCustomers("customers.csv");
		} catch (InvalidCustomerFileException e) {
			// TODO Auto-generated catch block
			System.out.println("File not Found");
		}

		try {  // Tries to create a new Loyal customer
			customers.addLoyalCustomer(voucher);
		} catch (UserNameAlreadyTakenException e) {
			// TODO Auto-generated catch block
			try {// If loyal customer already exists, add points to customer file
				LoyalCustomer loyal = customers.getLoyalCustomer(voucher);
				points = points + loyal.getPoints();
				if (!redeemed) {
					loyal.addPoints((int) total);
					customers.updateFile();
					redeemed = true;
				}
			} catch (CustomerNonExistantException e1) {
				// TODO Auto-generated catch block
			}
		}

		if (points > 17 && points < 300) {
			discount = 0.1;
		}

		else if (points >= 300) {
			discount = 0.2;

		}

		return discount;
	}

	/**
	 * Outputs invoice String
	 * 
	 * @return Invoice String
	 */
	public String getInvoice() {
		String item = "";
		for (Map.Entry m: orderItems.entrySet()) {
			item = item + String.format("%s           x%d        %2.2f\n", m.getKey().toString(),m.getValue(),Menu.getItem(m.getKey().toString()).getPrice());
	}

		item = item + String.format("\nTotal before discounts: %2.2f", calculateTotal() + discounts);
		
		return(String.format("%s \nTotal: %2.2f\n", item, calculateTotal()));
	}
	
	/**
	 * Outputs invoice String given a voucher code
	 * @param voucher Voucher to be entered by customer
	 * @return Invoice String 
	 */
	public String getInvoice(String voucher) {
		String item = "";
		for (Map.Entry m: orderItems.entrySet()) {
		item = item + String.format("%s           x%d        %2.2f\n", m.getKey().toString(),m.getValue(),Menu.getItem(m.getKey().toString()).getPrice());
	}

		if(validateDiscount(voucher)>0) {
			item = item + String.format("\n Loyalty percentage: %2.2f percent ", validateDiscount(voucher)*100);
		}
		
		item = item + String.format("\nTotal before discounts: %2.2f", calculateTotal(voucher) + discounts);
		
		return(String.format("%s \nTotal: %2.2f\n", item, calculateTotal(voucher)));
	}
	
	/**
	 * Order equals order if their Timestamp objects are equal
	 * @param o Order to be compared with
	 * @return true if Orders are equal, false otherwise
	 */
	public boolean equals(Order o) {
		if (o.getTime().equals(this.getTime())) {
			return true;
		}
		return false;
	}
	
	/**
	 * Orders are comparable through their Timestamps
	 * @param o Order to be compared with
	 * @return Comparison of times
	 */
	public int compareTo(Order o) {
		return time.compareTo(o.getTime());
	}
	
	public int hashCode() {
		
		return time.toString().hashCode();
	}
	
	public String getDetails() {
		String items = "";
		for (Map.Entry m: orderItems.entrySet()) {
			items = items + String.format(" %s(x%d) ", m.getKey().toString(),m.getValue());
		}

		return (String.format("%s   | %-10s |  %s\n", time.toString(), customer, items));
	}
	
	public String toString() {
		String items = "";
		for (Map.Entry m: orderItems.entrySet()) {
			//items = items + String.format(" %s(x%d) ", m.getKey().toString(),m.getValue());
			items = items + String.format(",%s,%d", m.getKey().toString(),m.getValue());
		}
		return (String.format("%s,%s %s\n", time.toString(), customer, items));
	}
	/**
	 * Check if order has been processed
	 * @return True if order is processed, false if not
	 */
	public boolean isProcessed() {
		return processed;
	}

	/**
	 * Check if the order is in the queue to be processed
	 * @return True if order is in the queue, false else
	 */
	public boolean isQueued(){
		return queued;
	}

	/**
	 * Process order and update stock
	 */
	public void processOrder() {
		processed = true;

		for (Map.Entry m: orderItems.entrySet()) {
			try {
			Menu.getItem(m.getKey().toString()).decreaseQuantity(orderItems.get(m.getKey()));
			}
			catch(NotEnoughStockException e) {
				processed = false;

				log.logWarning("Order " + time.toString() + " from " + customer + " could NOT be processed");
			}

		}
		
		if(processed) {
			Menu.updateFile();
			log.logInfo("Order " + time.toString() + " from " + customer + " has been processed");
		}
	}

	public void setAsQueued(){
		queued = true;
	}
}

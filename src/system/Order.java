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
	private boolean processed = false;
	Map<MenuItem,Integer> orderItems = new HashMap<MenuItem,Integer>();
	
	public Order(Timestamp t) {
		time = t;
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


	public static void main(String[] args) {
		System.out.println("Hello World");
		LoginGUI gui = new LoginGUI();
		//StaffGUI gui = new StaffGUI();
		Menu menu = new Menu();
		// declares file path if file is located in diff location not in the same folder
		String filename = "menuItems.csv";
		// reads items from file 
		//menu.readFile(filename);
		
		
	}
		

	public int getItemQuantity(MenuItem i) {
		return orderItems.get(i);
	}
	
	public int getItemQuantity(String i) {
		return orderItems.get(Menu.getItem(i));
	}
	
	/**
	 * Add items order hashmap memory and computes their total price (given a MenuItem)
	 * @param item containing MenuItem object
	 * @param quantity containing how many of this item have been ordered 
	 */
	public void addItem(MenuItem item, Integer quantity ) {
		this.orderItems.put(item, quantity);
		this.total = total + item.getPrice().doubleValue()*quantity;
		
	}
	
	/**
	 *  Add items order hashmap memory and computes their total price (given the item name)
	 * @param item containing String of item name
	 * @param quantity containing how many of this item have been ordered
	 */
	public void addItem(String item, Integer quantity ) {
		if (Menu.getItem(item) != null) {
			this.orderItems.put(Menu.getItem(item), quantity);
			this.total = total + Menu.getItem(item).getPrice().doubleValue() * quantity;
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
	 * @param voucher
	 * @return order total price
	 */
	public double calculateTotal(String voucher) {
		double discount = (1-validateDiscount(voucher));
		double offer = 0.0;
		
		// Order 2 Mochas and get a FREE COOKIE!!!
		if (orderItems.get(Menu.getItem("Mocha")) != null) {
			if (orderItems.get(Menu.getItem("Mocha")) >= 2) {
				if (orderItems.get(Menu.getItem("Cookie")) == null) {
					orderItems.put(Menu.getItem("Cookie"), 1);
				} else {
					orderItems.put(Menu.getItem("Cookie"), orderItems.get(Menu.getItem("Cookie")) + 1);
				}
			}
		}
		// Order a brownie and get your Latte for half price!!!
		if (orderItems.get(Menu.getItem("Brownie")) != null) {
			if (orderItems.get(Menu.getItem("Brownie")) >= 1) {
				if (orderItems.get(Menu.getItem("Latte")) != null) {
					offer = offer + (double) Menu.getItem("Latte").getPrice().doubleValue()*0.5;
				}
			}
		}
		
		//MEAL DEAL: COLD DRINK + SANDWICH + PASTRY = £5.99
		boolean cold=false, sand=false, pastry=false;
		double cPrice=0.0, sPrice=0.0, pPrice=0.0;
		for (Map.Entry m: orderItems.entrySet()) {
			if(Menu.getItem(m.getKey().toString()).getCategory()==Category.COLDDRINK) {
				cold=true;
				cPrice = Menu.getItem(m.getKey().toString()).getPrice().doubleValue();
			}
			if(Menu.getItem(m.getKey().toString()).getCategory()==Category.SANDWICH) {
				sand=true;
				sPrice = Menu.getItem(m.getKey().toString()).getPrice().doubleValue();
			}

			if(Menu.getItem(m.getKey().toString()).getCategory()==Category.PASTRIES) {
				pPrice = Menu.getItem(m.getKey().toString()).getPrice().doubleValue();
				pastry=true;
			}
		}
		
		if(cold && sand && pastry) {
			offer = discount*(pPrice + cPrice + sPrice) - 5.99;
		}
		
		return discount*total - offer;
	}
	
	/**
	 * Calculate order total price when no discount voucher is provided
	 * @return order total price
	 */
	public double calculateTotal() {
		double offer = 0.0;
		
		// Order 2 Mochas and get a FREE COOKIE!!!
		if (orderItems.get(Menu.getItem("Mocha")) != null) {
			if (orderItems.get(Menu.getItem("Mocha")) >= 2) {
				if (orderItems.get(Menu.getItem("Cookie")) == null) {
					orderItems.put(Menu.getItem("Cookie"), 1);
				} else {
					orderItems.put(Menu.getItem("Cookie"), orderItems.get(Menu.getItem("Cookie")) + 1);
				}
			}
		}
		// Order a brownie and get your Latte for half price!!!
		if (orderItems.get(Menu.getItem("Brownie")) != null) {
			if (orderItems.get(Menu.getItem("Brownie")) >= 1) {
				if (orderItems.get(Menu.getItem("Latte")) != null) {
					offer = offer + (double) Menu.getItem("Latte").getPrice().doubleValue()*0.5;
				}
			}
		}
		
		//MEAL DEAL: COLD DRINK + SANDWICH + PASTRY = £5.99
		boolean cold=false, sand=false, pastry=false;
		double cPrice=0.0, sPrice=0.0, pPrice=0.0;
		for (Map.Entry m: orderItems.entrySet()) {
			if(Menu.getItem(m.getKey().toString()).getCategory()==Category.COLDDRINK) {
				cold=true;
				cPrice = Menu.getItem(m.getKey().toString()).getPrice().doubleValue();
			}
			if(Menu.getItem(m.getKey().toString()).getCategory()==Category.SANDWICH) {
				sand=true;
				sPrice = Menu.getItem(m.getKey().toString()).getPrice().doubleValue();
			}

			if(Menu.getItem(m.getKey().toString()).getCategory()==Category.PASTRIES) {
				pPrice = Menu.getItem(m.getKey().toString()).getPrice().doubleValue();
				pastry=true;
			}
		}
		
		if(cold && sand && pastry) {
			offer = pPrice + cPrice + sPrice - 5.99;
		}
		return total - offer;
	}
	
	/**
	 * Gets discount from customer's voucher or create a new LoyalCustomer
	 * @param voucher
	 * @return discount percentage
	 */
	private double validateDiscount(String voucher){
		AllCustomers customers = null;
		int points = 0;
		double discount = 0.0;
		
		try { // Tries to create a new Loyal customer
			customers = new AllCustomers("D:\\Software Engineering\\caffeine\\customers.csv");
		} catch (InvalidCustomerFileException e) {
			// TODO Auto-generated catch block
			System.out.println("File not Found");
		}
		
		try { // If loyal customer already exists, add points to customer file
			customers.addLoyalCustomer(voucher);
		} catch (UserNameAlreadyTakenException e) {
			// TODO Auto-generated catch block
			try {
				LoyalCustomer loyal = customers.getLoyalCustomer(voucher);
				points = points + loyal.getPoints();
				loyal.addPoints((int)total);
			} catch (CustomerNonExistantException e1) {
				// TODO Auto-generated catch block
			}
		}
		
		customers.updateFile();
		
		if (points > 17 && points < 300) {
			discount = 0.1;
		}
		
		else if (points >=300) {
			discount = 0.2;
			
		}
		
		
		return discount;
	}
	
	/**
	 * Outputs invoice String
	 * @return Invoice String
	 */
	public String getInvoice() {
		String item = "";
		for (Map.Entry m: orderItems.entrySet()) {
			item = item + String.format("%s           x%d        %2.2f\n", m.getKey().toString(),m.getValue(),Menu.getItem(m.getKey().toString()).getPrice().doubleValue());
	}
		return(String.format("%s \nTotal: %2.2f\n", item, calculateTotal()));
	}
	
	/**
	 * Outputs invoice String given a voucher code
	 * @param voucher
	 * @return Invoice String 
	 */
	public String getInvoice(String voucher) {
		String item = "";
		for (Map.Entry m: orderItems.entrySet()) {
		item = item + String.format("%s           x%d        %2.2f\n", m.getKey().toString(),m.getValue(),Menu.getItem(m.getKey().toString()).getPrice().doubleValue());
	}
		if(validateDiscount(voucher)>0) {
			item = item + String.format("\nTotal before discount: %2.2f", total);
		}
		return(String.format("%s \nTotal: %2.2f\n", item, calculateTotal(voucher)));
	}
	
	/**
	 * Order equals order if their Timestamp objects are equal
	 * @param o Order to be compared with
	 * @return
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
	 * @return
	 */
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
	
	public boolean isProcessed() {
		return processed;
	}
	
	public void processOrder() {
		processed = true;
	}
}

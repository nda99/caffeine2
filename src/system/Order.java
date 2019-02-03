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
		// This line is written by Alaa to test 
	}

	
}

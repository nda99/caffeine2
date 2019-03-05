package main;

import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import controller.MenuController;
import model.AllOrders;
import model.Menu;
import model.Order;
import view.MenuGUI;

public class MainClass {

	public static void main(String[] args) {
		MenuGUI gui = new MenuGUI();
		MenuController mco = new MenuController(gui);
		try {
			AllOrders.readOrderFile("orders.csv");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Menu.updateFile();
		System.out.println("Finished");
		

	
		
		//Queue generator
		PriorityQueue<Order> orderQueue = new PriorityQueue <Order> (new OrderComparator());
		
		// add order to queue
		try {
			AllOrders.readOrderFile("orders.csv");
		} 
		catch (FileNotFoundException e) {
			System.out.print("File not found");
		}
		for(Map.Entry<Timestamp, Order> entry: AllOrders.getOrderMap().entrySet()) {
			orderQueue.add(entry.getValue());
		System.out.print("Orders being processed: " + orderQueue.poll().getDetails());
		
		
		
		}
		
	}
	
}


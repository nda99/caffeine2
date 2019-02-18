package system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class AllOrders {

	private static TreeMap<Timestamp, Order> orderMap = new TreeMap<Timestamp,Order>();

	public AllOrders() {
		
	}
	
	/**
	 * Adds new order to map
	 */
	public static void newOrder() {
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		Order order = new Order(time);
		orderMap.put(time, order);
	}
	
	/**
	 * Reads order file
	 * @param orderFileName
	 */
	public static void readOrderFile(String orderFileName) {
		File file = new File(orderFileName);
		try {
			Scanner scanner = new Scanner(file);
			 while(scanner.hasNextLine()) {
				 String line = scanner.nextLine();
				 String[] details = line.split(",");
				 
				 Timestamp timestamp = toTimestamp(details[0]);
				 Order nOrder = new Order(timestamp);
				 
				 for (int i=1; i<details.length; i = i+2) {
					 nOrder.addItem(Menu.getItem(details[i]), Integer.parseInt(details[i+1]));
				 }
				 
				 orderMap.put(timestamp, nOrder);
			 }
			 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		}
		

		
	}
	
	/**
	 * Updates order file with corrections and removing processed orders
	 * 
	 * @param orderFileName
	 */
	public static void updateOrderFile(String orderFileName) {
		FileWriter file = null;

		try {
			file = new FileWriter(orderFileName);
			for (Map.Entry m : getOrderMap().entrySet()) {

				Order o = orderMap.get(m.getKey());
				if (!orderMap.get(m.getKey()).isProcessed()) {
					file.write(o.toString());
					System.out.println(o.toString());
				}
			}

			file.close();
		} catch (IOException e) {
			System.out.println("File not found");
		}

	}
	
	/**
	 * Converts time in String to Timestamp 
	 * @param time
	 * @return Timestamp object
	 */
	public static Timestamp toTimestamp(String time) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    Date parsedDate = null;
		try {
			parsedDate = dateFormat.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			try {
				parsedDate = dateFormat2.parse(time);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		Timestamp t = new Timestamp(parsedDate.getTime());
		
		return t;
	}
	
	/**
	 * Get Order object from its time
	 * @param t Time string to search for order
	 * @return Order made at provided time
	 */
	public static Order getOrder(String t) throws nullOrderException{
		Timestamp s = toTimestamp(t);
		if (orderMap.get(s)==null) {throw new nullOrderException(t);}
		return orderMap.get(s);
	}
	
	/**
	 * Get Order object from its Timestamp
	 * @param t Time Timestamp object to get correspondent order
	 * @return Order made at provided time
	 */
	public static Order getOrder(Timestamp t) {
		return orderMap.get(t);
	}
	
	/**
	 * Gets the next unprocessed order on the queue (by oldest) 
	 * @return Next order to process
	 */
	public static Order getNextOrder() {
		Order next;
		Entry<Timestamp,Order> ent = orderMap.firstEntry();
		next = ent.getValue();
		
		if(next.isProcessed()) {
			next = getNextOrder(next.getTime());
		}
		
		return next;
	}
	
	/**
	 * Gets the next unprocessed order on the queue (by comparing with time of current order) 
	 * @param t Timestamp of current order
	 * @return Next order to be processed
	 */
	public static Order getNextOrder(Timestamp t) {
		Order next;
		Entry<Timestamp,Order> ent = orderMap.higherEntry(t);
		next = ent.getValue();
		
		if(next.isProcessed()) {
			next = getNextOrder(next.getTime());
		}
		
		return next;
	}
	
	
	/**
	 * Getter for order TreeMap
	 * @return
	 */
	public static TreeMap<Timestamp, Order> getOrderMap() {
		return orderMap;
	}
}

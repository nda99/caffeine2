package model;

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
import exceptions.*;

public class AllOrders {

	private static TreeMap<Timestamp, Order> orderMap = new TreeMap<Timestamp,Order>();
	private static AllOrders orders= new AllOrders();
	
	private AllOrders() {
		
	}
	
	public static AllOrders getInstance() {
		return orders;
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
	 * Adds an existing Order object to the TreeMap
	 * @param o Existing Order Object
	 */
	public static void addOrder(Order o) {
		orderMap.put(o.getTime(), o);
	}
	
	/**
	 * Reads order file, creating several orders.
	 * @param orderFileName
	 */
	public static void readOrderFile(String orderFileName) throws FileNotFoundException {
		File file = new File(orderFileName);
		try {
			Scanner scanner = new Scanner(file);
			 while(scanner.hasNextLine()) {
				 String line = scanner.nextLine();
				 String[] details = line.split(",");
				 
				 Timestamp timestamp = toTimestamp(details[0]);
				 Order nOrder = new Order(timestamp);
				 nOrder.setCustomer(details[1]);
				 for (int i=2; i<details.length; i = i+2) {
					 nOrder.addItem(Menu.getItem(details[i]), Integer.parseInt(details[i+1]));
				 }
				 nOrder.setAsQueued();
				 if(nOrder.isQueued()) {
					 OrdersQueue.getInstance().addOrder(nOrder);
				 }
				 
				 orderMap.put(timestamp, nOrder);
			 }

			 scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
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
				}
			}

			file.close();
		} catch (IOException e) {
			System.out.println("File not found");
		}

	}

	/**
	 * Converts time in String to Timestamp
	 * 
	 * @param time
	 * @return Timestamp object
	 */
	public static Timestamp toTimestamp(String time) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		SimpleDateFormat dateFormat4 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Date parsedDate = null;
		try {
			parsedDate = dateFormat.parse(time);
		} catch (ParseException e) {
			try {
				parsedDate = dateFormat2.parse(time);
			} catch (ParseException e1) {
				try {
					parsedDate = dateFormat3.parse(time);
				} catch (ParseException e2) {

					try {
						parsedDate = dateFormat4.parse(time);
					} catch (ParseException e3) {

						try {
							parsedDate = dateFormat.parse(String.format("%s-01-01 00:00:00.000", time));
						} catch (ParseException e4) {
							try {
								parsedDate = dateFormat.parse(String.format("%s 00:00:00.000", time));
							} catch (ParseException e5) {
								System.out.println(String.format("The time %s is in the wrong format. Try dd/MM/yyyy hh:mm:ss", time));
							}
						}
					}
				}
			}
		}
		
		Timestamp t = new Timestamp(parsedDate.getTime());
		
		return t;
	}
	
	/**
	 * Checks if String has the time formatting
	 * @param time String
	 * @return true if it complies with format, false otherwise
	 */
	public static boolean isTime(String time) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		SimpleDateFormat dateFormat4 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		
	    Date parsedDate = null;
	    
	    boolean format = false;
	    
		try {
			parsedDate = dateFormat.parse(time);
			format = true;
		} catch (ParseException e) {
			try {
				parsedDate = dateFormat2.parse(time);
				format = true;
			} catch (ParseException e1) {
				try {
					parsedDate = dateFormat3.parse(time);
					format = true;
				} catch (ParseException e2) {

					try {
						parsedDate = dateFormat4.parse(time);
						format = true;
					} catch (ParseException e3) {

						try {
							parsedDate = dateFormat.parse(String.format("%s-01-01 00:00:00.000",time));
							format = true;
						} catch (ParseException e4) {

							try {
								parsedDate = dateFormat.parse(String.format("%s 00:00:00.000",time));
								format = true;
							} catch (ParseException e5) {
								System.out.println(String.format("The time %s is in the wrong format. Try dd/MM/yyyy hh:mm:ss", time));
							}
						}
					}
				}
			}
		}
		
		return format;
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
	 * Gets the next order on the queue in relation to another Order
	 * @param o Order which will be followed by Next Order
	 * @return Next Order
	 */
	public static Order getNextOrder(Order o) {
		Order next;
		Entry<Timestamp,Order> ent = orderMap.higherEntry(o.getTime());
		next = ent.getValue();
		
		if(next.isProcessed() && isNextOrder(next)) {
			next = getNextOrder(next.getTime());
		}
		
		return next;
	}
	/**
	 * Checks if there is a next order after input order
	 * @param o Order
	 * @return false if there is no next order, true if there is 
	 */
	public static boolean isNextOrder(Order o) {
		try {
			Entry<Timestamp,Order> ent = orderMap.higherEntry(o.getTime());
			Order next = ent.getValue();
			return true;
		}
		catch(NullPointerException e) {
			return false;
		}
	}
	
	/**
	 * Delete an order from the TreeMap
	 * @param t time of the order (String)
	 */
	public static void deleteOrder(String t) {
		try {
		AllOrders.getOrder(t);
		orderMap.remove(toTimestamp(t));
		}
		catch(nullOrderException e){
			
		}
	}
	
	/**
	 * Getter for order TreeMap
	 * @return
	 */
	public static TreeMap<Timestamp, Order> getOrderMap() {
		return orderMap;
	}
}

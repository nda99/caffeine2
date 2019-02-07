package system;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TreeMap;

public class AllOrders {

	private static TreeMap<Timestamp,Order> orderMap;

	public AllOrders() {
		
	}
	
	
	public void newOrder() {
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		Order order = new Order(time);
		orderMap.put(time, order);
	}
	
	public void readOrderFile(String orderFileName) {
		File file = new File(orderFileName);
		try {
			Scanner scanner = new Scanner(file);
			 while(scanner.hasNextLine()) {
				 String line = scanner.nextLine();
				 String[] details = line.split(",");
				 
				 Timestamp timestamp = this.toTimestamp(details[0]);
				 Order nOrder = new Order(timestamp);
				 
				 for (int i=1; i<details.length; i = i+2) {
					 nOrder.addItem(details[i], Integer.parseInt(details[i+1]));
				 }
				 
				 orderMap.put(timestamp, nOrder);
			 }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		}
		

		
	}
	
	public Timestamp toTimestamp(String time) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
	    Date parsedDate = null;
		try {
			parsedDate = dateFormat.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new java.sql.Timestamp(parsedDate.getTime());
	}
	
}

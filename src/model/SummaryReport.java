package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFileChooser;

public class SummaryReport {
	private int ordersCounter = 0;
	private double totalIncome = 0.0;
	private Map<Timestamp, Order> ordersMap = AllOrders.getInstance().getOrderMap();
/**
 * Constructor method
 	It reads the menuItems.csv and Orders.csv**/
	public SummaryReport() {
		try {
			Menu.readFile("menuItems.csv");

		} catch (FileNotFoundException f) {
			f.getMessage();
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle("Choose an input file to process:");
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);

			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
				System.out.println("getSelectedFile() : " + chooser.getSelectedFile());

				try {
					Menu.readFile(chooser.getSelectedFile().toString());

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				System.out.println("No selection");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

//		try {
//			AllOrders.readOrderFile("orders.csv");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
	}
	
	
	/**
	 * @param time String
	 * @return HashMap of the itemsIncome
	 * */
	
	public HashMap<String, Integer> calculateStatistics(String from,String to)  {
		HashMap<String, Integer> itemsIncome = new HashMap<String, Integer>();

		ordersCounter = 0;
		totalIncome = 0.0;
		
		
		// Calculate frequency analysis for items only for the time range
		for (Map.Entry<Timestamp, Order> entry : AllOrders.getInstance().getOrderMap().entrySet()) {

			if (entry.getKey().after(AllOrders.toTimestamp(from)) && entry.getKey().before(AllOrders.toTimestamp(to))) {

				ordersCounter++;
				totalIncome += entry.getValue().calculateTotal();
				Map<MenuItem, Integer> items = entry.getValue().getOrderItems();
				for (MenuItem item : items.keySet()) {
					if (itemsIncome.containsKey(item.getName())) {

						int temp = itemsIncome.get(item.getName());
						temp++;
						itemsIncome.replace(item.getName(), temp);
					} else {
						itemsIncome.put(item.getName(), 1);
					}
				}
			}

		}

		return itemsIncome;

	}
	
	public boolean printSummaryReport(String from,String to) {
		try (PrintWriter writer = new PrintWriter(new File("Summary Report.csv"))) {

			StringBuilder sb = new StringBuilder();
			sb.append("ItemID");
			sb.append(',');
			sb.append("Times Ordered");
			sb.append(',');
			sb.append("Price*Qty");
			sb.append('\n');

			Map<String, Integer> itemsIncome = calculateStatistics(from,to);

			for (String item : itemsIncome.keySet()) {

				sb.append(item);
				sb.append(',');
				sb.append(itemsIncome.get(item).toString());
				sb.append(',');
				sb.append(Menu.getItem(item).getPrice() * itemsIncome.get(item)).toString();
				sb.append('\n');
			


			}
			sb.append('\n');
			sb.append('\n');
			sb.append("Number of Orders");
			sb.append(',');
			sb.append("Total Income");
			sb.append('\n');
			sb.append(getOrderCounter());
			sb.append(',');
			sb.append(getTotalIncome());
			sb.append('\n');

			writer.write(sb.toString());


			
			return true;

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public int getOrderCounter()
	{
		return ordersCounter;
	}
	
	public double getTotalIncome()
	{
		return totalIncome;
	}
	
	public boolean checkTimeStamp(String time)
	{

		 for (int i = 0; i < time.length(); i++) {
	         // checks whether the character is not a letter
	         // if it is not a letter ,it will return false
	         if ((Character.isLetter(time.charAt(i)) == true)) {
	            return false;
	         }
	      }
		 return true;
	}
	
	public boolean isCorrectRange(String from,String to)
	{

		
		if(checkTimeStamp(from) && checkTimeStamp(to))
		{
			if(AllOrders.isTime(from) && AllOrders.isTime(to))
			{
				if(AllOrders.toTimestamp(to).before(AllOrders.toTimestamp(from)))		
				{
					return false;
				}
				else
				{
					return true;
				}
			}
			else
			{
				return false;
			}
			
		}
		else
			{
				return false;
			}
		 
		
		
	}
	
	 

}

package system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class SummaryReport {
	int ordersCounter = 0;
	double totalIncome = 0.0;
	private Map<Timestamp, Order> ordersMap = AllOrders.getOrderMap();
	AllOrders orders = new AllOrders();


	public SummaryReport() {
		try {
			Menu.readFile("menuItems.csv");

		} catch (FileNotFoundException f) {
			f.getMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			AllOrders.readOrderFile("orders.csv");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public HashMap<String, Integer> calculateStatistics(String from,String to) {
		HashMap<String, Integer> itemsIncome = new HashMap<String, Integer>();

		ordersCounter = 0;
		totalIncome = 0.0;
		from += " 00:00:00.000";
		to += " 00:00:00.000";
		// Calculate frequency analysis for items
		for (Map.Entry<Timestamp, Order> entry : ordersMap.entrySet()) {

			if (entry.getKey().after(AllOrders.toTimestamp(from)) && entry.getKey().before(AllOrders.toTimestamp(to))) {

				ordersCounter++;
				totalIncome += entry.getValue().calculateTotal();
				Map<MenuItem, Integer> items = entry.getValue().getOrderItems();
				System.out.println("order ++");
				for (MenuItem item : items.keySet()) {
					System.out.println("item ++");
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

}

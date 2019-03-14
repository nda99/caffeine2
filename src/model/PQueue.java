package model;

import java.sql.Timestamp;
import java.util.Map;
import java.util.PriorityQueue;

public class PQueue {

	public static PriorityQueue<Order> orderQueue = new PriorityQueue<Order>(new OrderComparator());

	public void returnQueue() {

		for(Map.Entry<Timestamp, Order> entry:AllOrders.getOrderMap().entrySet()) {
			orderQueue.add(entry.getValue());
			System.out.print("Orders being processed: " + entry.getValue().getDetails());
		}
	}
}

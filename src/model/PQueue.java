package model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class PQueue implements Subject{

	public PriorityQueue<Order> orderQueue ;
	private List<Observer> observers;
	private static PQueue pQueue = new PQueue();
	
	public PQueue()
	{this.orderQueue = new PriorityQueue<Order>(new OrderComparator());
}
	
	public synchronized PriorityQueue<Order> getQueue() {

		for(Map.Entry<Timestamp, Order> entry:AllOrders.getOrderMap().entrySet()) {
			if(!entry.getValue().isProcessed())
			{
				orderQueue.add(entry.getValue());
			//orderQueue.setText(entry.getValue().getDetails());
			System.out.print("Orders being processed: " + entry.getValue().getDetails());
			}
		}
		return orderQueue;
	}

	public static synchronized PQueue getInstance() {
		return pQueue;
	}
	
	public synchronized Order getNextOrder()
	{
		return this.orderQueue.poll();
	}
	
	public void registerObserver(Observer o) {
		observers.add(o);
		
	}

	
	public void removeObserver(Observer o) {
		observers.remove(o);
		
		
	}

	
	@Override
	public void notifyObserver() {
		for(Observer o : observers)
		{
			o.update();
		}
	}
}

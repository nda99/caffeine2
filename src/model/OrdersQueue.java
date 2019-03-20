package model;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class OrdersQueue implements Subject{

	public PriorityQueue<Order> orders ;
	private List<Observer> observers;
	private static OrdersQueue pQueue = new OrdersQueue();
	
	public OrdersQueue()
	{
		this.orders = new PriorityQueue<Order>(new OrderComparator());
        observers = new LinkedList<Observer>();

	}
	
	public synchronized PriorityQueue<Order> getQueue() {

		for(Map.Entry<Timestamp, Order> entry:AllOrders.getOrderMap().entrySet()) {
			if(!entry.getValue().isProcessed())
			{
				addOrder(entry.getValue());
			//orderQueue.setText(entry.getValue().getDetails());
			System.out.print("Orders being processed: " + entry.getValue().getDetails());
			}
		}
		return orders;
	}

	public static synchronized OrdersQueue getInstance() {
		return pQueue;
	}
	
	public void addOrder(Order o )
	{
		orders.add(o);
		notifyObserver();
	}
	
	public synchronized Order getNextOrder()
	{
		return this.orders.poll();
	}
	
	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
		
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);	
		
	}

	@Override
	public void notifyObserver() {
		for(Observer o : observers)
		{
			o.update();
			System.out.println("NOTIFIED");

		}
	}
}

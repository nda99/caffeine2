package model;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class OrdersQueue implements Subject{

	public PriorityQueue<Order> orders ;
	private  List<Observer> observers;
	public static OrdersQueue pQueue = new OrdersQueue();
	
	public OrdersQueue()
	{
		this.orders = new PriorityQueue<Order>(new OrderComparator());
        this.observers = new LinkedList<Observer>();

	}
	
	public  PriorityQueue<Order> getQueue() {

	/*	for(Map.Entry<Timestamp, Order> entry:AllOrders.getOrderMap().entrySet()) {
			if(!entry.getValue().isProcessed() && !entry.getValue().isQueued())
			{
				entry.getValue().setAsQueued();
				addOrder(entry.getValue());
			//orderQueue.setText(entry.getValue().getDetails());
			System.out.print("Orders being processed: " + entry.getValue().getDetails());
			}
		}*/
		System.out.println("GET QUEUE CALLED");
		return orders;
	}

	public static OrdersQueue getInstance() {
		return pQueue;
	}
	
	public void addOrder(Order o )
	{
		orders.add(o);
		notifyObserver();
	}
	
	public Order getNextOrder()
	{
		notifyObserver();
		return orders.poll();
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
			System.out.println("OQ NOTIFIED");
			o.update();

		}
	}
}

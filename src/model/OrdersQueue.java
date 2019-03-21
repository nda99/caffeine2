package model;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * This class is a singleton
 */
public class OrdersQueue implements Subject{

	public PriorityQueue<Order> orders ;
	private  List<Observer> observers;
	public static OrdersQueue pQueue = new OrdersQueue();
	
	public OrdersQueue()
	{
		this.orders = new PriorityQueue<Order>(new OrderComparator());
        observers = new LinkedList<Observer>();

	}
	
	public  PriorityQueue<Order> getQueue() {
		//System.out.println("GET QUEUE CALLED");
		return orders;
	}

	public static OrdersQueue getInstance() {
		return pQueue;
	}

	/**
	 * Adds an order to the queue
	 * @param o order to add to the queue
	 */
	public void addOrder(Order o )
	{
		orders.add(o);
		notifyObserver();
	}

	/**
	 * Returns the next order to process in the queue and remove it from queue
	 * @return the removed order
	 */
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

package model;

import java.util.PriorityQueue;

import main.MainClass;

public class OrdersQueue implements Observer{
	private PQueue OrdersQueue;
	
	public OrdersQueue (PQueue orders) {
		this.OrdersQueue = orders ;
		orders.registerObserver(this);
		update();
		
	}
	
	
	
	@Override 
	public void update() {
		
		
	}

}

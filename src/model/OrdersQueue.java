package model;

import java.util.PriorityQueue;

import main.MainClass;

public class OrdersQueue implements Observer{
	private PriorityQueue OrdersQueue;
	
	public OrdersQueue (PriorityQueue orders) {
		this.OrdersQueue = orders ;
		
	}
	
	
	
	@Override 
	public void update() {
		
		
	}

}

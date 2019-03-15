package model;

import java.util.PriorityQueue;

import javax.swing.JPanel;

import main.MainClass;

public class OrdersQueue extends JPanel implements Observer{
	private PriorityQueue OrdersQueue;
	
	public OrdersQueue (PQueue orders) {
		this.OrdersQueue = orders ;
		orders.registerObserver(this);
		update();
		
	}
	
	
	
	@Override 
	public void update() {
		
		
	}

}

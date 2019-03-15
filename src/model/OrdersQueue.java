package model;

import java.util.PriorityQueue;

import javax.swing.JPanel;
import javax.swing.JTextField;

import main.MainClass;

public class OrdersQueue extends JPanel implements Observer{
	private PQueue QueueData;
	private JTextField orderText = new JTextField(10);
	
	public OrdersQueue (PQueue pqueue) {
		this.QueueData = pqueue ;
		pqueue.registerObserver(this);
		update();
		
	}
	
	
	
	@Override 
	public void update() {
		PriorityQueue<Order> order = QueueData.getQueue();
		orderText.setText(text); // needed to implent update dont know how to make it work 
	}

}

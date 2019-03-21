package model;

import java.awt.GridLayout;
import java.sql.Timestamp;
import java.util.Map;
import java.util.PriorityQueue;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main.MainClass;
import view.OrdersGUI;

public class OrdersQueueObserver extends JPanel implements Observer{
	private OrdersQueue queueData;
	private JTextArea orderText = new JTextArea();
	private static JPanel ordersBlock = new JPanel();
	
	
	public OrdersQueueObserver (OrdersQueue pqueue) {
		this.queueData = pqueue ;
		pqueue.registerObserver(this);
		buildBlock();
		
	}
	
	
	
	@Override 
	public void update() {
		System.out.println("OrdersQueue observer called");
		String orders = "There are currently " +queueData.getQueue().size() + " people waiting in the queue:\n";

		for(Order order :queueData.getQueue()) {
				
			orders += order.getCustomer() + "         "+order.getOrderItems().size() +" items\n";
				
			
			}
		orderText.enableInputMethods(false);
		orderText.setLineWrap(true);
		orderText.setWrapStyleWord(true);
		orderText.setText(orders);
		OrdersGUI.updateView();

	}
	
	public void buildBlock()
	{
		ordersBlock.setLayout(new GridLayout(4,1,5,5));
		String process = "There are currently " +queueData.getQueue().size() + " people waiting in the queue:\n";
		orderText.enableInputMethods(false);
		orderText.setLineWrap(true);
		orderText.setWrapStyleWord(true);
		orderText.setText(process);
		ordersBlock.add(orderText);


	}
	
	public static JPanel get()
	{
		System.out.println("Order Block created");
		
		return ordersBlock;
	}

}

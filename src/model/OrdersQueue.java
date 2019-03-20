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

public class OrdersQueue extends JPanel implements Observer{
	private PQueue QueueData;
	private JTextArea orderText = new JTextArea();
	private static JPanel	ordersBlock = new JPanel();
	
	public OrdersQueue (PQueue pqueue) {
		this.QueueData = pqueue ;
		pqueue.registerObserver(this);
		buildBlock();
		
	}
	
	
	
	@Override 
	public void update() {
		PriorityQueue<Order> order = QueueData.getQueue();
		String orders = "";
		for(Map.Entry<Timestamp, Order> entry:AllOrders.getOrderMap().entrySet()) {
				if(!entry.getValue().isProcessed())
				{
					orders += entry.getValue().getCustomer() + "         "+entry.getValue().getOrderItems().size() +"\n";
				}
			
			}
		orderText.setText(orders);
		OrdersGUI.updateView();

	}
	
	public void buildBlock()
	{
		ordersBlock.setLayout(new GridLayout(2,1,5,5));
		String process = "There are currently" +QueueData.getQueue().size() + "people waiting in the queue:";
		orderText.enableInputMethods(false);
		orderText.setLineWrap(true);
		orderText.setWrapStyleWord(true);
		orderText.setText(process);
		ordersBlock.add(orderText);


	}
	
	public static JPanel get()
	{
		System.out.println("Server Block created");
		return ordersBlock;
	}

}

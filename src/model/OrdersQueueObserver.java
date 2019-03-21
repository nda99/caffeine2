package model;

import java.awt.GridLayout;
import java.sql.Timestamp;
import java.util.Map;
import java.util.PriorityQueue;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main.MainClass;
import view.OrdersGUI;

public class OrdersQueueObserver extends JPanel implements Observer{
	private OrdersQueue queueData;
	private JTextArea orderText = new JTextArea();
	private static JPanel ordersBlock = new JPanel();
	private JScrollPane scroll = new JScrollPane(orderText);
	
	
	public OrdersQueueObserver (OrdersQueue pqueue) {
		this.queueData = pqueue ;
		pqueue.registerObserver(this);
		buildBlock();
		
	}
	
	
	
	@Override 
	public void update() {
		String speed = "NORMAL";
		if (StaffThread.getEta() > 8000) speed = "SLOW";
		if (StaffThread.getEta() == 4000) speed = "FAST";
		String orders = "Speed: " + speed + "\n" + "There are currently " +queueData.getQueue().size() + " people waiting in the queue:\n";
		

		for(Order order :queueData.getQueue()) {
				
			orders += order.getCustomer() + "         "+order.getOrderItems().size() +" items\n";
				
			
			}
		orderText.enableInputMethods(false);
		orderText.setText(orders);
		OrdersGUI.updateView();

	}
	
	public void buildBlock()
	{
		ordersBlock.setLayout(new GridLayout(1,1,5,5));
		String speed = "NORMAL";
		if (StaffThread.getEta() > 8000) speed = "SLOW";
		if (StaffThread.getEta() == 4000) speed = "FAST";
		
		String process = "Speed: " + speed + "\n" + "There are currently " +queueData.getQueue().size() + " people waiting in the queue:\n";
		orderText.enableInputMethods(false);
		orderText.setLineWrap(true);
		orderText.setWrapStyleWord(true);
		orderText.setText(process);
		ordersBlock.add(orderText);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);



	}
	
	public static JPanel get()
	{
		
		
		return ordersBlock;
	}

}

package model;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import view.OrdersGUI;

public class StaffServing extends JPanel implements Observer{

	private Staff currentServer;
	private static JPanel	serverBlock = new JPanel();
	private JTextArea tf = new JTextArea();

	
	
	public StaffServing(Staff server)
	{
		currentServer = server;
		server.registerObserver(this);
		buildBlock();
	}
	
	// update method is called when an observer is notified, it will add the orders to the staff block
	@Override
	public void update() {
		String process = currentServer.getFullName();
		process += "\n Processing "+currentServer.getOrderWorkingOn().getDetails();
		process += "\n Total £"+Math.round(currentServer.getOrderWorkingOn().calculateTotal()) ;
		process += "with £ "+currentServer.getOrderWorkingOn().getDiscount()+"+ discount";
		tf.setText(process);
		
		OrdersGUI.updateView();
		
		}
	
	//this method is called once staffserving observer constructor is called, it will only build the block of the staff
	public void buildBlock()
	{
		serverBlock.setLayout(new GridLayout(2,1,5,5));
		String process = currentServer.getFullName();
		tf.enableInputMethods(false);
		tf.setLineWrap(true);
        tf.setWrapStyleWord(true);
		tf.setText(process);
		serverBlock.add(tf);


	}
	
	//this method returns the JPanel to the ordersGUI
	public static JPanel get()
	{
		System.out.println("Server Block created");
		return serverBlock;
	}
	

}

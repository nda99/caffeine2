package model;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import view.OrdersGUI;

public class StaffServing extends JPanel implements Observer{

	private Staff currentServer;
	private static JPanel	serverBlock = new JPanel();
	private JTextArea tf = new JTextArea();

	
	public StaffServing() {}
	
	public StaffServing(Staff server)
	{
		currentServer = server;
		server.registerObserver(this);
		buildBlock();
	}
	
	@Override
	public void update() {
		//JLabel staffName = new JLabel(currentServer.getCurrentServer());
		String process = currentServer.getFullName();
		process += "\n Processing "+currentServer.getOrderWorkingOn().getDetails();
		process += "\n Total £"+Math.round(currentServer.getOrderWorkingOn().calculateTotal()) ;
		process += "with £ "+currentServer.getOrderWorkingOn().getDiscount()+"+ discount";
		tf.setText(process);
		
		//serverBlock.add(staffName);
		OrdersGUI.updateView();
		
		}
	
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
	
	public static JPanel get()
	{
		System.out.println("Server Block created");
		return serverBlock;
	}
	

}

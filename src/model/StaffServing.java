package model;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import view.OrdersGUI;

public class StaffServing extends JPanel implements Observer{

	private StaffThread currentServer;
	private static JPanel	serverBlock = new JPanel();
	private JTextArea tf = new JTextArea();

	
	public StaffServing() {}
	
	public StaffServing(StaffThread server)
	{
		currentServer = server;
		server.registerObserver(this);
		buildBlock();
	}
	
	@Override
	public void update() {
		System.out.println("STAFF THREAD ADDED" +currentServer.getCurrentOrder());
		//JLabel staffName = new JLabel(currentServer.getCurrentServer());
		String process = currentServer.getCurrentServer();
		process += "\n Processing "+currentServer.getCurrentOrder().getDetails();
		process += "\n Total £"+Math.round(currentServer.getCurrentOrder().calculateTotal()) ;
		process += "with £"+currentServer.getCurrentOrder().getDiscount()+"+discount";
		tf.setText(process);
		
		//serverBlock.add(staffName);
		OrdersGUI.updateView();
		
		}
	
	public void buildBlock()
	{
		serverBlock.setLayout(new GridLayout(2,1,5,5));
		String process = currentServer.getCurrentServer();
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

package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.OrdersQueueObserver;
import model.StaffServing;

public class OrdersGUI extends JFrame{
	private JFrame frame = new JFrame();
	private static JPanel servers = new JPanel();
	private static JPanel orders = new JPanel();

	
	public OrdersGUI()
	{
		buildGUI();
	}
	
	public static void updateView()
	{
		 orders = OrdersQueueObserver.get();
		 servers = StaffServing.get();
		
	}


	private void buildGUI() {
		frame.setTitle("Caffeine App");
		frame.setSize(500, 700);
		frame.setLocation(300, 500);
		frame.setVisible(true);
		JLabel title;
		title = new JLabel(" ** Monitor ** ", JLabel.CENTER);
		Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 24);
		title.setFont(titleFont);
		frame.add(title, BorderLayout.NORTH);
		// TODO Auto-generated method stub
		frame.setLayout(new GridLayout(3,1,5,5));
		//JPanel ordersObserver = new JPanel();
		frame.add(orders);
		frame.add(servers);
		//frame.add(staffObserver);
	}
	
}

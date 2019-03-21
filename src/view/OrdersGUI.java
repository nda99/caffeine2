package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.OrdersQueueObserver;
import model.StaffServing;

public class OrdersGUI extends JFrame{
	private JFrame frameTotal = new JFrame();
	private static JPanel frame = new JPanel();
	private static JPanel servers = new JPanel();
	private static JPanel orders = new JPanel();
	private static JPanel speed = new JPanel();
	private static JButton slow = new JButton("Slow");
	private static JButton normal = new JButton("Normal");
	private static JButton fast = new JButton("Fast");
	private static JLabel speedLabel = new JLabel("Speed", JLabel.CENTER);
	private static JLabel title = new JLabel(" ** Monitor ** ", JLabel.CENTER);
	
	
	public OrdersGUI()
	{
		buildGUI();
	}
	
	//this method will update the panels, its called once the blocks are created in the observers
	public static void updateView()
	{
		 orders = OrdersQueueObserver.get();
		 servers = StaffServing.get();
		
	}
	
	// listens to the buttons
	public void addListener(ActionListener e, String speed) {
		switch(speed) {
		case "slow":
			slow.addActionListener(e);
			break;
		case "normal":
			normal.addActionListener(e);
			break;
		case "fast":
			fast.addActionListener(e);
		}
	}


	private void buildGUI() {
		frameTotal.setTitle("Caffeine App");
		frameTotal.setSize(500, 700);
		frameTotal.setLocation(300, 500);
		frameTotal.setVisible(true);

		speed.setLayout(new GridLayout(1,4,10,500));
		frameTotal.setLayout(new BorderLayout());
		//Simulations speed controls
		speed.add(speedLabel);
		speed.add(slow);
		speed.add(normal);
		speed.add(fast);
		Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 24);
		Font speedFont = new Font(Font.SANS_SERIF, Font.BOLD, 24);
		speedLabel.setFont(speedFont);
		title.setFont(titleFont);
		frame.add(title, BorderLayout.NORTH);
		frameTotal.add(speed, BorderLayout.NORTH);
		frame.setLayout(new GridLayout(3,1,5,5));
		//the orders observer > observing ordersQueue
		frame.add(orders);
		//staff observer > observing working staff 
		frame.add(servers);
		frameTotal.add(frame, BorderLayout.CENTER);
	}
	
	/**
	 * Displays custom message window on Orders GUI
	 * 
	 * @param m Custom Message
	 */
	public void displayMessage(String m) {

		JOptionPane.showMessageDialog(frameTotal, m, "Comm", JOptionPane.INFORMATION_MESSAGE);
	}

}

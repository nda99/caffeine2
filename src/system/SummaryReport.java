package system;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SummaryReport extends JFrame implements ActionListener{
	// will need to add hashMap of orders
	AllOrders orders = new AllOrders();
	JFrame frame = new JFrame();
	JButton print = new JButton("Print");
	JPanel centralPanel = new JPanel();
	
	public SummaryReport()
	{
	}
	
	public void calculateStatistics()
	{
		
	}
	
	public void printSummaryReport()
	{
		
	}
	
	public void viewSummaryReport()
	{
		
	}
	
	public void buildGUI()
	{
		frame.setTitle("Caffiene App");
		frame.setSize(500,700);
		frame.setLocation(300,500);
		frame.setVisible(true);
		frame.setLayout(new BorderLayout(50, 50));
		JLabel title;
		title = new JLabel(" ** Summary Report ** ", JLabel.CENTER);
		Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 24);
		title.setFont(titleFont);
		frame.add(title, BorderLayout.NORTH);
		//frame.add(print);
		//centralPanel.setLayout(new GridLayout(1,4,10,10));
		centralPanel.add(title);
		centralPanel.add(title);
		centralPanel.add(title);
		centralPanel.add(title);
		frame.add(centralPanel, BorderLayout.CENTER);

		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

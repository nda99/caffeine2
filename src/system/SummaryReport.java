package system;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SummaryReport extends JFrame implements ActionListener{
	// will need to add hashMap of orders
	//private Map<Timestamp, Order> orders =  AllOrders.orderMap;
	JFrame frame = new JFrame();
	JButton print = new JButton("Print");
	JPanel northPanel = new JPanel();
	JPanel centralPanel = new JPanel();
	JLabel label = new JLabel("OrderID");
	JLabel label1 = new JLabel("Date");
	JLabel label2 = new JLabel("Customer");
	JLabel label3 = new JLabel("total");
	/*JLabel label4 = new JLabel("total");
	JLabel label5 = new JLabel("test");
	JLabel label6 = new JLabel("test");
	JLabel label7 = new JLabel("test");*/
	
	public SummaryReport()
	{
	}
	
	public void calculateStatistics()
	{
		
	}
	
	public void printSummaryReport()
	{
		 try (PrintWriter writer = new PrintWriter(new File("Summary Report.csv"))) {

		      StringBuilder sb = new StringBuilder();
		      sb.append("ID");
		      sb.append(',');
		      sb.append("Date");
		      sb.append(',');
		      sb.append("Customer");
		      sb.append(',');
		      sb.append("Total");
		      sb.append('\n');

		      writer.write(sb.toString());

		      System.out.println("done!");

		    } catch (FileNotFoundException e) {
		      System.out.println(e.getMessage());
		    }
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
		frame.setLayout(new BorderLayout(10, 10));
		JLabel title;
		title = new JLabel(" ** Summary Report ** ", JLabel.CENTER);
		Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 24);
		title.setFont(titleFont);
		northPanel.setLayout(new GridLayout(2,1));
		northPanel.add(title);
		print.addActionListener(this);
		northPanel.add(print);

		
		frame.add(northPanel, BorderLayout.NORTH);
		centralPanel.setLayout(new GridLayout(0,4));
		centralPanel.add(label);
		centralPanel.add(label1);
		centralPanel.add(label2);
		centralPanel.add(label3);
		/*centralPanel.add(label4);
		centralPanel.add(label5);
		centralPanel.add(label6);
		centralPanel.add(label7);*/

		frame.add(centralPanel, BorderLayout.CENTER);

		
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource() == print)
		{
			System.out.print("creating csv file");

			printSummaryReport();
		}
		
	}

}

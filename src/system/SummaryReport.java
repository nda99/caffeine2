package system;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class SummaryReport extends JFrame implements ActionListener{
	//private AllOrders orders = new AllOrders();
	
	private Map<Timestamp,Order> ordersMap = AllOrders.getOrderMap();
	JFrame frame = new JFrame();
	JButton print = new JButton("Print");
	JPanel northPanel = new JPanel();
	JPanel centralPanel = new JPanel();
	JPanel westPanel = new JPanel();
	JPanel eastPanel = new JPanel();
	JLabel label = new JLabel("ItemID");
	JLabel label1 = new JLabel("Times Ordered");
	JLabel label2 = new JLabel("Price*Qty");
	HashMap<String,Integer> itemsIncome = new HashMap<String,Integer>();

	//JLabel label3 = new JLabel("total");
	
	
	public SummaryReport()
	{AllOrders temp = new AllOrders();
	temp.readOrderFile("orders.csv");
	}
	
	public HashMap<String,Integer> calculateStatistics()
	{
		// Calculate frequency analysis for items
		System.out.println(ordersMap);

        for (Map.Entry<Timestamp,Order> entry : ordersMap.entrySet())  
        {
        	Map<MenuItem, Integer> items = entry.getValue().getOrderItems();
        //	Map<String,Integer> items = entry.getValue().getOrderItems();
        	System.out.println("order ++");
        	for (MenuItem item:items.keySet())
        	//for (String item:items.keySet())
        	{
        		System.out.println("item ++");
        		//if(itemsIncome.containsKey(item.getName())) {
        		if(itemsIncome.containsKey(item.getName())) {
        			//cntr = itemsIncome.get(item.getName());
        			int temp = itemsIncome.get(item.getName());
        			temp ++;
        			itemsIncome.replace(item.getName(), temp);
        			//itemsIncome.replace(item,temp);
        		}
        		else
        		{
        			itemsIncome.put(item.getName(),1);
        			//itemsIncome.put(item, 1);
        		}
        	}
        	
        	
        	
        }
        System.out.println("Done");
        return itemsIncome;
        
	}
	
	public void printSummaryReport()
	{
		 try (PrintWriter writer = new PrintWriter(new File("Summary Report.csv"))) {

		      StringBuilder sb = new StringBuilder();
		      sb.append("ItemID");
		      sb.append(',');
		      sb.append("Times Ordered");
		      sb.append(',');
		      sb.append("Price*Qty");
		      sb.append('\n');

		      writer.write(sb.toString());

		      System.out.println("done!");

		    } catch (FileNotFoundException e) {
		      System.out.println(e.getMessage());
		    }
	}
	
	public void viewSummaryReport()
	{
		calculateStatistics();
		JLabel label3 ;
		JLabel label4 ;
		JLabel label5 ;
		for (String item : itemsIncome.keySet())
		{
			 label3 = new JLabel(item);
			 label4 = new JLabel(itemsIncome.get(item).toString());
			 label5 = new JLabel("number");
			centralPanel.add(label3);
			centralPanel.add(label4);
			centralPanel.add(label5);
		}
		


		
	}
	
	public void buildGUI()
	{
		frame.setTitle("Caffiene App");
		frame.setSize(500,700);
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocation(300,500);
		//  Container con = frame.getContentPane();
		 centralPanel.setBackground(Color.WHITE);

		frame.setVisible(true);
		frame.setLayout(new BorderLayout(10, 10));
		JLabel title;
		title = new JLabel(" ** Summary Report ** ", JLabel.CENTER);
		Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 24);
		title.setFont(titleFont);
		northPanel.setLayout(new GridLayout(2,1));
		frame.add(eastPanel,BorderLayout.EAST);
		frame.add(westPanel,BorderLayout.WEST);

		northPanel.add(title);
		print.addActionListener(this);
		northPanel.add(print);

		
		frame.add(northPanel, BorderLayout.NORTH);
		centralPanel.setLayout(new GridLayout(0,3));
		JTable table = new JTable(5,3);
		centralPanel.add(label);
		centralPanel.add(label1);
		centralPanel.add(label2);
		//centralPanel.add(label3);
		

		frame.add(centralPanel, BorderLayout.CENTER);
		viewSummaryReport();
		
		
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

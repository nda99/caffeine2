package system;


import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class SummaryReport extends JFrame implements ActionListener{
	
	private JFrame frame = new JFrame();
	private JButton print = new JButton("Print");
	private JButton view = new JButton("View");
	private JTextField dateFrom= new JTextField(10);
	private JTextField dateTo= new JTextField(10);
	private JPanel northPanel = new JPanel();
	private JPanel centralPanel = new JPanel();
	private JPanel westPanel = new JPanel();
	private JPanel eastPanel = new JPanel();
	private Map<Timestamp,Order> ordersMap = AllOrders.getOrderMap();
	private String from;
	private String to;
	private int ordersCounter = 0;
	private double totalIncome =0.0;

	private HashMap<String,Integer> itemsIncome = new HashMap<String,Integer>();
	AllOrders temp = new AllOrders();
	
	
	public SummaryReport()
	{
		
		 AllOrders.readOrderFile("orders.csv");

	}
	
	
	public void calculateStatistics(String from,String to)
	{
		from += " 00:00:00.000";
		to += " 00:00:00.000";
		  // Calculate frequency analysis for items
		    for (Map.Entry<Timestamp,Order> entry : ordersMap.entrySet())  
		    {
		
		    	if(entry.getKey().after(temp.toTimestamp(from)) && entry.getKey().before(temp.toTimestamp(to)))
		    	{

		    		ordersCounter ++;
		    		totalIncome += entry.getValue().calculateTotal();
		        	Map<MenuItem, Integer> items = entry.getValue().getOrderItems();
		        	System.out.println("order ++");
		        	System.out.println(items);
		        	for (MenuItem item:items.keySet())
		        	{
		        		System.out.println("item ++");
		        		if(itemsIncome.containsKey(item.getName())) {
		        			int temp = itemsIncome.get(item.getName());
		        			temp ++;
		        			itemsIncome.replace(item.getName(), temp);
		        		}
		        		else
		        		{
		        			itemsIncome.put(item.getName(),1);
		        		}
		        	}
		    	}
		    	
		    	
		    }		
		
		  System.out.println("Done");
	       // return itemsIncome;
	      
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
	
	public void viewSummaryReport(String from, String to)
	{
		calculateStatistics(from,to);
		String[] columnNames = {"ItemID",
                "Times Ordered",
                "Price*Qty"};
		System.out.println(itemsIncome);

		Object[][] data = new Object[itemsIncome.keySet().size()][3] ;
		for (Object obj : data)
		{
			for (String item : itemsIncome.keySet())
			{
				String[] itemDetails = new String[3];
				 itemDetails[0] = item;
				 itemDetails[1] = itemsIncome.get(item).toString();
				 double total = Menu.getItem(item).getPrice() * itemsIncome.get(item);
				 itemDetails[1] = Double.toString(total);
				 obj = itemDetails;
			}
			System.out.println("printing object");
		}
		
		JTable tableA = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(tableA);
		frame.add(scrollPane);
		
		/*String[] columnNames = {"ItemID",
                "Times Ordered",
                "Price*Qty"};
		Object[][] data = new Object[itemsIncome.keySet().size()][3] ;
		for (Object obj : data)
		{
			for (String item : itemsIncome.keySet())
			{
				String[] itemDetails = new String[3];
				 itemDetails[0] = item;
				 itemDetails[1] = itemsIncome.get(item).toString();
				 double total = Menu.getItem(item).getPrice().doubleValue() * itemsIncome.get(item);
				 itemDetails[1] = Double.toString(total);
				 obj = itemDetails;
			}
		}*/


		
	}
	
	private void setupNorthPanel()
	{
		//North Panel has two sub panels, a: is for the title, b: is for the filtering by date and print
		JPanel a = new JPanel();
		JPanel b = new JPanel();
		//Setting up A panel
		a.setLayout(new BorderLayout(10,10));
		JLabel title;
		title = new JLabel(" ** Summary Report ** ", JLabel.CENTER);
		Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 24);
		title.setFont(titleFont);
		a.add(title,BorderLayout.CENTER);
		//Setting up B panel
		b.setLayout(new GridLayout(1,6,5,5));
		print.addActionListener(this);
		view.addActionListener(this);
		b.add(new JLabel("From: "));
		b.add(dateFrom);
		b.add(new JLabel("To: "));
		b.add(dateTo);
		b.add(view);
		b.add(print);
		//Adding both panels to the north panel
		northPanel.setLayout(new GridLayout(2,1));
		northPanel.add(a);
		northPanel.add(b);
		//Adding north panel to the main frame
		frame.add(northPanel, BorderLayout.NORTH);
		
	}
	private void setupCentralPanel()
	{
		 centralPanel.setBackground(Color.WHITE);
		 centralPanel.setLayout(new GridLayout(2,1));
		// viewSummaryReport(to,from);
		/* String[] columnNames = {"First Name",
                 "Last Name",
                 "Sport",
                 "# of Years",
                 "Vegetarian"};
		 Object[][] data = {
				    {"Kathy", "Smith",
				     "Snowboarding", new Integer(5), new Boolean(false)},
				    {"John", "Doe",
				     "Rowing", new Integer(3), new Boolean(true)},
				    {"Sue", "Black",
				     "Knitting", new Integer(2), new Boolean(false)},
				    {"Jane", "White",
				     "Speed reading", new Integer(20), new Boolean(true)},
				    {"Joe", "Brown",
				     "Pool", new Integer(10), new Boolean(false)}
				};
		 JTable table = new JTable(data, columnNames);
			System.out.print("displaying summary report");

		 JScrollPane scrollPane = new JScrollPane(table);
		 table.setFillsViewportHeight(true);
		 centralPanel.add(scrollPane);
		 String[] columnNames2 = {"First Name",
                 "Last Name",
                 "Sport",
                 "# of Years",
                 "Vegetarian"};
		 Object[][] data2 = {
				    {"Kathy", "Smith",
				     "Snowboarding", new Integer(5), new Boolean(false)},
				    {"John", "Doe",
				     "Rowing", new Integer(3), new Boolean(true)},
				    {"Sue", "Black",
				     "Knitting", new Integer(2), new Boolean(false)},
				    {"Jane", "White",
				     "Speed reading", new Integer(20), new Boolean(true)},
				    {"Joe", "Brown",
				     "Pool", new Integer(10), new Boolean(false)}
				};
		 JTable table2 = new JTable(data2, columnNames2);
			System.out.print("displaying summary report");

		 JScrollPane scrollPane2 = new JScrollPane(table2);
		 table2.setFillsViewportHeight(true);
		 centralPanel.add(scrollPane2);
		 frame.add(centralPanel, BorderLayout.CENTER);*/



	}
	
	public void buildGUI()
	{
		//Setting up the main frame 
		frame.setLayout(new BorderLayout(10, 10));
		frame.setTitle("Caffiene App");
		frame.setSize(600,700);
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocation(300,500);
		frame.setVisible(true);

		//Adding central panel to the main frame through the setupCentralPanel method
		setupCentralPanel();
		//Adding north panel to the main frame through the setupNorthPanel method
		setupNorthPanel();
		//Adding east and west panels to the main frame
		frame.add(eastPanel,BorderLayout.EAST);
		frame.add(westPanel,BorderLayout.WEST);
				
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// Action taken once 'Print' button is clicked
		if(event.getSource() == print)
		{
			System.out.print("creating csv file");

			printSummaryReport();
		}
		// Action taken once 'View' button is clicked

		if(event.getSource() == view)
		{
			from = dateFrom.getText();
			to = dateTo.getText();
			 viewSummaryReport(from,to);
		}
		
	}

}

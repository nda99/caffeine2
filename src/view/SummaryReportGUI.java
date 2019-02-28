package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.Menu;
import model.SummaryReport;

public class SummaryReportGUI extends JFrame implements ActionListener {

	private JFrame frame = new JFrame();
	private JButton print = new JButton("Print");
	private JButton view = new JButton("View");
	private JTextField dateFrom = new JTextField("2018-01-01");
	private JTextField dateTo = new JTextField("2019-02-20");
	private JPanel northPanel = new JPanel();
	private JPanel centralPanel = new JPanel();
	private JPanel westPanel = new JPanel();
	private JPanel eastPanel = new JPanel();
	private String from = "2018-01-01";
	private String to = "2020-01-01";
	private SummaryReport report;

/** @return void
 * Constructor that creates an object of SummaryReport
 * */
	public SummaryReportGUI() {
		report = new SummaryReport();

	}

	
	public void viewSummaryReport(String from, String to) {
		
		//Refresh the central panel because the user can change the values in from and to
		centralPanel.removeAll();
		centralPanel.repaint();
		centralPanel.revalidate();
		//----------------BUILD THE FIRST REPORT
		//Get the hashMap of the items, and times ordered
		Map<String, Integer> itemsIncome = report.calculateStatistics(from,to);
		//Create array for columns
		String[] columnNames = { "ItemID", "Times Ordered", "Price*Qty" };
		//Create 2d array of objects
		Object[][] data = new Object[itemsIncome.keySet().size()][3];
		int counter = 0;
		for (String item : itemsIncome.keySet()) {

			String[] itemDetails = new String[3];
			itemDetails[0] = item;
			itemDetails[1] = itemsIncome.get(item).toString();
			double total = Menu.getItem(item).getPrice() * itemsIncome.get(item);
			itemDetails[2] = Double.toString(total);
			data[counter] = itemDetails;

			counter++;
		}
		//Adding them to JTable
		JTable tableA = new JTable(data, columnNames);
		//Adding JTable to JScrollPane
		JScrollPane scrollPane = new JScrollPane(tableA);
		tableA.setFillsViewportHeight(true);
		//Adding ScrollPane to central panel
		centralPanel.add(scrollPane);
		//Adding central panel to the frame
		frame.add(centralPanel);

		//----------------BUILD THE FIRST REPORT
		//Create array for columns

		String[] columnNames2 = { "Number of Orders", "Total Income" };
		//Create 2d array of objects
		Object[][] data2 = new Object[1][2];
		//Create 1D array to add it to the 2D array
		String[] orderDetails = new String[2];
		orderDetails[0] = "" + report.getOrderCounter();
		orderDetails[1] = "" + report.getTotalIncome();
		//Fill in the only object for this report
		data2[0] = orderDetails;
		//Adding them to JTable

		JTable tableB = new JTable(data2, columnNames2);
		//Adding JTable to JScrollPane
		JScrollPane scrollPane2 = new JScrollPane(tableB);
		tableB.setFillsViewportHeight(true);
		//Adding ScrollPane2 to central panel
		centralPanel.add(scrollPane2);
		//Adding central panel to the frame
		frame.add(centralPanel);

	}

	private void setupNorthPanel() {
		// North Panel has two sub panels, a: is for the title, b: is for the filtering
		// by date and print
		JPanel a = new JPanel();
		JPanel b = new JPanel();
		// Setting up A panel
		a.setLayout(new BorderLayout(10, 10));
		JLabel title;
		title = new JLabel(" ** Summary Report ** ", JLabel.CENTER);
		Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 24);
		title.setFont(titleFont);
		a.add(title, BorderLayout.CENTER);
		// Setting up B panel
		b.setLayout(new GridLayout(1, 6, 5, 5));
		print.addActionListener(this);
		view.addActionListener(this);
		b.add(new JLabel("From: "));
		b.add(dateFrom);
		b.add(new JLabel("To: "));
		b.add(dateTo);
		b.add(view);
		b.add(print);
		// Adding both panels to the north panel
		northPanel.setLayout(new GridLayout(2, 1));
		northPanel.add(a);
		northPanel.add(b);
		// Adding north panel to the main frame
		frame.add(northPanel, BorderLayout.NORTH);

	}

	private void setupCentralPanel() {
		centralPanel.setBackground(Color.WHITE);
		centralPanel.setLayout(new GridLayout(2, 1,5,5));
		if(report.isCorrectRange(from, to))
		{
			viewSummaryReport(from, to);

		}

	}

	public void buildGUI() {
		// Setting up the main frame
		frame.setLayout(new BorderLayout(10, 10));
		frame.setTitle("Caffeine App");
		frame.setSize(600, 700);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocation(300, 500);
		frame.setVisible(true);

		// Adding central panel to the main frame through the setupCentralPanel method
		setupCentralPanel();
		// Adding north panel to the main frame through the setupNorthPanel method
		setupNorthPanel();
		// Adding east and west panels to the main frame
		frame.add(eastPanel, BorderLayout.EAST);
		frame.add(westPanel, BorderLayout.WEST);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// Action taken once 'Print' button is clicked
		if (event.getSource() == print) {
			System.out.print("creating csv file");
			//Call print method, if succeeds or not show a popup window
			if(report.printSummaryReport(from,to)) {
			JOptionPane.showMessageDialog(frame, "CSV file of the summary report is successfully created!");
			}
			else
			{
			JOptionPane.showMessageDialog(frame, "Oops! Something went wrong, couldn't create CSV of the report!");

			}

		}
		// Action taken once 'View' button is clicked

		if (event.getSource() == view) {
			//Set the values of from and to, because both are needed to call other methods
			from = dateFrom.getText();
			to = dateTo.getText();
			//Check first if the from is before the to

		
			
				if(report.isCorrectRange(from, to))
				{
					viewSummaryReport(from, to);

				}
				else
				{
					JOptionPane.showMessageDialog(frame, "Oops! You didn't set the interval correctly!");

				}
			
		}

	}

}

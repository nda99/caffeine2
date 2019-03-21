package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.*;

public class StaffGUI extends JFrame{

	// StaffGUI Components:
	private JFrame frame = new JFrame();
	private JPanel northPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JPanel eastPanel = new JPanel();
	private JPanel westPanel = new JPanel();
	private JPanel service = new JPanel();
	private JLabel user = new JLabel("Welcome");
	private JLabel logout = new JLabel("Logout");
	private JLabel label1 = new JLabel("Update Stock", JLabel.LEFT);
	private JLabel label2 = new JLabel("View Stock", JLabel.LEFT);
	private JLabel label3 = new JLabel("View Orders", JLabel.LEFT);
	private JLabel label4 = new JLabel("Edit Loyal Customers", JLabel.LEFT);
	private JLabel label5 = new JLabel("Confirm Stock Orders", JLabel.LEFT);
	private JLabel label6 = new JLabel("Summary Report", JLabel.LEFT);
	private JLabel label7 = new JLabel("Current Orders", JLabel.LEFT);
	private JButton start = new JButton("Start Serving");
	private JButton finish = new JButton("Stop Serving");
	


	// Constructor Method don't forget to add user object as parameter
	public StaffGUI(Staff staff) {
		System.out.println("staff Logged in ");
		user.setText("Weclome " + staff.getUserName());
		northPanel.add(user);
		northPanel.add(logout);
		frame.add(northPanel,BorderLayout.EAST);
		buildGUI();
	}

	public StaffGUI(Manager mngr) {
		System.out.println("Manager Logged in ");
		user.setText("Weclome " + mngr.getUserName());
		northPanel.add(user);
		northPanel.add(logout);
		frame.add(northPanel, BorderLayout.EAST);
		buildGUI();
		addManagerOptions();
	}

	public void buildGUI() {

		frame.setTitle("Caffeine App");
		frame.setSize(500, 700);
		frame.setLocation(300, 500);
		frame.setVisible(true);
		frame.setLayout(new BorderLayout(50, 50));
		JLabel title;
		title = new JLabel(" ** Staff Menu ** ", JLabel.CENTER);
		Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 24);
		title.setFont(titleFont);
		frame.add(title, BorderLayout.NORTH);
		centerPanel.setLayout(new GridLayout(8, 1, 5, 10));
		service.setLayout(new GridLayout(1,2,5,5));
		service.add(start);
		service.add(finish);
		
		Font itemsFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
		label1.setFont(itemsFont);
		label2.setFont(itemsFont);
		label3.setFont(itemsFont);
		label6.setFont(itemsFont);
		label7.setFont(itemsFont);
		
		centerPanel.add(service);
		centerPanel.add(label2);
		centerPanel.add(label3);
		centerPanel.add(label6);
		centerPanel.add(label1);
		centerPanel.add(label7);
		frame.add(eastPanel, BorderLayout.EAST);
		frame.add(westPanel, BorderLayout.WEST);
		frame.add(centerPanel, BorderLayout.CENTER);
		
	}

	public JLabel getLabel(int i) {
		if (i==1) return label1;
		else if (i==2) return label2;
		else if (i==3) return label3;
		else if (i==7) return label6;
		else if (i==9) return label7;
		else if (i==8) return logout;
		
		return null;
	}
	public void setLabel(JLabel l, int i) {
		if (i==1) label1 = l;
		else if (i==2) label2 = l;
		else if (i==3) label3 = l;
		else if (i==7) label6 = l;
		else if (i==8) logout = l;
		else if (i==9) label7 = l;

	}
	// Consider making in private method
	private void addManagerOptions() {
		
		Font itemsFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
		label4.setFont(itemsFont);
		label5.setFont(itemsFont);
		label6.setFont(itemsFont);
		centerPanel.add(label4);
		centerPanel.add(label5);
		//disable the service buttons for the manager
		start.setEnabled(false);
		finish.setEnabled(false);

	}
	
	//this method return the service buttons to allow controller to enable and disable them
	public JButton getButton(String btn)
	{
		switch(btn){
			case "start": return start;
			case "finish" : return finish;
			default: return null;
		}
	}

	public JFrame getFrame() {
		return frame;
	}
	
	public void addStartListener(MouseListener m) {
		start.addMouseListener(m);
	}
	public void addFinishListener(MouseListener m) {
		finish.addMouseListener(m);
	}
	public void addViewOrdersListener(MouseListener m) {
		label3.addMouseListener(m);
	}
	public void addStockListener(MouseListener m) {
		label2.addMouseListener(m);
	}
	public void addUpdateListener(MouseListener m) {
		label1.addMouseListener(m);
	}
	public void addSummaryReportListener(MouseListener m) {
		label6.addMouseListener(m);
	}
	public void addOrdersListener(MouseListener m) {
		label7.addMouseListener(m);
	}
	public void addLogoutListener(MouseListener m) {
		logout.addMouseListener(m);
	}
	


}

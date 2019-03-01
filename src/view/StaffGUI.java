package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.SummaryController;
import controller.ViewOrdersController;
import model.*;

public class StaffGUI extends JFrame{

	// StaffGUI Components:
	private JFrame frame = new JFrame();
	private JPanel northPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JPanel eastPanel = new JPanel();
	private JPanel westPanel = new JPanel();
	private JLabel user = new JLabel("Welcome");
	private JLabel logout = new JLabel("Logout");
	private JLabel label1 = new JLabel("Update Stock", JLabel.LEFT);
	private JLabel label2 = new JLabel("View Stock", JLabel.LEFT);
	private JLabel label3 = new JLabel("View Orders", JLabel.LEFT);
	private JLabel label4 = new JLabel("Edit Loyal Customers", JLabel.LEFT);
	private JLabel label5 = new JLabel("Confirm Stock Orders", JLabel.LEFT);
	private JLabel label6 = new JLabel("Summary Report", JLabel.LEFT);

	// Constructor Method don't forget to add user object as parameter
	public StaffGUI(Staff staff) {
		System.out.println("staff Logged in ");
		user.setText("Weclome " + staff.getUserName());
		northPanel.add(user);

		northPanel.add(logout);
		frame.add(northPanel, BorderLayout.EAST);
		buildGUI();
	}

	public StaffGUI(Manager mngr) {
		System.out.println("Manager Logged in ");
		user.setText("Weclome " + mngr.getUserName());
		northPanel.add(user);
		northPanel.add(logout);
		logout.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				logout.setForeground(Color.blue);
			}

			public void mouseExited(MouseEvent e) {
				logout.setForeground(Color.black);
			}

			public void mouseClicked(MouseEvent e) {
				mngr.logout();
				frame.dispose();
			}
		});
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
		centerPanel.setLayout(new GridLayout(7, 1, 10, 10));
		Font itemsFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
		label1.setFont(itemsFont);
		label2.setFont(itemsFont);
		label3.setFont(itemsFont);
		label6.setFont(itemsFont);
		centerPanel.add(label2);
		centerPanel.add(label3);
		centerPanel.add(label6);
		centerPanel.add(label1);
		frame.add(eastPanel, BorderLayout.EAST);
		frame.add(westPanel, BorderLayout.WEST);
		frame.add(centerPanel, BorderLayout.CENTER);
		//control(label1, 1);
		//control(label2, 2);
		//control(label3, 3);
		//control(label6, 7);
		//control(logout, 8);
	}

	public JLabel getLabel(int i) {
		if (i==1) return label1;
		else if (i==2) return label2;
		else if (i==3) return label3;
		else if (i==7) return label6;
		else if (i==8) return logout;
		
		return null;
	}
	public void setLabel(JLabel l, int i) {
		if (i==1) label1 = l;
		else if (i==2) label2 = l;
		else if (i==3) label3 = l;
		else if (i==7) label6 = l;
		else if (i==8) logout = l;
	}
	// Consider making in private method
	private void addManagerOptions() {
		Font itemsFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
		label4.setFont(itemsFont);
		label5.setFont(itemsFont);
		label6.setFont(itemsFont);
		// control(label4,1);
		// control(label5,2);
		//control(label6, 7);
		centerPanel.add(label4);
		centerPanel.add(label5);

	}

	public JFrame getFrame() {
		return frame;
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
	public void addLogoutListener(MouseListener m) {
		logout.addMouseListener(m);
	}


}

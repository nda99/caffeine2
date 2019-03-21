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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.StaffController;
import model.*;

public class LoginGUI extends JFrame {
	private JFrame frame = new JFrame();
	private JPanel centerPanel = new JPanel();
	private JPanel northPanel = new JPanel();
	private JPanel westPanel = new JPanel();
	private JPanel southPanel = new JPanel();
	private JPanel eastPanel = new JPanel();
	private String staffFile = "staff.csv";
	private JLabel title = new JLabel("** Login **");
	private JLabel user = new JLabel("Username");
	private JTextField userField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JLabel password = new JLabel("Password");
	private JButton slogin = new JButton("Staff Login");
	private JButton mlogin = new JButton("Manager Login");
	private JLabel register = new JLabel("Register");
	private JLabel error = new JLabel();

	
	
	public LoginGUI()
	{
		buildGUI();
	}
	public void setupNorthPanel()
	{
		
		Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 24);
		title.setFont(titleFont);
		northPanel.add(title,BorderLayout.CENTER);
		frame.add(northPanel,BorderLayout.NORTH);
	}
	public void setupCentralPanel()
	{
		centerPanel.setLayout(new GridLayout(4,2,5,5));
		centerPanel.add(user);
		centerPanel.add(userField);
		centerPanel.add(password);
		centerPanel.add(passwordField);
		//slogin.addActionListener(this);
		centerPanel.add(slogin);
		//mlogin.addActionListener(this);
		centerPanel.add(mlogin);
		centerPanel.add(register);
		centerPanel.add(error);
		frame.add(centerPanel,BorderLayout.CENTER);

	}
	
	public void buildGUI()
	{
	frame.setLayout(new BorderLayout(90, 90));
	frame.setTitle("Caffeine App");
	frame.setSize(700,500);
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
	frame.add(southPanel,BorderLayout.SOUTH);
			
	}
	
	public void setRegisterForeground(String colour) {
		if(colour.equals("black")) register.setForeground(Color.black);
		else if (colour.equals("blue")) register.setForeground(Color.blue);
	}
	
	public void addStaffLoginListener(ActionListener s) {
		slogin.addActionListener(s);
	}
	
	public void addManagerLoginListener(ActionListener m) {
		mlogin.addActionListener(m);
	}
	
	public void addRegisterListener(MouseListener m) {
		register.addMouseListener(m);
	}
	
	public String getUser() {
		return userField.getText();
	}
	
	public char[] getPassword() {
		return passwordField.getPassword();
	}
	
	public void setErrorText(String text) {
		error.setText(text);
	}
	
	public void setErrorRed() {
		error.setForeground(Color.red);
	}
	
	public void setErrorVisible(boolean vis) {
		error.setVisible(vis);
	}
	
	public String getStaffFile() {
		return staffFile;
	}
	
	public JFrame getFrame() {
		return frame;
	}

}

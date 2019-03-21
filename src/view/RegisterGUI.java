package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import exceptions.*;

import model.*;

public class RegisterGUI extends JFrame{
	private JFrame frame = new JFrame();
	private JPanel centerPanel = new JPanel();
	private JPanel northPanel = new JPanel();
	private JPanel westPanel = new JPanel();
	private JPanel southPanel = new JPanel();
	private JPanel eastPanel = new JPanel();
	private JLabel title = new JLabel("** Register **");
	private JLabel user = new JLabel("Username");
	private JTextField userField = new JTextField();
	private JLabel userhint = new JLabel();

	private JPasswordField passwordField = new JPasswordField();
	private JLabel password = new JLabel("Password");
	private JLabel passhint = new JLabel();

	private JLabel email = new JLabel("Email Address");
	private JTextField emailField = new JTextField();
	private JLabel emailhint = new JLabel();

	private JLabel fullname = new JLabel("Full Name");
	private JTextField nameField = new JTextField();
	private JLabel namehint = new JLabel();

	private JButton register = new JButton("Register");
	private JLabel error = new JLabel("Error");
	private JRadioButton staff = new JRadioButton("Staff");
	private JRadioButton manager = new JRadioButton("Manager");
	private ButtonGroup position = new ButtonGroup();
	private String positionValue = "";
	private String staffFile = "staff.csv";
	private Login login;

	public RegisterGUI() {
		try {
			this.login = new Login(staffFile);
			System.out.println("successfull creation");
		} catch (InvalidUsersFileException e) {
			error.setText(e.getMessage());
		}
		buildGUI();
	}

	public void setupNorthPanel() {

		Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 24);
		title.setFont(titleFont);
		northPanel.add(title, BorderLayout.CENTER);
		frame.add(northPanel, BorderLayout.NORTH);
	}

	public void setupCentralPanel() {
		centerPanel.setLayout(new GridLayout(6, 3, 5, 5));
		centerPanel.add(fullname);
		centerPanel.add(nameField);
		centerPanel.add(namehint);

		centerPanel.add(user);
		centerPanel.add(userField);
		centerPanel.add(userhint);

		centerPanel.add(password);
		centerPanel.add(passwordField);
		centerPanel.add(passhint);

		centerPanel.add(email);
		centerPanel.add(emailField);
		centerPanel.add(emailhint);

		position.add(staff);
		position.add(manager);
		centerPanel.add(staff);
		centerPanel.add(manager);
		centerPanel.add(register);
		//register.addActionListener(this);
		//staff.addActionListener(this);
		//manager.addActionListener(this);
		error.setVisible(false);
		centerPanel.add(error);

		frame.add(centerPanel, BorderLayout.CENTER);

	}

	public void buildGUI() {
		frame.setLayout(new BorderLayout(90, 90));
		frame.setTitle("Caffeine App");
		frame.setSize(700, 500);
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
		frame.add(southPanel, BorderLayout.SOUTH);

	}

	public void setErrorForeground() {
		error.setForeground(Color.red);
	}
	public void setErrorVisible(boolean v) {
		error.setVisible(v);
	}
	public void setErrorText(String t) {
		error.setText(t);
	}
	
	public void setUserHint(String t) {
		userhint.setText(t);
	}
	public String getStaffFile() {
		return staffFile;
	}
	
	public String getUser() {
		return userField.getText();
	}
	
	public String getName() {
		return nameField.getText();
	}
	
	public String getEmail() {
		return emailField.getText();
	}
	
	public char[] getPassword() {
		return passwordField.getPassword();
	}
	
	public void addStaffListener(ActionListener s) {
		staff.addActionListener(s);
	}
	
	public void addManagerListener(ActionListener m) {
		manager.addActionListener(m);
	}
	
	public void addRegisterListener(ActionListener r) {
		register.addActionListener(r);
	}
	
	public void setHint(String text, String field) {
		if(field.equals("user")) userhint.setText(text);
		if(field.equals("email")) emailhint.setText(text);
		if(field.equals("name")) namehint.setText(text);
		if(field.equals("pass")) passhint.setText(text);
		
	}


	public void setComponents() {
	userhint.setText("");
	passhint.setText("");
	emailhint.setText("");
	namehint.setText("");
	error.setText("");
	userhint.setForeground(Color.RED);
	namehint.setForeground(Color.RED);
	emailhint.setForeground(Color.RED);
	passhint.setForeground(Color.RED);
	error.setForeground(Color.RED);}
	
}

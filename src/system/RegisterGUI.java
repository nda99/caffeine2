package system;

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

public class RegisterGUI extends JFrame implements ActionListener{
	JFrame frame = new JFrame();
	JPanel centerPanel = new JPanel();
	JPanel northPanel = new JPanel();
	JPanel westPanel = new JPanel();
	JPanel southPanel = new JPanel();
	JPanel eastPanel = new JPanel();
	String staffFile = "staff.csv";
	JLabel title = new JLabel("** Register **");
	JLabel user = new JLabel("Username");
	JTextField userField = new JTextField();
	JPasswordField passwordField = new JPasswordField();
	JLabel password = new JLabel("Password");
	JLabel email = new JLabel("Email Address");
	JTextField emailField = new JTextField();
	JLabel fullname = new JLabel("Full Name");
	JTextField nameField = new JTextField();
	JButton register = new JButton("Register");
	JLabel error = new JLabel("Error");
	private JRadioButton staff = new JRadioButton("Staff");
	private JRadioButton manager= new JRadioButton("Manager");
	private ButtonGroup position = new ButtonGroup();
	String pass;

	
	
	public RegisterGUI()
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
		centerPanel.setLayout(new GridLayout(6,2,5,5));
		centerPanel.add(fullname);
		
		centerPanel.add(nameField);
		centerPanel.add(user);
		centerPanel.add(userField);
		centerPanel.add(password);
		centerPanel.add(passwordField);
		centerPanel.add(email);
		centerPanel.add(emailField);
		position.add(staff);
		position.add(manager);
		centerPanel.add(staff);
		centerPanel.add(manager);
		centerPanel.add(register);
		error.setVisible(false);
		centerPanel.add(error);
		
		frame.add(centerPanel,BorderLayout.CENTER);

	}
	
	public void buildGUI()
	{
	frame.setLayout(new BorderLayout(90, 90));
	frame.setTitle("Caffiene App");
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
	@Override
	public void actionPerformed(ActionEvent e) 
{
		// TODO Auto-generated method stub
		
		

	}
}

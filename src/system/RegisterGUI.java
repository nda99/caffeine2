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
	private JRadioButton manager= new JRadioButton("Manager");
	private ButtonGroup position = new ButtonGroup();
	private String positionValue ="";
	private String staffFile = "staff.csv";
	private Login login;


	
	
	public RegisterGUI()
	{
		try {
			this.login  = new Login(staffFile);
			System.out.println("successfull creation");
		} catch (InvalidUsersFileException e) {
			// TODO Auto-generated catch block
			error.setText(e.getMessage());
		}
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
		centerPanel.setLayout(new GridLayout(6,3
				,5,5));
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
		register.addActionListener(this);
		staff.addActionListener(this);
		manager.addActionListener(this);
		error.setVisible(false);
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
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == register)
		{
			checkRegisterationForm();
		}
		if(e.getSource() == staff)
		{
			System.out.println("clicked");

			positionValue = "Staff";
		}
		if(e.getSource() == manager)
		{
			positionValue = "Manager";
		}
		
		

	}
	private void checkRegisterationForm() {
		userhint.setText("");
		passhint.setText("");
		emailhint.setText("");
		namehint.setText("");
		error.setText("");
		userhint.setForeground(Color.RED);
		namehint.setForeground(Color.RED);
		emailhint.setForeground(Color.RED);
		passhint.setForeground(Color.RED);
		error.setForeground(Color.RED);




		String passText = new String(passwordField.getPassword());

		System.out.println(login);
		//First check if user already exist
		if(userField.getText().equals("") || nameField.getText().equals("") || passText.equals("") || emailField.getText().equals(""))
		{
			if (userField.getText().equals(""))
			{	
				userhint.setText("*Required Field");
			}
			if (nameField.getText().equals(""))
			{
				namehint.setText("*Required Field");
			}
			if(passText.equals(""))
			{
				passhint.setText("*Required Field");
			}
			if(emailField.getText().equals(""))
			{
				emailhint.setText("*Required Field");
			}
		}
		
		else if(!login.staffExist(userField.getText()))
		{

			try {
				if(!login.register(userField.getText(), passText, positionValue, nameField.getText(), emailField.getText()))
				{
					error.setText("Oops something went wrong!");
				}
				else
				{
					switch(positionValue) {
						case "Staff":
									try {
										Staff staff = new Staff(userField.getText(),staffFile);
										Boolean loggedIn = staff.login(passText);
											if(loggedIn == true)
											{
												error.setText("");
												error.setVisible(false);
												StaffGUI gui = new StaffGUI(staff);
											}
										} catch (StaffNonExistantException e) {
										// TODO Auto-generated catch block
										error.setText(e.getMessage());
										}
						
						break;
						case "Manager":
									try {
										Manager manager = new Manager(userField.getText(),staffFile);
										Boolean loggedIn = manager.login(passText);
										if(loggedIn == true)
										{
											StaffGUI gusi = new StaffGUI(manager);
											error.setVisible(false);
										}	
										
									} catch (StaffNonExistantException e1) {
										// TODO Auto-generated catch block
										error.setForeground(Color.red);
										error.setText("Oops! User does not exist. ");
										e1.printStackTrace();
									} catch (NotAManagerException e1) {
										// TODO Auto-generated catch block
										error.setForeground(Color.red);
										error.setVisible(true);
										error.setText("Oops! You don't have manager privilege. ");
									}
							
						}
				}
			} catch (InvalidRegistration e) {
				// TODO Auto-generated catch block
				error.setText(e.getMessage());			
				}
			
			
			//LoginGUI loginGui = new LoginGUI();

		}
		else
		{
			userhint.setText("User already exists!");

		}
		
	}
}

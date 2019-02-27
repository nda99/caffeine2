package system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginGUI extends JFrame implements ActionListener{
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
		slogin.addActionListener(this);
		centerPanel.add(slogin);
		mlogin.addActionListener(this);
		centerPanel.add(mlogin);
		centerPanel.add(register);
		centerPanel.add(error);
		register.addMouseListener(new MouseAdapter()  
		{  
			public void mouseEntered(MouseEvent e)
			{
				register.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent e)
			{
				register.setForeground(Color.black);
			}
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	RegisterGUI rGUI = new RegisterGUI();
		    }  
		
		});
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
		// TODO Auto-generated method stub
		if(e.getSource() == slogin)
		{
				try {
					Staff staff = new Staff(userField.getText(),staffFile);
					String passText = new String(passwordField.getPassword());
					Boolean loggedIn = staff.login(passText);
					if(loggedIn == true)
					{
						error.setText("");
						error.setVisible(false);
						frame.dispose();

						StaffGUI gui = new StaffGUI(staff);
					}
					else
					{
						error.setText("Invalid Password");
					}
					
				} catch (StaffNonExistantException e1) {
					// TODO Auto-generated catch block
					error.setForeground(Color.red);
					error.setText("Oops! User does not exist. ");
					//e1.printStackTrace();
				}
			
			
		}
		if(e.getSource() == mlogin)
		{
			try {
				Manager manager = new Manager(userField.getText(),staffFile);
				Boolean loggedIn = manager.login(new String(passwordField.getPassword()));
				if(loggedIn == true)
				{
					StaffGUI gusi = new StaffGUI(manager);
					error.setVisible(false);
					frame.dispose();

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
			//	e1.printStackTrace();
			}
		}	

	}
}

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
	JFrame frame = new JFrame();
	JPanel centerPanel = new JPanel();
	JPanel northPanel = new JPanel();
	JPanel westPanel = new JPanel();
	JPanel southPanel = new JPanel();
	JPanel eastPanel = new JPanel();
	String staffFile;
	JLabel title = new JLabel("** Login **");
	JLabel user = new JLabel("Username");
	JTextField userField = new JTextField();
	JPasswordField passwordField = new JPasswordField();
	JLabel password = new JLabel("Password");
	JButton slogin = new JButton("Staff Login");
	JButton mlogin = new JButton("Manager Login");
	JLabel register = new JLabel("Register");

	
	
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
		centerPanel.setLayout(new GridLayout(4,2,20,20));
		centerPanel.add(user);
		centerPanel.add(userField);
		centerPanel.add(password);
		centerPanel.add(passwordField);
		slogin.addActionListener(this);
		centerPanel.add(slogin);
		mlogin.addActionListener(this);
		centerPanel.add(mlogin);
		centerPanel.add(register);
		
		frame.add(centerPanel,BorderLayout.CENTER);

	}
	
	public void buildGUI()
	{
	frame.setLayout(new BorderLayout(90, 90));
	frame.setTitle("Caffiene App");
	frame.setSize(600,500);
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == slogin)
		{
				try {
					Staff staff = new Staff(userField.getText(),staffFile);
					staff.login(passwordField.getPassword().toString());
					
				} catch (StaffNonExistantException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			
		}
		
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
		    	
		    }  
		
		});
	


	

}
	}

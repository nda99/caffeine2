package system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StaffGUI extends JFrame implements ActionListener{
	
	//StaffGUI Components:
	private JFrame frame = new JFrame();
	private JPanel centerPanel = new JPanel();
	private JPanel eastPanel = new JPanel();
	private JPanel westPanel = new JPanel();
	private JLabel label1 = new JLabel("Update Stock", JLabel.LEFT);
	private JLabel label2 = new JLabel("View Stock", JLabel.LEFT);
	private JLabel label3 = new JLabel("View Orders", JLabel.LEFT);
	private JLabel label4 = new JLabel("Edit Menu", JLabel.LEFT);
	private JLabel label5 = new JLabel("Discounts", JLabel.LEFT);
	private JLabel label6 = new JLabel("Confirm Stock Orders", JLabel.LEFT);
	private JLabel label7 = new JLabel("Summary Report", JLabel.LEFT);
	
	//Constructor Method don't forget to add user object as parameter
	public StaffGUI()
	{
		buildGUI();
	}
	public void buildGUI()
	{

		frame.setTitle("Caffiene App");
		frame.setSize(500,700);
		frame.setLocation(300,500);
		frame.setVisible(true);
		frame.setLayout(new BorderLayout(50, 50));
		JLabel title;
		title = new JLabel(" ** Staff Menu ** ", JLabel.CENTER);
		Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 24);
		title.setFont(titleFont);
		frame.add(title, BorderLayout.NORTH);
		centerPanel.setLayout(new GridLayout(7, 1,10,10));
		Font itemsFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
		label1.setFont(itemsFont);
		label2.setFont(itemsFont);
		label3.setFont(itemsFont);
		label7.setFont(itemsFont);
		centerPanel.add(label2);
		centerPanel.add(label3);
		centerPanel.add(label7);
		centerPanel.add(label1);
		frame.add(eastPanel, BorderLayout.EAST);
		frame.add(westPanel, BorderLayout.WEST);

		frame.add(centerPanel, BorderLayout.CENTER);
		control(label1,1);
		control(label2,2);
		control(label3,3);
		control(label7,7);

		
		


	}
	
	//Consider making in private method
	public void addManagerOptions()
	{
		Font itemsFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
		label4.setFont(itemsFont);
		label5.setFont(itemsFont);
		label6.setFont(itemsFont);
		centerPanel.add(label4);
		centerPanel.add(label5);
		centerPanel.add(label6);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent event) {}
	
	public void control(JLabel label, int number)
	{
		label.addMouseListener(new MouseAdapter()  
		{  
			public void mouseEntered(MouseEvent e)
			{
				label.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent e)
			{
				label.setForeground(Color.black);
			}
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	switch(number)
		    	{
		    		case 1: break;
		    		case 2:break;
		    		case 3: break;
		    		case 4:break;
		    		case 7:SummaryReport report = new SummaryReport();
		    		report.buildGUI();
		    		break;
		    		
		    	}
		    }  
		}); 
		
	}
	

}

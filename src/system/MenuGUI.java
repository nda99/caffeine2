package system;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuGUI extends JFrame implements ActionListener{
JFrame Menuframe = new JFrame("Caffeine App");
private Menu menu = new Menu();

JPanel northPanel = new JPanel();
JPanel eastPanel = new JPanel();
JPanel southPanel = new JPanel();
JPanel westPanel = new JPanel();
JPanel centerPanel = new JPanel();
JLabel l1, l2, l3, l4, l5, l6;
JButton b1, b2, b3, b4, b5, b6;
JButton StaffLogin, Pastries, Sandwiches, HotDrinks, ColdDrinks, Checkout;

//need to add drop down boxes too

//set up panels that will be on the GUI 

public MenuGUI(){

	
	
	//JMenuBar menuBar = new JMenuBar();
    /*menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.PAGE_AXIS));
    menuBar.add(createMenu("Menu 1"));
    menuBar.add(createMenu("Menu 2"));
    menuBar.add(createMenu("Menu 3")); */
	
	
	
	setNorthPanel();
	setSouthPanel();
	setEastPanel();
	setWestPanel();
	setCenterPanel();
	
	//setMenuBar();

	Menuframe.setSize(300,300);
	Menuframe.setLocation(300,500);
	Menuframe.setVisible(true);
	
	//Menuframe.add(menuBar);
	
	Menuframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
}

//create the north JPanel that allows a staff member to login
private void setNorthPanel(){
	JPanel northPanel = new JPanel();
	northPanel.setLayout(new GridLayout(1, 2, 5, 5));
	JLabel title;
	title = new JLabel(" ** Menu ** ", JLabel.CENTER);
	Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 24);
	title.setFont(titleFont);
	b1 = new JButton("Staff Login");
	//b1.addActionListener(this);
	northPanel.add(title);

	northPanel.add(b1);
	//northPanel.add(StaffLogin);
	
	Menuframe.add(northPanel, BorderLayout.NORTH);
	pack();
	}

private void setWestPanel(){
	JPanel westPanel = new JPanel();
	westPanel.setLayout(new GridLayout(4, 1, 5, 5));
	
	
	b2 = new JButton("Pastries");
	b3 = new JButton("Sandwiches");
	b4 = new JButton("Hot Drinks");
	b5 = new JButton("Cold Drinks");
	
	b2.addActionListener(this);
	b3.addActionListener(this);
	b4.addActionListener(this);
	b5.addActionListener(this);
	
	westPanel.add(b2);
	//westPanel.add(Pastries);
	
	westPanel.add(b3);
	//westPanel.add(Sandwiches);
	
	westPanel.add(b4);
	//westPanel.add(HotDrinks);
	
	westPanel.add(b5);
	//westPanel.add(ColdDrinks);
	
	Menuframe.add(westPanel, BorderLayout.WEST);
	
	pack();
	}


private void setSouthPanel(){
	JPanel southPanel = new JPanel();
	b6 = new JButton("Checkout");
	b6.addActionListener(this);
	
	southPanel.add(b6);
	//southPanel.add(Checkout);
	
	Menuframe.add(southPanel, BorderLayout.SOUTH);
	
	pack();
}

private void setEastPanel(){
	JPanel eastPanel = new JPanel();
	
	Menuframe.add(eastPanel, BorderLayout.EAST);
	
	pack();
}

private void setCenterPanel(){
	JPanel centerPanel = new JPanel();
	centerPanel.setLayout(new GridLayout(10,2,5,5));
	
	Menuframe.add(centerPanel, BorderLayout.CENTER);
	
	pack();
}


	
	
	public static void main (String [] args) {
		MenuGUI gui = new MenuGUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		
		if (e.getSource()==b2)
		{
			displayItems(b2.getText());
		}
		
		if (e.getSource()==b3)
		{
			displayItems(b3.getText());
		}
		if (e.getSource()==b4)
		{
			displayItems(b4.getText());
		}
		if (e.getSource()==b5)
		{
			displayItems(b5.getText());
		}
		
	}
	
	public void displayItems(String category) {
		String filename = "menuItems.csv";
		try {
			Menu.readFile(filename);
		} catch (FileNotFoundException fnf) {
			// file not there 
			System.out.println("File not found");
		} catch (IOException e) {
			// having problems reading and writing to file
			System.out.println("Problems accessing the file");
			System.exit(1);
		}
		int counter =1;
		ArrayList<JPanel> itemBlock = new ArrayList<JPanel>();

		Map<String,MenuItem> items = Menu.getMenuMap();
		for (Map.Entry<String,MenuItem> entry : items.entrySet())  
	    {
			
			if(entry.getValue().getCategory().equals(Menu.translateCategory(category)))
			{			System.out.println("inside");

				itemBlock.add(new JPanel())  ;
				itemBlock.get(counter-1).setLayout(new GridLayout(1,2,5,5));
				JLabel item = new JLabel(entry.getValue().getName());
				itemBlock.get(counter-1).add(item);
				JButton add = new JButton("+");
				JButton remove = new JButton("-");
				JPanel qtyBlock = new JPanel();
				qtyBlock.setLayout(new GridLayout(2,1,5,5));
				qtyBlock.add(add);
				qtyBlock.add(remove);
				itemBlock.get(counter-1).add(qtyBlock);
				centerPanel.add(itemBlock.get(counter-1));

				
				counter++;

				
				
			}

	    }
		Menuframe.add(centerPanel);
	
		
	}




}



	
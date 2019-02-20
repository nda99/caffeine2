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
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuGUI extends JFrame implements ActionListener, ListSelectionListener{
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
JList<MenuItem> menuDisplay;
JList<MenuItem> basket;
DefaultListModel<MenuItem> pastriesModel, sandwichModel, hotModel, coldModel, basketModel;

//need to add drop down boxes too

//set up panels that will be on the GUI 

public MenuGUI(){

	
	
	//JMenuBar menuBar = new JMenuBar();
    /*menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.PAGE_AXIS));
    menuBar.add(createMenu("Menu 1"));
    menuBar.add(createMenu("Menu 2"));
    menuBar.add(createMenu("Menu 3")); */
	
	
	setUpModels();
	setNorthPanel();
	setSouthPanel();
	setEastPanel();
	setWestPanel();
	setCenterPanel();
	
	//setMenuBar();

	Menuframe.setSize(800,800);
	Menuframe.setLocation(300,500);
	Menuframe.setVisible(true);
	
	//Menuframe.add(menuBar);
	
	Menuframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
}

private void setUpModels() {
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
	
	this.sandwichModel = new DefaultListModel<MenuItem>();
	ArrayList<MenuItem> listTemp = Menu.getAllFromCategory("Sandwich");
	for (MenuItem item : listTemp) {
		this.sandwichModel.addElement(item);
	}
	this.pastriesModel = new DefaultListModel<MenuItem>();
	ArrayList<MenuItem> listTemp1 = Menu.getAllFromCategory("Pastries");
	for (MenuItem item : listTemp1) {
		this.pastriesModel.addElement(item);
	}
	this.hotModel = new DefaultListModel<MenuItem>();
	ArrayList<MenuItem> listTemp2 = Menu.getAllFromCategory("Hot drink");
	for (MenuItem item : listTemp2) {
		this.hotModel.addElement(item);
	}
	this.coldModel = new DefaultListModel<MenuItem>();
	ArrayList<MenuItem> listTemp3 = Menu.getAllFromCategory("Cold drink");
	for (MenuItem item : listTemp3) {
		this.coldModel.addElement(item);
	}
	
	
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
	b1.addActionListener(this);
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
	b3 = new JButton("Sandwich");
	b4 = new JButton("Hot Drinks");
	b5 = new JButton("Cold Drinks");
	
	b2.addActionListener(this);
	//b3.addActionListener(this);
	b4.addActionListener(this);
	b5.addActionListener(this);
	
	b3.addActionListener(this); //{
	    //public void actionPerformed(ActionEvent e) {
	    //	displayItems(b3.getText());
	    //  }
	    //});
	
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
	centerPanel.setLayout(new GridLayout(1,2,5,5));
	//JPanel centerPanel = new JPanel();
	menuDisplay = new JList<MenuItem>();
	basket = new JList<MenuItem>();
	menuDisplay.setFont(new Font ("Arial",Font.BOLD,18));
	basket.setFont(new Font ("Arial",Font.BOLD,18));
	centerPanel.add(menuDisplay);
	centerPanel.add(basket);
	
	Menuframe.add(centerPanel, BorderLayout.CENTER);
	menuDisplay.addListSelectionListener(this);
	//displayItems("Sandwich");
	
	basketModel = new DefaultListModel<MenuItem>();
	basket.setModel(basketModel);
	
	pack();
}


	
	
	public static void main (String [] args) {
		MenuGUI gui = new MenuGUI();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			int index = menuDisplay.getSelectedIndex();
			MenuItem item = menuDisplay.getModel().getElementAt(index);
			basketModel.addElement(item);
		}
	}
	/**
	 * method gets an array list of the selected menu items
	 * @return ArrayList
	 */
	public ArrayList<MenuItem> getBasket(){
		ArrayList<MenuItem> basketList = new ArrayList<MenuItem>();
		for (int i=0; i<basketModel.size(); i++) {
			MenuItem item = basketModel.getElementAt(i);
			basketList.add(item);
		}
		return basketList;
	}
	//basketModel.removeAllElements();
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource()==b1){
			LoginGUI loginGUI = new LoginGUI();
		}

		if (e.getSource()==b2)
		{
			//displayItems(b2.getText());
			
			this.menuDisplay.setModel(this.pastriesModel);
		}
		
		if (e.getSource()==b3)
		{
			this.menuDisplay.setModel(this.sandwichModel);
		}
		if (e.getSource()==b4)
		{
			this.menuDisplay.setModel(this.hotModel);
		}
		if (e.getSource()==b5)
		{
			this.menuDisplay.setModel(this.coldModel);
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

		ArrayList<MenuItem> listTemp = Menu.getAllFromCategory(category);
		
		Map<String,MenuItem> items = Menu.getMenuMap();
		centerPanel.setLayout(new GridLayout(10,2,5,5));
		for (MenuItem mItem : listTemp)  
	    {
			
			{			System.out.println("inside");

				itemBlock.add(new JPanel())  ;
				itemBlock.get(counter-1).setLayout(new GridLayout(1,2,5,5));
				JLabel item = new JLabel(mItem.getName());
				itemBlock.get(counter-1).add(item);
				JButton add = new JButton("+");
				JButton remove = new JButton("-");
				JPanel qtyBlock = new JPanel();
				qtyBlock.setLayout(new GridLayout(2,1,5,5));
				qtyBlock.add(add);
				qtyBlock.add(remove);
				itemBlock.get(counter-1).add(qtyBlock);
				centerPanel.add(itemBlock.get(counter-1));
				//centerPanel.add(new JTextArea(20,20));
				counter++;

				
				
			}

	    }

		//centerPanel.add(new JTextArea(20,20));
		Menuframe.add(centerPanel, BorderLayout.CENTER);
		
	}




}



	
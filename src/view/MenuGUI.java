package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.MenuItem;

public class MenuGUI extends JFrame implements ActionListener, ListSelectionListener {
	JFrame Menuframe = new JFrame("Caffeine App");
	//private Menu menu = new Menu();

	JPanel northPanel = new JPanel();
	JPanel eastPanel = new JPanel();
	JPanel southPanel = new JPanel();
	JPanel westPanel = new JPanel();
	JPanel centerPanel = new JPanel();
	JLabel l1, l2, l3, l4, l5, l6;
	JButton b1, b2, b3, b4, b5, b6, b7,b8,b9;
	JButton StaffLogin, Pastries, Sandwiches, HotDrinks, ColdDrinks, Checkout, EmptyBasket;
	JList<MenuItem> menuDisplay;
	JList<MenuItem> basket;
	DefaultListModel<MenuItem> pastriesModel, sandwichModel, hotModel, coldModel,allModel, basketModel;

	/**
	 * set up panels that will be on the GUI
	 */

	public MenuGUI() {

		setNorthPanel();
		setSouthPanel();
		setEastPanel();
		setWestPanel();
		setCenterPanel();

		Menuframe.setSize(800, 900);
		Menuframe.setLocation(100, 150);
		Menuframe.setVisible(true);

		Menuframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	
	public void getListModel(DefaultListModel<MenuItem> mList, String category){
		
		if(category.equals("Sandwich")) this.sandwichModel = mList;
		if(category.equals("Pastries")) this.pastriesModel = mList;
		if(category.equals("Hot drink")) this.hotModel = mList;
		if(category.equals("Cold drink")) this.coldModel = mList;
		if(category.equals("View All")) this.allModel = mList;


		
	}
	/**
	 * create the north JPanel that allows a staff member to login and also displays
	 * the Menu title
	 */
	private void setNorthPanel() {
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
	/*	JLabel cat = new JLabel("Categories");
		JLabel menuItems = new JLabel("Items List");
		JLabel basketContent = new JLabel("Basket");
		basketContent.setFont(new Font("Arial", Font.BOLD, 20));
		menuItems.setFont(new Font("Arial", Font.BOLD, 20));
		cat.setFont(new Font("Arial", Font.BOLD, 20));
		northPanel.add(cat);
		northPanel.add(menuItems);
		northPanel.add(basketContent);*/

		Menuframe.add(northPanel, BorderLayout.NORTH);
		pack();
	}

	/**
	 * create the west panel showing the category buttons
	 */
	private void setWestPanel() {
		JPanel westPanel = new JPanel();
		westPanel.setLayout(new GridLayout(5, 1, 5, 5));

		b2 = new JButton("Pastries");
		b3 = new JButton("Sandwich");
		b4 = new JButton("Hot Drinks");
		b5 = new JButton("Cold Drinks");
		b9 = new JButton("View All");

		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b9.addActionListener(this);

		westPanel.add(b2);
		westPanel.add(b3);
		westPanel.add(b4);
		westPanel.add(b5);
		westPanel.add(b9);
		

		Menuframe.add(westPanel, BorderLayout.WEST);

		pack();
	}

	/**
	 * create the south panel with empty basket and checkout buttons
	 */
	private void setSouthPanel() {
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(1, 3, 20, 5));
		b7 = new JButton("Empty Basket");
		b7.addActionListener(this);
		b6 = new JButton("Checkout");
		b6.addActionListener(this);
		b8 = new JButton("Remove Item");
		b8.addActionListener(this);
		southPanel.add(b7);
		southPanel.add(b8);
		southPanel.add(b6);

		Menuframe.add(southPanel, BorderLayout.SOUTH);

		pack();
	}

	/**
	 * create east panel, it is empty though
	 */
	private void setEastPanel() {
		JPanel eastPanel = new JPanel();

		Menuframe.add(eastPanel, BorderLayout.EAST);

		pack();
	}

	/**
	 * create the centre panel, including lists of items in each category and list
	 * of selected items
	 */
	private void setCenterPanel() {
		centerPanel.setLayout(new GridLayout(1, 2, 5, 5));
		// JPanel centerPanel = new JPanel();
		/**
		 * create new list called menuDisplay that will display the items in the menu
		 * 
		 * @return JList
		 */
		menuDisplay = new JList<MenuItem>();
		/**
		 * create new list called basket that will display the items selected and added
		 * to the basket
		 * 
		 * @return JList
		 */
		basket = new JList<MenuItem>();
		menuDisplay.setFont(new Font("Arial", Font.BOLD, 18));
		basket.setFont(new Font("Arial", Font.BOLD, 18));
	
		centerPanel.add(menuDisplay);
		centerPanel.add(basket);

		Menuframe.add(centerPanel, BorderLayout.CENTER);
		menuDisplay.addListSelectionListener(this);

		basketModel = new DefaultListModel<MenuItem>();
		basket.setModel(basketModel);

		pack();
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
	 * method gets an array list of the selected menu items, used for checkout
	 * 
	 * @return ArrayList
	 */
	public ArrayList<MenuItem> getBasket() {
		ArrayList<MenuItem> basketList = new ArrayList<MenuItem>();
		for (int i = 0; i < basketModel.size(); i++) {
			MenuItem item = basketModel.getElementAt(i);
			basketList.add(item);
		}
		return basketList;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == b2) {
			// displayItems(b2.getText());

			this.menuDisplay.setModel(this.pastriesModel);
		}

		if (e.getSource() == b3) {
			this.menuDisplay.setModel(this.sandwichModel);
		}
		if (e.getSource() == b4) {
			this.menuDisplay.setModel(this.hotModel);
		}
		if (e.getSource() == b5) {
			this.menuDisplay.setModel(this.coldModel);
		}

		if (e.getSource() == b7) {

			int index = 0 ;
			ArrayList<MenuItem> basket = getBasket();
			for (int i = index; i < basket.size(); i++) {
				if (index >= 0) { // Remove only if a particular item is selected
					basketModel.removeElementAt(index);
				}
			}
		}
		if(e.getSource() == b8)
		{
			int index = basket.getSelectedIndex();
			if(index == -1)
			{
				JOptionPane.showMessageDialog(this, "No item selected","Oops ..",  JOptionPane.ERROR_MESSAGE);
				
			}
			else
			{
				basketModel.removeElementAt(index);

			}

		}
		if(e.getSource() == b9)
		{
			this.menuDisplay.setModel(this.allModel);
			

		}

	}
	
	public void addCheckoutListener(ActionListener ck) {
		b6.addActionListener(ck);
	}
	
	public void addLoginListener(ActionListener lg) {
		b1.addActionListener(lg);
	}


}

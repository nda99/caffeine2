package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import model.AllOrders;
import model.Menu;
import model.MenuItem;
import model.Order;
import view.InvoiceGUI;
import view.LoginGUI;
import view.MenuGUI;

public class MenuController {
	MenuGUI mGUI;
	private Menu menu = new Menu();
	
	public MenuController(MenuGUI mg) {
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
		
		this.mGUI=mg;
		
		setListsGUI();
		
		mGUI.addCheckoutListener(new Checkout());

		mGUI.addLoginListener(new MakeLogin());
	}
	
	public void setListsGUI() {
		DefaultListModel<MenuItem> sandwichModel = new DefaultListModel<MenuItem>();
		ArrayList<MenuItem> listTemp = Menu.getAllFromCategory("Sandwich");
		for (MenuItem item : listTemp) {
			sandwichModel.addElement(item);
		}
		DefaultListModel<MenuItem> pastriesModel = new DefaultListModel<MenuItem>();
		ArrayList<MenuItem> listTemp1 = Menu.getAllFromCategory("Pastries");
		for (MenuItem item : listTemp1) {
			pastriesModel.addElement(item);
		}
		DefaultListModel<MenuItem> hotModel = new DefaultListModel<MenuItem>();
		ArrayList<MenuItem> listTemp2 = Menu.getAllFromCategory("Hot drink");
		for (MenuItem item : listTemp2) {
			hotModel.addElement(item);
		}
		DefaultListModel<MenuItem> coldModel = new DefaultListModel<MenuItem>();
		ArrayList<MenuItem> listTemp3 = Menu.getAllFromCategory("Cold drink");
		for (MenuItem item : listTemp3) {
			coldModel.addElement(item);
		}
		
		DefaultListModel<MenuItem> allModel = new DefaultListModel<MenuItem>();
		ArrayList<MenuItem> listAll = new ArrayList<MenuItem>();
		listAll.addAll(listTemp);
		listAll.addAll(listTemp1);
		listAll.addAll(listTemp2);
		listAll.addAll(listTemp3);

		
		for (MenuItem item : listAll) {
			allModel.addElement(item);
		}
		
		mGUI.getListModel(sandwichModel,"Sandwich");
		mGUI.getListModel(pastriesModel,"Pastries");
		mGUI.getListModel(hotModel,"Hot drink");
		mGUI.getListModel(coldModel,"Cold drink");
		mGUI.getListModel(allModel, "View All");
	}
	
	public class Checkout implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Order o = new Order(mGUI.getBasket());
			AllOrders.addOrder(o);
			InvoiceGUI checkoutGUI = new InvoiceGUI(AllOrders.getOrder(o.getTime()));
			InvoiceController ico = new InvoiceController(checkoutGUI,AllOrders.getOrder(o.getTime()));
			checkoutGUI.displayGUI();
		}
		
	}
	
	public class MakeLogin implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			LoginGUI loginGUI = new LoginGUI();
			LoginController lco = new LoginController(loginGUI);
		}
		
	}
}

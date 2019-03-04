package main;

import java.io.FileNotFoundException;

import controller.MenuController;
import model.AllOrders;
import model.Menu;
import view.MenuGUI;

public class MainClass {

	public static void main(String[] args) {
		MenuGUI gui = new MenuGUI();
		MenuController mco = new MenuController(gui);
		try {
			AllOrders.readOrderFile("orders.csv");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Menu.updateFile();
		System.out.println("Finished");
	}
	
}

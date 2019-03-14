package main;

import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.PriorityQueue;
import controller.MenuController;
import model.*;
import view.MenuGUI;

public class MainClass {

	//Queue generator
	
	public static void main(String[] args) {
		

		MenuGUI gui = new MenuGUI();
		MenuController mco = new MenuController(gui);
		try {
			AllOrders.readOrderFile("orders.csv");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// add order to queue
//		try {
//			AllOrders.readOrderFile("orders.csv");
//		}
//		catch (FileNotFoundException e) {
//			System.out.print("File not found");
//		}
		

	/*	StaffThread john = new StaffThread("John");
		StaffThread lila = new StaffThread("Lila", (long) 6000.0);
		john.start();
		lila.start();*/


		Menu.updateFile();
		System.out.println("Finished");
		
	}
	
}


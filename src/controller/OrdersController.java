package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.StaffThread;
import view.OrdersGUI;

public class OrdersController {
	OrdersGUI oGUI;

	public OrdersController(OrdersGUI og) {
		this.oGUI = og;
		oGUI.addListener(new SlowListener(), "slow");
		oGUI.addListener(new NormalListener(), "normal");
		oGUI.addListener(new FastListener(), "fast");
	}
	
	public class SlowListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(StaffThread.getEta() < 16000) {
			oGUI.displayMessage("Simulation speed set to SLOW");
			}
			StaffThread.setEta(16000);
		}
		
	}
	
	public class NormalListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(StaffThread.getEta() != 8000) oGUI.displayMessage("Simulation speed set to NORMAL");
			StaffThread.setEta(8000);
		}
		
	}
	
	public class FastListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if(StaffThread.getEta() > 4000) oGUI.displayMessage("Simulation speed set to FAST");
			StaffThread.setEta(4000);
		}
		
	}
}

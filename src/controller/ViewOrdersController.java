package controller;

import view.InvoiceGUI;
import view.ViewOrdersGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.AllOrders;
import model.nullOrderException;

public class ViewOrdersController {
	ViewOrdersGUI vGUI;
	
	public ViewOrdersController(ViewOrdersGUI vg) {
		this.vGUI = vg;
		vGUI.addProcessListener(new ProcessListener());
		vGUI.addRemoveListener(new RemoveListener());
		
	}
	
	public class ProcessListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {

	    	try {
	    		if(AllOrders.getOrder(vGUI.getCounter().get(Integer.parseInt(vGUI.getOrderNumber()))).isProcessed()) {
		    		vGUI.displayError("This order has already been processed");
	    		}
	    		else{
	    			InvoiceGUI iGUI = new InvoiceGUI(AllOrders.getOrder(vGUI.getCounter().get(Integer.parseInt(vGUI.getOrderNumber()))));
	    			InvoiceController ico = new InvoiceController(iGUI, 
	    			AllOrders.getOrder(vGUI.getCounter().get(Integer.parseInt(vGUI.getOrderNumber()))));
					iGUI.displayGUI();
	    		}
			} catch (nullOrderException e1) {

	    		vGUI.displayError("Please enter a valid order number");
			}
	    	catch(NumberFormatException e2) {
	    		vGUI.displayError("Please enter the order number");
	    	}
	    	catch(NullPointerException e3) {
	    		vGUI.displayError("Please enter a valid order number");
	    	}
		}}
	
	public class RemoveListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {

	    	try {
		    	AllOrders.deleteOrder(vGUI.getCounter().get(Integer.parseInt(vGUI.getOrderNumber())));
	    	}		    	
	    	catch(NumberFormatException e2) {
	    		vGUI.displayError("Please enter the order number");
	    	}
	    	catch(NullPointerException e3) {
	    		vGUI.displayError("Please enter a valid order number");
	    	}
	    	AllOrders.updateOrderFile("orders.csv");
	    	vGUI.getGUI().dispose();
	    	ViewOrdersGUI newView = new ViewOrdersGUI();
	    	ViewOrdersController vco = new ViewOrdersController(newView);
	    	newView.displayViewOrdersGUI();
		}}
}


package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import model.AllOrders;
import model.Order;
import model.nullOrderException;
import view.InvoiceGUI;

public class InvoiceController {
	InvoiceGUI iGUI;
	Order order;
	public InvoiceController(InvoiceGUI ig, Order o) {
		this.iGUI = ig;
		this.order = o;
		iGUI.addPayListener(new PayListener());
		iGUI.addBackListener(new BackListener());
		iGUI.addValidateListener(new ValidateListener());
	}
	
	public class PayListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Random rand = new Random();
			int chance = rand.nextInt(100);
			// Randomness of the Payment: fails 20% of the time
			if (chance > 80) {
				iGUI.displayError("Payment unsuccessful, please try again");
			}

			else {
				if (iGUI.getOrder() != null) {
					try {
						AllOrders.getOrder(order.getTime().toString()).processOrder();
					} catch (nullOrderException e) {
						System.out.println("Order does not exist");
					}
				}
				if(order.isProcessed()) {
					iGUI.displayMessage("Payment successful");
					AllOrders.updateOrderFile("orders.csv");
					iGUI.getFrame().dispose();
				}
				else {
					iGUI.displayError("Processing error, items out of stock");
				}
			}
			
		}}
	
	public class BackListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			AllOrders.updateOrderFile("orders.csv");
			iGUI.getFrame().dispose();

			
		}}
	

	public class ValidateListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {

			if (order != null) {
				String vouch = iGUI.getVoucher();
				if (!vouch.equals("")) {
					double total = order.calculateTotal(vouch);
				}
				iGUI.setInvoiceText(order.getInvoice(iGUI.getVoucher()));
			}

			
		}}


}

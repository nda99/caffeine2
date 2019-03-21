package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import model.AllOrders;
import model.Order;
import model.OrdersQueue;
import exceptions.*;
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
					OrdersQueue.getInstance().addOrder(iGUI.getOrder());
					iGUI.getOrder().setAsQueued();
				}
				if(order.isQueued()) {
					iGUI.displayMessage("Payment successful. Your order has been added to the queue.");
					AllOrders.updateOrderFile("orders.csv");
					iGUI.getFrame().dispose();
				}
				else {
					iGUI.displayError("Processing error, item(s) out of stock");
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

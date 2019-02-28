package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

import controller.InvoiceController;
import model.AllOrders;
import model.Order;
import model.nullOrderException;

public class ViewOrdersGUI {
	private JTextArea orders = new JTextArea(40,20);
	private JPanel orderList = new JPanel();
	private JPanel southPanel = new JPanel();
	private JFrame orderView = new JFrame();
	private JLabel title = new JLabel();
	private JLabel choice = new JLabel();
	private JButton remove = new JButton("Remove");
	private JButton process = new JButton("Process Order");
	private JTextField input = new JTextField();
	private HashMap<Integer,String> times = new HashMap<Integer,String>(); 
	
	public ViewOrdersGUI() {
		
	}
	
	
	
	public void displayViewOrdersGUI() {
		
		title = createOneLabel("Order List:",15);
		choice = createOneLabel("Order Number: ", 11);
		orderView.setTitle("Caffeine App");
		orderView.setSize(500,400);
		orderView.setLayout(new BorderLayout(0,0));
		southPanel.setLayout(new GridLayout(1,4));
		southPanel.add(choice);
		southPanel.add(input);
		southPanel.add(process);
		southPanel.add(remove);
		//orders.setLayout(new GridLayout(20,1));
		orders.setEditable(false);
		orderList.setLayout(new GridLayout(1,1));
		orderList.add(orders);
		orderView.add(orderList,BorderLayout.CENTER);
		orderView.add(title, BorderLayout.NORTH);
		orderView.add(southPanel, BorderLayout.SOUTH);
		orderView.setVisible(true);
		Order o = AllOrders.getNextOrder();
		int counter = 1;
		orders.append("Order# |                    Time                    | Items\n--------------------------------"
				+ "-----------------------------------------------------------------------------------------\n");
		orders.append(String.format("     %d     |   %s", counter, o.getDetails()));
		times.put(counter, o.getTime().toString());
		while(AllOrders.isNextOrder(o)) {
			counter++;
			o = AllOrders.getNextOrder(o);
			if(!o.isProcessed()) {
				orders.append(String.format("     %d     |   %s", counter, o.getDetails()));
			}
			times.put(counter, o.getTime().toString());
		}
		
		
		process.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try {
		    		if(AllOrders.getOrder(times.get(Integer.parseInt(input.getText()))).isProcessed()) {
			    		displayError("This order has already been processed");
		    		}
		    		else{
		    			InvoiceGUI iGUI = new InvoiceGUI(AllOrders.getOrder(times.get(Integer.parseInt(input.getText()))));
		    			InvoiceController ico = new InvoiceController(iGUI, 
		    			AllOrders.getOrder(times.get(Integer.parseInt(input.getText()))));
						iGUI.displayGUI();
		    		}
				} catch (nullOrderException e1) {

		    		displayError("Please enter a valid order number");
				}
		    	catch(NumberFormatException e2) {
		    		displayError("Please enter the order number");
		    	}
		    	catch(NullPointerException e3) {
		    		displayError("Please enter a valid order number");
		    	}
		      }
		    });
		
		remove.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try {
			    	AllOrders.deleteOrder(times.get(Integer.parseInt(input.getText())));
		    	}		    	
		    	catch(NumberFormatException e2) {
		    		displayError("Please enter the order number");
		    	}
		    	catch(NullPointerException e3) {
		    		displayError("Please enter a valid order number");
		    	}
		    	AllOrders.updateOrderFile("orders.csv");
		    	orderView.dispose();
		    	ViewOrdersGUI newView = new ViewOrdersGUI();
		    	newView.displayViewOrdersGUI();
		      }
		    });
	}
	
	/**
	 * Displays custom ERROR window on View Order GUI 
	 * @param m Custom error message
	 */
	public void displayError(String m) {
		
		JOptionPane.showMessageDialog(orderView,
			    m,
			    "Input Error",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	
	
	/**
	 * Method to create a Label for the GUI
	 * @param s
	 * @param size
	 * @return
	 */
	public static JLabel createOneLabel (String s, int size) {
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, size);
		JLabel label= new JLabel(s, JLabel.CENTER);
		label.setFont(f);
		label.setOpaque(true);
		return label;
		}
	
}

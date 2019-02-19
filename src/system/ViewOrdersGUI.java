package system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	public ViewOrdersGUI() {
		
	}
	
	public static void main(String[] args) {
		ViewOrdersGUI view = new ViewOrdersGUI();

		Menu a = new Menu();
		a.readFile("menuItems.csv");

		AllOrders.readOrderFile("D:\\\\Software Engineering\\\\caffeine\\\\orders.csv");
		view.displayViewOrdersGUI();
	}
	
	public void displayViewOrdersGUI() {
		
		title = createOneLabel("Order List:",15);
		choice = createOneLabel("Order Time: ", 11);
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
		orders.append(o.getDetails());
		while(AllOrders.isNextOrder(o)) {
			boolean check = AllOrders.isNextOrder(o);
			o = AllOrders.getNextOrder(o);
			orders.append(o.getDetails());
		}
		
		
		process.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try {
					InvoiceGUI iGUI = new InvoiceGUI(AllOrders.getOrder(input.getText()));
					iGUI.displayGUI();
				} catch (nullOrderException e1) {

					InvoiceGUI iGUI = new InvoiceGUI();
					iGUI.displayGUI();
				}
		      }
		    });
		
		remove.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	AllOrders.deleteOrder(input.getText());
		    	//AllOrders.updateOrderFile("D:\\\\Software Engineering\\\\caffeine\\\\orders_update.csv");
		    	//AllOrders.readOrderFile("D:\\\\Software Engineering\\\\caffeine\\\\orders_update.csv");
		    	orderView.dispose();
		    	ViewOrdersGUI newView = new ViewOrdersGUI();
		    	newView.displayViewOrdersGUI();
		      }
		    });
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

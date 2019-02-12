package system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class InvoiceGUI {

	public static void main(String[] args) {
		AllOrders orders = new AllOrders();
		orders.readOrderFile("D:\\Software Engineering\\caffeine\\orders.csv");
		InvoiceGUI iGui = new InvoiceGUI(orders.getOrder("2017-11-01 21:31:04.971"));
		iGui.displayGUI();
	}
	
	private Order order = null;
	private JFrame invoiceFrame = new JFrame();
	private JPanel titlePanel = new JPanel();
	private JPanel payPanel = new JPanel();
	private JPanel invoicePanel = new JPanel();
	private JPanel voucherPanel = new JPanel();
	private JPanel southPanel = new JPanel();
	private JTextArea invoiceText = new JTextArea(15,50);
	private JTextField voucher = new JTextField(15);
	private JButton validate = new JButton("Validate Voucher");
	private JButton pay = new JButton("Pay");
	private JButton back = new JButton("Go Back");
	JLabel title = new JLabel();
	private final static String newline = "\n";
	
	public InvoiceGUI(Order o)
	{
		this.order = o;
		
	}
	
	public void displayGUI() {
		title = createOneLabel("Invoice details:",18);
		titlePanel.add(title);
		voucherPanel.add(voucher);
		voucherPanel.add(validate);
		southPanel.setLayout(new GridLayout(2,1));
		southPanel.add(voucherPanel);
		payPanel.setLayout(new BorderLayout(0,0));
		payPanel.add(pay,BorderLayout.EAST);
		payPanel.add(back,BorderLayout.WEST);
		southPanel.add(payPanel);
		invoiceText.setSize(200, 200);
		invoiceText.append(this.order.getInvoice());
		invoiceText.setEditable(false);
		invoicePanel.setSize(250,250);
		invoicePanel.add(invoiceText);
		invoiceFrame.setSize(600,450);
		invoiceFrame.setLocation(300,300);
		invoiceFrame.setLayout(new BorderLayout(0,0));
		invoiceFrame.setTitle("Caffeine: Your Invoice");
		invoiceFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		invoiceFrame.add(titlePanel,BorderLayout.NORTH);
		invoiceFrame.add(invoicePanel,BorderLayout.CENTER);
		invoiceFrame.add(southPanel, BorderLayout.SOUTH);
		invoiceFrame.setVisible(true);
		
		validate.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	double total = order.calculateTotal(voucher.getText());
		    	
		    	invoiceText.append(String.format("\n Total with discount: %f", total));
		      }
		    });
	}
	
	public static JLabel createOneLabel (String s, int size) {
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, size);
		JLabel label= new JLabel(s, JLabel.CENTER);
		label.setFont(f);
		label.setOpaque(true);
		return label;
		}
	
}

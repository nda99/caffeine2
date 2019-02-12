package system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class MenuGUI extends JFrame implements ActionListener{
	public static void main(String[] args) {
		Menu menu = new Menu();
		// declares file path if file is located in diff location not in the same folder
		String filename = "menuItems.csv";
		// reads items from file 
		menu.readFile(filename);
		
		//MenuGUI component
		private JFrame frame = new JFrame();
		private JPanel centrePanel = new JPanel();
		private JPanel northPanel = new JPanel();
		private JPanel eastPanel = new JPanel();
		private JPanel southPanel = new JPanel();
		private JPanel titlePanel = new JPanel();
		
		
		private JPanel westPanel = new JPanel();
		private JLabel label1 = new JLabel("Staff Login", JLabel.NORTH_EAST);
		private JLabel label2 = new JLabel("Menu", JLabel.TOP);
		private JLabel label3 = new JLabel("Hot Drinks", JLabel.LEFT);
		private JLabel label4 = new JLabel("Cold Drinks", JLabel.LEFT);
		private JLabel label5 = new JLabel("Pastries", JLabel.LEFT);
		private JLabel label6 = new JLabel("Sandwiches", JLabel.LEFT);
		
		
		
		private JLabel label4 = new JLabel("Checkout", JLabel.SOUTH);
		
		
		//Create the buttons
		private JButton StaffLogin = new JButton("Staff Login");
		private JButton Menu = new JButton("Menu");
		private JButton HotDrinks = new JButton("Hot Drinks");
		private JButton ColdDrinks = new JButton("ColdDrinks");
		private JButton Pastries = new JButton("Pastries");
		private JButton Sandwiches = new JButton("Sandwiches");
		private JButton Checkout = new JButton("Checkout");
		
		
		// report
		//System.out.println();
	 
				
	}


}

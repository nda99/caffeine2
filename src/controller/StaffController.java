package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.AllOrders;
import model.Manager;
import model.Menu;
import model.OrdersQueue;
import model.Staff;
import model.StaffServing;
import model.StaffThread;
import model.SummaryReport;
import view.StaffGUI;
import view.StockGUI;
import view.SummaryReportGUI;
import view.ViewOrdersGUI;
import view.OrdersGUI;

public class StaffController {
	StaffGUI staffGUI;
	Staff staff;
	Manager manager;
	public StaffController(StaffGUI sg, Staff st) {
		this.staffGUI = sg;
		this.staff = st;
		staffGUI.addStartListener(new startListener());
		staffGUI.addFinishListener(new finishListener());
		staffGUI.addViewOrdersListener(new ViewOrdersListener());
		staffGUI.addStockListener(new StockListener());
		staffGUI.addUpdateListener(new UpdateListener());
		staffGUI.addSummaryReportListener(new SummaryReportListener());
		staffGUI.addOrdersListener(new ordersListener());
		staffGUI.addLogoutListener(new LogoutListener());
	}
	
	public StaffController(StaffGUI sg, Manager mg) {
		this.staffGUI =sg;
		this.manager = mg;
		staffGUI.addViewOrdersListener(new ViewOrdersListener());
		staffGUI.addStockListener(new StockListener());
		staffGUI.addUpdateListener(new UpdateListener());
		staffGUI.addSummaryReportListener(new SummaryReportListener());
		staffGUI.addOrdersListener(new ordersListener());
		staffGUI.addLogoutListener(new LogoutListener());
	}
	
	public class UpdateListener implements MouseListener{

		//public void control(JLabel label, int number) {
			//label.addMouseListener(new MouseAdapter() {
		
			public void mouseEntered(MouseEvent e) {
				JLabel label = staffGUI.getLabel(1);
				label.setForeground(Color.blue);
				staffGUI.setLabel(label,1);
			}

			public void mouseExited(MouseEvent e) {
				JLabel label = staffGUI.getLabel(1);
				label.setForeground(Color.black);
				staffGUI.setLabel(label,1);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				JOptionPane.showMessageDialog(staffGUI.getFrame(), "This feature will be available soon!");
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}

	}
	

	public class LogoutListener implements MouseListener{

		@SuppressWarnings("deprecation")
		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(manager==null) {
				staff.logout();
				//Core-Functional Requirement #3, report is generated
				SummaryReport report = new SummaryReport();
				Date today = new Date();
				if(report.getOrderCounter() != 0)
					{
					report.printSummaryReport(today.getDate()+"", today.getDate()+"");
					JOptionPane.showMessageDialog(staffGUI, "Thank you report is generated");
					}
			}
			else {
				manager.logout();
			}
			staffGUI.getFrame().dispose();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			JLabel label = staffGUI.getLabel(8);
			label.setForeground(Color.blue);
			staffGUI.setLabel(label,8);}

		@Override
		public void mouseExited(MouseEvent arg0) {
			JLabel label = staffGUI.getLabel(8);
			label.setForeground(Color.black);
			staffGUI.setLabel(label,8);	
		}

		@Override
		public void mousePressed(MouseEvent arg0) {}

		@Override
		public void mouseReleased(MouseEvent arg0) {}
	
	}
	
	public class StockListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			new StockGUI();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {				
			JLabel label = staffGUI.getLabel(2);
		label.setForeground(Color.blue);
		staffGUI.setLabel(label,2);
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			JLabel label = staffGUI.getLabel(2);
			label.setForeground(Color.black);
			staffGUI.setLabel(label,2);
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {}

		@Override
		public void mouseReleased(MouseEvent arg0) {}
		
	}
	
	public class ViewOrdersListener implements MouseListener{
			@Override
			public void mouseClicked(MouseEvent e) {
	
					try {
						Menu.readFile("menuItems.csv");

					} catch (FileNotFoundException f) {
						f.getMessage();

						/////////////////////////////////////////
						JFileChooser chooser = new JFileChooser();
						chooser.setCurrentDirectory(new java.io.File("."));
						chooser.setDialogTitle("Choose an input file to process:");
						chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
						chooser.setAcceptAllFileFilterUsed(false);

						if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
							System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
							System.out.println("getSelectedFile() : " + chooser.getSelectedFile());

							try {
								Menu.readFile(chooser.getSelectedFile().toString());

							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							System.out.println("No selection");
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					try {
						ViewOrdersGUI view = new ViewOrdersGUI();
						AllOrders.readOrderFile("orders.csv");
						ViewOrdersController vco = new ViewOrdersController(view);
						view.displayViewOrdersGUI();

					} catch (FileNotFoundException f) {
						f.getMessage();
						/////////////////////////////////////////
						JFileChooser chooser = new JFileChooser();
						chooser.setCurrentDirectory(new java.io.File("."));
						chooser.setDialogTitle("Choose an input file to process:");
						chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
						chooser.setAcceptAllFileFilterUsed(false);

						if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
							System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
							System.out.println("getSelectedFile() : " + chooser.getSelectedFile());

							try {
								AllOrders.readOrderFile(chooser.getSelectedFile().toString());

							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							System.out.println("No selection");
						}
					}

					////////////////////////////////////////
					catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				JLabel label = staffGUI.getLabel(3);
				label.setForeground(Color.blue);
				staffGUI.setLabel(label,3);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				JLabel label = staffGUI.getLabel(3);
				label.setForeground(Color.black);
				staffGUI.setLabel(label,3);
			}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
			}

	public class SummaryReportListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			SummaryReport rep = new SummaryReport();
			SummaryReportGUI report = new SummaryReportGUI(rep);
			SummaryController sc = new SummaryController(report, rep);
			report.buildGUI();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			JLabel label = staffGUI.getLabel(7);
			label.setForeground(Color.blue);
			staffGUI.setLabel(label,7);
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			JLabel label = staffGUI.getLabel(7);
			label.setForeground(Color.black);
			staffGUI.setLabel(label,7);
		}

		@Override
		public void mousePressed(MouseEvent arg0) {}

		@Override
		public void mouseReleased(MouseEvent arg0) {}
		
	}
	
	
	public class ordersListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			OrdersGUI og = new OrdersGUI();
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			JLabel label = staffGUI.getLabel(9);
			label.setForeground(Color.blue);
			staffGUI.setLabel(label,9);
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			JLabel label = staffGUI.getLabel(9);
			label.setForeground(Color.black);
			staffGUI.setLabel(label,9);
		}

		@Override
		public void mousePressed(MouseEvent arg0) {}

		@Override
		public void mouseReleased(MouseEvent arg0) {}
		
	}
	public class startListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			//On mouse click, start the thread.
			OrdersQueue orders = new OrdersQueue();
			orders.getQueue();
			staff.startServing();
			staffGUI.getButton("start").setEnabled(false);
			staffGUI.getButton("finish").setEnabled(true);

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		
		}

		@Override
		public void mousePressed(MouseEvent arg0) {}

		@Override
		public void mouseReleased(MouseEvent arg0) {}
		
	}
	
	public class finishListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			//On mouse click, kill the thread.
			staff.stopServing();
			staffGUI.getButton("start").setEnabled(true);
			staffGUI.getButton("finish").setEnabled(false);
			JOptionPane.showMessageDialog(staffGUI, "Thank you for your service!");
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}

		@Override
		public void mousePressed(MouseEvent arg0) {}

		@Override
		public void mouseReleased(MouseEvent arg0) {}
		
	}
	
	
} //End of the class


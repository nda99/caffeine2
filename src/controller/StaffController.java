package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.AllOrders;
import model.Manager;
import model.Menu;
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

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(manager==null) {
				staff.logout();
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
			
		//SummaryReportGUI report = new SummaryReportGUI(rep);
		ServerController sc = new ServerController(og);
		//	report.buildGUI();
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
			StaffThread currentStaff = new StaffThread(staff.getUserName(), (long) 6000.0);
			currentStaff.start();
			StaffServing server = new StaffServing(currentStaff);
			staffGUI.getButton("start").disable();
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


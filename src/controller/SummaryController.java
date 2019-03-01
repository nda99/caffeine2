package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.SummaryReport;
import view.StockGUI;
import view.SummaryReportGUI;

public class SummaryController {
	StockGUI stockGUI;
	SummaryReportGUI srGUI;
	SummaryReport report;

	public SummaryController(StockGUI sg) {
		this.stockGUI = sg;
	}
	
	public SummaryController(SummaryReportGUI sr, SummaryReport rep) {
		this.srGUI = sr;
		this.report = rep;
		srGUI.addViewListener(new ViewListener());
		srGUI.addPrintListener(new SummaryListener());
	}
	
	public class SummaryListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			// Action taken once 'Print' button is clicked
				System.out.print("creating csv file");
				//Call print method, if succeeds or not show a popup window
				if(report.printSummaryReport(srGUI.getFrom(),srGUI.getTo())) {
				JOptionPane.showMessageDialog(srGUI.getFrame(), "CSV file of the summary report is successfully created!");
				}
				else
				{
				JOptionPane.showMessageDialog(srGUI.getFrame(), "Oops! Something went wrong, couldn't create CSV of the report!");

				}

		}
	}
			// Action taken once 'View' button is clicked
	public class ViewListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Set the values of from and to, because both are needed to call other methods
				srGUI.setFrom(srGUI.getDateFrom().getText());
				srGUI.setTo(srGUI.getDateTo().getText());
				//Check first if the from is before the to

			
				
					if(report.isCorrectRange(srGUI.getFrom(), srGUI.getTo()))
					{
						srGUI.viewSummaryReport(srGUI.getFrom(), srGUI.getTo());

					}
					else
					{
						JOptionPane.showMessageDialog(srGUI.getFrame(), "Oops! You didn't set the interval correctly!");

					}
				
				
			}

		}
	
	
}


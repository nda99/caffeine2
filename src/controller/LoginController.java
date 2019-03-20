package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Manager;
import model.Staff;
import view.LoginGUI;
import view.RegisterGUI;
import view.StaffGUI;
import exceptions.*;

public class LoginController {
	LoginGUI loginGUI;
	public LoginController(LoginGUI lg) {
		this.loginGUI = lg;
		loginGUI.addStaffLoginListener(new StaffLoginListener());
		loginGUI.addManagerLoginListener(new ManagerLoginListener());
		loginGUI.addRegisterListener(new RegisterListener());
	}
	
	public class StaffLoginListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				Staff staff = new Staff(loginGUI.getUser(),loginGUI.getStaffFile());
				String passText = new String(loginGUI.getPassword());
				Boolean loggedIn = staff.login(passText);
				if(loggedIn == true)
				{
					loginGUI.setErrorText("");
					loginGUI.setErrorVisible(false);
					loginGUI.getFrame().dispose();

					StaffGUI gui = new StaffGUI(staff);
					StaffController sco = new StaffController(gui,staff);
				}
				else
				{
					loginGUI.setErrorText("Invalid Password");
				}
				
			} catch (StaffNonExistantException e1) {
				// TODO Auto-generated catch block
				loginGUI.setErrorRed();
				loginGUI.setErrorText("Oops! User does not exist. ");
				//e1.printStackTrace();
			}
		}
		
	}
	
	public class ManagerLoginListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				Manager manager = new Manager(loginGUI.getUser(),loginGUI.getStaffFile());
				Boolean loggedIn = manager.login(new String(loginGUI.getPassword()));
				if(loggedIn == true)
				{
					StaffGUI gusi = new StaffGUI(manager);
					StaffController sco = new StaffController(gusi,manager);
					loginGUI.setErrorVisible(false);
					loginGUI.getFrame().dispose();

				}	
				
			} catch (StaffNonExistantException e1) {
				loginGUI.setErrorRed();
				loginGUI.setErrorText("Oops! User does not exist. ");
				e1.printStackTrace();
			} catch (NotAManagerException e1) {
				loginGUI.setErrorRed();
				loginGUI.setErrorVisible(true);
				loginGUI.setErrorText("Oops! You don't have manager privilege. ");
			//	e1.printStackTrace();
			}
		}
		
	}
	
	public class RegisterListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
	    	RegisterGUI rGUI = new RegisterGUI();
	    	RegisterController rco = new RegisterController(rGUI);
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			loginGUI.setRegisterForeground("blue");
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			loginGUI.setRegisterForeground("black");
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}

package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.InvalidRegistration;
import model.InvalidUsersFileException;
import model.Login;
import model.Manager;
import model.NotAManagerException;
import model.Staff;
import model.StaffNonExistantException;
import view.RegisterGUI;
import view.StaffGUI;

public class RegisterController {
	RegisterGUI rGUI;
	Login login;
	String staffFile;
	String positionValue = "";
	public RegisterController(RegisterGUI rg) {
		this.rGUI = rg;

		staffFile=rGUI.getStaffFile();
		try {
			login = new Login(staffFile);
		} catch (InvalidUsersFileException e) {
			// TODO Auto-generated catch block
			rGUI.setErrorText(e.getMessage());
		}
		rGUI.addStaffListener(new StaffRadioListener());
		rGUI.addManagerListener(new ManagerRadioListener());
		rGUI.addRegisterListener(new RegistrationListener());
	}
	
	private void checkRegisterationForm() {
		
		rGUI.setComponents();

		String passText = new String(rGUI.getPassword());

		System.out.println(login);
		// First check if user already exist
		if (rGUI.getUser().equals("") || rGUI.getName().equals("") || passText.equals("")
				|| rGUI.getEmail().equals("")) {
			if (rGUI.getUser().equals("")) {
				rGUI.setHint("*Required Field","user");
			}
			if (rGUI.getName().equals("")) {
				rGUI.setHint("*Required Field","name");
			}
			if (passText.equals("")) {
				rGUI.setHint("*Required Field","pass");
			}
			if (rGUI.getEmail().equals("")) {
				rGUI.setHint("*Required Field","email");
			}
		}

		else if (!login.staffExist(rGUI.getUser())) {

			try {
				if (!login.register(rGUI.getUser(), passText, positionValue, rGUI.getName(),
						rGUI.getEmail())) {
					rGUI.setErrorText("Oops something went wrong!");
				} else {
					switch (positionValue) {
					case "Staff":
						try {
							Staff staff = new Staff(rGUI.getUser(), staffFile);
							Boolean loggedIn = staff.login(passText);
							if (loggedIn == true) {
								rGUI.setErrorText("");
								rGUI.setErrorVisible(false);
								StaffGUI gui = new StaffGUI(staff);
								StaffController sco = new StaffController(gui,staff);
							}
						} catch (StaffNonExistantException e) {
							// TODO Auto-generated catch block
							rGUI.setErrorText(e.getMessage());
						}

						break;
					case "Manager":
						try {
							Manager manager = new Manager(rGUI.getUser(), staffFile);
							Boolean loggedIn = manager.login(passText);
							if (loggedIn == true) {
								StaffGUI gusi = new StaffGUI(manager);
								StaffController sco = new StaffController(gusi,manager);
								rGUI.setErrorVisible(false);
							}

						} catch (StaffNonExistantException e1) {
							// TODO Auto-generated catch block
							rGUI.setErrorForeground();
							rGUI.setErrorText("Oops! User does not exist. ");
							e1.printStackTrace();
						} catch (NotAManagerException e1) {
							rGUI.setErrorForeground();
							rGUI.setErrorVisible(true);
							rGUI.setErrorText("Oops! You don't have manager privilege. ");
						}

					}
				}
			} catch (InvalidRegistration e) {
				// TODO Auto-generated catch block
				rGUI.setErrorText(e.getMessage());
			}

			// LoginGUI loginGui = new LoginGUI();

		} else {
			rGUI.setHint("User already exists!","user");

		}

	}
	
	public class RegistrationListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {

			checkRegisterationForm();

		}
		

	}
	
	public class StaffRadioListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {

				System.out.println("clicked");

				positionValue = "Staff";

		}
		

	}
	
	public class ManagerRadioListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {

			positionValue = "Manager";


		}
		

	}
}

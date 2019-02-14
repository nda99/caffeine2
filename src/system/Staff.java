package system;

public class Staff extends User{
	
	protected String fullName;
	protected String emailAddress;
	protected String userName;

	/**
	 * Staff Constructor 
	 * **/
	public Staff(String fullName, String emailAddress, String userName){
		this.fullName = fullName;
		this.emailAddress = emailAddress;
		this.userName = userName;
	}

	/**
	 * Staff default constructor
	 */
	public Staff(){}
	
	//Standard getter and setters
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}


}

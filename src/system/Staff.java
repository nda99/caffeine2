package system;

public class Staff {
	
	private String fullName;
	private String emailAddress;
	private String userName;
	private String password;
	
	/**
	 * Staff Constructor 
	 * **/
	public Staff(String fullName, String emailAddress, String userName, String password) {
		super();
		this.fullName = fullName;
		this.emailAddress = emailAddress;
		this.userName = userName;
		this.password = password;
	}
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	
	

}

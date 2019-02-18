package system;

/**
 * Staff class, doesn't have the stock methods yet
 */
public class Staff extends User{
	
	protected String fullName;
	protected String emailAddress;
	protected String userName;
	protected boolean loggedIn;
	protected Login loginData;
    protected String staffFile;

	/**
	 * Staff Constructor, create and register a new staff member
	 * **/
	public Staff(String fullName, String emailAddress, String userName, String password, String staffFile)
            throws UserNameAlreadyTakenException{
		this.fullName = fullName;
		this.emailAddress = emailAddress;
		this.userName = userName;
		this.loggedIn = false;
		this.staffFile = staffFile;
		try{
            this.loginData = new Login(staffFile);
            //this might be a bit ugly but should work
            String position = getClass().getName().split("\\.")[1];
            loginData.register(userName,password,position,fullName,emailAddress);
        }catch(InvalidUsersFileException e){
		    e.printStackTrace();
        }
	}

	/**
	 * Staff default constructor
	 */
	public Staff(){
	    this.loggedIn = false;
    }

    /**
     * create staff object based on what is found in the staff file
     * @param userName
     * @param staffFile
     */
    public Staff(String userName, String staffFile) throws CustomerNonExistantException {
        this.userName = userName;
        this.loggedIn = false;
	    try{
            this.loginData = new Login(staffFile);
            if(!loginData.staffExist(userName)){
                throw new CustomerNonExistantException(userName);
            }
            String[] details = loginData.getDetails(userName);
            this.fullName = details[0];
            this.emailAddress = details[1];
        }catch(InvalidUsersFileException e){
		    e.printStackTrace();
        }
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

	public String getStaffFile(){
	    return this.staffFile;
    }

    public void setStaffFile(String staffFile) {
        this.staffFile = staffFile;
        try{
            this.loginData = new Login(staffFile);
        }catch(InvalidUsersFileException e){
            e.printStackTrace();
        }
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Recommended method to use to login
     * @param password
     * @return
     */
    public boolean login(String password){
	    this.loggedIn = loginData.login(this.userName, password);
	    return this.loggedIn;
    }

    public void logout(){
        this.loggedIn = false;
    }
}

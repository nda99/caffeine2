package model;
import java.util.LinkedList;
import java.util.List;

import exceptions.*;
import view.StaffServing;

/**
 * Staff class, doesn't have the stock methods yet
 */
public class Staff implements Subject{
	
	protected String fullName;
	protected String emailAddress;
	protected String userName;
	protected boolean loggedIn;
	protected Login loginData;
    protected String staffFile;
    private ActivityLog log = ActivityLog.getInstance();
    private Order currentOrder;
    private List<Observer> observers;
    //private StaffThread currentStaff;
	private StaffThread currentStaff;


	/**
	 * Staff Constructor, create and register a new staff member
	 * **/
	public Staff(String fullName, String emailAddress, String userName, String password, String staffFile)
            throws InvalidRegistrationException {
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
    public Staff(String userName, String staffFile) throws StaffNonExistantException {
        this.userName = userName;
        this.loggedIn = false;
	    try{
            this.loginData = new Login(staffFile);
            if(!loginData.staffExist(userName)){
                throw new StaffNonExistantException(userName);
            }
            String[] details = loginData.getDetails(userName);
            this.fullName = details[0];
            this.emailAddress = details[1];
        }catch(InvalidUsersFileException e){
		    e.printStackTrace();
        }
	    this.currentStaff = new StaffThread(this, (long) 6000.0);
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
        log.logInfo("User " + userName + " has logged out." );
    }
    
    /**
     *  This method is called once the staff will click on start serving 
     */
    public void startServing()
    {
    	if(currentStaff.getState().equals(Thread.State.NEW)) {
			currentStaff.start();
			observers = new LinkedList<Observer>();
			StaffServing server = new StaffServing(this);
		}else{
    		currentStaff.resumeService();
		}
		log.logInfo("Staff " + currentStaff.name + " started working");

    }
    /**
     *  This method is called once the staff will click on stop serving 
     */
    public void stopServing()
    {
    	currentStaff.pause();
		log.logInfo("Staff " + currentStaff.name + " stopped working");
    }
    
    /**
     * this method is called by thread once an order is fetched and assigned to the staff
     * @param order Order which is being processed
     */
    public void processingOrder(Order order)
    {
    	order.setServer(this);
    	currentOrder = order;
    	notifyObserver();
    }
    
    /**
     * This method returns the order this staff is working on
     * @return The current order
     */
    public Order getOrderWorkingOn()
    {
    	return currentOrder ;
    }
    
   
    

	@Override
	public void registerObserver(Observer o) {
		observers.add(o);

	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);	
		
	}

	@Override
	public void notifyObserver() {
		for(Observer o : observers)
		{
			o.update();

		}
	}
    


}

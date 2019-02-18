package system;

/**
 * Class Manager, a manager has to login before being able to do anything
 */
public class Manager extends Staff {
    public Manager(String fullName, String emailAddress, String userName,String password, String staffFile)
            throws UserNameAlreadyTakenException{
        super(fullName, emailAddress, userName,password, staffFile);
    }

    public Manager(String userName, String staffFile) throws CustomerNonExistantException, NotAManagerException{
        super(userName, staffFile);
        String position = loginData.getDetails(userName)[2];
        if(!position.equals("Manager")){
            throw new NotAManagerException(userName, position);
        }
    }
    /**
     * I have no idea what this is supposed to do yet
     */
    public void createDiscount(int discount) throws UserNotLoggedInException{
        String method = "create discount";
        if(!loggedIn){
            throw new UserNotLoggedInException(userName, method);
        }

    }

    public void activateDiscount(int discount) throws UserNotLoggedInException{
        String method = "create discount";
        if(!loggedIn){
            throw new UserNotLoggedInException(userName, method);
        }
    }

    public void deactivateDiscount(int discount){

    }

}

package exceptions;

public class UserNotLoggedInException extends Exception {
    public UserNotLoggedInException(String userName, String method){
        System.out.println("User "+ userName+" has to be logged in to " + method);
    }
}

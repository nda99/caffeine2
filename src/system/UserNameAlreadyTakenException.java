package system;

public class UserNameAlreadyTakenException extends Exception {
    public UserNameAlreadyTakenException(String userName){
        System.out.println("the user name "+ userName +" is already taken" );
    }
}

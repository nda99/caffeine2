package model;

public class UserNameAlreadyTakenException extends Exception {
    String un;

    public UserNameAlreadyTakenException(String userName){
        un = userName;
        //System.out.println("the user name "+ userName +" is already taken" );
    }

    @Override
    public String getMessage() {
        return "the user name "+ un +" is already taken";
    }
}

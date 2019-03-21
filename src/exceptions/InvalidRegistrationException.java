package exceptions;

public class InvalidRegistrationException extends Exception {

    private String message;

    public InvalidRegistrationException(String mes){
        this.message = mes;
    }

    @Override
    public String getMessage(){
        return "[Invalid registration] " + this.message;
    }
}

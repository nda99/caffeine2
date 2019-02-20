package system;

public class InvalidRegistration extends Exception {

    private String message;

    public InvalidRegistration(String mes){
        this.message = mes;
    }

    @Override
    public String getMessage(){
        return this.message;
    }
}

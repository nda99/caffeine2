package system;

public class CustomerNonExistantException extends Exception {

    private String un;

    public CustomerNonExistantException(String userName){
        this.un = userName;
    }

    @Override
    public String getMessage() {
        return "Customer" + un +" does not exist";
    }
}

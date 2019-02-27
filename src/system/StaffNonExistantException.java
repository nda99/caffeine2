package system;

public class StaffNonExistantException extends Exception {

    private String un;

    public StaffNonExistantException(String userName){
        this.un = userName;
    }

    @Override
    public String getMessage() {
        return "Staff member" + un +" does not exist";
    }
}

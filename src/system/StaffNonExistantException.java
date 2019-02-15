package system;

public class StaffNonExistantException extends Exception {
    public StaffNonExistantException(String userName){
        System.out.println("Staff member" + userName +" does not exist");
    }
}

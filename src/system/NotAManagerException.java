package system;

public class NotAManagerException extends Exception {
    public NotAManagerException(String username, String position){
        System.out.println(username + " is " + position + " and not manager !");
    }
}

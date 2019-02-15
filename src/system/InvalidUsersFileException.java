package system;

public class InvalidUsersFileException extends Exception {
    public InvalidUsersFileException(){
        super();
    }
    public InvalidUsersFileException(String message) {
        System.out.println("Invalid users file : "+ message);
    }
    public InvalidUsersFileException(int lineCounter){
        if(lineCounter > 5){
            System.out.println("too many attributes in line " + lineCounter);
        }
        else if (lineCounter < 5){
            System.out.println("missing attribute in line " + lineCounter);
        }
    }
}

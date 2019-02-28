package model;

public class NotAManagerException extends Exception {

    String pos;
    String un;

    public NotAManagerException(String username, String position){
        pos = position;
        un = username;
//        System.out.println(username + " is " + position + " and not manager !");
    }

    @Override
    public String getMessage() {
        return un + " is " + pos + " and not manager !";
    }
}

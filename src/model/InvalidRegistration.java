package model;

public class InvalidRegistration extends Exception {

    private String message;

    public InvalidRegistration(String mes){
        this.message = mes;
    }

    @Override
    public String getMessage(){
        return "[Invalid registration] " + this.message;
    }
}

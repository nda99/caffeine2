package model;

public class InvalidUsersFileException extends Exception {
    private int nb;
    private int lineCounter;

    public InvalidUsersFileException(int lineNB, int num){
        nb = num;
        lineCounter = lineNB;
    }

    @Override
    public String getMessage() {
        if(nb > 5){
            return "too many attributes in line " + lineCounter;
        }
        else if (nb < 5){
            return "missing attribute in line " + lineCounter;
        }
        return "wrong number of attributes";
    }
}

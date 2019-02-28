package model;

public class InvalidCustomerFileException extends Exception{
    private int nb;
    private int lineCounter;

    public InvalidCustomerFileException(int lineNB, int num){
        nb = num;
        lineCounter = lineNB;
    }

    @Override
    public String getMessage() {
        if(nb > 2){
            return "too many attributes in line " + lineCounter;
        }
        else if (nb < 2){
            return "missing attribute in line " + lineCounter;
        }
        return "wrong number of attributes";
    }
}

package exceptions;

public class NotEnoughStockException extends Exception{
    private int nbleft;
    private String name;

    public NotEnoughStockException(int nbleft, String name){
        this.nbleft = nbleft;
        this.name = name;
    }

    @Override
    public String getMessage(){
        return "There is only " + nbleft + " " + this.name + " left";
    }

}

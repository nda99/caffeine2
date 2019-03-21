package exceptions;

import model.ActivityLog;

public class InvalidCustomerFileException extends Exception{
    private int nb;
    private int lineCounter;
    private ActivityLog log = ActivityLog.getInstance();

    public InvalidCustomerFileException(int lineNB, int num){
        nb = num;
        lineCounter = lineNB;
    }

    @Override
    public String getMessage() {
        if(nb > 2){
            log.logWarning("too many attributes in line " + lineCounter);
            return "too many attributes in line " + lineCounter;
        }
        else if (nb < 2){
            log.logWarning("missing attribute in line " + lineCounter);
            return "missing attribute in line " + lineCounter;
        }
        log.logWarning("wrong number of attributes");
        return "wrong number of attributes";
    }
}

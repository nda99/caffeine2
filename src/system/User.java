package system;

import java.rmi.server.UID;

public class User {
    protected UID id;

    public User(){
        this.id = new UID();
    }

    public UID getId(){
        return this.id;
    }

    /**
     * I think this should be in allCustomers
     * @return
     */
    protected String[][] readUserFile(){
        return null;
    }
}

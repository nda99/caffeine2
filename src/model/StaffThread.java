package model;




public class StaffThread extends Thread{
    public String name;
    private long eta = (long) 5000.0;

    public StaffThread(String staffName){
        this.name = staffName;
    }

    public StaffThread(String staffName, long eta){
        this.name = staffName;
        this.eta = eta;
    }

    public void run(){
        while(true){
            if(!PQueue.orderQueue.isEmpty()){
                Order tempOrder = PQueue.orderQueue.remove();
                System.out.println("Staff " + this.name +" Processing: " + tempOrder.toString());
                try {
                    sleep(eta);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

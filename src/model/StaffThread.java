package model;


import java.util.LinkedList;
import java.util.List;

public class StaffThread extends Thread{
	private Staff staff;
    public String name;
    private Order currentOrder;
    private long eta = (long) 5000.0;

    public StaffThread(Staff staff){
        this.name = staff.getFullName();
        this.staff = staff;

    }

    public StaffThread(Staff staff, long eta){
        this.name = staff.getFullName();
        this.staff = staff;
        this.eta = eta;
    }
    
  

    public void run(){
        while(true){
            if(!OrdersQueue.orders.isEmpty()){
                currentOrder = getOrderToProcess();
                System.out.println("Staff " + this.name +" Processing: " + currentOrder.toString());
                staff.processingOrder(currentOrder);
                try {
                    sleep(eta);
                    staff.processingOrder(currentOrder);
                    System.out.println(staff.getFullName() + " is asleep!");
                    currentOrder.processOrder();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    //This method will return the current order served by the staff
    public Order getCurrentOrder()
    {
    	return currentOrder;
    }
    
    public String getCurrentServer()
    {
    	return name;
    }
    //this method will pop an order from the order queue to be served
    public Order getOrderToProcess()
    {
    	OrdersQueue ordersQueue = OrdersQueue.getInstance();
    	ordersQueue.getQueue();
    	Order tempOrder = ordersQueue.getNextOrder();
    	System.out.print("CURRENTLY WORKING ON :" +tempOrder);
    	
    	return tempOrder;
    }
 

}

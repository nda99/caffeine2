package model;


import java.util.LinkedList;
import java.util.List;

public class StaffThread extends Thread implements Subject{
    public String name;
    private Order currentOrder;
    private long eta = (long) 5000.0;
    private List<Observer> observers;

    public StaffThread(String staffName){
        this.name = staffName;
        observers = new LinkedList<Observer>();
        

    }

    public StaffThread(String staffName, long eta){
        this.name = staffName;
        this.eta = eta;
        observers = new LinkedList<Observer>();
    }
    
  

    public void run(){
        while(true){
          //  if(!PQueue.orderQueue.isEmpty()){
                currentOrder = getOrderToProcess();
                System.out.println("Staff " + this.name +" Processing: " + currentOrder.toString());
                notifyObserver();
                try {
                    sleep(eta);
                    currentOrder.processOrder();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
           // }
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
    public synchronized Order getOrderToProcess()
    {
    	OrdersQueue ordersQueue = OrdersQueue.getInstance();
    	ordersQueue.getQueue();
    	Order tempOrder = ordersQueue.getNextOrder();
    	System.out.print("CURRENTLY WORKING ON :" +tempOrder);
    	
    	return tempOrder;
    }
 
	@Override
	public void registerObserver(Observer o) {

		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyObserver() {
		for(Observer o : observers)
		{
			o.update();
		}
	}
}

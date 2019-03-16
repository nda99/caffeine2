package model;

<<<<<<< HEAD

=======
import java.util.LinkedList;
>>>>>>> f8a4d2b6f36945c4989b4086479a655db94780b4
import java.util.List;

import main.MainClass;

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
    	Order tempOrder = MainClass.orderQueue.remove();
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

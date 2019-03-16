package model;

import java.util.LinkedList;
import java.util.List;

import main.MainClass;

public class StaffThread extends Thread implements Subject{
    public String name;
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
            if(!MainClass.orderQueue.isEmpty()){
            	notifyObserver();
                Order tempOrder = MainClass.orderQueue.remove();
                System.out.println("Staff " + this.name +" Processing: " + tempOrder.toString());
                try {
                    sleep(eta);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
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

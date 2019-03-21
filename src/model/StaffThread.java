package model;

public class StaffThread extends Thread{
	private Staff staff;
    public String name;
    private volatile boolean paused = false;
    private Order currentOrder;
    private ActivityLog log = ActivityLog.getInstance();
    private static long eta = (long) 8000.0;

    public static long getEta() {
		return eta;
	}

	public static void setEta(long eta) {
		StaffThread.eta = eta;
	}

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
            if(!paused) {
                if (!OrdersQueue.getInstance().orders.isEmpty()) {
                    currentOrder = getOrderToProcess();
                    //                System.out.println("Staff " + this.name +" Processing: " + currentOrder.toString());
                    log.logInfo("Staff " + this.name + " Processing: " + currentOrder.toString());
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
    }
    
    //This method will return the current order served by the staff
    public Order getCurrentOrder()
    {
    	return currentOrder;
    }
    
    //this method returns the current staff name
    public String getCurrentServer()
    {
    	return name;
    }

    /**
     * Gets the order to process from the queue, it calls {@link OrdersQueue#getNextOrder() OrdersQueue.getNextOrder()}
     * so the order is removed from the queue
     * @return order to process
     */
    public synchronized Order getOrderToProcess()
    {
    	OrdersQueue ordersQueue = OrdersQueue.getInstance();
    	ordersQueue.getQueue();
    	Order tempOrder = ordersQueue.getNextOrder();
    	ordersQueue.notifyObserver();
    	
    	return tempOrder;
    }

    public void pause(){
        this.paused = true;
    }

    public void resumeService(){
        this.paused = false;
    }

}

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

    /**
     * main method of the thread, if the thread is not paused, it will process the orders in the queue.
     */
    public void run(){
        while(true){
            if(!paused) {
                if (!OrdersQueue.getInstance().orders.isEmpty()) {
                    currentOrder = getOrderToProcess();
                    log.logInfo("Staff " + this.name + " Processing: " + currentOrder.toString());
                    staff.processingOrder(currentOrder);
                    try {
                        //sleep so the simulation is not too fast
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

    /**
     * Pauses the thread
     */
    public void pause(){
        this.paused = true;
    }
    /**
     * Resumes thread activity
     */
    public void resumeService(){
        this.paused = false;
    }
    
    /*
     * This method will return the current order served by the staff
     */
    public Order getCurrentOrder()
    {
    	return currentOrder;
    }
    
    /*
     * This method returns the current staff name
     */
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

}

package model;

public class nullOrderException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String id;

	   public nullOrderException(String x) {
	      id = x;
	   }
	   
	   public String toString() {
		      return "Order not found with time " + id + "";
		   }
}

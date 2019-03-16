package model;

import java.util.Comparator;
import model.Order;

public class OrderComparator implements Comparator<Order> {
	
	public boolean equals(Order o) {
		if (o.getTime().equals(o.getTime())) {
			return true;
		}
		return false;
	}


	// override compare to method of comparator
	// decending timestamp time
	@Override
	public int compare(Order o1, Order o2) {
		Order first_order;
		Order second_order;
		first_order=o1;
		second_order=o2;

<<<<<<< HEAD
			return second_order.getTime().compareTo(first_order.getTime());

=======
		return second_order.getTime().compareTo(first_order.getTime());
>>>>>>> f8a4d2b6f36945c4989b4086479a655db94780b4
		}

}
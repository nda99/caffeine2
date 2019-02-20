package system;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.MathContext;

import org.junit.jupiter.api.Test;

class OrderTest {
	
	@Test
	public void testCalculateTotal(){
		Order order1 = new Order();
		Order order2 = new Order();
		Order order3 = new Order();
		Order order4 = new Order();
		Order order5 = new Order();
		
		Menu.addItem("Cookie", new MenuItem(1,"Cookie",Category.PASTRIES, 1.20, 9));
		Menu.addItem("Espresso", new MenuItem(2,"Espresso",Category.HOTDRINK, 1.30,10));
		Menu.addItem("Mocha", new MenuItem(3,"Mocha",Category.HOTDRINK, 1.50,15));
		Menu.addItem("Latte", new MenuItem(4,"Latte",Category.HOTDRINK, 1.40,89));
		Menu.addItem("Cake", new MenuItem(5,"Cake",Category.PASTRIES, 2.30,90));
		Menu.addItem("Water", new MenuItem(6,"Water",Category.COLDDRINK,0.70,101));
		Menu.addItem("Brownie", new MenuItem(7,"Brownie",Category.PASTRIES, 2.10,46));
		Menu.addItem("Sandwich", new MenuItem(8,"Sandwich",Category.SANDWICH, 3.5,52));
		
		order1.addItem(Menu.getItem("Cookie"), 1);
		order1.addItem(Menu.getItem("Espresso"), 2);
		
		order2.addItem(Menu.getItem("Mocha"), 2);
		
		order3.addItem(Menu.getItem("Mocha"), 2);
		order3.addItem("Cookie", 1);
		
		order4.addItem("Brownie", 1);
		order4.addItem("Latte", 1);
		
		order5.addItem("Brownie", 1);
		order5.addItem("Water", 1);
		order5.addItem("Sandwich", 1);
		
		String message1 = "The price is wrong for Cookie(x1) and Espresso (x2)";
		double expected1 = 3.80;
		double actual1 = order1.calculateTotal();
		

		String message2 = "The price is wrong for Mocha (x2)";
		double expected2 = 3.0;
		double actual2 = order2.calculateTotal();
		
		String message3 = "The price is wrong for Mocha (x2) and Cookie(x1)";
		double expected3 = 4.2;
		double actual3 = order3.calculateTotal();
		

		String message4 = "The price is wrong for Brownie (x1) and Latte(x1)";
		double expected4 = 2.80;
		double actual4 = order4.calculateTotal();
		
		String message5 = "The price is wrong for the Meal Deal (1 Brownie, 1 Water, 1 Sandwich)";
		double expected5 = 5.99;
		double actual5 = order5.calculateTotal();
		

		
		assertEquals(message1,(Double)expected1,(Double)actual1);
		assertEquals(message2,(Double)expected2,(Double)actual2);
		assertTrue(order2.getItemQuantity("Cookie")==1);
		assertEquals(message3,(Double)expected3,(Double)actual3);
		assertTrue(order3.getItemQuantity("Cookie")==2);
		assertEquals(message4,(Double)expected4,(Double)actual4);
		assertEquals(message5,(Double)expected5,(Double)actual5);
		
	}

}

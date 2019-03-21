package test;

import static org.junit.Assert.*;

import model.Category;
import model.MenuItem;
import org.junit.Before;
import org.junit.Test;

public class MenuItemTest {
	private MenuItem testItem1;
	private MenuItem testItem2;
	
	@Before
	public void setUp(){
		testItem1 = new MenuItem(1, "Cheese Sandwich", Category.SANDWICH, 4.50, 1);
		testItem2 = new MenuItem(1, "Cheese Sandwich", Category.SANDWICH, 4.50, 1);
		
	}

	@Test
	public void menuItem_getName_usingCorrectName_test() {
		String name = testItem1.getName();
		assertEquals("MenuItem getName() method failed", name, "Cheese Sandwich");
		//assertEquals(message for when it goes wrong, things you're checking, what it should be);
		
	}

	@Test
	public void menuItem_getNumber_usingCorrectNumber_test() {
		int No = testItem1.getNumber();
		assertEquals("MenuItem getNumber() method failed", No, 1);
		//assertEquals(message for when it goes wrong, things you're checking, what it should be);
	}
	
	@Test
	public void menuItem_getCategory_usingCorrectCategory_test() {
		Category category = testItem1.getCategory();
		assertEquals("MenuItem getNumber() method failed", category, Category.SANDWICH);
		//assertEquals(message for when it goes wrong, things you're checking, what it should be);
	}
	
	@Test
	public void menuItem_getQuantity_usingCorrectQuantity_test() {
		int quantity = testItem1.getQuantity();
		assertEquals("MenuItem getNumber() method failed", quantity, 1);
		//assertEquals(message for when it goes wrong, things you're checking, what it should be);
	}

	@Test
	public void menuItem_getPrice_usingCorrectPrice_test() {
		double price = testItem1.getPrice();
		assertEquals("MenuItem getNumber() method failed", price, 4.50, 0.0001);
		//assertEquals(message for when it goes wrong, things you're checking, what it should be);
	}

	@Test
	public void menuItem_equals_usingTwoObjects_test() {
		assertEquals("testItem1 is not equal to testItem2", true, testItem1.equals(testItem2));
		
}
}

package test;

import static org.junit.Assert.*;

import model.Category;
import model.Menu;
import model.MenuItem;
import org.junit.Before;
import org.junit.Test;

public class MenuTest {
	private Menu menu1;
	private MenuItem menuItem1;
	
	@Before
	public void setup() {
		menuItem1 = new MenuItem (1, "Cheese Sandwich", Category.SANDWICH, 4.50, 1);
		menu1 = new Menu();
		
	}
	

	@Test
	public void check_addItem_method_with_new_item_test() {
		menu1.addItem("Cheese Sandwich", menuItem1);
		assertEquals("Item not added", menuItem1,menu1.getItem("Cheese Sandwich"));
	}
	
	@Test
	public void check_deleteItem_method_with_new_items_test() {
		menu1.addItem("Cheese Sandwich", menuItem1);
		menu1.deleteItem("Cheese Sandwich");
		assertEquals("Item is still there", null ,menu1.getItem("Cheese Sandwich"));
	}

}

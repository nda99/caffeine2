package system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	public static void main(String[] args){
// add all items from list 
	MenuItem allmenuItemList;
	allmenuItemList = new MenuItem();
	allmenuItemList.readFile("/Users/emmawood/Documents/git_caffine/caffeine/menuItems.csv");
	
	


	class MeniItemList {
		private ArrayList<MenuItem> menuItemList;
	public  MeniItemList() {
		this.menuItemList = new ArrayList<MenuItem>();	
	}

	
		
		//print table of competitors 
		System.out.println(allmenuItemList.getTableOfItemsInMenu());
	
		
		
	
	}
	}}



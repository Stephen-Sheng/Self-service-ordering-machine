

import java.util.ArrayList;

/**
 * Title : Menu.java 
 * Description: This class is an entity class of menu.
 * 
 * @author : Jiaqi Luo
 * @date : 8/5/2020
 */

public class Menu {
	private static ArrayList<String> option; // items in menu
	private static ArrayList<String> price; // price of a single dish and all add-ons
 	private static ArrayList<ArrayList<String>> content; // options of each item
 	private static int addonscount, linecount; // amount of add-ons and total items
 	
 	/**
 	 * Set item names
 	 * @param option Items in menu
 	 */
	public void setOption(ArrayList<String> option) {
		Menu.option = option;
		addonscount = 0;
		linecount = Menu.option.size();
		for(int i=0;i<option.size();i++) {
			if(option.get(i).contains("[Add_ons]"))
				addonscount++;
		}
	}
	
	/**
	 * Set price of items
	 * @param price Price of a single dish and all add-ons
	 */
	public void setPrice(ArrayList<String> price) {
		Menu.price = price;
	}
	
	/**
	 * Set option contents of each item
	 * @param content Option contents of each item
	 */
	public void setContent(ArrayList<ArrayList<String>> content) {
		Menu.content = content;
	}
	
	/**
	 * Get item names
	 * @return items in menu
	 */
	public ArrayList<String> getOption() {
		return option;
	}
	
	/**
	 * Get price of item
	 * @return price of a single dish and all add-ons
	 */
	public ArrayList<String> getPrice(){
		return price;
	}
	
	/**
	 * Get option contents of each item
	 * @return option contents of each item
	 */
	public ArrayList<ArrayList<String>> getContent(){
		return content;
	}
	
	/**
	 * Get amount of total items
	 * @return amount of total items
	 */
	public int getLineCount() {
		return linecount;
	}
	
	/**
	 * Get amount of add-ons
	 * @return amount of add-ons
	 */
	public int getAddonsCount() {
		return addonscount;
	}
}

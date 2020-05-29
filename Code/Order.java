import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Title : Order.java 
 * Description: This class is an entity class of order.
 * 
 * @author : Jiaqi Luo
 * @date : 8/5/2020
 */

public class Order {
	// item names, fixed item names and optional item names in one order
	private static ArrayList<String> option, fixedOrderOption, optionalOrderOption;
	// option contents, fixed option contents and optional contents in one order
	private static ArrayList<String> content, fixedOrderContent, optionalOrderContent;
	private static int linecount, userID; // amount of total items in order and id number of user (int type)
	private static String userid; // id number of user (String type)
	private static double totalprice; // price of a single dish
	
	private Calendar cal = Calendar.getInstance();
	private java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private String cdate = sdf.format(cal.getTime());
	private String orderID = cdate + "001";
	private Menu m = new Menu();

	/**
	 * Set item names
	 * @param option Item names
	 */
	public void setOption(ArrayList<String> option) {
		linecount = 0;
		Order.option = option;
		linecount = option.size();
		fixedOrderOption = new ArrayList<String>();
		optionalOrderOption = new ArrayList<String>();
		for(int i=0;i<option.size();i++) {
			if((!option.get(i).contains("[Add_ons]"))&&(!option.get(i).contains("[USERID]")))
				fixedOrderOption.add(option.get(i).substring(option.get(i).indexOf('[') + 1,option.get(i).indexOf(']')));
			if(option.get(i).contains("[Add_ons]"))
				optionalOrderOption.add(option.get(i).substring(option.get(i).indexOf(']') + 1));
		}
	}

	/**
	 * Set option contents of each item
	 * @param content Option contents of each item
	 */
	public void setContent(ArrayList<String> content) {
		Order.content = content;
		//System.out.println("Order setContent:   " + Order.content);
		fixedOrderContent = new ArrayList<String>();
		optionalOrderContent = new ArrayList<String>();
		for(int i=0;i<option.size();i++) {
			if((!option.get(i).contains("[Add_ons]"))&&(!option.get(i).contains("[USERID]")))
				fixedOrderContent.add(content.get(i));
			if(option.get(i).contains("[Add_ons]"))
				optionalOrderContent.add(content.get(i));
			}
	}

	/**
	 * Set option contents of fixed items
	 * @param contentone Option contents of fixed items
	 */
	public void setContentOne(ArrayList<String> contentone) {
		Order.fixedOrderContent = contentone;
	}

	/**
	 * Set option contents of optional items
	 * @param contenttwo Option contents of optional items
	 */
	public void setContentTwo(ArrayList<String> contenttwo) {
		Order.optionalOrderContent = contenttwo;
	}
	
	/**
	 * Set ID number of user
	 * @param userID ID number of user
	 */
	public void setUserID(String userID) {
		Order.userID = Integer.parseInt(userID.substring(userID.indexOf(':') + 1));
		userid = userID;
	}

	/**
	 * Get item names
	 * @return item names
	 */
	public ArrayList<String> getOption() {
		return option;
	}
	
	/**
	 * Get option contents of fixed items
	 * @return fixedOrderOption
	 */
	public ArrayList<String> getFixedOrderOption(){
		return fixedOrderOption;
	}
	
	/**
	 * Get option contents of optional items
	 * @return optionalOrderOption
	 */
	public ArrayList<String> getOptionalOrderOption(){
		return optionalOrderOption;
	}

	/**
	 * Get option contents of all items
	 * @return content
	 */
	public ArrayList<String> getContent() {
		if (fixedOrderContent != null && optionalOrderContent == null) {
			for (int i = 0; i < fixedOrderContent.size(); i++) {
				content.set(i, fixedOrderContent.get(i));
				//System.out.println(contentone.get(i));
			}
		} else if (fixedOrderContent != null && optionalOrderContent != null) {
			for (int i = 0; i < fixedOrderContent.size(); i++) {
				content.set(i, fixedOrderContent.get(i));
				//System.out.println(contentone.get(i));
			}
			for (int i = 0; i < optionalOrderContent.size(); i++) {
				content.set(i + 7, optionalOrderContent.get(i));
			}
		}
		return content;
	}
	
	/**
	 * Get option contents of fixed items
	 * @return fixedOrderContent
	 */
	public ArrayList<String> getFixedOrderContent(){
		return fixedOrderContent;
	}
	
	/**
	 * Get option contents of optional items
	 * @return optionalOrderContent
	 */
	public ArrayList<String> getOptionalOrderContent(){
		return optionalOrderContent;
	}

	/**
	 * Get amount of total items
	 * @return amount of total items
	 */
	public int getLineCount() {
		return linecount;
	}
	
	public int getUserID() {
		return userID;
	}
	
	/**
	 * Get user ID number
	 * @return user ID number
	 */
	public String getUserid() {
		return userid;
	}
	
	/**
	 * Get total price of the order
	 * @return total price
	 */
	public double getTotalPrcie() {
		totalprice = Double.parseDouble(m.getPrice().get(0));
		for(int i=0;i<linecount-7;i++) {
			double singleprice = Double.parseDouble(m.getPrice().get(i+7)) * Integer.parseInt(optionalOrderContent.get(i));
			totalprice = totalprice + singleprice;
		}
		return totalprice;
	}
	
	/**
	 * Get user ID number in the order
	 * @return user ID number in the order
	 */
	public String getOrderID() {
		return orderID;
	}
}

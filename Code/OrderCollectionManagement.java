

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.ArrayList;

/**
 * Title : OrderCollectionManagement.java Description : This class is used to
 * deal with the file orderCollection.txt.
 * 
 * @author : Yang Hu 
 * @author : Shuhan Guo
 * @author : Jiaqi Luo
 * @date : 12/5/2020
 */
public class OrderCollectionManagement {
	private String orderPath = "Files/order.txt"; // path of order file
	private String orderCollectionPath = "Files/orderCollection.txt"; // path of orderCollection file
	private String str0 = "";
	private ArrayList<String> orderList = new ArrayList<String>(); // create an array list to store last week orders with count number
	private ArrayList<String> orderList2 = new ArrayList<String>(); // create an array list to store last week orders without count number
	private ArrayList<String> allorders = new ArrayList<String>(); // create an array list to store all orders of one user
	private ArrayList<String> option = new ArrayList<String>(); // create an array list to store items to be displayed in last week report
	private ArrayList<String> count = new ArrayList<String>(); // create an array list to store amount of each items in last week report
	private Menu m = new Menu();
	private Order o = new Order();

	/**
	 * Get all orders of a user
	 * @return all orders
	 */
	public ArrayList<String> getAllOrders() {
		return allorders;
	}

	/**
	 * Get last week orders with count number
	 * @return order list
	 */
	public ArrayList<String> getList() {
		return orderList;
	}

	/**
	 * Get last week orders' item names
	 * @return option
	 */
	public ArrayList<String> getOption() {
		return option;
	}

	/**
	 * Get last week orders' sold amount of each item
	 * @return count
	 */
	public ArrayList<String> getCount() {
		return count;
	}

	/**
	 * Get spiciness content
	 * @return str0
	 */
	public String getText() {
		return str0;
	}

	/**
	 * This method is called to read the orders' information and store valid data in an array list.
	 */
	public void readOrderCollection() {
		File file = new File(orderCollectionPath);
		String currentLine;
		int year_order; // year of the order
		int mon_order; // month of the order
		int day_order; // day of the order
		int listCount1 = 0; // counter to calculate the number of satisfied orders
		long days_between = 0; // the date difference between Sunday of previous week and order time
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			while (true) {
				currentLine = br.readLine();
				if (currentLine == null)
					break;

				// extract year, month, day into different variables and the interval between
				// dates
				if (currentLine.contains("OrderNumber:")) {
					String orderNumber = currentLine.substring(currentLine.indexOf(':') + 1,
							currentLine.indexOf(':') + 9);
					year_order = Integer.parseInt(orderNumber.substring(0, 4));
					mon_order = Integer.parseInt(orderNumber.substring(4, 6));
					day_order = Integer.parseInt(orderNumber.substring(6, 8));
					try {
						days_between = Days_Between(year_order, mon_order, day_order);
					} catch (Exception e) {
						e.printStackTrace();
					}

					// only write previous week's order to array list
					if (days_between <= 7 && days_between > 0) {
						orderList2.add(currentLine);
						listCount1 = listCount1 + 1;
						currentLine = listCount1 + ". " + currentLine + "\n";
						orderList.add(currentLine);
					}
				} else {
					if (days_between <= 7 && days_between > 0) {
						orderList2.add(currentLine);
						currentLine = "      " + currentLine + "\n";
						orderList.add(currentLine);
					}
				}

				// extract data about spiciness in the order
				if (currentLine.contains("Spiciness")) {
					String spicy = currentLine.substring(currentLine.indexOf("Spiciness"),
							currentLine.indexOf("Spiciness") + 12);
					if (days_between <= 7 && days_between > 0) {
						// currentLine = currentLine + "\n";
						spicy = spicy + "\n";
						// System.out.println(currentLine);订单其他行
						str0 += spicy;
					}
				}
			}
			// the last array record total number of the valid orders.
			orderList.add(Integer.toString(listCount1));
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Calculate amount of days between the order and the date of viewing report
	 * @param year Year
	 * @param month Month
	 * @param day Day
	 * @return amount of days
	 * @throws Exception exception
	 */
	public long Days_Between(int year, int month, int day) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
		Date startDate = sdf.parse((year + " " + month + " " + day));
		Date endDate = sdf.parse(sdf.format(new Date()));
		long betweenDate = (endDate.getTime() - startDate.getTime()) / (60 * 60 * 24 * 1000);
		// get week
		Calendar c = Calendar.getInstance();
		// calculate the distance between last week and order date; By first get the
		// days between current and date of number, then according to current week,
		// calculate Monday of current week by minus 2
		betweenDate = betweenDate - (c.get(Calendar.DAY_OF_WEEK) - 2);
		return betweenDate;
	}

	/**
	 * Count sold amount of each item in menu
	 */
	public void getReport() {
		/*
		 * Get item names from menu
		 */
		option.add("Soup------------------Tonkotsu");
		option.add("Soup------------------Shoyu");
		option.add("Soup------------------Shio");
		option.add("Noodles--------------Soft");
		option.add("Noodles--------------Medium");
		option.add("Noodles--------------Firm");
		option.add("Spring Onion------No please");
		option.add("Spring Onion------Just a little");
		option.add("Spring Onion------A lot!");
		for (int i = 0; i < m.getAddonsCount(); i++) {
			option.add(m.getOption().get(i + 7));
		}

		/*
		 * Firstly set sold amount of each item to 0
		 */
		for (int i = 0; i < (m.getAddonsCount() + 9); i++) {
			count.add("0");
		}
		
		/*
		 * According to last week orders, get the sold amount of each item
		 */
		for (int i = 0; i < orderList2.size(); i++) {
			if (Pattern.matches(".*Tonkotsu.*", orderList2.get(i)))
				count.set(0, Integer.toString(Integer.parseInt(count.get(0)) + 1));
			if (Pattern.matches(".*Shoyu.*", orderList2.get(i)))
				count.set(1, Integer.toString(Integer.parseInt(count.get(1)) + 1));
			if (Pattern.matches(".*Shio.*", orderList2.get(i)))
				count.set(2, Integer.toString(Integer.parseInt(count.get(2)) + 1));
			if (Pattern.matches(".*Soft.*", orderList2.get(i)))
				count.set(3, Integer.toString(Integer.parseInt(count.get(3)) + 1));
			if (Pattern.matches(".*Medium.*", orderList2.get(i)))
				count.set(4, Integer.toString(Integer.parseInt(count.get(4)) + 1));
			if (Pattern.matches(".*Firm.*", orderList2.get(i)))
				count.set(5, Integer.toString(Integer.parseInt(count.get(5)) + 1));
			if (Pattern.matches(".*No please.*", orderList2.get(i)))
				count.set(6, Integer.toString(Integer.parseInt(count.get(6)) + 1));
			if (Pattern.matches(".*Just a little.*", orderList2.get(i)))
				count.set(7, Integer.toString(Integer.parseInt(count.get(7)) + 1));
			if (Pattern.matches(".*A lot!.*", orderList2.get(i)))
				count.set(8, Integer.toString(Integer.parseInt(count.get(8)) + 1));
			if (Pattern.matches(".*\\[Nori].*", orderList2.get(i))) {
				String str = orderList2.get(i).substring(orderList2.get(i).indexOf(":") + 1,
						orderList2.get(i).length());
				if (str.equals("Yes"))
					count.set(9, Integer.toString(Integer.parseInt(count.get(9)) + 1));
			}
			if (Pattern.matches(".*\\[Chashu].*", orderList2.get(i))) {
				String str = orderList2.get(i).substring(orderList2.get(i).indexOf(":") + 1,
						orderList2.get(i).length());
				if (str.equals("Yes"))
					count.set(12, Integer.toString(Integer.parseInt(count.get(12)) + 1));
			}
			if (Pattern.matches(".*\\[Boiled egg].*", orderList2.get(i))) {
				String str = orderList2.get(i).substring(orderList2.get(i).indexOf(":") + 1,
						orderList2.get(i).length());
				if (str.equals("Yes"))
					count.set(10, Integer.toString(Integer.parseInt(count.get(10)) + 1));
			}
			for (int j = 0; j < m.getAddonsCount(); j++) {
				if (Pattern.matches(".*\\" + option.get(j + 9) + ".*", orderList2.get(i))) {
					String str = orderList2.get(i).substring(orderList2.get(i).indexOf(":") + 1,
							orderList2.get(i).length());
					if (Integer.parseInt(str) != 0) {
						count.set(j + 9, Integer.toString(Integer.parseInt(count.get(j + 9)) + Integer.parseInt(str)));
					}
				}
			}
		}
	}

	/**
	 * Look up all orders of a user
	 * @param id User ID number
	 */
	public void readUserOrderCollection(String id) {
		System.out.println(id);
		File file = new File(orderCollectionPath);
		String currentLine;
		int count = 0;
		int belong = 0;
		/*
		 * Read orderCollection.txt, get orders of one user and add count number
		 */
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			while (true) {
				currentLine = br.readLine();
				if (currentLine == null)
					break;
				/*
				 * Judge the first line of one order
				 */
				if (currentLine.contains("OrderNumber:")) {
					if (currentLine.contains(id)) {
						belong = 1;
						count += 1;
						allorders.add(count + ". " + currentLine + "\n");
					} else {
						belong = 0;
					}
				} else {
					if (belong == 1) {
						allorders.add("      " + currentLine + "\n");
					}
				}
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to generate the collections of all orders and assign order ID.
	 **/
	public void writeOrderCollection() {
		String oneLine;
		File file = new File(orderCollectionPath);
		/*
		 * Assign order ID. The first eight bits represent the date of sale. The last
		 * three bits represents the number of this order sold today.
		 */
		Calendar cal = Calendar.getInstance();
		java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String cdate = sdf.format(cal.getTime());
		String orderID = cdate + "001";
		/* Write the order number into the file. */
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			while (true) {
				oneLine = br.readLine();
				if (oneLine == null) {
					break;
				}
				/*
				 * Judge if this is the first order of the day. If yes, set the sequence number
				 * of the last three digits to 001, if not, then the sequence number plus 1.
				 */
				if (oneLine.contains("OrderNumber:")) {
					String sub = oneLine.substring(oneLine.indexOf(':') + 1, oneLine.indexOf(':') + 9);

					if (sub.equals(cdate)) {
						String ID = oneLine.substring(oneLine.indexOf(':') + 1, oneLine.indexOf(' '));
						long number = Long.parseLong(ID) + 1;
						orderID = "" + number;
					} else {
						orderID = cdate + "001";
					}
				}
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/* Write the user ID and the order contents into the file. */
		try {
			Writer w = new FileWriter(orderCollectionPath, true);
			FileReader fr = new FileReader(orderPath);
			BufferedReader br = new BufferedReader(fr);
			w.write("OrderNumber:" + orderID + " USERID:" + o.getUserid() + "\n");
			for (int i = 0; i < o.getLineCount(); i++) {
				w.write(o.getOption().get(i) + ":" + o.getContent().get(i) + "\n");
			}
			br.close();
			fr.close();
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

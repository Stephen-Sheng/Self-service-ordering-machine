import java.util.ArrayList;
import java.io.*;
/**
 * Title 		: OrderManagement.java
 * Description	: This class is used to deal with the file order.txt and menu.txt.
 * @author		: Yang Hu
 * @author      : Jiaqi Luo
 * @date      	: 12/5/2020
 */
public class OrderManagement {
	private String orderPath = "Files/order.txt"; // path of order file
	private int linecount; // amount of total items
	private String userid; // user ID number
	private ArrayList<String> content = new ArrayList<String>(); // option content of each item
	private ArrayList<String> option = new ArrayList<String>(); // all item names

	private File menu = new File(orderPath);
	private MenuManagement rwm = new MenuManagement();
	private Order o = new Order();
	private Menu m = new Menu();

	/**
	 * Read menu and set information to order
	 */
	public OrderManagement() {
		rwm.readMenu();
		option = m.getOption();
		linecount = m.getLineCount();
		o.setOption(option);
	}
	
	/**
	 * If the menu.txt file do not exist, create it.
	 * */
	public void creatOrder() {
		try {
			if (!menu.exists()) {
				menu.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(menu));
			for (int k = 0; k < linecount; k++) {
				bw.write(option.get(k) + ":0\n");
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Read order.txt
	 */
	public void readOrder() {
		try {
			FileReader fileReader = new FileReader(orderPath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			for (int i = 0; i < linecount; i++) {
				String oneLine = bufferedReader.readLine();
				String[] line = oneLine.split("\\:");
				content.add(line[1]);
			}
			userid = bufferedReader.readLine();
			bufferedReader.close();
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		o.setContent(content);
		if (userid != null)
			o.setUserID(userid);
	}

	/**
	 * Print out order.txt
	 */
	public void printOrder() {
		content = o.getContent();
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(menu));
			for (int i = 0; i < linecount; i++) {
				bw.write(option.get(i) + ":" + content.get(i) + "\n");
			}
			if (userid != null) {
				bw.write(userid);
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Write the user ID of this order to the order.txt.
	 * @param IDnum User ID number
	 */
	public void addUserid(String IDnum) {
		try {
			Writer w = new FileWriter(orderPath, true);
			w.write("[USERID]:" + IDnum);
			System.out.println(IDnum);
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Delete order.txt
	 */
	public void deleteOrder() {
		if (menu.exists()) {
			menu.delete();
		}
	}

	/**
	 * Get user ID number
	 * @return user ID number
	 */
	public String getUserid() {
		return userid;
	}
}

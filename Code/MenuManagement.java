import java.io.*;
import java.util.ArrayList;

/**
 * Title : MenuManagement.java 
 * Description: This class reads and writes the file of menu.
 * 
 * @author : Yang Hu
 * @author : Jiaqi Luo
 * @date : 8/5/2020
 */

public class MenuManagement {
	private String fileName = "Files/menu.txt";
	private String[] originalcontent = new String[3];
	private ArrayList<String> line = new ArrayList<String>();// = new String [11];//each one line in the file
	private ArrayList<OneLine> option_content = new ArrayList<OneLine>();// split one line into different parts
	private ArrayList<String> option = new ArrayList<String>();// all the categories of options
	private ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();// the content of all options, after
																					// ":"
	private ArrayList<String> price = new ArrayList<String>();// price of soup, nori, chashu, egg bamboo
	int i = 0;
	Menu m = new Menu();

	/**
	 * Read the file of menu (menu.txt)
	 */
	public MenuManagement() {
		/*
		 * Read menu file and use entity class OneLine to store different type of information
		 */
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String str;
			while ((str = bufferedReader.readLine()) != null) {
				line.add(str);
				OneLine oneLine = new OneLine(line.get(i));
				option_content.add(oneLine);
				option.add(option_content.get(i).option);
				list.add(option_content.get(i).getContent());
				price.add(option_content.get(i).price);
				i++;
			}
			bufferedReader.close();
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Set the information from file to entity menu
	 */
	public void readMenu() {
		m.setOption(option);
		m.setPrice(price);
		m.setContent(list);
	}

	/**
	 * Write the file of Menu (menu.txt)
	 */
	public void writeMenu() {
		/*
		 * Get menu information from entity class Menu
		 */
		option = m.getOption();
		list = m.getContent();
		price = m.getPrice();
		/*
		 * Write to menu.txt
		 */
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
			for (int j = 0; j < option.size(); j++) {
				bw.write(option.get(j) + ":" + price.get(j) + "#" + String.join("/", list.get(j)) + "\n");
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fixed options of soup
	 * @return fixed options of soup
	 */
	public String[] getOriginalContent() {
		originalcontent[0] = "Tonkotsu";
		originalcontent[1] = "Shoyu";
		originalcontent[2] = "Shio";
		return originalcontent;
	}

	/**
	 * Add new add-ons to menu
	 * @param name Item name
	 * @param price Item price
	 */
	public void addItem(String name, String price) {
		try {
			Writer w = new FileWriter(fileName, true);
			w.write("[Add_ons]" + name + ":" + price + "#enough\n");
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

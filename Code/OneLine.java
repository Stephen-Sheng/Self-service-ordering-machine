import java.util.ArrayList;
import java.util.Arrays;

/**
 * Title : OneLine.java 
 * Description: This class is used to deal with each one line in menu.txt
 * 
 * @author : Jiaqi Luo
 * @date : 8/5/2020
 */

public class OneLine {
	public String option; // item in one line of menu
	public String content; // option content in one line of menu
	public String price; // price in one line of menu
	
	/**
	 * Split one line into three parts
	 * @param str String of one line
	 */
	public OneLine(String str) {
		String[] splitline = str.split("\\:|\\#");
		option = splitline[0];
		price = splitline[1];
		content = splitline[2];
	}
	
	/**
	 * Get option contents of item in one line
	 * @return option contents of item in one line
	 */
	public ArrayList<String> getContent(){
		ArrayList<String> list = new ArrayList<String>();
		list.addAll(new ArrayList<String>(Arrays.asList(content.split("/"))));
		return list;
	}
	
}

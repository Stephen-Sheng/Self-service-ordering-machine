/**
 * Title : Test.java 
 * Description: This class is the initial class.
 * 
 * @author : Jiaqi Luo
 * @date : 8/5/2020
 */

public class Test {
	public static void main(String args[]) {
		OrderManagement m = new OrderManagement();
		m.deleteOrder();
		m.creatOrder();
		new Welcome();
	}
}
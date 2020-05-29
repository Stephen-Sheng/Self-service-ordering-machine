

import javax.swing.*;

/**
 * Title 		: PaymentPageControl.java
 * Description	: This class is the control class for the payment function part.
 * @author		: Yang Hu 
 * @date      	: 12/5/2020
 */
public class PaymentPageControl {
	LoginControl lc = new LoginControl();
	UserInfoManagement uim = new UserInfoManagement();
	OrderCollectionManagement ocm = new OrderCollectionManagement();
	
	/**
	 * Determine the amount of stamp and display different payment methods. 
	 * If the amount of stamp is greater than 10, users can choose whether to pay with stamp or not. 
	 * If the amount of stamp is less than 10, users can only pay by card or cash.
	 * @param stamp To receive the number of stamps of integer type. 
	 * 
	 **/
	public void payment(int stamp) {		
		if (stamp>=10) {
			PaymentPage2 pay = new PaymentPage2();
			pay.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			pay.setVisible(true);
			stamp=stamp-10;
			ocm.writeOrderCollection();
		}
		else{
			PaymentPage pay2 = new PaymentPage();
			pay2.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			pay2.setVisible(true);
			stamp=stamp+1;
			System.out.println("stamp="+stamp);
			ocm.writeOrderCollection();
		}		
	}
	
	/**
	 *	Display the payment success prompt messagewhen users choose to pay by card or cash,
	 *	 write the order information to orderCollection.txt 
	 *	and update the stamp value in userInfo.txt.
	 * 
	 **/
	public void judge1() {
		Object[] options = { "OK" };
		JOptionPane.showOptionDialog(null, "Payment Completed!", "Payment Message", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		int stamp = Integer.parseInt(lc.getUser().getStamp())+1;
		lc.getUser().setStamp(String.valueOf(stamp));
		uim.writeUserInfo();
	}
	
	/**
	 *	Display the payment success prompt message when users choose to pay by stamps, 
	 *	write the order information to orderCollection.txt 
	 *	and update the stamp value in userInfo.txt.
	 * 
	 **/
	public void judge2() {
		Object[] options ={ "OK" };
		JOptionPane.showOptionDialog(null, "Payment Completed!", "Payment Message",JOptionPane.YES_NO_OPTION, 
				JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		int stamp = Integer.parseInt(lc.getUser().getStamp())-10;
		lc.getUser().setStamp(String.valueOf(stamp));
		System.out.println("new stamp at PaymentPage2:  "+stamp);
		uim.writeUserInfo();
	}
}

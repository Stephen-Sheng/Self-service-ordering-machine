

import javax.swing.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Title : ViewAccount.java 
 * Description: This class is the GUI for client viewing account information.
 * 
 * @author : Mingzhi Tang
 * @date : 8/5/2020
 */

public class ViewAccount {
	private JFrame accountframe; // create a frame
	private JButton Button1, Button2, Button3; // buttons for switching page
	private LoginControl lc = new LoginControl();

	/**
	 * Build the GUI for client viewing account information
	 */
	public ViewAccount() {
		Font f = new Font("Cambria", Font.ITALIC+Font.BOLD, 20);
		Font hint = new Font("Cambria", Font.BOLD, 17);
		Font btnFont = new Font("Cambria", Font.BOLD, 18);
		
		accountframe = new JFrame("My Account");
		accountframe.setBounds(600, 130, 400, 600);
		accountframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		accountframe.setVisible(true);
		accountframe.setLocationRelativeTo(null);

		accountframe.getContentPane().removeAll();
		accountframe.repaint();
		Container container = accountframe.getContentPane();
		container.setBackground(new Color(250,240,215));
		accountframe.setLayout(null);

		// label creating
		JLabel Label1 = new JLabel("WELCOME, " + "YOUR ID : " + lc.getUser().getID());
		Label1.setFont(f);
		Label1.setBounds(50, 25, 300, 50);
		accountframe.add(Label1);

		JLabel Label2 = new JLabel("Your Name:");
		Label2.setFont(hint);
		Label2.setBounds(75, 100, 150, 25);
		accountframe.add(Label2);

		JLabel Label3 = new JLabel("Your E_Mail:");
		Label3.setFont(hint);
		Label3.setBounds(75, 150, 150, 25);
		accountframe.add(Label3);

		JLabel Label4 = new JLabel("Your Phone:");
		Label4.setFont(hint);
		Label4.setBounds(75, 200, 150, 25);
		accountframe.add(Label4);

		JLabel Label5 = new JLabel("Your Age:");
		Label5.setFont(hint);
		Label5.setBounds(75, 250, 150, 25);
		accountframe.add(Label5);

		JLabel Label6 = new JLabel("Your tickets:");
		Label6.setFont(hint);
		Label6.setBounds(75, 300, 150, 25);
		accountframe.add(Label6);

		JLabel Label11 = new JLabel(lc.getUser().getName());
		Label11.setFont(hint);
		Label11.setBounds(200, 100, 175, 25);
		accountframe.add(Label11);

		JLabel Label12 = new JLabel(lc.getUser().getEmail());
		Label12.setFont(hint);
		Label12.setBounds(200, 150, 175, 25);
		accountframe.add(Label12);

		JLabel Label13 = new JLabel(lc.getUser().getPhone());
		Label13.setFont(hint);
		Label13.setBounds(200, 200, 175, 25);
		accountframe.add(Label13);

		JLabel Label14 = new JLabel(lc.getUser().getAge());
		Label14.setFont(hint);
		Label14.setBounds(200, 250, 175, 25);
		accountframe.add(Label14);

		JLabel Label15 = new JLabel(lc.getUser().getStamp());
		Label15.setFont(hint);
		Label15.setBounds(200, 300, 175, 25);
		accountframe.add(Label15);
		
		// button creating
		Button3 = new JButton("Modify information");
		Button3.setBorderPainted(false);
		Button3.setForeground(new Color(254,245,228));
		Button3.setBackground(new Color(150,122,111));
		Button3.setFont(btnFont);
		Button3.setBounds(80, 375, 240, 30);
		accountframe.add(Button3);
		Button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accountframe.setVisible(false);
				new ModifyInformation();;
			}
		});
		
		Button2 = new JButton("View history orders");
		Button2.setBorderPainted(false);
		Button2.setForeground(new Color(254,245,228));
		Button2.setBackground(new Color(150,122,111));
		Button2.setFont(btnFont);
		Button2.setBounds(80, 425, 240, 30);
		accountframe.add(Button2);
		Button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accountframe.setVisible(false);
				new ViewHistoryOrder();
			}
		});
		
		Button1 = new JButton("Pay");
		Button1.setBorderPainted(false);
		Button1.setForeground(new Color(254,245,228));
		Button1.setBackground(new Color(150,122,111));
		Button1.setFont(btnFont);
		Button1.setBounds(80, 475, 240, 30);
		accountframe.add(Button1);
		Button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accountframe.setVisible(false);
				new CheckOrder();
			}
		});
	}

}

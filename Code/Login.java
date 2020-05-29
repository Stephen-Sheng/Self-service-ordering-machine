

import java.awt.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Title : Login.java 
 * Description: This class is the GUI for client login.
 * 
 * @author : Mingzhi Tang
 * @date : 8/5/2020
 */

public class Login implements ActionListener {
	// Define some components to be used next, such as JFrame and JButtons. 
	private JFrame loginframe;
	private JButton Button1, Button2, Button3;
	private JTextField ID; // Define the text field for user to write the id number
	private JPasswordField password; // Define the text field for user to write the password
	private UserInfoManagement uim = new UserInfoManagement();
	private OrderManagement om = new OrderManagement();

	/**
	 * Build the GUI interface for user login.
	 */
	public Login() {
		Font f = new Font("Cambria", Font.ITALIC+Font.BOLD, 20);
		Font hint = new Font("Cambria", Font.BOLD, 20);
		Font hint2 = new Font("Cambria", Font.BOLD, 12);
		Font textFont = new Font("Cambria", Font.ITALIC + Font.BOLD, 15);
		uim.readUserInfo();
		loginframe = new JFrame("Log in");
		loginframe.setLayout(null);
		Container container = loginframe.getContentPane();
		container.setBackground(new Color(250,240,215));

		JLabel Label1 = new JLabel("Enter Information:");
		Label1.setFont(f);
		Label1.setForeground(new Color(105,77,76));
		Label1.setBounds(75, 25, 300, 50);
		loginframe.add(Label1);

		JLabel Label2 = new JLabel("Phone or email:");
		Label2.setFont(hint);
		Label2.setForeground(new Color(105,77,76));
		Label2.setBounds(40, 100, 150, 25);
		loginframe.add(Label2);

		JLabel Label3 = new JLabel("Password:");
		Label3.setFont(hint);
		Label3.setForeground(new Color(105,77,76));
		Label3.setBounds(60, 150, 100, 25);
		loginframe.add(Label3);

		JLabel Label4 = new JLabel("No account?");
		Label4.setFont(f);
		Label4.setBounds(50, 475, 150, 25);
		loginframe.add(Label4);

		ID = new JTextField(10);
		ID.setBackground(Color.white);
		ID.setForeground(new Color(105, 77, 76));
		ID.setFont(textFont);
		ID.setBounds(200, 100, 150, 30);
		loginframe.add(ID);

		password = new JPasswordField();
		password.setBounds(200, 150, 150, 30);
		loginframe.add(password);
		loginframe.setBounds(600, 130, 400, 600);
		
		
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		JLabel imageLabel = new JLabel();
		ImageIcon icon = new ImageIcon("Files/ramen.jpg");
		imageLabel.setIcon(icon);
		icon.setImage(icon.getImage().getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING));
		imageLabel.setBounds(200, 225, 150, 150);
		p.add(imageLabel, BorderLayout.WEST);
	
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(2,1,20,10));
		btnPanel.setBackground(new Color(254,245,228));
		btnPanel.setForeground(new Color(250,240,215));
		btnPanel.setBounds(30, 275, 150, 80);
		Button1 = new JButton("Log in");
		Button1.setFont(hint);
		Button1.setBorderPainted(false);
		Button1.setForeground(new Color(254,245,228));
		Button1.setBackground(new Color(150,122,111));
		btnPanel.add(Button1);

		Button2 = new JButton("Join loyalty scheme");
		Button2.setFont(hint2);
		Button2.setBorderPainted(false);
		Button2.setForeground(new Color(254,245,228));
		Button2.setBackground(new Color(150,122,111));
		Button2.setBounds(200, 475, 150, 25);
		loginframe.add(Button2);

		Button3 = new JButton("Skip to pay");
		Button3.setBorderPainted(false);
		Button3.setForeground(new Color(254,245,228));
		Button3.setBackground(new Color(150,122,111));
		Button3.setFont(hint);
		//Button3.setBounds(100, 375, 200, 40);
		btnPanel.add(Button3,BorderLayout.SOUTH);
		
		loginframe.add(btnPanel);
		loginframe.add(imageLabel);
		Button1.addActionListener(this);
		Button2.addActionListener(this);
		Button3.addActionListener(this);
		
		loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginframe.setVisible(true);
		loginframe.setLocationRelativeTo(null);
	}

	/**
	 * Action listener
	 */
	public void actionPerformed(ActionEvent e) {
		JButton eventSource = (JButton) e.getSource();
		if (eventSource.equals(Button1)) {
			String Password = new String(password.getPassword());
			LoginControl lc = new LoginControl();
			lc.loginControl(ID.getText());
			if (ID.getText().equals("")) {
				Object[] options = { "Try Again" };
				JOptionPane.showOptionDialog(null, "You does not enter your phone or email!", "",
						JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			} else if (!lc.getIdcheck().contains(true)) {
				Object[] options = { "Try Again" };
				JOptionPane.showOptionDialog(null, "You have not regitered an account yet!", "", 
						JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			} else if (!Password.equals(lc.getRightPwd())) {
				Object[] options = { "Try Again" };
				JOptionPane.showOptionDialog(null, "Your password is not correct!", "",
						JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			} else {
				lc.finishLogin();
				om.addUserid(lc.getRightId());
				new ViewAccount();
				loginframe.dispose();
			}
		} else if (eventSource.equals(Button2)) {
			new Register();
			loginframe.dispose();

		} else if (eventSource.equals(Button3)) {
			loginframe.setVisible(false);
			new CheckOrder();
		}
	}
}

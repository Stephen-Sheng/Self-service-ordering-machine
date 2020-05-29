import javax.swing.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Title : ModifyInformation.java 
 * Description: This class is the GUI for client modifying personal information
 * 
 * @author : Jiaqi Luo
 * @date : 8/5/2020
 */

@SuppressWarnings("serial")
public class ModifyInformation extends JFrame implements ActionListener {
	private JFrame registerframe; // create a frame
	private JButton Button1, Button2; // two buttons for switching page
	private JTextField name, e_mail, phone, age; // text fields of personal information
	private JPasswordField password, cpassword; // password field of password and confirming password
	private LoginControl lc = new LoginControl();

	/**
	 * Build the GUI for modifying personal information
	 */
	public ModifyInformation() {
		Font f = new Font("Cambria", Font.ITALIC + Font.BOLD, 26);
		Font hint = new Font("Cambria", Font.BOLD, 17);
		Font btnFont = new Font("Cambria", Font.BOLD, 20);
		Font textFont = new Font("Cambria", Font.ITALIC + Font.BOLD, 15);
		
		registerframe = new JFrame("Modify Information");
		registerframe.setLayout(null);
		Container container = registerframe.getContentPane();
		container.setBackground(new Color(250, 240, 215));
		
		// label creating
		JLabel Label1 = new JLabel("MODIFY INFORMATION");
		Label1.setFont(f);
		Label1.setForeground(new Color(105, 77, 76));
		Label1.setBounds(60, 20, 280, 50);
		registerframe.add(Label1);

		JLabel Label2 = new JLabel("Name:");
		Label2.setFont(hint);
		Label2.setForeground(new Color(105, 77, 76));
		Label2.setBounds(100, 100, 150, 25);
		registerframe.add(Label2);

		JLabel Label3 = new JLabel("E_Mail:");
		Label3.setFont(hint);
		Label3.setForeground(new Color(105, 77, 76));
		Label3.setBounds(100, 150, 150, 25);
		registerframe.add(Label3);

		JLabel Label4 = new JLabel("Phone:");
		Label4.setFont(hint);
		Label4.setForeground(new Color(105, 77, 76));
		Label4.setBounds(100, 200, 150, 25);
		registerframe.add(Label4);

		JLabel Label5 = new JLabel("Age:");
		Label5.setFont(hint);
		Label5.setForeground(new Color(105, 77, 76));
		Label5.setBounds(100, 250, 150, 25);
		registerframe.add(Label5);

		JLabel Label8 = new JLabel("Old Password");
		Label8.setFont(hint);
		Label8.setForeground(new Color(105, 77, 76));
		Label8.setBounds(50, 300, 150, 25);
		registerframe.add(Label8);

		JLabel Label6 = new JLabel("New Password");
		Label6.setFont(hint);
		Label6.setForeground(new Color(105, 77, 76));
		Label6.setBounds(50, 350, 150, 25);
		registerframe.add(Label6);

		JLabel Label7 = new JLabel("Password Comfirm");
		Label7.setFont(hint);
		Label7.setForeground(new Color(105, 77, 76));
		Label7.setBounds(25, 400, 150, 25);
		registerframe.add(Label7);

		// text field creating
		name = new JTextField(10);
		name.setForeground(new Color(105, 77, 76));
		name.setFont(textFont);
		name.setBounds(185, 100, 150, 25);
		name.setText(lc.getUser().getName());
		registerframe.add(name);

		e_mail = new JTextField(10);
		e_mail.setForeground(new Color(105, 77, 76));
		e_mail.setFont(textFont);
		e_mail.setBounds(185, 150, 150, 25);
		e_mail.setText(lc.getUser().getEmail());
		registerframe.add(e_mail);

		phone = new JTextField(10);
		phone.setForeground(new Color(105, 77, 76));
		phone.setFont(textFont);
		phone.setBounds(185, 200, 150, 25);
		phone.setText(lc.getUser().getPhone());
		registerframe.add(phone);

		age = new JTextField(10);
		age.setForeground(new Color(105, 77, 76));
		age.setFont(textFont);
		age.setBounds(185, 250, 150, 25);
		age.setText(lc.getUser().getAge());
		registerframe.add(age);

		JLabel pw = new JLabel(lc.getUser().getPassword());
		pw.setFont(hint);
		pw.setForeground(new Color(105, 77, 76));
		pw.setBounds(185, 300, 150, 25);
		registerframe.add(pw);

		password = new JPasswordField();
		password.setBounds(185, 350, 150, 25);
		password.setText(lc.getUser().getPassword());
		registerframe.add(password);

		cpassword = new JPasswordField();
		cpassword.setBounds(185, 400, 150, 25);
		cpassword.setText(lc.getUser().getPassword());
		registerframe.add(cpassword);

		// button creating
		Button1 = new JButton("Complete");
		Button1.setFont(btnFont);
		Button1.setBounds(65, 475, 125, 30);
		Button1.setBorderPainted(false);
		Button1.setForeground(new Color(254, 245, 228));
		Button1.setBackground(new Color(150, 122, 111));
		registerframe.add(Button1);

		Button2 = new JButton("Back");
		Button2.setFont(btnFont);
		Button2.setBounds(210, 475, 125, 30);
		Button2.setBorderPainted(false);
		Button2.setForeground(new Color(254, 245, 228));
		Button2.setBackground(new Color(150, 122, 111));
		registerframe.add(Button2);

		registerframe.setBounds(600, 130, 400, 600);
		registerframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		registerframe.setVisible(true);
		registerframe.setLocationRelativeTo(null);

		Button1.addActionListener(this);
		Button2.addActionListener(this);
	}

	/**
	 * Action listener
	 */
	public void actionPerformed(ActionEvent e) {
		JButton eventSource = (JButton) e.getSource();
		String str1, str2, str3, str4, str5;
		if (eventSource.equals(Button1)) {
			lc.beginModifyInfo();
			if ((str1=lc.judgeName(name.getText())) != "") {
				wrongIm(str1);
			} else {
				if ((str2=lc.judgeEmail(e_mail.getText())) != "") {
					wrongIm(str2);
				} else {
					if ((str3=lc.judgePhone(phone.getText())) != "") {
						wrongIm(str3);
					} else {
						if ((str4=lc.judgeAge(age.getText())) != "") {
							wrongIm(str4);
						} else {
							if ((str5=lc.judgePassword(new String(password.getPassword()),
									new String(cpassword.getPassword()))) != "") {
								wrongIm(str5);
							} else {
								lc.checkInfo(name.getText(), e_mail.getText(), phone.getText(), age.getText(),
										new String(password.getPassword()), new String(cpassword.getPassword()));
								lc.finishModifyInfo();
								new ViewAccount();
								registerframe.dispose();
							}
						}
					}
				}
			}
		} else if (eventSource.equals(Button2)) {
			lc.beginModifyInfo();
			lc.finishModifyInfo();
			registerframe.dispose();
			new ViewAccount();
		}
	}

	/**
	 * Worn GUI
	 * @param worn String of worn
	 */
	public void wrongIm(String worn) {
		Object[] options0 = { "OK" };
		JOptionPane.showOptionDialog(null, worn, "", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
				options0, options0[0]);
	}
}

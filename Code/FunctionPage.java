

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Title : FunctionPage.java 
 * Description: This class is the GUI for administrator choosing function.
 * 
 * @author : Jiaqi Luo
 * @date : 8/5/2020
 */

@SuppressWarnings("serial")
public class FunctionPage extends JFrame implements ActionListener {
	private JButton button1, button2, button3, button4, previous; // buttons for page switching

	/**
	 * Build the GUI interface for administrator choosing function.
	 */
	public FunctionPage() {
		super("Function Page");
		Container container = this.getContentPane();
		this.setLayout(null);
		container.setBackground(new Color(250, 240, 215));

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		panel1.setBackground(new Color(250, 240, 215));
		panel2.setBackground(new Color(250, 240, 215));
		container.add(panel1);
		container.add(panel2);
		panel1.setBounds(20, 100, 350, 100);
		panel2.setBounds(40, 200, 300, 200);
		panel2.setLayout(new GridLayout(4, 1, 20, 20));

		Font titleFont = new Font("Cambria", Font.ITALIC + Font.BOLD, 26);
		Font btnFont = new Font("Cambria", Font.ITALIC + Font.BOLD, 20);
		Font btnFont2 = new Font("Cambria", Font.BOLD, 15);

		JLabel label = new JLabel("Please choose one thing to do.");
		label.setForeground(new Color(105, 77, 76));
		label.setFont(titleFont);
		panel1.add(label);

		button1 = new JButton("Modify price");
		button2 = new JButton("Modify menu option");
		button3 = new JButton("View history orders");
		button4 = new JButton("Add menu option");
		button1.setFont(btnFont);
		button2.setFont(btnFont);
		button3.setFont(btnFont);
		button4.setFont(btnFont);
		button1.setBorderPainted(false);
		button1.setForeground(new Color(254, 245, 228));
		button1.setBackground(new Color(150, 122, 111));
		button2.setBorderPainted(false);
		button2.setForeground(new Color(254, 245, 228));
		button2.setBackground(new Color(150, 122, 111));
		button3.setBorderPainted(false);
		button3.setForeground(new Color(254, 245, 228));
		button3.setBackground(new Color(150, 122, 111));
		button4.setBorderPainted(false);
		button4.setForeground(new Color(254, 245, 228));
		button4.setBackground(new Color(150, 122, 111));
		panel2.add(button1);
		panel2.add(button2);
		panel2.add(button3);
		panel2.add(button4);
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);

		previous = new JButton("Previous page");
		previous.setFont(btnFont2);
		previous.setBorderPainted(false);
		previous.setForeground(new Color(254, 245, 228));
		previous.setBackground(new Color(150, 122, 111));
		previous.setBounds(125, 475, 140, 30);
		previous.addActionListener(this);
		container.add(previous);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 600);
		this.setVisible(true);
		setLocationRelativeTo(null);
	}

	/**
	 * Action listener
	 */
	public void actionPerformed(ActionEvent e) {
		JButton eventSource = (JButton) e.getSource();
		if (eventSource.equals(button1)) {
			setVisible(false);
			new ChangePrice();
		} else if (eventSource.equals(button2)) {
			setVisible(false);
			new ModifyMenu();
		} else if (eventSource.equals(button3)) {
			setVisible(false);
			try {
				new OrderList();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (eventSource.equals(button4)) {
			setVisible(false);
			new AddOptions();
		} else if (eventSource.equals(previous)) {
			setVisible(false);
			new Welcome();
		}
	}

	public static void main(String[] args) {
		new FunctionPage();
	}
}

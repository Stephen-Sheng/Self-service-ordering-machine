

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
/**
 * Title 		: AddOptions.java
 * Description	: This class is the GUI for administrators to add new options.
 * @author		: Yang Hu 
 * @date      	: 11/5/2020
 */
@SuppressWarnings("serial")
public class AddOptions extends JFrame implements ActionListener {
	private JLabel hint, l1, l2;
	private String name, price;
	private JPanel textPanel, p1, p2, btnPanel;
	private JTextField textField1, textField2;
	private JButton b1, b2;
	
	AdministratorControl ac = new AdministratorControl();
	/**
	 * Build the GUI interface for adding menu options.
	 * */	
	public AddOptions() {
		setTitle("Add options");
		setSize(400, 600);
		setLayout(new BorderLayout());
		Container container = this.getContentPane();
		container.setBackground(new Color(250, 240, 215));
		
		Font hintFont = new Font("Cambria", Font.ITALIC+Font.BOLD, 20);
		Font titleFont = new Font("Cambria", Font.ITALIC+Font.BOLD, 24);
		Font textFont = new Font("Cambria", Font.ITALIC + Font.BOLD, 15);
		hint = new JLabel("<html>Please enter the name of the added options and the price!");
		hint.setFont(titleFont);
		hint.setForeground(new Color(105, 77, 76));
		hint.setBorder(new EmptyBorder(50, 30, 0, 20));

		/* Input textbox panel*/
		textPanel = new JPanel();
		textPanel.setBackground(new Color(250, 240, 215));
		textPanel.setLayout(new FlowLayout());
		textPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
		
		/*Set the input text fields.*/
		p1 = new JPanel();
		p1.setBackground(new Color(250, 240, 215));
		p2 = new JPanel();
		p2.setBackground(new Color(250, 240, 215));
		l1 = new JLabel("Option Name :");
		l2 = new JLabel("         Price :         ");
		l1.setFont(hintFont);
		l2.setFont(hintFont);
		l1.setForeground(new Color(105, 77, 76));
		l2.setForeground(new Color(105, 77, 76));
		textField1 = new JTextField(15);
		textField1.setBackground(Color.white);
		textField1.setForeground(new Color(105, 77, 76));
		textField1.setFont(textFont);
		textField1.setBounds(150, 150, 200, 30);
		textField2 = new JTextField(15);
		textField2.setBackground(Color.white);
		textField2.setForeground(new Color(105, 77, 76));
		textField2.setFont(textFont);
		textField2.setBounds(150, 150, 200, 30);
		p1.setLayout(new FlowLayout());
		p2.setLayout(new FlowLayout());
		p1.add(l1);
		p1.add(textField1);
		p2.add(l2);
		p2.add(textField2);
		textPanel.add(p1);
		textPanel.add(p2);
		
		/*Image Label.*/
		JLabel imageLabel = new JLabel();
		ImageIcon icon = new ImageIcon("Files/ramen.jpg");
		imageLabel.setIcon(icon);
		icon.setImage(icon.getImage().getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING));
		textPanel.add(imageLabel);

		/* Button panel*/
		btnPanel = new JPanel();
		btnPanel.setBackground(new Color(250, 240, 215));
		btnPanel.setLayout(new GridLayout(2, 1, 10, 10));
		b1 = new JButton("OK");
		b1.setBorderPainted(false);
		b1.setForeground(new Color(254, 245, 228));
		b1.setBackground(new Color(150, 122, 111));
		b1.setFont(hintFont);
		b2 = new JButton("Previous Page");
		b2.setBorderPainted(false);
		b2.setForeground(new Color(254, 245, 228));
		b2.setBackground(new Color(150, 122, 111));
		b2.setFont(hintFont);
		b1.addActionListener(this);
		b2.addActionListener(this);
		btnPanel.add(b1);
		btnPanel.add(b2);
		btnPanel.setBorder(new EmptyBorder(40, 40, 40, 40));

		getContentPane().add(hint, BorderLayout.NORTH);
		getContentPane().add(textPanel, BorderLayout.CENTER);
		getContentPane().add(btnPanel, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 600);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e) {
		JButton eventSource = (JButton) e.getSource();
		if (eventSource.equals(b1)) {
			if (ac.judgeTextF1(textField1.getText())==2||ac.judgeTextF2(textField2.getText())==2) {
				Object[] options = { "OK" };
				JOptionPane.showOptionDialog(null, "Empty input!", "Error Message", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			} else if (ac.judgeTextF2(textField2.getText())==1||ac.judgeTextF2(textField2.getText())==2) {
				Object[] options = { "OK" };
				JOptionPane.showOptionDialog(null, "The price must be entered as two decimal places or integer!", "Error Message", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			} else {
				name = textField1.getText();
				price = textField2.getText();
				Object[] options = { "OK" };
				JOptionPane.showOptionDialog(null, "Adding Completed!", "Adding Message",
						JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

				MenuManagement rwm = new MenuManagement();
				rwm.addItem(name, price);
				setVisible(false);
				new AddOptions();
			}
		} else if (eventSource.equals(b2)) {
			setVisible(false);
			new FunctionPage();
		}
	}
}

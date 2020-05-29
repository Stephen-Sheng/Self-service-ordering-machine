

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Title : ChangePrice.java 
 * Description: This class is the GUI for changing price.
 * 
 * @author : Yutong Sheng
 * @date : 8/5/2020
 */

@SuppressWarnings("serial")
public class ChangePrice extends JFrame implements ActionListener {
	private JFrame frame = new JFrame("Change Price"); //create a frame
	private JButton submit, previous, quit; // two buttons for two kinds of switches
	private JTextField soupPrice; // price input field of a single dish
	private ArrayList<String> price = new ArrayList<String>(); // price list of all add-ons
	private ArrayList<String> newprice = new ArrayList<String>(); // new price list of all add-ons
	private ArrayList<String> option = new ArrayList<String>(); // option names in menu
	private JTextField[] text; // price input field of all add-ons
	private int addonscount; // amount of add-ons
	private Menu m = new Menu();
	private MenuManagement rwm = new MenuManagement();

	/**
	 * Build the GUI interface for administrator to change price.
	 */
	public ChangePrice() {
		rwm.readMenu();
		price = m.getPrice();
		option = m.getOption();
		addonscount = m.getAddonsCount();
		
		Font titleFont = new Font("Cambria", Font.ITALIC+Font.BOLD, 15);
		Font f = new Font("Cambria", Font.ITALIC + Font.BOLD, 16);
		Font textFont = new Font("Cambria", Font.ITALIC + Font.BOLD, 15);
		
		Font btnFont = new Font("TimesRoman", Font.ITALIC + Font.BOLD, 15);
		text = new JTextField[addonscount];
		JLabel[] label = new JLabel[addonscount];
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(350, 170 + addonscount * 80));
		panel.setLayout(null);
		panel.setBackground(new  Color(250, 240, 215));
		
		// label and text field creating
		JLabel hint = new JLabel("The price must be two decimal places or integer.");
		hint.setFont(titleFont);
		hint.setForeground(new Color(105,77,76));
		hint.setBounds(20, 0, 400, 50);
		JLabel soup_l = new JLabel("Single dish :");
		soupPrice = new JTextField();
		soup_l.setFont(f);
		soup_l.setForeground(new Color(105, 77, 76));
		soup_l.setBounds(20, 50, 100, 50);
		soupPrice.setText(price.get(0));
		soupPrice.setBackground(Color.white);
		soupPrice.setForeground(new Color(105, 77, 76));
		soupPrice.setFont(textFont);
		soupPrice.setBounds(215, 60, 150, 30);
		panel.add(hint);
		panel.add(soupPrice);
		panel.add(soup_l);

		for (int i = 0; i < addonscount; i++) {
			label[i] = new JLabel(option.get(i + 7));
			label[i].setBounds(20, 120 + 80 * i, 200, 50);
			label[i].setFont(f);
			label[i].setForeground(new Color(105, 77, 76));
			panel.add(label[i]);
			text[i] = new JTextField();
			text[i].setBackground(Color.white);
			text[i].setForeground(new Color(105, 77, 76));
			text[i].setFont(textFont);
			text[i].setText(price.get(i + 7));
			text[i].setBounds(215, 130 + 80 * i, 150, 30);
			panel.add(text[i]);
		}

		// button creating
		previous = new JButton("Previous Page");
		previous.setFont(btnFont);
		previous.setBorderPainted(false);
		previous.setForeground(new Color(254, 245, 228));
		previous.setBackground(new Color(150, 122, 111));
		submit = new JButton("Submit");
		submit.setFont(btnFont);
		submit.setBorderPainted(false);
		submit.setForeground(new Color(254, 245, 228));
		submit.setBackground(new Color(150, 122, 111));
		quit = new JButton("Exit");
		quit.setFont(btnFont);
		quit.setBorderPainted(false);
		quit.setForeground(new Color(254, 245, 228));
		quit.setBackground(new Color(150, 122, 111));
		previous.setBounds(5, 130 + (addonscount * 80), 140, 30);
		submit.setBounds(150, 130 + (addonscount * 80), 110, 30);
		quit.setBounds(265, 130 + (addonscount * 80), 110, 30);
		panel.add(previous);
		panel.add(submit);
		panel.add(quit);
		previous.addActionListener(this);
		submit.addActionListener(this);
		quit.addActionListener(this);

		// ScrollPane creating
		if (addonscount * 110 > 600) {
			JScrollPane scrollPane = new JScrollPane(panel);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			frame.add(scrollPane);
		} else {
			frame.add(panel);
		}

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 600);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	/**
	 * Action listener
	 * @param e Action event
	 */
	public void actionPerformed(ActionEvent e) {
		JButton eventSource = (JButton) e.getSource();
		if (eventSource.equals(previous)) {
			frame.setVisible(false);
			new FunctionPage();
		} else if (eventSource.equals(submit)) {
			ArrayList<String> format = new ArrayList<String>();
			if (!soupPrice.getText().matches("0?[0-9]+(\\.?[0-9]{2}|)")) {
				format.add("wrong");
				Object[] options = { "OK" };
				JOptionPane.showOptionDialog(null, "Soup price is not right format", "Error Input",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			} else {
				for (int j = 0; j < addonscount; j++) {
					if (!text[j].getText().matches("0?[0-9]+(\\.?[0-9]{2}|)")) {
						format.add("wrong");
						Object[] options = { "OK" };
						JOptionPane.showOptionDialog(null, option.get(j + 7) + " price is not right format",
								"Error Input", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
								options[0]);
					}
				}
				if (!format.contains("wrong")) {
					newprice.add(soupPrice.getText());
					for (int i = 0; i < 6; i++) {
						newprice.add("0");
					}
					for (int i = 0; i < addonscount; i++) {
						newprice.add(text[i].getText());
					}
					m.setPrice(newprice);
					rwm.writeMenu();
					Object[] options = { "OK" };
					JOptionPane.showOptionDialog(null, "Changing Completed!", "Changing Price",
							JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
					frame.setVisible(false);
					new ChangePrice();
				}
			}
		} else if(eventSource.equals(quit)) {
			frame.setVisible(false);
			System.exit(0);
		}
	}
}

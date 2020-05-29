import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Title : ModifyMenu.java 
 * Description: This class is the GUI for modifying menu information
 * 
 * @author : Jiaqi Luo
 * @date : 8/5/2020
 */

@SuppressWarnings("serial")
public class ModifyMenu extends JFrame implements ActionListener {
	private JFrame frame = new JFrame("Modify Menu"); // create a frame
	private JButton submit, previous, exit; // two buttons for two kinds of switches
	private ArrayList<String> option; // option names of menu
	private JRadioButton[] rbutton = new JRadioButton[3]; // radio buttons for three flavors of soup
	private JComboBox<?>[] box; // a variable number of add-on options
	private ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>(); // option contents of menu
	private String[] originaloptoins = new String[3]; // three fixed flavors of soup
	private int addonscount; // the amount of add-ons
	private Menu m = new Menu();
	MenuManagement rwm = new MenuManagement();

	/**
	 * Build the GUI interface for administrator to modify menu information.
	 */
	public ModifyMenu() {
		rwm.readMenu();
		option = m.getOption();
		list = m.getContent();
		originaloptoins = rwm.getOriginalContent();
		addonscount = m.getAddonsCount();
		
		JLabel[] label2 = new JLabel[addonscount];
		boolean[] state1 = new boolean[3];
		String[] state2 = new String[m.getAddonsCount()];
		String[] operation1 = { "available", "unavailable", "delete" };
		String[] operation2 = { "available", "unavailable" };

		if (list.get(0).contains("Tonkotsu")) {
			state1[0] = true;
		}
		if (list.get(0).contains("Shoyu")) {
			state1[1] = true;
		}
		if (list.get(0).contains("Shio")) {
			state1[2] = true;
		}

		for (int i = 7; i < list.size(); i++) {
			if (list.get(i).contains("enough")) {
				state2[i - 7] = "available";
			} else if (list.get(i).contains("null")) {
				state2[i - 7] = "unavailable";
			}
		}

		// panel creating
		JPanel panel = new JPanel();
		panel.setBackground(new  Color(250, 240, 215));
		panel.setPreferredSize(new Dimension(350, 240 + addonscount * 70));
		panel.setLayout(null);

		Font textFont = new Font("Cambria", Font.ITALIC + Font.BOLD, 16);
		Font btnFont = new Font("TimesRoman", Font.ITALIC + Font.BOLD, 13);

		// label, button and comboBox creating
		JLabel label = new JLabel("The chosen button refers to avaliable option.");
		label.setFont(textFont);
		//label.set
		label.setBounds(20, 0, 400, 50);
		panel.add(label);

		JLabel label1 = new JLabel(option.get(0));
		label1.setForeground(new Color(105, 77, 76));
		label1.setBounds(30, 70, 250, 40);
		label1.setFont(textFont);
		panel.add(label1);
		for (int j = 0; j < 3; j++) {
			rbutton[j] = new JRadioButton(originaloptoins[j]);
			rbutton[j].setBackground(new Color(250, 240, 215));
			rbutton[j].setForeground(new Color(105, 77, 76));
			rbutton[j].setFont(textFont);
			rbutton[j].setSelected(state1[j]);
			rbutton[j].setBounds(30 + (j * 120), 110, 100, 40);
			panel.add(rbutton[j]);
		}

		box = new JComboBox[m.getAddonsCount()];
		for (int k = 0; k < addonscount; k++) {
			label2[k] = new JLabel(option.get(k + 7));
			label2[k].setBackground(new Color(250, 240, 215));
			label2[k].setForeground(new Color(105, 77, 76));
			label2[k].setFont(textFont);
			label2[k].setBounds(30, 180 + (k * 70), 250, 40);
			panel.add(label2[k]);
			if(option.get(k + 7).contains("Extra"))
				box[k] = new JComboBox<>(operation2);
			else
				box[k] = new JComboBox<>(operation1);
			box[k].setFont(textFont);
			box[k].setBackground(Color.white);
			box[k].setSelectedItem(state2[k]);
			box[k].setBounds(230, 180 + (k * 70), 120, 40);
			panel.add(box[k]);
		}

		previous = new JButton("Previous");
		previous.setFont(btnFont);
		previous.setBorderPainted(false);
		previous.setForeground(new Color(254, 245, 228));
		previous.setBackground(new Color(150, 122, 111));
		submit = new JButton("Submit");
		submit.setFont(btnFont);
		submit.setBorderPainted(false);
		submit.setForeground(new Color(254, 245, 228));
		submit.setBackground(new Color(150, 122, 111));
		exit = new JButton("Exit");
		exit.setFont(btnFont);
		exit.setBorderPainted(false);
		exit.setForeground(new Color(254, 245, 228));
		exit.setBackground(new Color(150, 122, 111));
		previous.setBounds(30, 180 + (addonscount * 70), 100, 30);
		submit.setBounds(140, 180 + (addonscount * 70), 100, 30);
		exit.setBounds(250, 180 + (addonscount * 70), 100, 30);
		panel.add(previous);
		panel.add(submit);
		panel.add(exit);
		previous.addActionListener(this);
		submit.addActionListener(this);
		exit.addActionListener(this);

		// scrollPane creating
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
		originaloptoins = rwm.getOriginalContent();
		if (eventSource.equals(submit)) {
			ArrayList<ArrayList<String>> newlist = new ArrayList<ArrayList<String>>();
			ArrayList<String> array = new ArrayList<String>(); // options of Soup
			ArrayList<String> array1 = new ArrayList<String>(); // option only has "No"
			array1.add("No");
			ArrayList<String> array2 = new ArrayList<String>(); // option has "Yes" and "No"
			array2.add("Yes");
			array2.add("No");
			for (int b = 0; b < 3; b++) {
				if (rbutton[b].isSelected()) {
					array.add(originaloptoins[b]);
				}
			}
			newlist.add(array); // add options of soup
			newlist.add(list.get(1)); // add options of noodles
			newlist.add(list.get(2)); // add options of spring onion
			// add option of  nori
			if (box[0].getSelectedItem().toString().equals("available")) {
				newlist.add(array2);
			} else if(box[0].getSelectedItem().toString().equals("unavailable")) {
				newlist.add(array1);
			}
			// add option of chashu
			if (box[3].getSelectedItem().toString().equals("available")) {
				newlist.add(array2);
			} else if(box[3].getSelectedItem().toString().equals("unavailable")) {
				newlist.add(array1);
			}
			// add option of boiled egg
			if (box[1].getSelectedItem().toString().equals("available")) {
				newlist.add(array2);
			} else if(box[1].getSelectedItem().toString().equals("unavailable")) {
				newlist.add(array1);
			}
			newlist.add(list.get(6)); // add options of spiciness
			// add state (available or unavailable) of add-ons
			for (int i = 0; i < addonscount; i++) {
				ArrayList<String> contents = new ArrayList<String>();
				if(box[i].getSelectedItem().toString().equals("available")) {
					contents.add("enough");
					newlist.add(contents);
				} else if(box[i].getSelectedItem().toString().equals("unavailable")) {
					contents.add("null");
					newlist.add(contents);
				} else if(box[i].getSelectedItem().toString().equals("delete")) {
					m.getOption().remove(i+7);
					m.getPrice().remove(i+7);
				}
			}
			m.setContent(newlist);
			rwm.writeMenu();
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Modifying Completed!", "Modifying Message",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		} else if (eventSource.equals(previous)) {
			frame.setVisible(false);
			new FunctionPage();
		} else if(eventSource.equals(exit)) {
			frame.setVisible(false);
			System.exit(0);
		}
	}
}

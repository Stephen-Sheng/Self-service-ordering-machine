import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Title : OrderPageOne.java 
 * Description: This class is fixed food ordering page
 * 
 * @author : Shuhan Guo
 * @author : Jiaqi Luo
 * @date : 8/5/2020
 */
@SuppressWarnings("serial")
public class OrderPageTwoAddons extends JFrame {
	private JFrame frame = new JFrame("Please select your add-ons"); // Frame creating
	private JButton submit, previous; // Two buttons for two kinds of switches
	private JComboBox<?>[] box; // a variable number of add-on options
	private static int time = 0;
	private Menu m = new Menu();
	private Order o = new Order();
	private OrderManagement order = new OrderManagement();

	/**
	 * Build the GUI interface for the add-ons menu
	 */
	public OrderPageTwoAddons() {
		order.readOrder();
		ArrayList<String> option = o.getOption();
		ArrayList<ArrayList<String>> state = m.getContent();
		String[] quantity = { "0", "1", "2", "3", "4", "5" };
		String[] zero = { "0" };
		int addonscount = m.getAddonsCount();

		// Frame settings
		Font f1 = new Font("TimesRoman", Font.ITALIC + Font.BOLD, 15);
		Font textFont = new Font("Cambria", Font.ITALIC + Font.BOLD, 20);
		Font textFont1 = new Font("Cambria", Font.ITALIC + Font.BOLD, 15);
		Font btnFont = new Font("TimesRoman", Font.ITALIC + Font.BOLD, 15);
		
		frame.setSize(400, 600);
		Container container = frame.getContentPane();
		container.setBackground(new Color(250, 240, 215));
		
		// Panel creating
		JPanel panel = new JPanel();
		panel.setBackground(new Color(250, 240, 215));
		frame.add(panel);

		// Label and ComboBox creating
		JLabel label = new JLabel("If there is only \"0\" option, it means no stock");
		label.setForeground(new Color(105, 77, 76));
		label.setBounds(30, 0, 350, 30);
		label.setFont(f1);
		panel.add(label);
		
		JLabel[] labels = new JLabel[option.size() - 7];
		box = new JComboBox[option.size() - 7];
		
		for (int i = 0; i < option.size() - 7; i++) {
			String[] splitline = option.get(i + 7).split("]");
			labels[i] = new JLabel(splitline[1]);
			labels[i].setForeground(new Color(105, 77, 76));
			labels[i].setFont(textFont);
			labels[i].setBounds(65, 50 + 90 * i, 200, 50);
			panel.add(labels[i]);
			if (state.get(i + 7).contains("enough")) {
				box[i] = new JComboBox<>(quantity);
				box[i].setBackground(Color.white);
				box[i].setForeground(new Color(105, 77, 76));
				box[i].setFont(textFont1);
				box[i].setSelectedItem(o.getOptionalOrderContent().get(i));
			} else {
				box[i] = new JComboBox<>(zero);
				box[i].setBackground(Color.white);
				box[i].setForeground(new Color(105, 77, 76));
				box[i].setFont(textFont1);
			}
			box[i].setBounds(65, 100 + 90 * i, 250, 30);
			panel.add(box[i]);
		}
		
		panel.setPreferredSize(new Dimension(350, 110 + addonscount * 90));
		panel.setLayout(null);

		// scrollPane creating
		if (110 + addonscount * 90 > 600) {
			JScrollPane scrollPane = new JScrollPane(panel);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			frame.add(scrollPane);
		} else {
			frame.add(panel);
		}
		
		// Button creating
		previous = new JButton("Previous page");
		previous.setFont(btnFont);
		previous.setBorderPainted(false);
		previous.setForeground(new Color(254, 245, 228));
		previous.setBackground(new Color(150, 122, 111));
		submit = new JButton("Submit");
		submit.setFont(btnFont);
		submit.setBorderPainted(false);
		submit.setForeground(new Color(254, 245, 228));
		submit.setBackground(new Color(150, 122, 111));
		previous.setBounds(30, 70 + (addonscount * 90), 150, 30);
		submit.setBounds(200, 70 + (addonscount * 90), 150, 30);
		panel.add(previous);
		panel.add(submit);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> newContent = new ArrayList<String>();
				for (int i = 0; i < addonscount; i++) {
					newContent.add(box[i].getSelectedItem().toString());
				}
				o.setContentTwo(newContent);
				order.printOrder();
				frame.setVisible(false);
				String userid = order.getUserid();
				if (userid != null || time != 0) {
					new CheckOrder();
				} else {
					new EatinOrTakeaway();
				}
				time++;
			}
		});
		previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new OrderPageOne();
			}
		});
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}

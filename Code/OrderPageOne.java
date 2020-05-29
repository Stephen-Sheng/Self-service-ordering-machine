import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.*;
import java.awt.event.*;

/**
 * Title : OrderPageOne.java 
 * Description: This class is fixed food ordering page
 * 
 * @author : Shuhan Guo
 * @author : Jiaqi Luo
 * @date : 8/5/2020
 */
@SuppressWarnings("serial")
public class OrderPageOne extends JFrame{
	private JButton submit, previous; // Three buttons for three kinds of switches
	private JComboBox<?>[] box = new JComboBox[7]; // Seven fixed options need to be chosen
	private Menu m = new Menu();
	private Order o = new Order();
	private OrderManagement order = new OrderManagement();

	/**
	 * Build the GUI interface for the fixed menu
	 */
	public OrderPageOne() {
		Font f1 = new Font("TimesRoman", Font.ITALIC + Font.BOLD, 10);
		Font textFont = new Font("Cambria", Font.ITALIC + Font.BOLD, 20);
		Font textFont1 = new Font("Cambria", Font.ITALIC + Font.BOLD, 15);
		Font btnFont = new Font("TimesRoman", Font.ITALIC + Font.BOLD, 15);
		
		// Frame settings
		Container container = this.getContentPane();
		container.setBackground(new Color(250, 240, 215));
		setTitle("Please check your orders:");
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 600);
		this.setVisible(true);
		setLocationRelativeTo(null);
		
		// Get chosen options
		order.readOrder();
		ArrayList<String> content = new ArrayList<String>();
		content = o.getContent();

		// Panel creating
		JPanel panel = new JPanel();
		container.add(panel);
		panel.setSize(380, 30);
		panel.setLocation(3, 500);
		panel.setLayout(new GridLayout(1, 2, 20, 20));
		panel.setBorder(new EmptyBorder(0,30,0,30));

		// Label creating
		JLabel label1 = new JLabel("Each option has a default value. If you don't select one, we will 		set the");
		label1.setForeground(new Color(105, 77, 76));
		label1.setBounds(20, 0, 400, 15);
		label1.setFont(f1);
		container.add(label1);
		JLabel label2 = new JLabel("default value automatically. If there is only\"No\" option, it 		means no stock.");
		label2.setForeground(new Color(105, 77, 76));
		label2.setBounds(16, 10, 400, 15);
		label2.setFont(f1);
		container.add(label2);

		JLabel[] labels = new JLabel[7];
		labels[0] = new JLabel("Soup");
		labels[1] = new JLabel("Noodles");
		labels[2] = new JLabel("Spring onion");
		labels[3] = new JLabel("Nori");
		labels[4] = new JLabel("Chashu");
		labels[5] = new JLabel("Boiled egg");
		labels[6] = new JLabel("Spiciness");
		for (int i = 0; i < 7; i++) {
			labels[i].setBackground(Color.BLACK);
			labels[i].setFont(textFont);
			labels[i].setForeground(new Color(105, 77, 76));
			labels[i].setBounds(65, (18 + i * 65), 130, 50);
			container.add(labels[i]);
		}

		// ComboBox creating		
		ArrayList<ArrayList<String>> list = m.getContent();
		String spicinessoption = "0(No)/1/2/3/4/5(Max)";
		ArrayList<String> spicinessop = new 	ArrayList<String>(Arrays.asList(spicinessoption.split("/")));
		list.add(spicinessop);
		for (int j = 0; j < 7; j++) {
			box[j] = new JComboBox<>((String[]) list.get(j).toArray(new String[list.get(j).size()]));
			box[j].setBackground(Color.white);
			box[j].setForeground(new Color(105, 77, 76));
			box[j].setFont(textFont1);
			box[j].setSelectedItem(content.get(j));
			box[j].setBounds(65, (55 + j * 65), 250, 30);
			container.add(box[j]);
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
		panel.add(previous);
		panel.add(submit);
		panel.setBackground(new Color(250, 240, 215));
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> newContent = new ArrayList<String>();
				for (int i = 0; i < 7; i++) {
					newContent.add(box[i].getSelectedItem().toString());
				}
				o.setContentOne(newContent);
				order.printOrder();
				setVisible(false);
				new OrderPageTwoAddons();
			}
		});
		previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Welcome();
			}
		});
	}
}

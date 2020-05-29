

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * Title : CheckOrder.java 
 * Description : This class is the GUI for administrators to check orders.
 * 
 * @author : Yang Hu
 * @date : 12/5/2020
 */

@SuppressWarnings("serial")
public class CheckOrder extends JFrame {
	private ArrayList<String> singlePri = new ArrayList<String>();
	private JButton button1, button2;
	private LoginControl lc = new LoginControl();
	private User u = new User();

	/**
	 * Build the GUI interface for checking order.
	 */
	public CheckOrder() {
		Font titleFont = new Font("Times New Roman", Font.ITALIC, 40);
		Font buttonFont = new Font("Cambria", Font.ITALIC+Font.BOLD, 18);
		Font messageFont = new Font("Cambria", Font.PLAIN, 20);
		Menu m = new Menu();
		Order o = new Order();
		/* Get the single price of the add-ons, the basic price and the user ID. */
		for (int i = 0; i < m.getPrice().size() - 7; i++) {
			singlePri.add(m.getPrice().get(i + 7));
		}
		double soupprice = Double.parseDouble(m.getPrice().get(0));
		u = lc.getUser();
		
		/* Construct the GUI for checking order. */
		setSize(400, 600);
		setTitle("Check your order");
		JPanel panel = new JPanel();
		panel.setBackground(new Color(250,240,215));
		panel.setLayout(null);
		add(panel);
		
		JLabel titleLabel = new JLabel("Your Order");
		titleLabel.setForeground(new Color(105,77,76));
		titleLabel.setFont(titleFont);
		titleLabel.setBounds(100, 0, 200, 50);
		panel.add(titleLabel);
		
		/* Add fixed-items to the interface one by one.*/
		JLabel head1 = new JLabel(
				"<html><p style='font-family:Times New Roman;font-size:20px;font-style:italic;'>Fixed item (price:"
						+ soupprice + "):</p>");
		head1.setForeground(new Color(105,77,76));
		head1.setBounds(30,50,300,25);
		panel.add(head1);
		for (int i = 0; i < o.getFixedOrderOption().size(); i++) {
			JLabel food1 = new JLabel("<html>&nbsp &nbsp &nbsp " + o.getFixedOrderOption().get(i) + ":"
					+ o.getFixedOrderContent().get(i) + "</html>");
			food1.setBounds(30, 85+i*35, 400, 25);
			food1.setFont(messageFont);
			food1.setForeground(new Color(105,77,76));
			panel.add(food1);
		}
		
		/* Add add-ons to the interface one by one. */
		JLabel head2 = new JLabel(
				"<html><p style='font-family:Times New Roman;font-size:20px;font-style:italic;'>Add-ons (price*number):</p>");
		head2.setForeground(new Color(105,77,76));
		head2.setBounds(30, 330, 400, 25);
		panel.add(head2);
		int addonscount=0;
		for (int i = 0; i < o.getOptionalOrderOption().size(); i++) {
			if (!(o.getOptionalOrderContent().get(i).equals("0"))) {
				JLabel food2 = new JLabel("<html>&nbsp &nbsp &nbsp " + o.getOptionalOrderOption().get(i) + " "
						+ singlePri.get(i) + "*" + o.getOptionalOrderContent().get(i) + "</html>");
				food2.setBounds(30, 365+addonscount*35, 400, 25);
				food2.setForeground(new Color(105,77,76));
				food2.setFont(messageFont);
				panel.add(food2);
				addonscount++;
			}
		}
		
		/* Set the button panel. */
		button1 = new JButton("Pay");
		button2 = new JButton("Back to Order");
		button1.setFont(buttonFont);
		button2.setFont(buttonFont);
		button1.setBorderPainted(false);
		button1.setForeground(new Color(254,245,228));
		button1.setBackground(new Color(150,122,111));
		button2.setBorderPainted(false);
		button2.setForeground(new Color(254,245,228));
		button2.setBackground(new Color(150,122,111));
		
		Dimension preferredSize = new Dimension(100, 40);
		button1.setPreferredSize(preferredSize);
		button2.setPreferredSize(preferredSize);
		//Calculate the location of the buttons.
		if(addonscount * 35>200) {
			button1.setBounds(30,365+addonscount*35,150,30);
			button2.setBounds(200,365+addonscount*35,150,30);
		} else {
			button1.setBounds(30,500,150,30);
			button2.setBounds(200,500,150,30);
		}
		panel.add(button1);
		panel.add(button2);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				PaymentPageControl pc = new PaymentPageControl();
				pc.payment(Integer.parseInt(u.getStamp()));
			}
		});
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new OrderPageOne();
			}
		});
		
		// If the number of dishes is larger than the defined GUI can accommodate, a scroll bar is generated.
		panel.setPreferredSize(new Dimension(350, 400 + addonscount * 35));
		if(400 + addonscount * 35>600) {
			JScrollPane scrollPane = new JScrollPane(panel);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setBorder(null);
			add(scrollPane);
		} else {
			add(panel);
		}
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}

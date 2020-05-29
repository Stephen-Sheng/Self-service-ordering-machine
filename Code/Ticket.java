import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Title : Ticket.java 
 * Description : This class is the GUI of the ticket page.
 * @author : Yang Hu
 * @date : 12/5/2020
 */
@SuppressWarnings("serial")
public class Ticket extends JFrame {
	private JButton button;

	/**
	 * Build the GUI interface for the ticket.
	 */
	public Ticket() {
		// Set the general font
		Font titleFont = new Font("Times New Roman", Font.ITALIC, 40);
		Font messageFont = new Font("Cambria", Font.PLAIN, 20);
		Font buttonFont = new Font("Cambria", Font.ITALIC+Font.BOLD, 25);

		Order o = new Order();
		setSize(400, 600);
		setTitle("Ticket");
		
		/* Construct the GUI for checking order. */
		JPanel panel = new JPanel();
		panel.setBackground(new Color(250,240,215));
		panel.setLayout(null);
		add(panel);
		
		JLabel titleLabel = new JLabel("Ticket", JLabel.CENTER);
		titleLabel.setForeground(new Color(105,77,76));
		titleLabel.setFont(titleFont);
		JLabel border = new JLabel(
				"-------------------------------------------" + "--------------------------------------------------",
				JLabel.CENTER);
		border.setForeground(new Color(105,77,76));
		titleLabel.setBounds(150, 0, 100, 50);
		panel.add(titleLabel);
		/*Add fixed-items to the interface one by one.*/
		JLabel head1 = new JLabel(
				"<html><p style='font-family:Times New Roman;font-size:20px;font-style:italic;'>Fixed item:</p>");
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
		/*Add add-ons to the interface one by one.*/
		JLabel head2 = new JLabel(
				"<html><p style='font-family:Times New Roman;font-size:20px;font-style:italic;'>Add-ons:</p>");
		head2.setForeground(new Color(105,77,76));
		head2.setBounds(30, 330, 400, 25);
		panel.add(head2);
		int addonscount=0;
		for (int i = 0; i < o.getOptionalOrderOption().size(); i++) {
			if (!(o.getOptionalOrderContent().get(i).equals("0"))) {
				JLabel food2 = new JLabel("<html>&nbsp &nbsp &nbsp " + o.getOptionalOrderOption().get(i) + " :"
						+ o.getOptionalOrderContent().get(i) + "</html>");
				food2.setBounds(30, 365+addonscount*35, 400, 25);
				food2.setForeground(new Color(105,77,76));
				food2.setFont(messageFont);
				panel.add(food2);
				addonscount++;
			}
		}
		
		/* Set the button panel. */
		button = new JButton("OK");
		button.setBorderPainted(false);
		button.setForeground(new Color(254,245,228));
		button.setBackground(new Color(150,122,111));
		button.setFont(buttonFont);
		Dimension preferredSize = new Dimension(150, 30);
		button.setPreferredSize(preferredSize);
		//Calculate the location of the buttons.
		if(addonscount * 35>200) {
			button.setBounds(130,365+addonscount*35,140,30);
		} else {
			button.setBounds(130,500,140,30);
		}
		panel.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				System.exit(0);
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

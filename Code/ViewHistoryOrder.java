

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Title : ViewHistoryOrder.java 
 * Description: This class is the GUI for client viewing history orders.
 * 
 * @author : Jiaqi Luo
 * @date : 8/5/2020
 */

@SuppressWarnings("serial")
public class ViewHistoryOrder extends JFrame {
	private JButton back; // button for switching to last page
	private OrderCollectionManagement ocm = new OrderCollectionManagement();
	private LoginControl lc = new LoginControl();

	/**
	 * Build the GUI for client viewing history orders
	 */
	public ViewHistoryOrder() {
		Font btnFont = new Font("Cambria", Font.BOLD, 20);
		Font titleFont = new Font("Cambria", Font.ITALIC + Font.BOLD, 23);
		Font textFont = new Font("Cambria", Font.BOLD, 15);
		
		this.setTitle("Previous Orders");
		this.setLayout(null);
		this.setSize(400, 600);

		// label creating
		JLabel label = new JLabel("These are your history orders.");
		Container container = this.getContentPane();
		container.setBackground(new Color(250, 240, 215));
		label.setFont(titleFont);
		label.setForeground(new Color(105, 77, 76));
		label.setBounds(50, 50, 350, 50);
		container.add(label);
		ocm.readUserOrderCollection(lc.getUser().getID());
		ArrayList<String> orders = new ArrayList<String>();
		orders = ocm.getAllOrders();
		String text = "";
		for (int i = 0; i < orders.size(); i++) {
			text = text + orders.get(i);
		}
		
		// textPane and scrollPane creating
		JTextPane textPane = new JTextPane();
		textPane.setText(text);
		textPane.setForeground(new Color(105, 77, 76));
		textPane.setFont(textFont);
		textPane.setEditable(false);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(textPane);
		scrollPane.setBounds(50, 200, 300, 300);
		container.add(scrollPane, BorderLayout.CENTER);

		// button creating
		back = new JButton("back to ViewAccount");
		back.setFont(btnFont);
		back.setBorderPainted(false);
		back.setForeground(new Color(254, 245, 228));
		back.setBackground(new Color(150, 122, 111));
		back.setBounds(75, 120, 250, 30);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ViewAccount();
			}
		});
		container.add(back);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
}

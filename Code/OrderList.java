import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Title : OrderList.java 
 * Description : This class contains the definition of
 * the interface of presenting previous orders to the manager and calculate the
 * total orders.
 * @author : Shuhan Guo
 * @author : zlx
 * @version :2.0
 * 
 */
@SuppressWarnings("serial")
public class OrderList extends JFrame {
	private String text = "";
	private String total = "";
	
	public OrderList() {
		this.setLayout(null);
		this.setTitle("Previous orders");
		Container container = this.getContentPane();
		container.setBackground(new Color(250, 240, 215));
		setSize(400, 600);
		
		Font titleFont = new Font("Cambria", Font.ITALIC+Font.BOLD, 24);
		Font hintFont = new Font("Cambria", Font.ITALIC+Font.BOLD, 18);
		Font textFont = new Font("Cambria", Font.BOLD, 15);
		
		// declaration of planes and configuration
		JPanel upper = new JPanel();												// plane contains title(label_1)
		JPanel middle = new JPanel();												// plane contains two ranking button(button_spic, button_dishes)
		JPanel lower = new JPanel();												// plane contains subtitle(label_w)
		JPanel lowest = new JPanel();												// plane contains back button(back)
		upper.setSize(400, 70);
		upper.setBackground(new Color(250, 240, 215));
		upper.setLocation(0, 30);
		lower.setSize(400, 100);
		lower.setBackground(new Color(250, 240, 215));
		lower.setLocation(0, 100);
		middle.setBackground(new Color(250, 240, 215));
		lowest.setBackground(new Color(250, 240, 215));
		getContentPane().add(upper);
		getContentPane().add(middle);
		getContentPane().add(lower);
		getContentPane().add(lowest);
		JScrollPane scrollPane = new JScrollPane();									// scroll plane contains text plane(textPane)
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		scrollPane.setSize(300, 300);
		scrollPane.setLocation(50, 200);
		JTextPane textPane = new JTextPane();										// text plane embedded in scroll plane displays information of orders in previous week
		
		// components declaration
		JLabel label_1 = new JLabel("Previous Orders");				// main title
		JLabel label_2 = new JLabel("hlo");							// subtitle
		label_1.setForeground(new Color(105, 77, 76));
		label_1.setBounds(130, 20, 200, 50);
		label_1.setFont(titleFont);
		label_2.setForeground(new Color(105, 77, 76));
		label_2.setSize(400, 100);
		label_2.setFont(hintFont);
		upper.add(label_1);
		lower.add(label_2);
		middle.setSize(400, 50);
		middle.setLocation(0, 150);
		JButton button_spic = new JButton("Spiciness Ranking");						// jump to spiciness ranking page
		button_spic.setBorderPainted(false);
		button_spic.setForeground(new Color(254, 245, 228));
		button_spic.setBackground(new Color(150, 122, 111));
		button_spic.setFont(hintFont);
		JButton button_dishes = new JButton("Popular Dishes");						// jump to popular dishes page
		button_dishes.setBorderPainted(false);
		button_dishes.setForeground(new Color(254, 245, 228));
		button_dishes.setBackground(new Color(150, 122, 111));
		button_dishes.setFont(hintFont);
		JButton back = new JButton("Previous Page");	
		back.setBorderPainted(false);
		back.setForeground(new Color(254, 245, 228));
		back.setBackground(new Color(150, 122, 111));
		back.setFont(hintFont);// return button
		middle.add(button_spic);
		middle.add(button_dishes);
		button_spic.setLocation(300, 0);
		button_dishes.setLocation(100, 0);
		lowest.add(back);
		lowest.setSize(400, 100);
		lowest.setLocation(0, 500);
		back.setSize(100, 30);
		back.setLocation(0, 0);
						
		/*
		 * This part call the class: OrderCollectionManagement to obtain its data.
		 * @param	orders
		 * the data of last week orders in an array list	
		 * 
		 */		
		OrderCollectionManagement orderCollection = new OrderCollectionManagement();
		orderCollection.readOrderCollection(); 
		ArrayList<String> orders = new ArrayList<String>();
		orders = orderCollection.getList();
		// write the obtained order that meets the conditions to the scroll plane
		for (int i = 0; i < (orders.size() - 1); i++)
			text = text + orders.get(i);		
		textPane.setText(text);
		textPane.setForeground(new Color(105, 77, 76));
		textPane.setFont(textFont);
		textPane.setEditable(false);
		total = "Total order: " + orders.get(orders.size() - 1)+" ";
		label_2.setText(total);
		scrollPane.setViewportView(textPane);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		/*
		 * This method is called when clicked and jumps to RankSpiciness page
		 * @param	e
		 * the click event is triggered
		 * 
		 */
		button_spic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Jump to sort page
				setVisible(false);
				new RankSpiciness();
			}
		});
		
		/*
		 * This method is called when clicked and jumps to RankDishes page 
		 * @param	e	
		 * the click event is triggered
		 * 
		 */
		button_dishes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ViewReport();
			}
		});
		
		/*
		 * This method is called when clicked and jumps back to Function page
		 * @param	e
		 * the click event is triggered
		 * 
		 */
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new FunctionPage();
			}
		});
	}
}

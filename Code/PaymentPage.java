

import java.awt.*;
import java.text.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * Title : PaymentPage.java 
 * Description : This class is the GUI of the first kind of payment page.
 * 
 * @author : Yang Hu
 * @date : 12/5/2020
 */
@SuppressWarnings("serial")
public class PaymentPage extends JDialog {
	private JButton cash, card;
	private String imagePath = "Files/totoro.png";
	private PaymentPageControl pc = new PaymentPageControl();

	/**
	 * Build the GUI interface for the first type of payment page.
	 */
	public PaymentPage() {
		Font font1 = new Font("Cambria", Font.ITALIC+Font.BOLD, 25);
		Font titleFont = new Font("Times New Roman", Font.ITALIC+Font.BOLD, 40);
		Font hintFont = new Font("Times New Roman", Font.ITALIC, 20);

		Order o = new Order();
		DecimalFormat df = new DecimalFormat("#.00");
		String totalPrice = df.format(o.getTotalPrcie());
		setTitle("Payment Page");
		setSize(400, 600);
		getContentPane().setLayout(new BorderLayout());
		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(new Color(250,240,215));
		contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPanel.setLayout(new BorderLayout());
		// Center the window on the screen.
		setLocationRelativeTo(null);

		/* Set the title panel. */
		JPanel titlePanel = new JPanel(); // panel to contain title
		titlePanel.setBorder(new EmptyBorder(20, 20, 0, 20));
		titlePanel.setBackground(new Color(250,240,215));
		JLabel titleLabel = new JLabel("Totoro Ramen ", JLabel.CENTER);
		titleLabel.setForeground(new Color(105,77,76));
		titleLabel.setFont(titleFont);
		JLabel border = new JLabel(
				"------------------------------------------------------" + "-------------------------------",
				JLabel.CENTER);
		border.setForeground(new Color(105,77,76));
		titlePanel.setLayout(new BorderLayout());
		titlePanel.add(titleLabel, BorderLayout.NORTH);
		titlePanel.add(border, BorderLayout.CENTER);
		getContentPane().add(titlePanel, BorderLayout.NORTH);

		/* Set the price panel. */
		JPanel pricePanel = new JPanel();
		pricePanel.setBackground(new Color(250,240,215));
		JLabel priceLabel = new JLabel("Total Price : " + totalPrice);
		priceLabel.setFont(font1);
		priceLabel.setForeground(new Color(105,77,76));
		priceLabel.setBorder(new EmptyBorder(10, 80, 10, 10));
		JLabel imageLabel = new JLabel();
		ImageIcon icon = new ImageIcon(imagePath);
		imageLabel.setIcon(icon);
		icon.setImage(icon.getImage().getScaledInstance(250, 250, Image.SCALE_AREA_AVERAGING));
		imageLabel.setBorder(new EmptyBorder(10, 45, 10, 10));
		pricePanel.setLayout(new BorderLayout());
		pricePanel.add(imageLabel, BorderLayout.NORTH);
		pricePanel.add(priceLabel, BorderLayout.CENTER);

		/* Set the button panel. */
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(250,240,215));
		card = new JButton("Card");
		card.setBorderPainted(false);
		card.setForeground(new Color(254,245,228));
		card.setBackground(new Color(150,122,111));
		card.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.judge1();
				setVisible(false);
				new Ticket();
			}
		});

		cash = new JButton("Cash");
		cash.setBorderPainted(false);
		cash.setForeground(new Color(254,245,228));
		cash.setBackground(new Color(150,122,111));
		cash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.judge1();
				setVisible(false);
				new Ticket();
			}
		});
		card.setSize(200, 50);
		cash.setSize(200, 50);
		card.setFont(font1);
		cash.setFont(font1);
		buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));
		buttonPanel.setBorder(new EmptyBorder(10, 20, 10, 10));
		buttonPanel.add(card);
		buttonPanel.add(cash);
		TitledBorder tb = BorderFactory.createTitledBorder("Select a method of payment:");
		tb.setTitleFont(hintFont);
		tb.setTitleJustification(TitledBorder.LEFT);
		buttonPanel.setBorder(tb);
		pricePanel.add(buttonPanel, BorderLayout.SOUTH);
		contentPanel.add(pricePanel, BorderLayout.CENTER);
		this.add(contentPanel);
	}
}
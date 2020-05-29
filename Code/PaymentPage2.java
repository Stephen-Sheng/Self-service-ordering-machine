
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
/**
 * Title 		: PaymentPage2.java
 * Description	: This class is the GUI of the second kind of payment page.
 * @author		: Yang Hu 
 * @date      	: 12/5/2020
 */
@SuppressWarnings("serial")
public class PaymentPage2 extends JDialog{
	private int stamp=0;
	private String ID, username;
	private JButton refuse,exchange;
	private ArrayList<User> users = new ArrayList<User>();
	private String imagePath="Files/totoro.png";
	private UserInfoManagement uim = new UserInfoManagement();
	private PaymentPageControl pc = new PaymentPageControl();
	
	public void setID(String ID) {this.ID=ID;}
	/**
	 * Build the GUI interface for the second type payment page.
	 * */
	public PaymentPage2() {
		Font font1 = new Font("Times New Roman", Font.PLAIN+Font.BOLD, 25);// Set the general font
		Font titleFont = new Font("Times New Roman", Font.ITALIC+Font.BOLD, 40);
		Font messageFont = new Font("Cambria", Font.PLAIN, 20);
		
		/*Get the single price of the add-ons, the basic price and the user ID.*/
		Order o = new Order();
		ID=o.getUserid();
		users = uim.getUsers();
		for(int i=0;i<users.size();i++) {
			if(users.get(i).getID().equals(ID)) {
				username = users.get(i).getName();
				stamp = Integer.parseInt(users.get(i).getStamp());
			}
		}
		/*Set the title panel.*/
		setTitle("Payment Page");
		setSize(400,600);
		getContentPane().setLayout(new BorderLayout());
		JPanel contentPanel=new JPanel();
		contentPanel.setBackground(new Color(250,240,215));
		contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));		
		contentPanel.setLayout(new BorderLayout());
		// Center the window on the screen.
		setLocationRelativeTo(null);	
		JPanel titlePanel = new JPanel(); //panel to contain title
		titlePanel.setBorder(new EmptyBorder(20, 20, 0, 20));	
		JLabel titleLabel = new JLabel("Totoro Ramen ", JLabel.CENTER);
		titleLabel.setFont(titleFont);
		JLabel border = new JLabel("------------------------------------------------------"
						+ "-------------------------------",JLabel.CENTER);  
		titleLabel.setForeground(new Color(105,77,76));
		titleLabel.setFont(titleFont);
		titlePanel.setLayout(new BorderLayout());
		titlePanel.setBackground(new Color(250,240,215));
		border.setBackground(new Color(250,240,215));
		titlePanel.add(titleLabel,BorderLayout.NORTH);
		titlePanel.add(border,BorderLayout.CENTER);
		getContentPane().add(titlePanel,BorderLayout.NORTH);

		/*Set a message to show the rest stamps.*/  
		JPanel messagePanel = new JPanel();
		messagePanel.setBackground(new Color(250,240,215));
		messagePanel.setLayout(new BorderLayout());
		messagePanel.setBorder(new EmptyBorder(10,30,10,20));
		JLabel stampMessage1 = new JLabel("<html>Hello "+username+":<br><br> &nbsp &nbsp &nbsp "
						+ "Welcome to the loyalty scheme!<br><br> &nbsp &nbsp &nbsp  You have accumulated a total of "+stamp
						+" stamps. And can exchange 10 virtual stamps for a free meal.");
		stampMessage1.setFont(messageFont);
		stampMessage1.setForeground(new Color(105,77,76));
		messagePanel.add(stampMessage1,BorderLayout.NORTH);
				
		/*Set the button panel.*/
		exchange = new JButton("OK");
		exchange.setBorderPainted(false);
		exchange.setForeground(new Color(254,245,228));
		exchange.setBackground(new Color(150,122,111));
		exchange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.judge2();
				setVisible(false);
				new Ticket();
			}
		});
		refuse = new JButton("No,thanks");
		refuse.setBorderPainted(false);
		refuse.setForeground(new Color(254,245,228));
		refuse.setBackground(new Color(150,122,111));
		refuse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				PaymentPage page = new PaymentPage();
				page.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				page.setVisible(true);
			}
		});
				
		Dimension preferredSize = new Dimension(150,40);
		exchange.setPreferredSize(preferredSize);
		exchange.setFont(font1);
		refuse.setPreferredSize(preferredSize);
		refuse.setFont(font1);		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(250,240,215));
		buttonPanel.setBorder(new EmptyBorder(10, 40, 10, 40));
		buttonPanel.setLayout(new GridLayout(2,1,10,10));
		buttonPanel.add(exchange);
		buttonPanel.add(refuse);
				
		/*Set the image panel.*/
		JLabel imageLabel = new JLabel();
		ImageIcon icon = new ImageIcon(imagePath);
		imageLabel.setIcon(icon);
		imageLabel.setBorder(new EmptyBorder(10,55,10,20));
		messagePanel.add(imageLabel,BorderLayout.CENTER);
		messagePanel.add(buttonPanel,BorderLayout.SOUTH);
		getContentPane().add(messagePanel,BorderLayout.CENTER);
	}
}

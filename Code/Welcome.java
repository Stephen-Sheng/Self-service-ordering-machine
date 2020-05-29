
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

/**
 * Title : Welcome.java 
 * Description: This class is the GUI for welcoming client and administrator.
 * 
 * @author : Jiaqi Luo
 * @date : 8/5/2020
 */

@SuppressWarnings("serial")
public class Welcome extends JFrame{
	private JButton menu, adminlogin, quit; // Three buttons for three options
	
	/**
	 * Build the GUI for welcoming client and administrator
	 */
	public Welcome() {
		String imagePath = "Files/logo.jpg";
		Font f = new Font("Times New Roman", Font.ITALIC+Font.BOLD, 30);
		Font btn = new Font("Georgia", Font.ITALIC+Font.BOLD, 25);
		setTitle("Welcome to Totoro Ramen!"); 
		Container container = this.getContentPane();
		container.setBackground(Color.white);
	    this.setSize(400,600);
	    this.setLayout(new BorderLayout());
	    
	    //Logo Panel
		JPanel logoPanel = new JPanel();
		logoPanel.setLayout(new BorderLayout());
		logoPanel.setBackground(new Color(211,195,180));
		JLabel imageLabel = new JLabel();
		ImageIcon icon = new ImageIcon(imagePath);
		imageLabel.setIcon(icon);
		icon.setImage(icon.getImage().getScaledInstance(350, 150, Image.SCALE_AREA_AVERAGING));
		imageLabel.setBorder(new EmptyBorder(40, 10, 10, 10));
		
		JLabel label2 = new JLabel("Online self-ordering system");
		label2.setForeground(new Color(105,77,76));
		label2.setFont(f);
		label2.setBorder(new EmptyBorder(40, 20, 10, 10));
		
		logoPanel.add(imageLabel, BorderLayout.CENTER);	
		logoPanel.add(label2,BorderLayout.SOUTH);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(3,1,50,20));
		btnPanel.setBorder(new EmptyBorder(60, 70, 50, 70));
		btnPanel.setBackground(new Color(254,245,228));
		JLabel imageLabel2 = new JLabel();
		ImageIcon icon2 = new ImageIcon(imagePath);
		imageLabel.setIcon(icon2);
		icon2.setImage(icon2.getImage().getScaledInstance(350, 150, Image.SCALE_AREA_AVERAGING));
		imageLabel2.setBorder(new EmptyBorder(40, 10, 10, 10));
		
		menu = new JButton("menu");
		menu.setBorderPainted(false);
		menu.setForeground(new Color(254,245,228));
		menu.setBackground(new Color(150,122,111));
		menu.setFont(btn);
		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new OrderPageOne();
			}
		});
		
		adminlogin = new JButton("admin log in");
		adminlogin.setBorderPainted(false);
		adminlogin.setForeground(new Color(254,245,228));
		adminlogin.setBackground(new Color(150,122,111));
		adminlogin.setFont(btn);

		adminlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new FunctionPage();
			}
		});
		
		quit = new JButton("exit");
		quit.setBorderPainted(false);
		quit.setForeground(new Color(254,245,228));
		quit.setBackground(new Color(150,122,111));
		quit.setFont(btn);
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				System.exit(0);
			}
		});
		btnPanel.add(menu);
		btnPanel.add(adminlogin);
		btnPanel.add(quit);
		container.add(logoPanel,BorderLayout.NORTH);
		container.add(btnPanel,BorderLayout.SOUTH);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	}	
}

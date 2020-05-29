

import javax.swing.*;
import javax.swing.border.EmptyBorder;

//import BackgroundPanel;

import java.awt.*;
import java.awt.event.*;

/**
 * Title : EatinOrTakeaway.java 
 * Description: This class is the GUI for client choosing eat-in or take-away.
 * 
 * @author : Jiaqi Luo
 * @date : 8/5/2020
 */

@SuppressWarnings("serial")
public class EatinOrTakeaway extends JFrame{
	private JButton eatin, takeaway; // buttons for choosing eat-in or take-away
	
	/**
	 * Build the GUI for client choosing eat-in or take-away.
	 */
	public EatinOrTakeaway() {
	    this.setSize(400,600);
	    this.setLayout(new BorderLayout());
	    this.setTitle("Eat in or take away");
	    Font f = new Font("Cambria", Font.ITALIC+Font.BOLD, 26);
	    
	    BackgroundPanel bp= new BackgroundPanel();
	    bp.setLayout(new GridLayout(7,1,20,20));
	    bp.setBorder(new EmptyBorder(10, 35, 70, 35));
	    
	    // label creating
	    JLabel empty = new JLabel(" ");
		JLabel label = new JLabel("Please choose one option!");
		label.setForeground(new Color(105,77,76));
		label.setFont(f);
		label.setBounds(60, 50, 350, 40);
		
		// button creating
		eatin = new JButton("eat in");
		eatin.setFont(f);
		eatin.setBorderPainted(false);
		eatin.setForeground(new Color(254,245,228));
		eatin.setBackground(new Color(150,122,111));
		eatin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login();
			}
		});

		takeaway = new JButton("take away");
		takeaway.setFont(f);
		takeaway.setBorderPainted(false);
		takeaway.setForeground(new Color(254,245,228));
		takeaway.setBackground(new Color(150,122,111));
		takeaway.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login();
			}
		});
		bp.add(empty);
		bp.add(label);
		bp.add(eatin);
		bp.add(takeaway);
		
		this.add(bp);
	    this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	}
}

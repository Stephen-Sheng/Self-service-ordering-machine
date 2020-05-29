

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Title : ViewReport.java 
 * Description: This class is last week report viewing page
 * 
 * @author : Jiaqi Luo
 * @date : 8/5/2020
 */

@SuppressWarnings("serial")
public class ViewReport extends JFrame {
	private JFrame frame = new JFrame("View Report"); // create a frame
	private JButton previous; // button for switching to last page
	
	private MenuManagement rwm = new MenuManagement();
	private OrderCollectionManagement ocm = new OrderCollectionManagement();
	private Menu m = new Menu();
	
	/**
	 * Build the GUI for viewing last week report
	 */
	public ViewReport() {
		ocm.readOrderCollection();
		rwm.readMenu();
		ocm.getReport();
		ArrayList<String> option = ocm.getOption();
		ArrayList<String> count = ocm.getCount();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 600);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		Font textFont = new Font("Cambria", Font.ITALIC + Font.BOLD, 20);
		Font textFont1 = new Font("Cambria", Font.ITALIC + Font.BOLD, 15);
		Font btnFont = new Font("TimesRoman", Font.ITALIC + Font.BOLD, 15);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(350, 140+option.size()*40));
		panel.setBackground(new Color(250, 240, 215));
		frame.add(panel);
		panel.setLayout(null);
		
		if (140+option.size()*40 > 600) {
			JScrollPane scrollPane = new JScrollPane(panel);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			frame.add(scrollPane);
		} else {
			frame.add(panel);
		}
		
		JLabel label = new JLabel("This is the report for last week.");
		label.setBounds(40, 30, 300, 55);
		label.setForeground(new Color(105, 77, 76));
		label.setFont(textFont);
		panel.add(label);
		
		previous = new JButton("previous page");
		previous.setFont(btnFont);
		previous.setBorderPainted(false);
		previous.setForeground(new Color(254, 245, 228));
		previous.setBackground(new Color(150, 122, 111));
		previous.setBounds(120, 110+option.size()*40, 150, 25);
		previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new OrderList();
			}
		});
		panel.add(previous);
		
		JLabel[] labels1 = new JLabel[m.getAddonsCount()+9];
		for(int i=0;i<option.size();i++) {
			labels1[i] = new JLabel(option.get(i));
			labels1[i].setFont(textFont1);
			labels1[i].setForeground(new Color(105, 77, 76));
			labels1[i].setBounds(50, 100+40*i, 250, 25);
			panel.add(labels1[i]);
		}
		JLabel[] labels2 = new JLabel[m.getAddonsCount()+9];
		for(int i=0;i<count.size();i++) {
			labels2[i] = new JLabel(count.get(i));
			labels2[i].setFont(textFont1);
			labels2[i].setForeground(new Color(105, 77, 76));
			labels2[i].setBounds(300, 100+40*i, 200, 25);
			panel.add(labels2[i]);
		}
	}
}

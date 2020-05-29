import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//import filemanagement.OrderCollectionManagement;

import java.util.List;
import java.util.*;
/**
 * Title : RankSpiciness.java 
 * Description: This class is the ranking page of spiciness items in the past week
 * @author : Linxin Zheng
 * @date : 4/5/2020
 */
@SuppressWarnings("serial")
public class RankSpiciness extends JFrame {
	private String text0 = "";
	private String text1 = "Spiciness	Numbers of people\n";
	OrderCollectionManagement ocm = new OrderCollectionManagement();
	
	/**
	 *  The GUI for ranking spiciness page
	 */
	
	@SuppressWarnings("rawtypes")
	public RankSpiciness() {
		Container container = this.getContentPane();
		container.setBackground(new Color(250, 240, 215));
		this.setLayout(null);
		this.setSize(400, 600);
		this.setTitle("Rank Spiciness");
		JPanel upper = new JPanel();
		upper.setBackground(new  Color(250, 240, 215));
		Font f = new Font("Cambria", Font.ITALIC + Font.BOLD, 26);
		Font hintFont = new Font("Cambria", Font.ITALIC+Font.BOLD, 15);
		Font btnFont = new Font("Cambria", Font.BOLD, 20);
		Font textFont = new Font("Cambria", Font.BOLD, 15);

		JTextPane textPane = new JTextPane();
		textPane.setForeground(new Color(105, 77, 76));
		textPane.setFont(textFont);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new  Color(250, 240, 215));
		JButton button = new JButton("Return");
		JCheckBox jcb1 = new JCheckBox("<html>&nbsp &nbsp Generate a report and send it to <br> &nbsp &nbsp email once a week on Monday.");
		JLabel label_1 = new JLabel("Rank of spiciness last week");
		jcb1.setBackground(new  Color(250, 240, 215));
		jcb1.setForeground(new Color(105, 77, 76));
		jcb1.setFont(hintFont);
		jcb1.setBounds(65, 330, 300, 100);
		label_1.setFont(f);
		label_1.setForeground(new Color(105, 77, 76));
		button.setFont(btnFont);
		button.setBorderPainted(false);
		button.setForeground(new Color(254, 245, 228));
		button.setBackground(new Color(150, 122, 111));
		button.setBounds(90, 450, 200, 30);
		upper.add(label_1);
		this.add(jcb1);
		this.add(button);
		getContentPane().add(upper);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		upper.setSize(400, 50);
		upper.setLocation(0, 50);
		scrollPane.setSize(300, 200);
		scrollPane.setLocation(50, 100);
		scrollPane.setViewportView(textPane);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		ocm.readOrderCollection();
		text0 = ocm.getText();

		String[] split = text0.split("\\:|\n");
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		for (int i = 0; i < split.length; i++) {
			if (i % 2 == 0) {
				list2.add(split[i]);
			} else {
				list1.add(split[i]);
			}
		}

		// ArryList change to string
		String[] str0 = list1.toArray(new String[0]);
		// string change to int
		int[] str = Arrays.stream(str0).mapToInt(Integer::parseInt).toArray();

		/* Array sorting */
		Arrays.sort(str);
		int count = 0;
		int tmp = str[0];
		Map<Integer,Integer> map = new HashMap<>();
		for (int i = 0; i < str.length; i++) {
			if (tmp != str[i]) {
				tmp = str[i];
				count = 1;
			} else {
				count++;
			}
			map.put(str[i], count);
		}
		map = sortByValue(map);
		Set<Integer> key = map.keySet();
		for (Iterator it = key.iterator(); it.hasNext();) {
			Integer s = (Integer) it.next();
			text1 += "          " + s + "	            " + map.get(s) + "\n";
		}
		textPane.setText(text1 + "-------------------------------------------------------" + "\n");
		// add button actionlistener
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// jump to orderlist page
				setVisible(false);
				new OrderList();
			}
		});
	}

	/* Sort values from large to small */
	@SuppressWarnings("unchecked")
	public static Map<Integer, Integer> sortByValue(Map<Integer, Integer> map) {
		List<?> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Object>() {
			@SuppressWarnings("rawtypes")
			public int compare(Object o1, Object o2) {
				if (Integer.parseInt(((Map.Entry) o2).getValue() + "") > Integer
						.parseInt((((Map.Entry) o1).getValue()) + "")) {
					return 1;
				} else if (Integer.parseInt(((Map.Entry) o2).getValue() + "") == Integer
						.parseInt((((Map.Entry) o1).getValue()) + "")) {
					return 0;
				} else {
					return -1;
				}
			}
		});
		@SuppressWarnings("rawtypes")
		Map result = new LinkedHashMap<>();
		for (Iterator<?> it = list.iterator(); it.hasNext();) {
			@SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry) it.next();
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
}

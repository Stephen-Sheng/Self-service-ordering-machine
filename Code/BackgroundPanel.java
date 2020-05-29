
import javax.swing.*;
import java.awt.*;

/**
 * Title : BackgroundPanel.java 
 * Description : Repaint panels with self-defined picture.
 * 
 * @author : Yang Hu
 * @date : 20/5/2020
 */
@SuppressWarnings("serial")
public class BackgroundPanel extends JPanel{
	// The path of the picture.
	String path = "Files/totoro2.jpg";
	protected void paintComponent(Graphics g) { 
		super.paintComponent(g);
		ImageIcon icon = new ImageIcon(path); 
		g.drawImage(icon.getImage(), 0, 0, 400, 600, null);
	}
}

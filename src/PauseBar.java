import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PauseBar extends JPanel implements Config {

	/**
	 * 
	 */
	private Dimension size;
	private JLabel scoreLabel;

	public PauseBar(boolean visible) {
		scoreLabel = new JLabel("Pause");
		scoreLabel.setFont(new Font("TAHOMA", Font.BOLD, 24));
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		size = new Dimension(screenWidth, 80);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setBounds(0, screenHeight / 3, (int) size.getWidth(), (int) size.getHeight());
		setLayout(new BorderLayout());
		setOpaque(false);
		add(scoreLabel);
		setVisible(visible);
	}
	
	public void toggle() {
		setVisible(isVisible() ? false : true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.red);
		g.fillRect(0, 0, this.getSize().width, this.getSize().height);

//		g.drawImage(img, (img.getWidth(null) * i), 0, null);

	}
}

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class HealthBar extends JPanel implements Config {

	/**
	 * 
	 */
	private Image img;
	private Dimension size;
	private Player player;

	public HealthBar(Player player) {
		this.player = player;
		img = new ImageIcon("img/hp.png").getImage();
		size = new Dimension(img.getWidth(null) * 3, img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setBounds(30, margin, (int) size.getWidth(), (int) size.getHeight());
		setLayout(null);
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
//		g.setColor(Color.red);
//		g.fillRect(0, 0, this.getSize().width, this.getSize().height);

		for (int i = 0; i < player.getHP(); i++) {
			g.drawImage(img, (img.getWidth(null) * i), 0, null);
		}

	}

}

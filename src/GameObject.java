import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameObject extends JPanel implements Config {
	/**
	 * 
	 */
	protected Image img;
	protected Dimension size;
	protected int[] Coordinate = { 0, 0 };
	private int damage = 1;

	public GameObject(String img, int[] coordinate) throws IOException {
		this(ImageIO.read(new File(img)), coordinate);
	}

	public GameObject(String img, int coordinate_X, int coordinate_Y) throws IOException {
		this(img, new int[] { coordinate_X, coordinate_Y });
	}

	public GameObject(Image img, int[] coordinate) {
		this.img = img;
		size = new Dimension(img.getWidth(null), img.getHeight(null));
		this.Coordinate = coordinate;
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setOpaque(false);
		setBounds(coordinate[0], coordinate[1], (int) size.getWidth(), (int) size.getHeight());
		setLayout(null);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
//		g.fillRect(0, 0, this.getSize().width, this.getSize().height);
		g.drawImage(img, 0, 0, null);
		g.dispose();
	}

	public int getCoX() {
		return Coordinate[0];
	}

	public int getCoY() {
		return Coordinate[1];
	}

	public int getDamage() {
		return this.damage;
	}

	public void destroy() {
		size = new Dimension(0, 0);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setBounds(0, 0, 0, 0);
		this.setVisible(false);
	}

	public boolean isHit(GameObject obj) {
		if (this.getBounds().intersects(obj.getBounds())) {
			return true;
		}
		return false;

	}

}

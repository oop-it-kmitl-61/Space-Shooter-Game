import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

public class Enemy extends GameObject {

	/**
	 * 
	 */
	protected int damage = 1;
	protected int DownSpeed = 3;

	public Enemy(String img, int coordinate_X, int coordinate_Y) throws IOException {
		super(img, coordinate_X, coordinate_Y);

	}

	@Override
	public int getDamage() {
		return this.damage;
	}

	public void moveDown() {
		if (getCoY() > screenHeight + this.getHeight()) {
			destroy();
		} else {
			Coordinate[1] = Coordinate[1] + DownSpeed;
			setBounds(getCoX(), getCoY(), (int) size.getWidth(), (int) size.getHeight());
			// System.out.println("Move " + getCoY());
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
		g.setColor(Color.red);
		g.fillRect(0, 0, this.getSize().width, this.getSize().height);

	}
	

}

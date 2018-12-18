import javax.swing.ImageIcon;

public class GunShot extends GameObject {

	/**
	 * 
	 */
	
	public GunShot(Player player) {
		super(new ImageIcon("img/Shot.png").getImage(), new int[] { player.getCoX() + player.getWidth() / 2 - 8,
				player.getCoY() + player.getHeight() / 2 - 8 });
	}

	public void moveDown() {
		if (getCoY() < 0 - this.getHeight()) {
			destroy();
		} else {
			Coordinate[1] = Coordinate[1] - 5;
			setBounds(getCoX(), getCoY(), (int) size.getWidth(), (int) size.getHeight());
		}

	}

}

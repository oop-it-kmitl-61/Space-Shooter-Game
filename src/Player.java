import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Player extends GameObject implements KeyListener {

	/**
	 * 
	 */
	private int hitpoint = maxHP;
	private SpaceControl sp;
	private long lastShot = -1;

	public Player(SpaceControl sp) throws IOException {
		super("img/Player.png", screenWidth / 2 - 64, playerHeight);
		this.sp = sp;
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			sp.togglePause();			
		}
		if (!this.isDead() && !sp.isPause()) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				this.Coordinate[0] = this.Coordinate[0] + stepSide <= screenWidth - this.getWidth() / 2
						? this.Coordinate[0] + stepSide
						: screenWidth - this.getWidth() / 2;
				break;

			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				this.Coordinate[0] = this.Coordinate[0] - stepSide >= 0 - this.getWidth() / 2
						? this.Coordinate[0] - stepSide
						: 0 - this.getWidth() / 2;
				break;
			case KeyEvent.VK_SPACE:
				long tmp = System.currentTimeMillis() / 1000;
				if(tmp - lastShot >= 0.2 || lastShot == -1) {
					sp.shoot();
					lastShot = System.currentTimeMillis() / 1000;
				}
//				System.out.println(lastShot - tmp);
				break;
			}

			this.setBounds(this.getCoX(), this.getCoY(), this.getWidth(), this.getHeight());
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public boolean isHit(GameObject obj) {
		if (this.getBounds().intersects(obj.getBounds())) {
			obj.destroy();
			this.hitpoint = this.hitpoint - obj.getDamage();
//			System.out.println(this.hitpoint);

			return true;
		}
		return false;
	}

	public boolean isDead() {
		if (this.hitpoint < 0) {
			return true;
		}
		return false;
	}

	public int getHP() {
		return this.hitpoint;
	}

	public void heal(int i) {
		this.hitpoint = this.hitpoint >= maxHP ? this.hitpoint : this.hitpoint + i;
	}
}

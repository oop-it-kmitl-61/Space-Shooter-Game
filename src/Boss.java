import java.io.IOException;
import java.util.Random;

public class Boss extends Enemy implements Runnable, Config {
	/**
	 * 
	 */
	
	protected int damage = 15;
	private int hp = 5;
	private double angle = 0;
	private SpaceControl sc;
	private long lastShot = -1;
	private int walkpath;
	
	public Boss(String img, int coordinate_X, int coordinate_Y, SpaceControl sc) throws IOException {
		super(img, coordinate_X, coordinate_Y);
		this.sc = sc;
		hp = sc.getScore() / 3;
		walkpath = new Random().nextInt(1);
	}
	
	@Override
	public void run() {
		boolean hitRight = false;
		while(hp > 0 && ! sc.isOver()) {
			while(sc.isPause()) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			switch(walkpath) {
			case 0:
				// Circle animate
				this.Coordinate[0] = (int) (180 * Math.cos(angle)) + 170;
				this.Coordinate[1] = (int) (150 * Math.sin(angle)) + 150;
				angle = angle + 0.01;
				break;
			case 1:
				
				this.Coordinate[0] = hitRight ? this.Coordinate[0] -- : this.Coordinate[0] ++;
				hitRight = this.Coordinate[0] > screenWidth + size.getWidth() / 2 ? true : false;
				break;
			}
			setBounds(getCoX(), getCoY(), (int) size.getWidth(), (int) size.getHeight());
			try {
				long tmp = System.currentTimeMillis() / 1000;
				if(tmp - lastShot >= 0.2 || lastShot == -1) {
					sc.addEnemy(new Enemy("img/BossShot.png", (int) (Coordinate[0] + size.getWidth() / 2), (int) (Coordinate[1] + size.getHeight() / 2)));
					lastShot = System.currentTimeMillis() / 1000;
				}
				Thread.sleep(10);
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean isHit(GameObject obj) {
		if (this.getBounds().intersects(obj.getBounds())) {
			this.hp = this.hp - obj.getDamage();
			System.out.println(this.hp);
			if(hp <= 0) {
				this.destroy();
			}
			return true;
		}
		return false;

	}

}

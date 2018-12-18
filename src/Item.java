import java.io.IOException;

public abstract class Item extends GameObject implements Runnable{
	protected Player player;
	protected SpaceControl sc;
	
	public Item(String img, int coordinate_X, int coordinate_Y, Player player, SpaceControl sc) throws IOException {
		super(img, coordinate_X, coordinate_Y);
		// TODO Auto-generated constructor stub
		this.player = player;
		this.sc = sc;
	}
	
	protected abstract void getItem();
	
	public boolean isHit(GameObject obj) {
		if (this.getBounds().intersects(obj.getBounds())) {
			return true;
		}
		return false;

	}
	
	@Override
	public void run() {
		while(getCoY() < screenHeight + size.getHeight()) {
			while(sc.isPause()) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			this.Coordinate[1] = this.Coordinate[1] + 1 ;
//			System.out.println(getCoX() + ", " + getCoY());
			if(isHit(player)) {
				sc.event();
				getItem();
				this.destroy();
			}
			setBounds(getCoX(), getCoY(), (int) size.getWidth(), (int) size.getHeight());
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}

import java.io.IOException;

public class SmallHealthPotion extends Item {

	public SmallHealthPotion(int coordinate_X, int coordinate_Y, Player player, SpaceControl sc)
			throws IOException {
		super("img/hp.png", coordinate_X, coordinate_Y, player, sc);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void getItem() {
			
	}

}

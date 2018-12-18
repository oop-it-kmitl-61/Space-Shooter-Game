import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuFrame implements Config {
	private JFrame fr;
	private SpaceControl sp;
	private MenuPane mp;

	public MenuFrame(String text, int score) {
		mp = new MenuPane(this, text, score);
		fr = new JFrame(title);
		fr.add(mp.getPane());
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setSize(screenWidth, screenHeight);
		fr.setLocationRelativeTo(null);
		fr.setResizable(false);
		fr.setVisible(true);
	}
	
	public JFrame getFrame() {
		return fr;
	}
	
	
	public SpaceControl getSpaceControl() {
		return sp;
	}
}

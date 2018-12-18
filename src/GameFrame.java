import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame implements Config {
	private JFrame fr;
	private SpaceControl sp;
	private MenuPane mp;

	public GameFrame() {
		sp = new SpaceControl(this);
		fr = new JFrame(title);
		fr.add(sp.getPane());
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setSize(screenWidth, screenHeight);
		fr.setLocationRelativeTo(null);
		fr.setResizable(false);
		fr.setVisible(true);
	}
	
	public JFrame getFrame() {
		return fr;
	}
	
}

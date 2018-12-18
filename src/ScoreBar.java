import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ScoreBar extends JPanel implements Config {

	/**
	 * 
	 */
	private Image img;
	private Dimension size;
	private int score = 0;
	private JLabel scoreLabel;

	public ScoreBar(int score) {
		this.score = score;
		scoreLabel = new JLabel("0");
		scoreLabel.setFont(new Font("CONSOLAS", Font.BOLD, 24));
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		size = new Dimension(150, 30);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setBounds(screenWidth - margin - 10 - (int) size.getWidth(), 30, (int) size.getWidth(), (int) size.getHeight());
		setLayout(new BorderLayout());
		setOpaque(false);
		add(scoreLabel);
	}
	
	public void update() {
		scoreLabel.setText(""+this.score);
	}
	
	public void addScore(int score) {
		this.score = this.score + score;
		scoreLabel.setText(""+this.score);
	}
	
	public int getScore() {
		return this.score;
	}
}

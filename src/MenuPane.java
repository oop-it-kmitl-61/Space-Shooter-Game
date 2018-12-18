import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MenuPane implements ActionListener {
	private JPanel p0;
	private MenuFrame mf;
	private JButton btn;
	
	public MenuPane(MenuFrame mf, String text, int score) {
		this.mf = mf;
		btn = new JButton(text);
		JLabel scoreLable = new JLabel("Your score is " + score, SwingConstants.CENTER);
		scoreLable.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 24));
		scoreLable.setForeground(Color.WHITE);
		
		btn.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
		p0 = new JPanel() {

			/**
			 * 
			 */

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.red);
				g.drawImage(new ImageIcon("img/menu.png").getImage(), 0, 0, null);
			}

		};
		p0.setBounds(50, 50, 100, 150);
		btn.addActionListener(this);
		p0.setLayout(new BorderLayout());
		if (score > 0) {
			p0.add(scoreLable, BorderLayout.NORTH);			
		}
		p0.add(btn, BorderLayout.SOUTH);
	}
	
	public JPanel getPane() {
		return p0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new GameFrame();
		mf.getFrame().setVisible(false);
	}
}

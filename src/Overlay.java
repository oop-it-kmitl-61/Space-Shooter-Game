import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class Overlay extends JPanel implements Config, ActionListener {

	/**
	 * 
	 */
	private Dimension size;
	private JLabel label;
	private JPanel p0 = new JPanel(), p1;
	private SpaceControl sp;
	private Problem pb;
	private JTextArea ta;
	private ArrayList<JButton> btns;

	public Overlay(boolean visible, SpaceControl sp) {
		this.sp = sp;
		btns = new ArrayList<JButton>();
		label = new JLabel("Event");
		label.setFont(new Font("TAHOMA", Font.BOLD, 24));
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		size = new Dimension(screenWidth, screenHeight);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setBounds(0, 0, (int) size.getWidth(), (int) size.getHeight());
		setLayout(new BorderLayout());
		setOpaque(false);
		setVisible(visible);
		ta = new JTextArea("");
		ta.setLineWrap(true);
		ta.setWrapStyleWord(true);
		ta.setBounds(30,0, screenWidth-70, screenHeight);
		ta.setOpaque(false);
		ta.setEditable(false);
		ta.setFont(new Font("TAHOMA", Font.BOLD, 20));
		ta.setForeground(Color.WHITE);
		
		p0.setOpaque(false);		
		p0.add(label, BorderLayout.NORTH);
		p0.add(ta, BorderLayout.CENTER);
		newQuestion();
		add(p0);
		
	}

	public void toggle() {
		setVisible(isVisible() ? false : true);
		sp.togglePause();
	}

	public void event() {

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.setProperty("BGColor", "0x1E115B");
		g.setColor(Color.getColor("BGColor"));
		g.fillRect(0, 0, this.getSize().width, this.getSize().height);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Player p = sp.getPlayer();
		if(e.getActionCommand().equals(pb.getCorrect())) {
			p.heal(1);
		}
		newQuestion();
		setVisible(false);
		sp.togglePause();
		
	}
	
	public void newQuestion() {
		if(p1 != null) {
			p0.remove(p1);			
		}
		pb = new Problem();
		p1 = new JPanel();
		ta.setText(pb.getProblem());
		p1.setLayout(new GridLayout(0, 2));
		for(String ch : pb.getChoices()) {
			String[] c = ch.split(":")[0].split("#");
			JButton btn = new JButton(c[0]);
			btn.setActionCommand(ch);
			if(c.length > 1) {
				btn.setBackground(Overlay.hex2Rgb(c[1]));
			}
			btn.setPreferredSize(new Dimension(180, 100));
			btn.addActionListener(this);
			p1.add(btn);
			btns.add(btn);
		}
		p1.setOpaque(false);
		p1.setBounds(0,0, p0.getWidth(), p0.getHeight());
		p0.add(p1, BorderLayout.SOUTH);
	}
	
	private static Color hex2Rgb(String colorStr) {
	    return new Color(
	            Integer.valueOf( colorStr.substring( 0, 2 ), 16 ),
	            Integer.valueOf( colorStr.substring( 2, 4 ), 16 ),
	            Integer.valueOf( colorStr.substring( 4, 6 ), 16 ) );
	}
}

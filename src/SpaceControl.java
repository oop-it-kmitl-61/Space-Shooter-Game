
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SpaceControl implements Config {
	private JPanel p0, pane;
	private CopyOnWriteArrayList<Enemy> enemies = new CopyOnWriteArrayList<Enemy>();
	private CopyOnWriteArrayList<GunShot> gunShots = new CopyOnWriteArrayList<GunShot>();
	private Thread panelControl, enemyControl, Spawner, gunShotControl, boss, item;
	private Player player;
	private boolean over = false, pause = false;
	private Random rand = new Random();
	private int factor = startingFactor;
	private int score = 0;
	private ScoreBar sb;
	private PauseBar pb;
	private GameOver go;
	private Boss RedBoss = null;
	private SmallHealthPotion smhp = null;
	private HealthBar hb;
	private Overlay ol;
	private GameFrame gf;

	public SpaceControl(GameFrame gf) {
		this.gf = gf;
		try {
			player = new Player(this);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		p0 = new JPanel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 2532383525188505334L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.red);
				g.drawImage(new ImageIcon("img/bg.png").getImage(), 0, 0, null);
			}

		};
		sb = new ScoreBar(score);
		pb = new PauseBar(pause);
		hb = new HealthBar(player);
		ol = new Overlay(false, this);
		go = new GameOver(false);
		
		p0.add(ol);
		p0.add(go);
		p0.add(pb);
		p0.add(player);
		p0.add(hb);
		p0.add(sb);
		
		SpaceControl sc = this;
		JLabel scoreLabel = new JLabel("" + score);
		scoreLabel.setBounds(screenWidth - 50, screenHeight - 50, 0, 0);

		p0.add(scoreLabel);

		Spawner = new Thread(new Runnable() {

			@Override
			public void run() {
				while (!isOver() && !player.isDead()) {
					while(pause) {
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					dropItem(false);
					
					Enemy enemy = null;
					try {
						enemy = new Enemy("img/Enemy.png", rand.nextInt(screenWidth), 0);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					enemies.add(enemy);
					p0.add(enemy);
					factor--;

					try {
						Thread.sleep(interval * (factor / 100));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		});

		enemyControl = new Thread(new Runnable() {

			@Override
			public void run() {
				while (!isOver() && !player.isDead()) {
					while(pause) {
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					for (Enemy enemy : enemies) {
						enemy.moveDown();
						if (enemy.getCoY() > screenHeight + enemy.getHeight()) {
							enemy.destroy();
							enemies.remove(enemy);
						}
						for (GunShot shot : gunShots) {
							if (enemy.isHit(shot)) {
								enemy.destroy();
								shot.destroy();
								enemies.remove(enemy);
								gunShots.remove(shot);
								sb.addScore(1);
							}
						}
					}
					for (GunShot shot : gunShots) {
						if (RedBoss != null && RedBoss.isHit(shot)) {
							shot.destroy();
							gunShots.remove(shot);
							sb.addScore(3);
							dropItem(true);
						}
					}
					try {
						Thread.sleep(interval);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		});

		gunShotControl = new Thread(new Runnable() {

			@Override
			public void run() {
				while (!isOver() && !player.isDead()) {
					while(pause) {
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					for (GunShot shot : gunShots) {
						shot.moveDown();
						if (shot.getCoY() < 0 - shot.getHeight()) {
							shot.destroy();
							gunShots.remove(shot);
						}
					}
					try {
						Thread.sleep(interval);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		});

		panelControl = new Thread(new Runnable() {

			@Override
			public void run() {
				while (!isOver() && !player.isDead()) {
					while(pause) {
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					for (Enemy enemy : enemies) {
						if (player.isHit(enemy)) {
							p0.remove(enemy);
							enemy = null;
							enemies.remove(enemy);
						}
					}
					hb.repaint();
					p0.repaint();
					sb.update();
					try {
						Thread.sleep(interval);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (sb.getScore() % 60 == 20) {
						if (RedBoss == null || !boss.isAlive()) {
							try {
								RedBoss = new Boss("img/boss.png", (screenWidth - 128) / 2, screenHeight / 2 - 128,
										sc);
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							boss = new Thread(RedBoss);
							p0.add(RedBoss);
							boss.start();
						}

					}
				}
				over = true;
				gf.getFrame().setVisible(false);
				new MenuFrame("Game Over", sb.getScore());
			}

		});
		Spawner.start();
		gunShotControl.start();
		enemyControl.start();
		panelControl.start();
		p0.setLayout(null);
		
	}

	public JPanel getPane() {
		return this.p0;
	}

	public boolean isOver() {
		return over;
	}

	public void shoot() {
		GunShot gs = new GunShot(player);
		p0.add(gs);
		gunShots.add(gs);
	}

	public void addEnemy(Enemy enemy) {
		enemies.add(enemy);
		p0.add(enemy);
	}

	public int getScore() {
		return sb.getScore();
	}

	public void togglePause() {
		this.pause = pause ? false : true;
		pb.toggle();
	}

	public boolean isPause() {
		return pause;
	}
	
	public void event() {
		ol.toggle();
	}
	
	public void dropItem(boolean confirm) {
		if (item == null || rand.nextInt(1000) > 992 || confirm) {
			try {
				smhp = new SmallHealthPotion(rand.nextInt(screenWidth), 0, player, this);
				item = new Thread(smhp);
				item.start();
				p0.add(smhp);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Player getPlayer() {
		return player;
	}
	
}

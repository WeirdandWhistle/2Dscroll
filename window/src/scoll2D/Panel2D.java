package scoll2D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import entity.CollisionChecker;
import entity.Player;
import player.Platformer;
import player.TopDown2D;
import tile.TileManager;
public class Panel2D extends JPanel implements ActionListener,KeyListener{
	public final int scale = 1;
	public final int ogTileSize = 48;
	public final int tileSize = ogTileSize * scale;
	public final int maxScreenRow = 10;
	public final int maxScreenCol = 10;
	public final int width = maxScreenCol * ogTileSize;
	public final int height = maxScreenRow * ogTileSize;
	public final int maxWorldCol = 20;
	public final int maxWorldRow = 20;
	public final int worldHeight = maxWorldRow * tileSize;
	public final int worldWidth = maxWorldCol * tileSize;
	public final double jumpPower = 100;
	public final double gravity = 4.5;
	
	public boolean platformer = false;
	public boolean sprint =false;
	public boolean d = false;
	public boolean a = false;
	public boolean space = false;
	public boolean w = false;
	public boolean s = false;
	
	Timer gameClock;
	public final int gameTicks = 60;
	Dimension window = new Dimension(width,height);
	public TileManager tileM = new TileManager(this);
	Platformer pf = new Platformer(this);
	TopDown2D td2d = new TopDown2D();
	public Player player = new Player(this);
	public CollisionChecker cc = new CollisionChecker(this);
	
	
		public Panel2D(){
		this.setPreferredSize(window);
		this.requestFocusInWindow();
		this.addKeyListener(this);
			
		gameClock = new Timer(gameTicks/1000,this); 
		gameClock.start();
	}
	public void paint(Graphics g) {
		
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0,width,height);
		
		
		tileM.draw(g2d);
		
		player.update(g2d);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
			this.repaint();
			if(platformer) {
				pf.platformRender();
			}
			if(!platformer) {
				td2d.TopDownRender();
			}
			
		
//		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		if(e.getKeyCode()==16) {
			sprint=true;
		}		
		if(e.getKeyCode()==68) {
			d=true;
		}
		if(e.getKeyCode()==65) {
			a=true;
		}
		if(e.getKeyCode()==32) {
			space=true;
		}
		if(e.getKeyCode()==87) {
			w=true;
		}
		if(e.getKeyCode()==83) {
			s=true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==16) {
			sprint=false;
		}
		if(e.getKeyCode()==68) {
			d=false;
		}
		if(e.getKeyCode()==65) {
			a=false;
		}
		if(e.getKeyCode()==32) {
			space=false;
		}
		if(e.getKeyCode()==87) {
			w=false;
		}
		if(e.getKeyCode()==83) {
			s=false;
		}
		
	}

}

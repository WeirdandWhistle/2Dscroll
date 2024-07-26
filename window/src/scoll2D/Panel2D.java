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
import extra.CamLimit;
import extra.RoomHandeler;
import object.SuperObject;
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
	public final double norm = 0.707;
	public final int centerScreenX = width/2 - (tileSize/2);
	public final int centerScreenY = height/2 - (tileSize/2);
	
	public long starttime= 0;
	public long endtime = 0;
	public long deltatime=0;
	public int t = 0;
	public int atime = 0;
	public int ats;
	public int rt = 0;
	
	public int cameraX = 0 * tileSize;
	public int cameraY = 0 * tileSize;
	public boolean XF = false;
	public boolean YF = true;
	
	public boolean platformer = false;
	public boolean sprint =false;
	public boolean d = false;
	public boolean a = false;
	public boolean space = false;
	public boolean w = false;
	public boolean s = false;
	public boolean f = false;
	
	Timer gameClock;
	public final int gameTicks = 60;
//	public Rectangle cameraF = new Rectangle(centerScreenX - (tileSize/2),centerScreenY - (tileSize/2),tileSize,tileSize);
	public Dimension window = new Dimension(width,height);
	public TileManager tileM = new TileManager(this);
	public Player player = new Player(this);
	public CollisionChecker cc = new CollisionChecker(this);
	public SuperObject obj[] = new SuperObject[10];
	public RoomHandeler roomH = new RoomHandeler(this);
	public Initializer intit = new Initializer(this);
	public CamLimit camL = new CamLimit(this);
	
	
		public Panel2D(){
		this.setPreferredSize(window);
		this.requestFocusInWindow();
		this.addKeyListener(this);
		intit.intit();
			
		gameClock = new Timer(gameTicks/1000,this); 
		gameClock.start();
	}
	public void paint(Graphics g) {
		
		
		
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0,width,height);
		
		
		//WORLD TILES
		tileM.draw(g2d);
		
		// OBJECTS
		for(int i = 0; i<= (obj.length - 1); i++) {
			if(obj[i] != null) {
				obj[i].draw(g2d,this);
			}
		}
		//PLAYER
		player.draw(g2d);
		
		//ROOMS
		roomH.cover(g2d);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
			player.update();
			this.repaint();
			
//			if(platformer) {
//				pf.platformRender();
//			}
//			if(!platformer) {
//				td2d.TopDownRender();
//			}
//			if(t==0) {
//			starttime = System.currentTimeMillis();			
//			}
//			t++;
//			if(t==gameTicks) {
//				endtime = System.currentTimeMillis();
//				t=0;
//				ats++;
//				deltatime = endtime - starttime;
//				rt += deltatime;
//				atime = rt/ats;
//				System.out.println(atime);
//			}
			
//		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		char f = 'f';

		if(e.getKeyChar() == f) {
			
			boolean used = false; 
			
			if(player.follow && !used) { player.follow = false; used = true;}
			
			if(!player.follow && !used) {player.follow=true;  used = true;}
			
			used = false;
			
		}
		
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

package scoll2D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import entity.CollisionChecker;
import entity.Entity;
import entity.Player;
import extra.CamLimit;
import extra.RoomHandeler;
import extra.UI;
import object.SuperObject;
import tile.TileManager;
public class Panel2D extends JPanel implements ActionListener,KeyListener,Runnable{
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
	
	public boolean enterPressed;
	public boolean platformer = false;
	public boolean sprint =false;
	public boolean d = false;
	public boolean a = false;
	public boolean space = false;
	public boolean w = false;
	public boolean s = false;
	public boolean f = false;
	public boolean enter = false;
	
	//MOUSE
	public boolean mthis = false;
	public boolean pmp = false;
	public int sx = 0;
	public int sy = 0;
	public int x = 500;
	public int y = 100;
	public int ex = 0;
	public int ey = 0;
	public int ax = 0;
	public int ay = 0;
	public int tf = 0;
	public int ctf = 0;
	
	public Point m = new Point(sx,sy);
	
	//GAMESTATE
	public final int titleState = 0; 
	public final int pausedState = 2;
	public final int playState = 1;
	public final int dialogueState = 5;
	public final int computerScreen = 6;
	public int gameState = titleState;
	public int prevGameState = gameState;
	
	
//	private Timer gameClock;
	public Thread gameThread;
	public final int gameTicks = 60;
	public Dimension window = new Dimension(width,height);
	public TileManager tileM = new TileManager(this);
	public Player player = new Player(this);
	public CollisionChecker cc = new CollisionChecker(this);
	public SuperObject obj[] = new SuperObject[10];
	public RoomHandeler roomH = new RoomHandeler(this);
	public Initializer intit = new Initializer(this);
	public CamLimit camL = new CamLimit(this);
	public Entity[] npc = new Entity[10];
	public UI ui = new UI(this);
	public MouseHandler mh = new MouseHandler(this);
	
	
	
	
	
		public Panel2D(){
		this.setPreferredSize(window);
		this.requestFocusInWindow();
		this.addKeyListener(this);
//		this.addMouseListener(mh);
		
		intit.intit();
		
		this.startGameThread();
		}
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	public void paint(Graphics g) {
		
		
		
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0,width,height);
		
		// TILI SCREEN
		if(gameState == titleState || gameState == computerScreen) {
			ui.draw(g2d);
		}
		else {
			//WORLD TILES
			tileM.draw(g2d);
			
			// OBJECTS
			for(int i = 0; i<= (obj.length - 1); i++) {
				if(obj[i] != null) {
					obj[i].draw(g2d,this);
				}
			}
			// NPC
			for(int i = 0; i<=(npc.length -1);i++) {
				if(npc[i] != null) {
					npc[i].draw(g2d);
				}
			}
			//PLAYER
			player.draw(g2d);
			
			//ROOMS
			roomH.cover(g2d);
			
			// UI
			
			ui.draw(g2d);
//			g2d.drawString("lol",10,10);
		}
		

		
		
		
		
	}
	public void update() {
//		System.out.println(m);
//		System.out.println(mthis);
//		
//		ax = MouseInfo.getPointerInfo().getLocation().x;
//		ay = MouseInfo.getPointerInfo().getLocation().y;
//		this.mouseUpdate();
		
		
//		System.out.println(screen);
		
		
		this.repaint();
		switch(gameState) {
		case playState:
			
			player.update();
			
			for(int i =0;i <= npc.length-1;i++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}
			break;
		case pausedState:
			break;
		case dialogueState:
			break;
		case titleState:
			break;
		case computerScreen:
			break;
		}
		
		tf++;
		
	}
//	public void mathRevitavePosEntered() {
//		x = ax - ex;
//		y = ay - ey;
//	}
//	public void mathRevitavePosScreen() {
//		x = (ax - sx);
//		y = (ay - sy);
//	}
//	public void mouseUpdate() {
//		sx = ax - x;
//		sy = ay - y;
		
//		if(ctf != 0) {
//			if(ctf < tf) {
//				mthis = false;
//				System.out.println("debug");
//			}
//		}
		
//		m.setLocation(sx, sy);
//		am.setLocation(ax, ay);
//		screen.setLocation(x, y);
//	}
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000/gameTicks;
		double nextdrawTime = System.nanoTime() + drawInterval;
		
		
		while(gameThread != null) {
//			if(t==0) {
//				starttime = System.currentTimeMillis();			
//				}
			update();
		
		
		
//		System.out.println(e.getSource());
		
			
//				t++;
//				if(t==gameTicks) {
//					endtime = System.currentTimeMillis();
//					t=0;
//					ats++;
//					deltatime = endtime - starttime;
//					rt += deltatime;
//					atime = rt/ats;
//					System.out.println(atime);
//			}
				
			
			
			try {
				double remaningTime = nextdrawTime - System.nanoTime();
				remaningTime = remaningTime / 1000000;
				
				if(remaningTime < 0) {
					remaningTime = 0;
				}
				
				Thread.sleep((long) remaningTime);
				
				nextdrawTime += drawInterval;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
//		System.out.println(e.getKeyChar());
	}

	@Override
	public void keyPressed(KeyEvent e) {
//		System.out.println(e.getKeyCode());
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
		if(gameState ==playState) {
			if(e.getKeyCode()==10) {
				enterPressed = true;
			}
		}
		
		if(gameState==dialogueState) {
			if(e.getKeyCode()==10) {
				gameState = playState;
			}
			
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

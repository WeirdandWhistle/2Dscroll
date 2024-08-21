package extra;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import scoll2D.Panel2D;

public class UI {
	
	Panel2D p;
	public Graphics2D g2d;
	public String message = "";
	public boolean showM = false;
	public Font terminal,SansSerif40,SansSerif80;
	public String currentDialogue = "";
	public Button[] but = new Button[3];
	private BufferedImage door;
	private int dx = 0;
	private int dy = 0;
	private int sx = 0;
	private int sy = 0;
//	private int px = 0;
//	private int py = 0;
	
	
	public UI(Panel2D p) {
		this.p=p;
		terminal = new Font("Terminal",Font.PLAIN,20);
		SansSerif40 = new Font("SansSerif",Font.PLAIN,40);
		SansSerif80 = new Font("SansSerif",Font.PLAIN,80);
		
		door = setup("res\\extra\\door.png");
		
		this.setupButtonsTitle();
	}
	public void setupButtonsTitle() {
		but[0] = new Button(p,new Rectangle(200,250,100,40));
		but[1] = new Button(p,new Rectangle(200,290,160,40));
	}
	public void setupButtonsComputer() {
		but[0].modButton(new Rectangle(100,100,p.tileSize,p.tileSize));
		but[1].modButton(new Rectangle(150,200,p.tileSize,p.tileSize));
	}
	public void draw(Graphics2D g2d) {
		
		
		this.g2d = g2d;
//System.out.println(" prev: " +p.prevGameState);
//		
//		System.out.println(" real: " +p.gameState);
		if(p.prevGameState != p.gameState) {
			
			if(p.gameState == p.pausedState) {
				
			}
			else if(p.gameState == p.dialogueState) {
				
			}
			else if(p.gameState == p.titleState) {
				
				
			}
			else if(p.gameState == p.computerScreen) {
				this.setupButtonsComputer();
			}
		}
		p.prevGameState = p.gameState;
		
		if(p.gameState == p.pausedState) {
			drawPauseScreen();
		}
		if(p.gameState == p.dialogueState) {
			drawDialogue();
		}
		if(p.gameState == p.titleState) {
			drawTitleScreen();
			
		}
		if(p.gameState == p.computerScreen) {
			drawComputerScreen();
		}

		
//		System.out.println("post prev: " +p.prevGameState);
		
//		System.out.println("post real: " +p.gameState);
	}
	public BufferedImage setup(String filePath) {
		UTool ut = new UTool();
		BufferedImage img = null;
		
		try {
			img = ImageIO.read(new File(filePath));
			img = ut.scaleImage(img, p.tileSize, p.tileSize);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}
	public void drawDialogue() {
		int x = p.tileSize / 2;
		int y = p.tileSize / 2;
		int width = p.width - (p.tileSize*1);
		int height = p.tileSize * 4;
		
		drawSubWindow(x,y,width,height);
		g2d.setFont(terminal);
		x += (p.tileSize/3d);
		y += (p.tileSize/2);
		for(String line: currentDialogue.split("\n")) {
			g2d.drawString(line,x,y);
			y+= 20;
		}
	}
	public void drawSubWindow(int x,int y,int width,int height) {
		Color c = new Color(0,0,0,200);
		
		g2d.setColor(c);
		g2d.fillRoundRect(x,y,width,height,35,35);
		
		c =  new Color(255,255,255);
		g2d.setColor(c);
		g2d.setStroke(new BasicStroke(4));
		g2d.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
	public void drawString(String text) {
		message = text;
		showM = true;
	}
	public void drawPauseScreen() {
		
	}
	public void drawComputerScreen() {
		Color c = new Color(0,0,200);
		
		g2d.setColor(c);
		g2d.fillRect(0, 0,p.width,p.height);
//		
//		int x = p.width/2 - (p.tileSize/2);
//		int y = p.height/2 - (p.tileSize/2);
		int x = 400;
		int y = 400;
		
		g2d.drawImage(door,x,y,null);
		
		
		if(p.pmp  && (but[0].pe || but[0].entered())) {
			
			dx = p.sx - sx;
			dy = p.sy - sy;
			
			sx = 0;
			sy = 0;
			
			
			
			but[0].move(but[0].x += dx, but[0].y += dy);
		}
//		if(but[0].entered()) {
//			but[0].pe = true;
//		}
		
		
		
		sx = p.sx;
		sy = p.sy;
		p.pmp = p.mthis;
		but[0].pe = false;
		
		if(but[0].entered()) {
			but[0].pe = true;
		}
		
if(p.pmp  && (but[1].pe || but[1].entered())) {
			
			dx = p.sx - sx;
			dy = p.sy - sy;
			
			sx = 0;
			sy = 0;
			
			
			
			but[1].move(but[1].x += dx, but[1].y += dy);
		}
//		if(but[0].entered()) {
//			but[0].pe = true;
//		}
		
		
		
		sx = p.sx;
		sy = p.sy;
		p.pmp = p.mthis;
		but[1].pe = false;
		
		if(but[1].entered()) {
			but[1].pe = true;
		}
		
		
		
		g2d.setColor(Color.black);
		g2d.fillRect(but[0].x, but[0].y, but[0].width, but[0].height);
		g2d.setColor(Color.green);
		g2d.fillRect(but[1].x, but[1].y, but[1].width, but[1].height);
		
		
		
	}
	public void drawTitleScreen() {
		
		g2d.setFont(SansSerif40);
		
		String text = "THE NAME OF THE GAME";
		
		int x = getXForCenteredText(text);
		int y = p.height/4;
		
		Color c = new Color(0,200,200);
		g2d.setColor(c);
		g2d.fillRect(0,0,p.width,p.height);
		g2d.setFont(terminal);
		g2d.setColor(Color.black);
		g2d.drawString("Welcome to", getXForCenteredText("Welcome to")-5, (p.height/8)+5);
		g2d.setColor(Color.red);
		g2d.drawString("Welcome to", getXForCenteredText("Welcome to"), (p.height/8));
		g2d.setColor(Color.black);
		g2d.setFont(SansSerif40);
		g2d.drawString(text, x -5, y+5);
		g2d.setColor(Color.red);
		g2d.drawString(text, x, y);
		
		// COOL IMAGE
		y += p.tileSize;
		g2d.setColor(Color.darkGray);
		g2d.fillRect((p.width/2)-(p.tileSize/2), y, p.tileSize, p.tileSize);
		text ="new game";
		y += p.tileSize*2;
		x = this.getXForCenteredText(text);
		
		g2d.setFont(terminal);
		g2d.setColor(Color.black);
		if(but[0].entered()) {
		g2d.drawRect(but[0].x,but[0].y,but[0].width,but[0].height);
		if(but[0].clicked()) {
			p.gameState = p.playState;

		}
		}
		g2d.drawString(text, but[0].x+2, but[0].y + this.GetHeightOfString(text));
		
		text = "computer screen";
		
		g2d.setFont(terminal);
		g2d.setColor(Color.black);
		if(but[1].entered()) {
			g2d.drawRect(but[1].x,but[1].y,but[1].width,but[1].height);
			if(but[1].clicked()) {
				p.gameState = p.computerScreen;
		}
		}
		g2d.drawString(text, but[1].x+2, but[1].y + this.GetHeightOfString(text));
		
	}
	public int getXForCenteredText(String text) {
		int length = GetWidthOfString(text);
		int x = p.width/2 - length/2;
		return x;
				
	}

	public int GetWidthOfString(String text) {
		return (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
	}
	public int GetHeightOfString(String text) {
		return (int)g2d.getFontMetrics().getStringBounds(text, g2d).getHeight();
	}

}

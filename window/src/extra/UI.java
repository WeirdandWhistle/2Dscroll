package extra;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import scoll2D.Panel2D;

public class UI {
	
	Panel2D p;
	public Graphics2D g2d;
	public String message = "";
	public boolean showM = false;
	public Font terminal,SansSerif40,SansSerif80;
	public String currentDialogue = "";
	public Button[] but = new Button[3];
	
	
	public UI(Panel2D p) {
		this.p=p;
		terminal = new Font("Terminal",Font.PLAIN,20);
		SansSerif40 = new Font("SansSerif",Font.PLAIN,40);
		SansSerif80 = new Font("SansSerif",Font.PLAIN,80);
		
		but[0] = new Button(p,new Rectangle(200,250,100,40));
	}
	
	public void draw(Graphics2D g2d) {
		this.g2d = g2d;
		g2d.setFont(SansSerif40);
		if(showM) {
			g2d.drawString(message, 0, 40);
		}
		if(p.gameState == p.pausedState) {
			drawPauseScreen();
		}
		if(p.gameState == p.dialogueState) {
			drawDialogue();
		}
		if(p.gameState == p.titleState) {
			drawTitleScreen();
			
		}


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
//		p.add(b1);
		
		
		
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

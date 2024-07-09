package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import scoll2D.Panel2D;

public class Player extends Entity {

	Panel2D p;
	
	public int screenX;
	public int screenY;
	public Player(Panel2D p) {
		this.p = p;
		screenX = p.width/2 -(p.tileSize/2);
		screenY = p.height/2 -(p.tileSize/2);
		setDefVals();
	}
	public void setDefVals() {
		worldX = 9 * p.tileSize;
		worldY = 9 * p.tileSize;
		speed = 4;
		solidHitbox = new Rectangle();
		solidHitbox.x = 8 * p.scale;
		solidHitbox.y = (16 - 4) * p.scale;
		solidHitbox.height = 32 * p.scale;
		solidHitbox.width = 32 * p.scale;
		}
	public void update(Graphics2D g2d) {
		if(!p.platformer) {
			
			if(p.w)dir = "up";
			
			if(p.s)dir = "down";
			
			if(p.a)dir = "left";
			
			if(p.d)dir = "right";
			
			p.cc.check(this);
			System.out.println(collision);
			if(collision == false && dir !=null) {
				switch(dir) {
				case "up": worldY -= speed; break;
				case "down":  worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
				default: break;
				}
				dir=null;
			}
			g2d.setColor(Color.CYAN);
			g2d.fillRect(screenX,screenY,p.tileSize,p.tileSize);
			g2d.setColor(Color.red);
			g2d.fillRect(screenX+solidHitbox.x, screenY+solidHitbox.y, solidHitbox.width, solidHitbox.height);
		}
	}
}

package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import scoll2D.Panel2D;

public class Player extends Entity {

	Panel2D p;
	
	public int screenX;
	public int screenY;
	public boolean follow;
	public Player(Panel2D p) {
		this.p = p;
		screenX = p.width/2 -(p.tileSize/2);
		screenY = p.height/2 -(p.tileSize/2);
		follow = true;
		setDefVals();
	}
	public double norm(double speed) {
		return speed * p.norm;
	}
	public void setDefVals() {
		worldX = 2 * p.tileSize;
		worldY = 2 * p.tileSize;
		speed = 4;
		solidHitbox = new Rectangle();
		solidHitbox.x = 8 * p.scale;
		solidHitbox.y = (16 - 4) * p.scale;
		solidHitbox.height = 32 * p.scale;
		solidHitbox.width = 32 * p.scale;
		}
	public void update() {
		if(!p.platformer) {
			
			speed = 4;
			
			if(p.w)dir = "up";
			
			if(p.s)dir = "down";
			
			if(p.a)dir = "left";
			
			if(p.d)dir = "right";
			
			if(p.w && p.d)dir ="ur";
			
			if(p.w && p.a)dir = "ul";
			
			if(p.s && p.d)dir = "dr";
			
			if(p.s && p.a)dir = "dl";
			
			
			p.cc.check(this);
			p.cc.OBJCheck(this,true);
			
			if(collision == false && dir !=null) {
				
				switch(dir) {
				case "up": worldY -= speed;if(p.YF) {p.cameraY -= speed;} break;
				case "down":  worldY += speed; if(p.YF) {p.cameraY += speed;} break;
				case "left": worldX -= speed;if(p.XF) {p.cameraX -= speed;} break;
				case "right": worldX += speed;if(p.XF) {p.cameraX += speed;} break;
				case "ul":
					worldX -= norm(speed);
					worldY -= norm(speed);
					if(p.XF) {p.cameraX -= norm(speed);}
					if(p.YF) {p.cameraY -= norm(speed);} 
					break;
				case "ur":
					worldX += norm(speed);
					worldY -= norm(speed);
					if(p.XF) {p.cameraX += norm(speed);}
					if(p.YF) {p.cameraY -= norm(speed);}
					break;
				case "dl":
					worldX -= norm(speed);
					worldY += norm(speed);
					if(p.XF) {p.cameraX -= norm(speed);}
					if(p.YF) {p.cameraY += norm(speed);}
					break;
				case "dr":
					worldX += norm(speed); 
					worldY += norm(speed);
					if(p.XF) {p.cameraX += norm(speed);}
					if(p.YF) {p.cameraY += norm(speed);}
					break;
				default: break;
				}
				dir=null;
			}
			if(follow) {
			x = worldX;
			y = worldY;
			
			screenX = worldX - p.cameraX;
			screenY = worldY - p.cameraY;
			
				
				p.camL.snapLimit();
				p.camL.worldLimit();
			}
		}
	}
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.CYAN);
		g2d.fillRect(screenX,screenY,p.tileSize,p.tileSize);
		g2d.setColor(Color.red);
		g2d.fillRect(screenX+solidHitbox.x, screenY+solidHitbox.y, solidHitbox.width, solidHitbox.height);
	}
}

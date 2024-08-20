package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import scoll2D.Panel2D;

public class Player extends Entity {
	
	public int screenX;
	public int screenY;
	public boolean follow;
	public Player(Panel2D p) {
		super(p);
		screenX = p.width/2 -(p.tileSize/2);
		screenY = p.height/2 -(p.tileSize/2);
		follow = true;
		setDefVals();
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
			
			if(p.w) {dir = "up"; moving =true;}
			
			if(p.s) {dir = "down";moving =true;}
			
			if(p.a) {dir = "left";moving =true;}
			
			if(p.d) {dir = "right";moving =true;}
			
			if(p.w && p.d) {dir ="ur";moving =true;}
			
			if(p.w && p.a) {dir = "ul";moving =true;}
			
			if(p.s && p.d) {dir = "dr";moving =true;}
			
			if(p.s && p.a) {dir = "dl";moving =true;}
			
			
			p.cc.check(this);
			p.cc.OBJCheck(this,true);
			
			int npcIndex = p.cc.checkEntity(this, p.npc);
			npcInteract(npcIndex);
			
			if(collision == false && dir !=null && moving) {
				
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
				
			}
			moving=false;
			
			
			
			screenX = worldX - p.cameraX;
			screenY = worldY - p.cameraY;
			
			p.camL.snapLimit();
			p.camL.worldLimit();
			
		}
	}
	public void pickupObj(int obj) {
		if(obj != 999) {
			
		}
	}
	public void npcInteract(int npc) {
		if(npc != 999) {
			
			if(p.enterPressed == true) {
			p.gameState = p.dialogueState;
			
			p.npc[npc].speak();
		}
		}
		p.enterPressed = false;
	}
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.CYAN);
		g2d.fillRect(screenX,screenY,p.tileSize,p.tileSize);
		g2d.setColor(Color.red);
		g2d.fillRect(screenX+solidHitbox.x, screenY+solidHitbox.y, solidHitbox.width, solidHitbox.height);
	}
}

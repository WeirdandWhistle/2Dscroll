package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import extra.SpriteSheet;
import extra.UTool;
import scoll2D.Panel2D;

public class Entity {
	public Panel2D p;
	public int worldX,worldY;
	public int speed;
	public String dir; 	
	public BufferedImage sprite2D;
	public boolean collision;
	public Rectangle solidHitbox = new Rectangle(4,4,44,44);
	public int x,y;
	public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
	int actionFrameCounter;
	public boolean moving =false;

	public Entity(Panel2D p) {
		this.p = p;
	}
	public double norm(double speed) {
		return speed * p.norm;
	}
	public void setAction() {}
	public void update() {
		moving = true;
		setAction();
		
		collision =false;
		p.cc.check(this);
		p.cc.OBJCheck(this, false);
		
		
	}
	public void draw(Graphics g2d) {
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < p.maxWorldCol && worldRow < p.maxWorldRow) {
	
			BufferedImage img = null;
			int screenX = worldX - p.cameraX;
			int screenY = worldY - p.cameraY;
			if(dir != null) {
			switch(dir) {
			case "up": img = up1; break;
			case"down": img = down1; break;
			case"right": img = right1;break;
			case"left":img = left1;break;
			default: break;
			}
			}
			
			if(collision == false && dir !=null && moving) {
				
				switch(dir) {
				case "up": worldY -= speed; break;
				case "down":  worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
				case "ul":
					worldX -= norm(speed);
					worldY -= norm(speed);
					
					break;
				case "ur":
					worldX += norm(speed);
					worldY -= norm(speed);
					
					break;
				case "dl":
					worldX -= norm(speed);
					worldY += norm(speed);
					
					break;
				case "dr":
					worldX += norm(speed); 
					worldY += norm(speed);
					
					break;
				default: break;
				}
				moving=false;
			}
			
			g2d.drawImage(img, screenX, screenY, p.tileSize, p.tileSize,null);
			g2d.setColor(Color.red);
			g2d.fillRect((worldX - p.cameraX)+solidHitbox.x,(worldY - p.cameraY)+solidHitbox.y,solidHitbox.width,solidHitbox.height);
			
			worldCol++;
			
	
			if(worldCol == p.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
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
	public BufferedImage setupSprite(String filePath,int col,int row,int width,int height) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SpriteSheet ss = new SpriteSheet(img);
		return ss.getSprite(col, row, width, height);
	}

}

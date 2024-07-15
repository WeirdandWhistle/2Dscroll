package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

	public int worldX,worldY;
	public int speed;
	public String dir; 	
	public BufferedImage sprite2D;
	public boolean collision;
	public Rectangle solidHitbox;
	public int x,y;
}

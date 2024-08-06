package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import scoll2D.Panel2D;

public class Entity {

	public int worldX,worldY;
	public int speed;
	public String dir; 	
	public BufferedImage sprite2D;
	public boolean collision;
	public Rectangle solidHitbox;
	public int x,y;
	public Panel2D p;
	
	public Entity(Panel2D p) {
		this.p = p;
	}
	public double norm(double speed) {
		return speed * p.norm;
	}
}

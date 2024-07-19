package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import scoll2D.Panel2D;

public class SuperObject {
	public BufferedImage img;
	public int WX, WY;
	public String name;
	public boolean collision = false;
	
	public void draw(Graphics2D g2d, Panel2D p) {
		if(!p.platformer) {
			int worldCol = 0;
			int worldRow = 0;
			
			while(worldCol < p.maxWorldCol && worldRow < p.maxWorldRow) {
		
				
				int screenX = WX - p.player.worldX + p.player.screenX;
				int screenY = WY - p.player.worldY + p.player.screenY;
				
				g2d.drawImage(img, screenX, screenY, p.tileSize, p.tileSize,null);
				
				worldCol++;
				
		
				if(worldCol == p.maxWorldCol) {
					worldCol = 0;
					worldRow++;
				}
			}
		}
	}
}

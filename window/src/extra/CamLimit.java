package extra;

import java.awt.Rectangle;
import java.util.ArrayList;

import scoll2D.Panel2D;

public class CamLimit {
	
	Panel2D p;
	ArrayList<Rectangle> limits = new ArrayList<>();
	boolean done = false;
	
	public CamLimit(Panel2D p) {
		this.p = p;
	}
	public void worldLimit() {
		if(p.player.screenX >= p.centerScreenX - (p.tileSize/8) && p.player.screenX <= p.centerScreenX + (p.tileSize/8)) {
			p.XF = true;
		}
		else {p.XF =false;}
		if(p.player.screenY >= p.centerScreenY - (p.tileSize/8) && p.player.screenY <= p.centerScreenY + (p.tileSize/8)) {
			p.YF = true;
			
		}
		else {p.YF = false;}
		
		if(p.cameraX < 0) {
			p.XF = false;
			p.cameraX = 0;
		}
		if(p.cameraY < 0) {
			p.YF = false;
			p.cameraY = 0;
		}
		if(p.cameraX > (p.worldWidth) - p.width) {
			p.XF = false;
			p.cameraX = (p.worldWidth) - p.width;
		}
		if(p.cameraY > (p.worldHeight) - p.height) {
			p.XF = false;
			p.cameraY= (p.worldHeight) - p.height;
		}
	}
	public void addLimit(Rectangle limit) {
		limits.add(limit);
		toWorld(limits.size() - 1);
	}
	public void snapLimit() {
		
		if(!p.platformer) {
		if(limits != null) {
			
			Rectangle compare = new Rectangle(p.player.worldX,p.player.worldY,p.tileSize,p.tileSize);
			for(int i = 0; i <= limits.size() - 1; i++) {
				
				if(limits.get(i).intersects(compare) == true ) {
					System.out.println("here");
					if(p.cameraX < limits.get(i).x) {
						p.XF = false;
						p.cameraX = limits.get(i).x;
					}
					if(p.cameraY < limits.get(i).y) {
						p.YF =false;
						p.cameraY = limits.get(i).y;
					}
					if(p.cameraX > limits.get(i).width - p.width) {
						p.XF = false;
						p.cameraX = limits.get(i).width - p.width;
					}
					if(p.cameraY > limits.get(i).height - p.height) {
						p.YF =false;
						p.cameraY = limits.get(i).height - p.height;
					}
					done = false;
				}
				else if(limits.get(i).intersects(compare) == false && !done) {
					int offSetX = p.player.screenX - p.centerScreenX;
					p.cameraX += offSetX;
					p.player.screenX += offSetX;
					
					int offSetY = p.player.screenY - p.centerScreenY;
					p.cameraY += offSetY;
					p.player.screenY += offSetY;
					done = true;
				}
			}
		}
		}
	}
	public void toWorld(int i) {
		limits.get(i).x *= p.tileSize;
		limits.get(i).y *= p.tileSize;
		limits.get(i).width *= p.tileSize;
		limits.get(i).height *= p.tileSize;
	}
	

}

package extra;

import scoll2D.Panel2D;

public class CamLimit {
	
	Panel2D p;
	
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

}

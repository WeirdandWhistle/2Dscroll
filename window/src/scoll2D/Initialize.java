package scoll2D;

import object.OBJ_potato;

public class Initialize {
	Panel2D p;
	public Initialize(Panel2D p) {
		
		this.p = p;
		
	}
	
	public void intit() {
		p.obj[0] = new OBJ_potato();
		p.obj[0].WX = 5 * p.tileSize;
		p.obj[0].WY = 5 * p.tileSize;
	}

}

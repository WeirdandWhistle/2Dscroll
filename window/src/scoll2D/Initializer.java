package scoll2D;

import java.awt.Rectangle;

import object.OBJ_barrel;
import object.OBJ_potato;


public class Initializer {
	Panel2D p;
	public Initializer(Panel2D p) {
		
		this.p = p;
		
	}
	
	public void intit() {
		//OBJECTS
		int objNum =0;
		for(int i = 0; i < p.obj.length; i ++) {
			if(p.obj[i] != null) {
				p.obj[i].intitHitbox(p,p.obj[i]);
			}
		}
		
		//POTATOS
		p.obj[objNum] = new OBJ_potato(p, 5 * p.tileSize,5 * p.tileSize);
		objNum++;
		
		//BARRELS
		p.obj[objNum] = new OBJ_barrel(p,10 * p.tileSize, 10 * p.tileSize);
		objNum++;
		
		//ROOMS
		p.roomH.addRoom(new Rectangle(0,0,9,9));
//		p.camL.addLimit(new Rectangle(0,0,19,9));
		
		p.roomH.addRoom(new Rectangle(10,0,9,9));
		
		p.roomH.addRoom(new Rectangle(0,10,9,9));
		
		p.roomH.addRoom(new Rectangle(10,10,9,9));
		
		
	}

}

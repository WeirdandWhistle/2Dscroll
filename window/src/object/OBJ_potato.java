package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import scoll2D.Panel2D;


public class OBJ_potato extends SuperObject {
	
	public OBJ_potato(Panel2D p) {
		intitHitbox(p,this);
		name = "potato";
		
		try {
			img = ImageIO.read(new File("res\\objects\\potato.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	public OBJ_potato(Panel2D p,int WX, int WY) {
		intitHitbox(p,this);

		this.WX = WX;
		this.WY = WY;
		
		name = "potato";
		
		try {
			img = ImageIO.read(new File("res\\objects\\potato.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

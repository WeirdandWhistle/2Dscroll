package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import scoll2D.Panel2D;

public class OBJ_barrel extends SuperObject{

	public OBJ_barrel(Panel2D p) {
		intitHitbox(p,this);
		name = "barrel";
		
		collision = true;
		
		try {
			img = ImageIO.read(new File("res\\objects\\barrel.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
public OBJ_barrel(Panel2D p,int WX, int WY) {
		this.WX = WX; 
		this.WY = WY;
		name = "barrel";
		
		collision = true;
		
		intitHitbox(p,this);
		
		try {
			img = ImageIO.read(new File("res\\objects\\barrel.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

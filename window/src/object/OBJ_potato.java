package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class OBJ_potato extends SuperObject {
	
	public OBJ_potato() {
		
		name = "potato";
		
		try {
			img = ImageIO.read(new File("res\\objects\\potato.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	public OBJ_potato(int WX, int WY) {
		this.WX = WX;
		this.WY = WY;
	}
}

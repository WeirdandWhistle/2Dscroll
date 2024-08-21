package material;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import extra.SpriteSheet;
import extra.UTool;
import scoll2D.Panel2D;

public class Material {
	
	Panel2D p;
	BufferedImage stillImg;
	String name;
	
	public Material(Panel2D p) {
		this.p=p;
	}
	public BufferedImage setup(String filePath) {
		UTool ut = new UTool();
		BufferedImage img = null;
		
		try {
			img = ImageIO.read(new File(filePath));
			img = ut.scaleImage(img, p.tileSize, p.tileSize);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}
	public BufferedImage setupSprite(String filePath,int col,int row,int width,int height) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SpriteSheet ss = new SpriteSheet(img);
		return ss.getSprite(col, row, width, height);
	}

}

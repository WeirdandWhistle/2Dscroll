package player;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import scoll2D.Panel2D;

public class Platformer {
	
	Panel2D p;
	BufferedImage playerSprite;
	private BufferedImage leftStill;
	boolean ll=true;
	boolean lr=false;
	
	public int playerHeight = 334/2;
	public double playerYVelo = 0;
	public int playerWidth =112/2;
	public int playerX = 0;
	public double playerY;
	public double playerSpeed = 5;
	public boolean grounded = true;
	public double floor;
	public Platformer(Panel2D p) {
		this.p=p;
		playerY = p.height-playerHeight;
		floor = p.width; 
		try {
			leftStill = ImageIO.read(new File("res\\character.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void platformRender() {
		playerY+=playerYVelo/10;
		playerYVelo+=(p.gravity);	
		if(p.sprint) playerSpeed = 7;
		else if(!p.sprint) playerSpeed = 3;
		if(p.d) playerX += playerSpeed; ll=true; lr=false;
		if(p.a) playerX -= playerSpeed; lr = true; ll=false;
		if (!p.d&&!p.a&&ll) playerSprite = leftStill;
		if(p.space&&grounded) { 
		grounded=false;
			playerYVelo-=(p.jumpPower);
		}
		if(playerYVelo<0) {
			if(playerY<=0) {playerYVelo=1/p.gameTicks; playerY=0;}
		}
		if(playerYVelo>0) {
			if(playerY+playerHeight>floor) {playerYVelo=0;playerY=floor-playerHeight;grounded=true;}
		}
	}
	public void draw(Graphics2D g2d) {
		if(p.platformer)g2d.drawImage(playerSprite,playerX, (int)playerY, playerWidth, playerHeight,null);

	}

}

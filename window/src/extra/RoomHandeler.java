package extra;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;

import scoll2D.Panel2D;

public class RoomHandeler {
	
	HashMap<Integer,Rectangle> rooms = new HashMap<>();
	Panel2D p;
	int currentRoom = 999;
	int index = 0;
	int roomNum = 1;
	
	
	
	public RoomHandeler(Panel2D p) {
		this.p = p;
	}
	
	
	public void cover(Graphics2D g2d) {
		
		
		if(!p.platformer && rooms != null) {
			Rectangle compare = new Rectangle(p.player.worldX,p.player.worldY,p.tileSize,p.tileSize);
			
			for(int i = 1; i <= rooms.size(); i++) {
				
				if(rooms.get(i).intersects(compare) == false) {
				
					g2d.setColor(new Color(0,0,0,230));
					g2d.fillRect(rooms.get(i).x - p.cameraX, rooms.get(i).y - p.cameraY, rooms.get(i).width + 1, rooms.get(i).height);
				}
				else if(rooms.get(i).intersects(compare) == true) {
					currentRoom = i;
				}
				else {
					currentRoom = 999;
				}
			}
			
		}
	}
	public void toWorld(int i) {
		rooms.get(i).x *= p.tileSize;
		rooms.get(i).y *= p.tileSize;
		rooms.get(i).width *= p.tileSize;
		rooms.get(i).height *= p.tileSize;
	}
	public void addRoom(Rectangle rect) {
		rooms.put(roomNum,rect);
		toWorld(roomNum);
		roomNum++;
	}
	
	

}

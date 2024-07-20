package entity;

import java.awt.Rectangle;

import scoll2D.Panel2D;

public class CollisionChecker {
	Panel2D p;
	public CollisionChecker(Panel2D p) {
		this.p=p;
	}
	public void check(Entity entity) {
		if(entity.dir != null) {
		int entityLeftWorldX = entity.worldX + entity.solidHitbox.x;
		int entityRightWorldX = entityLeftWorldX + entity.solidHitbox.width;
		int entityTopWorldY = entity.worldY + entity.solidHitbox.y;
		int entityBottomWorldY= entityTopWorldY + entity.solidHitbox.height;
		
		int entityLeftCol = entityLeftWorldX/p.tileSize;
		int entityRightCol = entityRightWorldX/p.tileSize;
		int entityTopRow = entityTopWorldY/p.tileSize;
		int entityBottomRow = entityBottomWorldY/p.tileSize;
		
		int num1,num2;
		switch(entity.dir) {
		case "up": entityTopRow = (entityTopWorldY - entity.speed) / p.tileSize;
			num1 = p.tileM.tileNum[entityRightCol][entityTopRow];
			num2 = p.tileM.tileNum[entityLeftCol][entityTopRow];
			if(p.tileM.tile[num1].col == true || p.tileM.tile[num2].col == true) entity.collision = true;
			else entity.collision = false;

			break;
		case "down": entityBottomRow = (entityBottomWorldY + entity.speed) / p.tileSize;
			num1 = p.tileM.tileNum[entityRightCol][entityBottomRow];
			num2 = p.tileM.tileNum[entityLeftCol][entityBottomRow];
			if(p.tileM.tile[num1].col == true || p.tileM.tile[num2].col == true) entity.collision = true;
			else entity.collision = false;
			break;
		case "left": entityLeftCol = (entityLeftWorldX - entity.speed) / p.tileSize;
			num1 = p.tileM.tileNum[entityLeftCol][entityTopRow];
			num2 = p.tileM.tileNum[entityLeftCol][entityBottomRow];
			if(p.tileM.tile[num1].col == true || p.tileM.tile[num2].col == true) entity.collision = true;
			else entity.collision = false;

			break;
		case "right": entityRightCol = (entityRightWorldX + entity.speed) / p.tileSize;
			num1 = p.tileM.tileNum[entityRightCol][entityTopRow];
			num2 = p.tileM.tileNum[entityRightCol][entityBottomRow];
			if(p.tileM.tile[num1].col == true || p.tileM.tile[num2].col == true) entity.collision = true;
			else entity.collision = false;
			break;
		case "ul": entityTopRow = (entityTopWorldY - entity.speed) / p.tileSize;
		entityBottomRow = (entityBottomWorldY + entity.speed) / p.tileSize;
		entityLeftCol = (entityLeftWorldX - entity.speed) / p.tileSize;
		entityRightCol = (entityRightWorldX + entity.speed) / p.tileSize;
			num1 = p.tileM.tileNum[entityLeftCol][entityTopRow];
			num2 = p.tileM.tileNum[entityLeftCol][entityTopRow];
			if(p.tileM.tile[num1].col == true || p.tileM.tile[num2].col == true) entity.collision = true;
			else entity.collision = false;

			break;
		case "ur": entityTopRow = (entityTopWorldY - entity.speed) / p.tileSize;
		entityBottomRow = (entityBottomWorldY + entity.speed) / p.tileSize;
		entityLeftCol = (entityLeftWorldX - entity.speed) / p.tileSize;
		entityRightCol = (entityRightWorldX + entity.speed) / p.tileSize;
			num1 = p.tileM.tileNum[entityRightCol][entityTopRow];
			num2 = p.tileM.tileNum[entityRightCol][entityTopRow];
			if(p.tileM.tile[num1].col == true || p.tileM.tile[num2].col == true) entity.collision = true;
			else entity.collision = false;
			break;
		case "dl": entityTopRow = (entityTopWorldY - entity.speed) / p.tileSize;
		entityBottomRow = (entityBottomWorldY + entity.speed) / p.tileSize;
		entityLeftCol = (entityLeftWorldX - entity.speed) / p.tileSize;
		entityRightCol = (entityRightWorldX + entity.speed) / p.tileSize;
			num1 = p.tileM.tileNum[entityLeftCol][entityBottomRow];
			num2 = p.tileM.tileNum[entityLeftCol][entityBottomRow];
			if(p.tileM.tile[num1].col == true || p.tileM.tile[num2].col == true) entity.collision = true;
			else entity.collision = false;
			break;
		case "dr": entityTopRow = (entityTopWorldY - entity.speed) / p.tileSize;
		entityBottomRow = (entityBottomWorldY + entity.speed) / p.tileSize;
		entityLeftCol = (entityLeftWorldX - entity.speed) / p.tileSize;
		entityRightCol = (entityRightWorldX + entity.speed) / p.tileSize;
			num1 = p.tileM.tileNum[entityRightCol][entityBottomRow];
			num2 = p.tileM.tileNum[entityRightCol][entityBottomRow];
			if(p.tileM.tile[num1].col == true || p.tileM.tile[num2].col == true) entity.collision = true;
			else entity.collision = false;
			break;
			
			
			}
		}
	}
	public int OBJCheck(Entity entity, boolean player) {
		
		int index = 999;
		
		for(int i = 0; i < p.obj.length;i++) {
			
			if(p.obj[i] != null) {
				
				Rectangle e = new Rectangle(entity.solidHitbox.x + entity.worldX, entity.solidHitbox.y + entity.worldY, entity.solidHitbox.width,entity.solidHitbox.height);
				
				
				Rectangle OBJ = new Rectangle(p.obj[i].solidArea);
				
				OBJ.x += p.obj[i].WX;
				OBJ.y += p.obj[i].WY;
					
				if(entity.dir != null) {
				switch(entity.dir) {
				case "up": e.y -= entity.speed;
					if(e.intersects(OBJ)) {
						if(p.obj[i].collision) {
							entity.collision = true;
						}
						if(player) {
							index = i;
						}
					}
					break;
				case "down": e.y += entity.speed;
				if(e.intersects(OBJ)) {
					if(p.obj[i].collision) {
						entity.collision = true;
					}
					if(player) {
						index = i;
					}				}
					break;
				case "left": e.x -= entity.speed;
				if(e.intersects(OBJ)) {
					if(p.obj[i].collision) {
						entity.collision = true;
					}
					if(player) {
						index = i;
					}				}
					break;
				case "right": e.x += entity.speed;
				if(e.intersects(OBJ)) {
					if(p.obj[i].collision) {
						entity.collision = true;
					}
					if(player) {
						index = i;
					}				}
					break;
				case "ur":e.x += entity.speed; e.y -= entity.speed;
				if(e.intersects(OBJ)) {
					if(p.obj[i].collision) {
						entity.collision = true;
					}
					if(player) {
						index = i;
					}				}
					break;
				case "ul":e.x -= entity.speed; e.y -= entity.speed;
				if(e.intersects(OBJ)) {
					if(p.obj[i].collision) {
						entity.collision = true;
					}
					if(player) {
						index = i;
					}				}
					break;
				case "dr":e.x += entity.speed;e.y += entity.speed;
				if(e.intersects(OBJ)) {
					if(p.obj[i].collision) {
						entity.collision = true;
					}
					if(player) {
						index = i;
					}				}
					break;
				case "dl":e.x -= entity.speed;e.y += entity.speed;
				if(e.intersects(OBJ)) {
					if(p.obj[i].collision) {
						entity.collision = true;
					}
					if(player) {
						index = i;
					}				}
					break;
				
				}
			}
			}
		}
		return index;
	}
}

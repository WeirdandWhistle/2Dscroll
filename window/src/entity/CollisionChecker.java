package entity;

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
			
			
		}
		
		}
	}
}

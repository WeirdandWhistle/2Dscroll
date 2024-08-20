package entity;

import scoll2D.Panel2D;

public class NPC_boss extends Entity{
	
	public NPC_boss(Panel2D p) {
		super(p);
		
		dir = "up";
		speed = 2;
		worldX = 7 * p.tileSize;
		worldY = 8 * p.tileSize;
		getImage();
		setDialogue();
	}
	public void setDialogue() {
		dialogue[0] = "what up chump!";
		dialogue[1] = "on and on and on and on and on and on and \non and on and on and on and on and on"; 
	}
	public void getImage() {
		up1 = setupSprite("res\\npc\\bossSprite.png",0,0,16,16);
		left1 = setupSprite("res\\npc\\bossSprite.png",0,1,16,16);
		down1 = setupSprite("res\\npc\\bossSprite.png",1,1,16,16);
		right1 = setupSprite("res\\npc\\bossSprite.png",1,0,16,16);
	}
	public void setAction() {
		
		if(actionFrameCounter == 60 *2 || collision ) {
			
			
			int ran = (int) Math.round((Math.random() *100) + 1);
			if(ran <=25) dir = "up";
			if(ran > 25 &&ran <=50) dir = "down";
			if(ran > 50 && ran <=75)dir = "right";
			if(ran > 75 && ran <= 100)dir = "left";
			actionFrameCounter = 0;
//			System.out.println(ran);
			
			
		}
		
		
		
		actionFrameCounter++;
	}
	public void speak() {
		if(dialogue[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		
		p.ui.currentDialogue = dialogue[dialogueIndex];
		dialogueIndex++;
		
	}

}

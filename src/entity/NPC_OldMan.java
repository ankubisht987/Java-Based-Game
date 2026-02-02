package entity;


import java.util.Random;

import main.Gamepanel;


public class NPC_OldMan extends Entity{
	
	public NPC_OldMan(Gamepanel gp) {

		super(gp);
		
		direction = "down";
		speed = 1;
		
		getImage();
		setDialogue();
	}
	//NPC image start
		public void getImage() {
			
			
			up1 =setup("/npc/oldman_up_1");
			up2 =setup("/npc/oldman_up_2");
			down1 =setup("/npc/oldman_down_1");
			down2 =setup("/npc/oldman_down_2");
			left1 =setup("/npc/oldman_left_1");
			left2 =setup("/npc/oldman_left_2");
			right1 =setup("/npc/oldman_right_1");
			right2 =setup("/npc/oldman_right_2");
			
			
		}
		public void setDialogue() {
			dialogues[0] = "Hello, I am GolD.Roger. \n ONE PIECE is REAL";                                              // write the text 
			dialogues[1] = "\"My treasure? Why it's right where I left it...\n But you'll have to search the whole world to find it.\n 'My treasure? If you want it, \nyou can have it.'";
			dialogues[2] = "";
			dialogues[3] = "Hello, I am GolD.Roger.";
		}
	public void setAction() {
		actionLockCounter ++;
		if(actionLockCounter == 120) {
			
			Random random = new Random();
			int i = random.nextInt(100)+1;
			
			if(i<= 25) {
				direction = "up";
			}
			if(i>25 && i<= 50) {
				direction = "down";
				
			}
			if(i>50 && i<=75) {
				direction = "left";
			}
			if(i>75 && i<= 100) {
				direction = "right";
			}
			
			actionLockCounter = 0;
			
		}
	
	}
	public void speak() {
		
		// do this charactere spesific stuff
		super.speak();
		
	}

	
	
}

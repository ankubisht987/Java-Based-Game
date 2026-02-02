package entity;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Gamepanel;
import main.KeyHandler;
import main.UtilityTool;
public class Player extends Entity{
	
	
	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	public int hasKey =0; // key number                          video 13                        this
	private int standCounter =0;
	
	
	public Player(Gamepanel gp, KeyHandler keyH) {
		
		super(gp);
	
		this.keyH = keyH;
		screenX = gp.screenWidth/2 - (gp.titleSize/2);
		screenY = gp.screenHeight/2 - (gp.titleSize/2);
		//area of the character
		solidArea =new Rectangle();
		solidArea.x=8;
		solidArea.y =16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width =32;
		solidArea.height =32;
		
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		
		worldX= gp.titleSize * 23;
		
		worldY= gp.titleSize * 21;
		speed= 4;
		direction ="down";
	}
	//player image start
	public void getPlayerImage() {
		
		
		up1 =setup("/Player/boy_up_1");
		up2 =setup("/Player/boy_up_2");
		down1 =setup("/Player/boy_down_1");
		down2 =setup("/Player/boy_down_2");
		left1 =setup("/Player/boy_left_1");
		left2 =setup("/Player/boy_left_2");
		right1 =setup("/Player/boy_right_1");
		right2 =setup("/Player/boy_right_2");
		
		
	}
	//player speed 
	public void update() {
		if(keyH.upPressed == true ||  keyH.downPressed == true || 
				keyH.leftPressed==true ||keyH.rightPressed == true) {
			
		if(keyH.upPressed == true) {
			direction = "up";
						
		}
		else if(keyH.downPressed == true) {
			direction ="down";
			
		}
		else if(keyH.leftPressed == true) {
			direction = "left";
			
		}
		else if(keyH.rightPressed == true) {
			direction ="right";
			
		}
		//check tile collision
		collisionOn =false;
		gp.Checker.checkTile(this);
		
		//check object collision
		int objIndex = gp.Checker.checkObject(this, true);
		pickUpObject(objIndex);
		
		//check npc collision
		int npcIndex = gp.Checker.checkEntity(this,gp.npc);
		interactNPC(npcIndex);
		
		//if collision is false , player is move
		if(collisionOn == false) {
			
			switch(direction) {
			case "up": worldY -= speed; break;
			case "down": worldY += speed; break;
			case "left": worldX -= speed; break;
			case "right": worldX += speed; break;
				
			}		
		}
		spriteCounter++;
		if(spriteCounter > 12) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		}
		else {
			standCounter++;
			if(standCounter == 20) {
				spriteNum = 1;
				standCounter = 0;
				
			}
		}
	}
	
	public void  pickUpObject(int i) {
		if(i !=999) {
			
			                                                     // video 13              this 
		String objectName = gp.obj[i].name;
		
		switch (objectName) {
		case "Key":
			gp.playSE(1);
		hasKey++;
		gp.obj[i] = null;
		gp.ui.showMessage(" Ke key");
		break;
		case "Door":
			if(hasKey>0) {
				gp.playSE(3);
				gp.obj[i] = null;
				hasKey--;
				gp.ui.showMessage(" Door");
				
			}
			else {
				gp.ui.showMessage(" key!");
			}
			
			break;
		case "Boots"://fast the character
			gp.playSE(2);
			speed +=2;
			gp.obj[i] =null;
			gp.ui.showMessage("Speed Up");
			break;
		case "Chest":
			gp.ui.gameFinished =true;
			gp.stopMusic();
			gp.playSE(4);
		
			break;                                                             // up to this 
		}}
		}
		
	private void interactNPC(int i) {
		if(i !=999) {
//		System.out.println("You are hiting npc");                        video 15
			if(gp.keyH.enterPressed == true) {
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
			
		}
		gp.keyH.enterPressed = false;
	}
	public void draw(Graphics2D g2) {
		

		//player direction
		BufferedImage image =null;
		
		switch(direction) {
		
		case "up":
			if(spriteNum == 1) {
			image =up1;
			}
			if(spriteNum ==2) {
				image =up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
			image = down1;
			}
			if(spriteNum ==2) {
				image =down2;
			}
			break;
		case "left":
			if(spriteNum ==1) {
			image = left1;
			}
			if(spriteNum ==2) {
				image =left2;
			}
			break;
		case "right":
			if(spriteNum ==1) {
			image = right1;
		}
			if(spriteNum ==2) {
				image =right2;
			}
			break;
		
		
		}
		g2.drawImage(image , screenX,screenY ,null);
			
			

	}

}

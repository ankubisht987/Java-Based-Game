package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Gamepanel;
import main.UtilityTool;

public class Entity {

	Gamepanel gp;
	public int worldX , worldY;
	public int speed;
	public BufferedImage up1,up2, down1,down2, left1,left2 , right1,right2;
	public String direction;
	public int spriteCounter =0;
	public int spriteNum =1;
	public Rectangle solidArea = new Rectangle(0,0, 48, 48);//area of the character
	public int solidAreaDefaultX , solidAreaDefaultY;
	public boolean collisionOn = false;//area of the character
	public int actionLockCounter =0;
	String dialogues[] = new String[20];                                       // change the dialogue using this number
	int dialogueIndex =0;
	public Entity(Gamepanel gp) {
		this.gp = gp;
	}	
	
	public void setAction() {}
	public void speak() {
		

		if(dialogues[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;
		
		switch(gp.Player.direction) {
		case "up":
			direction = "down";
			break;
		case "down":
			direction = "up";
			break;
		case "left":
			direction = "right";
			break;
		case "right":
			direction = "left";
			break;
			
		}
		
		
	}
	public void update() {
		
		setAction();
		
		collisionOn = false;
		gp.Checker.checkTile(this);
		gp.Checker.checkObject(this, false);
		gp.Checker.checkPlayer(this);
		
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
	public void draw(Graphics2D g2) {
		BufferedImage image =null;
		
		int screenX =worldX -gp.Player.worldX + gp.Player.screenX;
		int screenY = worldY - gp.Player.worldY + gp.Player.screenY;
		
		if(worldX + gp.titleSize > gp.Player.worldX - gp.Player.screenX &&
           worldX - gp.titleSize< gp.Player.worldX + gp.Player.screenX &&
	       worldY + gp.titleSize> gp.Player.worldY - gp.Player.screenY&&
		   worldY - gp.titleSize< gp.Player.worldY + gp.Player.screenY) {
			
			
			
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
		g2.drawImage(image , screenX, screenY ,gp.titleSize , gp.titleSize,null);
		}
		
	}
	public BufferedImage setup(String imagePath) {
		UtilityTool uTool =new UtilityTool();
		BufferedImage Image =null;
		try {
			Image= ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			Image = uTool.scaleImage(Image,gp.titleSize, gp.titleSize);
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return Image;
	}
}

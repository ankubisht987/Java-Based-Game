package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Gamepanel;
import main.UtilityTool;

public class SuperObject {

	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	public Rectangle solidArea = new Rectangle(0,0,48,48);
	public int solidAreaDefaultX =0;
	public int solidAreaDefaultY =0;
	UtilityTool uTool = new UtilityTool();
	
	public void draw(Graphics2D g2, Gamepanel gp) {
		
		int screenX =worldX -gp.Player.worldX + gp.Player.screenX;
		int screenY = worldY - gp.Player.worldY + gp.Player.screenY;
		
		if(worldX + gp.titleSize > gp.Player.worldX - gp.Player.screenX &&
           worldX - gp.titleSize< gp.Player.worldX + gp.Player.screenX &&
	       worldY + gp.titleSize> gp.Player.worldY - gp.Player.screenY&&
		   worldY - gp.titleSize< gp.Player.worldY + gp.Player.screenY) {
		g2.drawImage(image , screenX, screenY ,gp.titleSize , gp.titleSize,null);
		}
		
	}
}

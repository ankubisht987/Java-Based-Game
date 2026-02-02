package tile;


import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.Gamepanel;
import main.UtilityTool;

public class TileManager {
Gamepanel gp;
public Tile[] tile;
public int mapTileNum[][];

public TileManager(Gamepanel gp) {
	this.gp = gp;
	tile =new Tile[50];
	mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
	getTileImage();
	loadMap("/maps/worldV2.txt");
}
public void getTileImage() {

		                                    //place holders universal true for walk
		setup(0,"grass00", false);
		setup(1,"grass00", false);
		setup(2,"grass00", false);
		setup(3,"grass00", false);
		setup(4,"grass00", false);
		setup(5,"grass00", false);
		setup(6,"grass00", false);
		setup(7,"grass00", false);
		setup(8,"grass00", false);
		setup(9,"grass00", false);
	
		                                            //place holder
		setup(10,"grass00", false);
		setup(11,"grass01", false);
		
		                                              // not for walk
		setup(12,"water00", true);
		setup(13,"water01", true);
		setup(14,"water02", true);
		setup(15,"water03", true);
		setup(16,"water04", true);
		setup(17,"water05", true);
		setup(18,"water06", true);
		setup(19,"water07", true);
		setup(20,"water08", true);
		setup(21,"water09", true);
		
		setup(22,"water10", true);
		setup(23,"water11", true);
		setup(24,"water12", true);
		setup(25,"water13", true);
		                                               // for walk
		setup(26,"road00", false);
		setup(27,"road01", false);
		setup(28,"road02", false);
		setup(29,"road03", false);
		setup(30,"road04", false);
		setup(31,"road05", false);
	
		setup(32,"road06", false);
		setup(33,"road07", false);
		setup(34,"road08", false);
		setup(35,"road09", false);
		setup(36,"road10", false);
		setup(37,"road11", false);
		setup(38,"road12", false);
		                               // for walk
		setup(39,"earth", false);
	                                      	//not for walk
		setup(40,"wall", true);
		                                            //not for walk
		setup(41,"tree", true);
		
	
	
}
public void setup(int index, String imageName, boolean collision) {
	UtilityTool uTool = new UtilityTool();
	try {
		tile[index]=new Tile();
		tile[index].image=ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName +".png"));
		tile[index].image= uTool.scaleImage(tile[index].image, gp.titleSize, gp.titleSize);
	tile[index].collision = collision;
	}
	catch(IOException e) {
		e.printStackTrace();
	}
	
}
public void loadMap(String filePath) {
	
	try {
		InputStream is = getClass().getResourceAsStream(filePath);
		BufferedReader br= new BufferedReader(new InputStreamReader(is));
		int col = 0;
		int row = 0;
		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
			
			String line = br.readLine();
			
			while(col < gp.maxWorldCol) {
				
				String numbers[] = line.split(" ");
				int num = Integer.parseInt(numbers[col]);
				mapTileNum[col][row]=num;
				col++;
				
			}
			if(col ==gp.maxWorldCol) {
				col=0;
				row++;
			}
		}
		br.close();
	}
	catch(Exception e) {
		
	}
}
public void draw(Graphics2D g2) {
	
	int worldCol = 0;
	int worldRow = 0;

	
	while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
		
		int tileNum = mapTileNum[worldCol][worldRow];
		int worldX = worldCol * gp.titleSize;
		int worldY = worldRow * gp.titleSize;
		int screenX = worldX - gp.Player.worldX + gp.Player.screenX;
		int screenY = worldY - gp.Player.worldY + gp.Player.screenY;
		
		if(worldX + gp.titleSize > gp.Player.worldX - gp.Player.screenX &&
           worldX - gp.titleSize< gp.Player.worldX + gp.Player.screenX &&
	       worldY + gp.titleSize> gp.Player.worldY - gp.Player.screenY&&
		   worldY - gp.titleSize< gp.Player.worldY + gp.Player.screenY) {
		g2.drawImage(tile[tileNum].image , screenX, screenY ,null);
		}
		worldCol++;
		
		if(worldCol == gp.maxWorldCol) {
			worldCol = 0;
		
			worldRow++;
		
		}
	}
}
}

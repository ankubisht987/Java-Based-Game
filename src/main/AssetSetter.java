package main;

import entity.NPC_OldMan;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

	Gamepanel gp;
	
	public AssetSetter(Gamepanel gp) {
		this.gp = gp;
	}
	public void setObject() {
		//video 13                                                                     this
//		//key 1
		gp.obj[0] = new OBJ_Key(gp);
		gp.obj[0].worldX = 23* gp.titleSize;
		gp.obj[0].worldY = 7* gp.titleSize;
		//key2
		gp.obj[1] = new OBJ_Key(gp);
		gp.obj[1].worldX = 23 * gp.titleSize;
		gp.obj[1].worldY = 40* gp.titleSize; 
		//key 3
		gp.obj[2] = new OBJ_Key(gp);
		gp.obj[2].worldX = 37 * gp.titleSize;
		gp.obj[2].worldY = 7* gp.titleSize; 
		//key 4
		gp.obj[3] = new OBJ_Key(gp);
		gp.obj[3].worldX = 35 * gp.titleSize;
		gp.obj[3].worldY = 27* gp.titleSize; 
		
		//door1
		gp.obj[4] = new OBJ_Door(gp);
		gp.obj[4].worldX = 10 * gp.titleSize;
		gp.obj[4].worldY = 11* gp.titleSize; 
		//door1,2
		gp.obj[5] = new OBJ_Door(gp);
		gp.obj[5].worldX = 11 * gp.titleSize;
		gp.obj[5].worldY = 11* gp.titleSize;
		
		//door2
		gp.obj[6] = new OBJ_Door(gp);
		gp.obj[6].worldX = 8 * gp.titleSize;
		gp.obj[6].worldY = 28* gp.titleSize; 
		
		//door3
		gp.obj[7] = new OBJ_Door(gp);
		gp.obj[7].worldX = 12 * gp.titleSize;
		gp.obj[7].worldY = 22* gp.titleSize; 
		
		//Chest1
		gp.obj[8] = new OBJ_Chest(gp);
		gp.obj[8].worldX = 10 * gp.titleSize;
		gp.obj[8].worldY = 7* gp.titleSize; 
		
		//boots1
		gp.obj[9] = new OBJ_Boots(gp);
		gp.obj[9].worldX = 37 * gp.titleSize;
		gp.obj[9].worldY = 42 * gp.titleSize;                                                    // up to this 
	}
	public void setNPC() {
		
		gp.npc[0] = new NPC_OldMan(gp);
		gp.npc[0].worldX = gp.titleSize*21;
		gp.npc[0].worldY = gp.titleSize*21;
	}
}

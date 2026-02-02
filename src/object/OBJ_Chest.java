package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Gamepanel;

public class OBJ_Chest extends SuperObject{
	Gamepanel gp;
	public OBJ_Chest(Gamepanel gp) {
		name ="Chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Chest.png"));
			uTool.scaleImage(image, gp.titleSize, gp.titleSize);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}


}

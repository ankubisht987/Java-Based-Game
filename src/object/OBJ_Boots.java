package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Gamepanel;

public class OBJ_Boots extends SuperObject {
	Gamepanel gp;
	public OBJ_Boots(Gamepanel gp) {
		name ="Boots";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
			uTool.scaleImage(image, gp.titleSize, gp.titleSize);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	
		
		
	}

	
}



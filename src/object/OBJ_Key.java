package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Gamepanel;

public class OBJ_Key extends SuperObject{
	Gamepanel gp;
	
	public OBJ_Key(Gamepanel gp) {
		name ="Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Key.png"));
			uTool.scaleImage(image, gp.titleSize, gp.titleSize);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		//solidArea.x =5;
		
		
	}

	
}

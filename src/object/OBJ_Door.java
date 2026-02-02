package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Gamepanel;

public class OBJ_Door extends SuperObject{
	Gamepanel gp;
	public OBJ_Door(Gamepanel gp) {
		name ="Door";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Door.png"));
			uTool.scaleImage(image, gp.titleSize, gp.titleSize);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}


}

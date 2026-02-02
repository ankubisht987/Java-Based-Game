package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import object.OBJ_Key;

public class UI {
	Gamepanel gp;
	Graphics2D g2;
	Font CHILLER,VINERITC;
	BufferedImage keyImage;                                                //this
	public boolean messageOn =false;
	public String message = "";
	int messageCounter = 0; //key time or message timing
	public boolean gameFinished = false;
	public String currentDialogue ="";                                           // for text in npc
	public int commandNum = 0; 
	public int titleScreenState = 0;// 0; the first screen  1:screen
	
	
	double playTime;                                                               //   video 15         this
	DecimalFormat dFormat = new DecimalFormat("#0.00");                               // up to this
	
	public UI (Gamepanel gp) {
		this.gp=gp;
		

		try {
			InputStream is = getClass().getResourceAsStream("/font/CHILLER.TTF");
			CHILLER = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/font/VINERITC.TTF");
			VINERITC = Font.createFont(Font.TRUETYPE_FONT, is);

		} catch (FontFormatException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	CHILLER = new Font("CHILLER",Font.PLAIN,30);                 //this 
		CHILLER = new Font("CHILLER", Font.BOLD,30);
		OBJ_Key key = new OBJ_Key(gp);
		keyImage = key.image;                                            // up to this
		
	}
	public void showMessage(String text) {
		message = text;
		messageOn =true;
		
	}
	public void draw(Graphics2D g2) {
		
                                                                             // this 
		
		if(gameFinished  == true) {
			g2.setFont(CHILLER);
			g2.setColor(Color.white);
			
			
			String text;
			int textLength;
			int x;
			int y;
			text = "you find the treasure";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			 x = gp.screenWidth/2 - textLength/2;
			 y = gp.screenHeight/2 -(gp.titleSize*3);
			 g2.drawString(text, x, y);
			 
			 
				text = "you time is :"+ dFormat.format(playTime)+ "!";
				textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
				 x = gp.screenWidth/2 - textLength/2;
				 y = gp.screenHeight/2 +(gp.titleSize*4);
				 g2.drawString(text, x, y);
				 
				 
			 
			 
			 g2.setFont(CHILLER);
				g2.setColor(Color.blue);
				text = "Congratulations!!";
				textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
				 x = gp.screenWidth/2 - textLength/2;
				 y = gp.screenHeight/2 +(gp.titleSize*2);
				 g2.drawString(text, x, y);
				 
				gp.gameThread = null;
				 
			
		}
		else {
			
			
			g2.setFont(CHILLER);
			g2.setColor(Color.white);
			g2.drawImage(keyImage, gp.titleSize/2,gp.titleSize/2,gp.titleSize,gp.titleSize,null);
			g2.drawString("x"+ gp.Player.hasKey,74,65);
			
			//time
			playTime +=(double)1/60;
			g2.drawString("Time:"+dFormat.format(playTime), gp.titleSize*11,65);
			//message
			if(messageOn == true) {
				
				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString(message , gp.titleSize/2,gp.titleSize*5);
				messageCounter++;
				//key timing
				if(messageCounter>120) {
					
					messageCounter=0;
					messageOn = false;
				}
		}
	
	}
	
		                                                                                           // up to this
		
		                                                                                    
		
		this.g2 = g2;
		
		g2.setFont(CHILLER);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);     // you can delete this line if you can
		g2.setColor(Color.white);
		
		// Title State
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
			
		}
		//play state
		if(gp.gameState == gp.playState) {
			//Do Playstate stuff latter
		}
		if(gp.gameState == gp.pauseState) {
			drawPausedScreen();
		}
		// Dialogue State
		if(gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
		}

	}
	public void drawTitleScreen() {
		
		if(titleScreenState ==0) {
			
			
			g2.setColor(new Color(0,0,0));                   // change the color in background sung this video 17
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);                            // video 17
			// Title name
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
			String text = "treasiure hunt";                                   //title of the game
			int x = getXforCenterText(text);
			int y = gp.titleSize*3;
			 
			// shadow
			g2.setColor(Color.gray);                              // for shadow
			g2.drawString(text, x+6 , y+6);                       // for shadow 
			// main color
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			// character image
			x= gp.screenWidth/2 - (gp.titleSize*2)/2;
			y+= gp.titleSize*2;
			g2.drawImage(gp.Player.down1, x, y, gp.titleSize*2, gp.titleSize*2,null);
			
			// menue
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
			
			text = "NEW GAME";                                        // for game menu
			x= getXforCenterText(text);
			y +=gp.titleSize*3.5;
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				
				g2.drawString(">", x-gp.titleSize, y);    //  out put->     >start game 
			}
			
			
			text = "LEVEL 1-10";
			x= getXforCenterText(text);
			y +=gp.titleSize;
			g2.drawString(text, x, y);
			
		if(commandNum == 1) {                                  // the out put gone                      
				
				g2.drawString(">", x-gp.titleSize, y);    //  out put->     >start game 
			}
			
			
			text = "QUIT";
			x= getXforCenterText(text);
			y +=gp.titleSize;
			g2.drawString(text, x, y);
			
		if(commandNum == 2) {                                     // the out put gone
				
				g2.drawString(">", x-gp.titleSize, y);    //  out put->     >start game 
			}
		}
		else if(titleScreenState==1) {
			
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(42F));
			
			String text = "SELECT YOU WANT";
			int x =getXforCenterText(text);
			int y = gp.titleSize*3;
			g2.drawString(text, x, y);
			
			text ="hard";
			x =getXforCenterText(text);
			 y += gp.titleSize*3;
			 g2.drawString(text, x, y);
			 if(commandNum == 0) {
				 g2.drawString(">",x-gp.titleSize,y);
			 }
			 
				text ="easy";
				x =getXforCenterText(text);
				 y += gp.titleSize;
				 g2.drawString(text, x, y);
				 if(commandNum == 1) {
					 g2.drawString(">",x-gp.titleSize,y);
				 }
				 
					text ="very hard";
					x =getXforCenterText(text);
					 y += gp.titleSize;
					 g2.drawString(text, x, y);
					 if(commandNum == 2) {
						 g2.drawString(">",x-gp.titleSize,y);
					 }
					 
						text ="Back";
						x =getXforCenterText(text);
						 y += gp.titleSize*2;
						 g2.drawString(text, x, y);
						 if(commandNum == 3) {
							 g2.drawString(">",x-gp.titleSize,y);
						 }
		}
		
	}
	public void drawPausedScreen() {
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		
		String text = "PAUSED";
		int x = getXforCenterText(text);	
		int y=  gp.screenHeight/2;
		g2.drawString(text, x, y);
		
	}
	public void drawDialogueScreen() {
		
		// Window
		int x = gp.titleSize*2;
		int y = gp.titleSize/2;
		int width = gp.screenWidth - (gp.titleSize*4);
		int height = gp.titleSize*4;
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));             // increase the size of text or change the text font 
		x+= gp.titleSize;
		y+= gp.titleSize;
		
		for (String line : currentDialogue.split("\n")) {                    // you can add any symbol using to break the line \n or any symbol
		
			g2.drawString(line, x, y);
			y += 40;
		}
		
	}
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c= new Color(0,0,0,210);   // using 220 to 100 you change the npc talk transpancy level
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);                                               // we can change
		
		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
		
	}
	public int getXforCenterText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x= gp.screenWidth/2 - length/2;
		return x;
	}

}

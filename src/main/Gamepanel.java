package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class Gamepanel extends JPanel implements Runnable{
	

	private static final long serialVersionUID = 1L;
	//SCREEN SETTING
	final int originalTitleSize =16;  //16*16 tile
	final int scale = 3;
	
	public final int titleSize = originalTitleSize * scale;  //48*48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = titleSize * maxScreenCol ; //768 pixels
	public final int screenHeight = titleSize * maxScreenRow; //578 pixels
	
	//world map setting
	public final int maxWorldCol =100;//tile size column
	public final int maxWorldRow =100;//tile size row
	
//	public final int worldWidth = titleSize * maxWorldCol;
//	public final int worldHeight = titleSize * maxWorldRow;
	
	
	// FPS 
	int FPS =60;
	
	TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this); // key handler                     //video13
    Sound music = new Sound();
    Sound se = new Sound();
	
	public CollisionChecker Checker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui =new UI(this);
	Thread gameThread;
	
	//ENTITY and object
	public Player Player = new Player(this,keyH);
	//set Player Default Position
	//keyHandler
	public SuperObject obj[]=new SuperObject[10];
	public Entity npc[] = new Entity[10];
	
	//Game State
	public int gameState;
	public final int titleState =0;                                 

	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	
	public Gamepanel() {
		this.setPreferredSize(new Dimension(screenWidth , screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);//keyHandler
		this.setFocusable(true);//keyHandler
	}
public  void setupGame() {
	
	aSetter.setObject();
	aSetter.setNPC();
	playMusic(0);
	stopMusic();                                                                  // to stop the music
	gameState = titleState;
}
	public void startGameThread() {
		gameThread =new Thread(this);
		gameThread.start();
	
	}

	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer =0;
		int drawCount =0;
		
		while (gameThread !=null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer +=(currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta>= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >=1000000000) {
//				System.out.println("FPS:"+ drawCount);
				drawCount =0;
				timer = 0;
			}
		}
	}
	public void update() {
		
		if(gameState == playState) {
			//Player
			Player.update();
			//npc
			for(int i= 0;i<npc.length;i++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}
		}
		if (gameState == pauseState) {
			//nothing
		}
		
//		Player.update();
		
		
	}
	
		public void paintComponent(Graphics g) {
			
			super.paintComponent(g);
			Graphics2D g2 =(Graphics2D)g;
			//Debug
			long drawStart =0;
			if(keyH.checkDrawTime == true) {
			drawStart =System.nanoTime();
			}
		                          	//Title screen
			if (gameState == titleState) {
				ui.draw(g2);
			}
			//others
			else {
				//tile
				tileM.draw(g2);
				//object
				for(int i = 0; i< obj.length; i++) {
					
				if(obj[i] != null) {
					obj[i].draw(g2, this);
				}
					
				}
				//NPC
				for(int i=0; i< npc.length; i++) {
					if(npc[i] != null) {
						npc[i].draw(g2);
					}
				}
				
				//player
				Player.draw(g2);  
				//UI
				ui.draw(g2);
			}
			
			
			//Debug
			if(keyH.checkDrawTime == true) {
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setColor(Color.white);
			g2.drawString("Draw Time: "+ passed, 10, 400);
			System.out.println("Draw Time:" +passed);
			}
			
			g2.dispose();
		}
		public void playMusic(int i) {
			
			music.setFile(i);
			music.play();
			music.loop();
			
			
		}
		public void stopMusic() {
			music.stop();
		}
		public void playSE(int i) {
			se.setFile(i);
			se.play();
		}
	}
	
	

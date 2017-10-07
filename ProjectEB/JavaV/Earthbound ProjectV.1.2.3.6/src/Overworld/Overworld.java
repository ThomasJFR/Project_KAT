package Overworld;

import java.awt.image.BufferedImage;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.Timer;

import Combat.CombatDisplay;
import Main.Display;
import Main.State;
import Main.StateManager;
import Tools.AnimationHandler;
import Tools.KeyInput;
import Tools.Textures;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

//'Illuminated' 'Illumerium' 'Illumility' 'Imperility' 'Moreality' 'Unity' 'Uninfinite' 'Terrale' 'Hope' 'Dreath' 'Dawn' 'Another Generic Tale' 'You' 'Us' 'Together' 'Fate' 'Your Tale' 'Your Story'

@SuppressWarnings("serial")
public class Overworld extends JPanel implements State{
	static int width = Main.Display.getScreenWidth(), height = Main.Display.getScreenHeight();
	public BufferedImage WhistletonN, player;
	public int location=1, i=(int)(width*1.2), i3=1, drawstate=0; //1=n,2=e,=3=s,4=w
	@SuppressWarnings("unused")
	private static int direction, xPos=0, yPos=0, xLstate, xRstate, yUstate, yDstate, KeyInputState=0;
	private AnimationHandler OverworldAnimations=new AnimationHandler();
	private static Rectangle eCollision, pCollision;
	private boolean OverworldRendition=true;
	public static int[][] whistleton= {
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, },
    };
     private Timer xL= new Timer(1, new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				direction=4;
				xPos+=1;
			}}), 
    		xR= new Timer(1, new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				direction=2;
				xPos-=1;
			}}), 
			yU= new Timer(1, new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				direction=1;
				yPos+=1;
			}}), 
			yD= new Timer(1, new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				direction=3;
				yPos-=1;
			}}), 
			countdown=new Timer(20, new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					if(i>300){
						i-=i3;
						i3+=1;
					}
					else if (i<=300){
						i3-=1;
					}
					if(i3<=-25){
						drawstate=2;
						countdown.stop();
						i=(int) (width*1.2);
						i3=1;
					}
				}});
     		
							    
		public Overworld(){
	            setFocusable(true);
	            setFocusTraversalKeysEnabled(false);
	            eCollision = new Rectangle(1000, 1000, 25, 50); //add to npc class
	            pCollision = new Rectangle((int)(Math.ceil(width/2)-22), (int)(Math.ceil(height/2)-60), 25, 60);
            try{
                player= Textures.getTexture(2, 1, 65, 80);//rename and reorganize if can
                WhistletonN=Textures.getMap(1);
                }
            catch(Exception ex)
            	{ex.printStackTrace();}
		}

	    public void checkCollisions() {
		    	if (xPos>=925){
					xPos=925;
				}
				if (yPos>=475){
					yPos=475;
				}
				if (yPos<=-655){
					yPos=-655;
				}
				if (xPos>=5000){
					xPos=4950;
				}
	            if (pCollision.intersects(eCollision)) {
	               KeyInputState=1;
	               if(drawstate==3){
	            	   
	               }
	               else if(drawstate!=2){
	               		drawstate=1;
	               		countdown.start();
	               }
	               	
	            }
	        }
		
		public static void setKeyInputState(int newIS){
			KeyInputState=newIS;
		}

		@Override
		public void tick(StateManager statemanager) {
			checkCollisions();
			if(KeyInputState==0){
				if (KeyInput.isKeyPressed(KeyEvent.VK_A))
					if (yUstate>=1){yUstate=2;yD.stop();}else{yUstate=1;}yU.start();
				if (KeyInput.isKeyPressed(KeyEvent.VK_D))
					if (xRstate>=1){xLstate=2;xR.stop();}else{xLstate=1;}xL.start();
				if (KeyInput.isKeyPressed(KeyEvent.VK_S))
					if (xLstate>=1){xRstate=2;xL.stop();}else{xRstate=1;}xR.start();
				if(KeyInput.isKeyPressed(KeyEvent.VK_W))
					if (yDstate>=1){yDstate=2;yU.stop();}else{yDstate=1;}yD.start();
				
				if (KeyInput.isKeyReleased(KeyEvent.VK_A))
					xL.stop();xLstate=0;//A
				if (KeyInput.isKeyReleased(KeyEvent.VK_D))
					xR.stop();xRstate=0;//D
				if (KeyInput.isKeyReleased(KeyEvent.VK_S))
					yD.stop();yDstate=0;//S
				if(KeyInput.isKeyReleased(KeyEvent.VK_W))
					yU.stop();yUstate=0;//W
			}
			else{
				xL.stop();
				xR.stop();
				yD.stop();
				yU.stop();
				xLstate=0;
				xRstate=0;
				yUstate=0;
				yDstate=0;
			}
			
		}

		@Override
		public void render(Graphics2D g) {//make specific render functions, perhaps a switch case
			g.setColor(Color.BLACK);
			if(OverworldRendition){
				g.fillRect( 0, 0, width, height);
			    g.drawImage(WhistletonN, xPos, yPos, this);
				g.drawImage(player, (int)(Math.ceil(width/2)-39), (int)(Math.ceil(height/2)-72), this);
				eCollision.setBounds(1000+xPos, 500+yPos, 25, 50);
				g.drawRect((int)pCollision.getX(),(int)pCollision.getY(),(int)pCollision.getWidth(),(int)pCollision.getHeight());
				g.drawRect((int)eCollision.getX(), (int)eCollision.getY(), (int)eCollision.getWidth(), (int)eCollision.getHeight());
			}
			if (drawstate==1){
				OverworldRendition=false;
				g.setColor(Color.BLACK);
				g.setStroke(new BasicStroke(70));
				g.drawOval((int)(Math.ceil(width/2)-(i/2)),(int)(Math.ceil(height/2)-(i/2)), i+20, i+20);
				g.drawOval((int)(Math.ceil(width/2)-(i/2)),(int)(Math.ceil(height/2)-(i/2)), i+20, i+20);
			}
			else if (drawstate==2){
				OverworldRendition=false;
				OverworldAnimations.FadeOut();
				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, OverworldAnimations.getAlpha()));
				g.fillRect(0, 0, width, height);
				if (OverworldAnimations.getAlpha()>=.6f){
					drawstate=3;
					CombatDisplay.createEnemies(2, 1, 0, 0, 0, 0);
					Display.statemanager.setState("Combat");
				}
			}
		}
		
		public String getName(){
			return "Overworld";
		}
}





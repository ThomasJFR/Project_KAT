package Tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.Timer;

import Combat.Combat;
import Combat.Player;

public class Text {
	
	private static int width = Main.Display.getScreenWidth(), height = Main.Display.getScreenHeight(), stringX=(int)(width*.23), stringY=(int)(height*.1), stringPrint, cY=0, rowWidth=(int)(Math.floor((width*.6)/(width*.0087)));
	public static Rectangle Diaboxical = new Rectangle((int)(stringX-(width*.015)), (int)(stringY-(width*.03)), (int)(width*.6), (height/5));
	private static Point c1= new Point(Diaboxical.x+(int)(Diaboxical.width*.47),Diaboxical.height+(int)(Diaboxical.height*.05)), c2 = new Point((int)(c1.x+(width*.01)),c1.y), c3 = new Point((int)(c1.x+(width*.01)/2),(int)(c1.y +(width*.01)));
	static Timer teletypeTimer = null;
	@SuppressWarnings("unused")
	private static boolean Ascending=true, complete=false, queuefull=false;
	public static boolean TextSkip=false;
	public static String line1="", line2="", line3="", queue="";
	
	public static void textAnimate(Graphics2D g){
		g.setStroke(new BasicStroke(10));
		g.draw(Diaboxical);
		stringPrint++;
		continueArrow(g);
		
		if(TextSkip&&!complete){
			TextSkip=false;
			complete=true;
			g.drawString(line1, stringX, stringY);
			g.drawString(line2, stringX, (int)(stringY*1.45));
			g.drawString(line3, stringX, (int)(stringY*1.9));
			stringPrint=0;
		}
		
		if (!complete){
			if(stringPrint>(line1.length()+line2.length())&&line3!=""&&stringPrint<=line3.length()+line1.length()+line2.length())
				g.drawString(line3.substring(0,(stringPrint-(line1.length()+line2.length()))), stringX, (int)(stringY*1.9));
			else if (stringPrint>line1.length()&&line2!=""&&stringPrint<=line2.length()+line1.length())	
				g.drawString(line2.substring(0,(stringPrint-line1.length())), stringX, (int)(stringY*1.45));
			else if(stringPrint<=line1.length())
				g.drawString(line1.substring(0,stringPrint), stringX, stringY);
			else{
				complete=true;
				stringPrint=0;
			}
		}	
		
	}
	
	public static void newDialogue(){
		if (line1.length()>rowWidth){
			if (!line1.substring(rowWidth-1, rowWidth).equals(" ")||!line1.substring(line1.length()-1,line1.length()).equals("")){
				line2=line1.substring(rowWidth-1, line1.length());
				line1=line1.substring(0,rowWidth-1);
			}else{
				line2=line1.substring(rowWidth, line1.length());
				line1=line1.substring(0,rowWidth);
			}
			if (line2.substring(0,1).equals(" "))
				line2=line2.substring(1,line2.length());
		}
		
		if (line2.length()>rowWidth){
			if (!line2.substring(rowWidth-1,rowWidth).equals(" ")||!line2.substring(line2.length()-1,line2.length()).equals("")){
				line3=line2.substring(rowWidth-1, line2.length());
				line2=line2.substring(0,rowWidth-1);
			}else{
				line3=line2.substring(rowWidth, line2.length());
				line2=line2.substring(0,rowWidth);	
			}
			if (line3.substring(0,1).equals(" "))
				line3=line3.substring(1,line3.length());
		}
		
		if (line3.length()>rowWidth){
			if (!line3.substring(rowWidth-1,rowWidth).equals(" ")||!line3.substring(line2.length()-1,line2.length()).equals("")){
				queue=line3.substring(rowWidth-1, line3.length());
				line3=line3.substring(0,rowWidth-1);
			}else{
				queue=line3.substring(rowWidth, line3.length());
				line3=line3.substring(0,rowWidth);	
			}
			if (queue.substring(0,1).equals(" "))
				queue=queue.substring(1,queue.length());
		}
		if (!queue.equals(""))
	  		queuefull=true;
		
		complete=false;
	}
	
	public static void continueArrow(Graphics2D g){
		if (complete){
			cY = Ascending ? cY+1 : cY-1;
			if(Math.abs(cY) == 7)
				Ascending = !Ascending;
			g.setColor(Color.BLACK);
			g.fillRect(c1.x-5 , c1.y-8, (int)(width*.02), (int)(height*.04));
			g.setStroke(new BasicStroke(2));
			g.setColor(Color.WHITE);
			g.drawLine(c1.x,c1.y+cY,c2.x,c2.y+cY);
		  	g.drawLine(c1.x,c1.y+cY,c3.x,c3.y+cY);
		  	g.drawLine(c2.x,c2.y+cY,c3.x,c3.y+cY);
		}
	}
	
	public static void clear(){
		line1="";
		line2="";
		line3="";
	}

	public static void EXP(){
		line1=("  ~You gained "+Combat.gainedxp+" Experience points.");
		newDialogue();
		Player.setPlayerEXP(Combat.gainedxp);
	}
	
	public static void LVL(){
		line1=("	Hey, Level "+Player.getPlayerLVL()+"! I'm "+Player.LevelupHP+" points healthier! Plus "+Player.LevelupDMG+" points stronger!\n	...Oh, I'm now "+Player.LevelupDEF+" points sturdier! Also, I'm "+Player.LevelupSPD+" points faster!\n	AND "+Player.LevelupLCK+"% more lucky!");
		newDialogue();
	}
	
	public static boolean isComplete(){
		return complete;
	}
	
}

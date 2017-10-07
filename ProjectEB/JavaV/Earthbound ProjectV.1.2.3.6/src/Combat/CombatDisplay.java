package Combat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import Main.State;
import Main.StateManager;
import Tools.KeyInput;
import Tools.Text;

@SuppressWarnings("serial")
public class CombatDisplay extends JPanel implements State{
	private static int width = Main.Display.getScreenWidth(), height = Main.Display.getScreenHeight(), stringScale=(int)(width*.02);
	private static Boolean Reset=false;
	public static Boolean cState=true;
	public static Rectangle Attack, Defend, Inventory, Flee, PSI, PlayerHPCOUNT;
	
	
		public CombatDisplay(){
				PlayerHPCOUNT=new Rectangle((int)(width*.25), height-200, 250,300);
				//PlayerHPCOUNT.setFont(new Font("Tahoma", Font.BOLD, (int)(width*.0233)));
				Attack = new Rectangle(((int)((width*.5)-(width*.5)*.5))+6, (int)(Math.ceil(height/67.5)), (width/7), (height/11));
				Defend= new Rectangle((int)((width*.5)-((width*.5)*.5)+(width*.16)), (int)(Math.ceil(height/67.5)), (width/7), (height/11));
				Inventory= new Rectangle((int)((width*.5)-((width*.5)*.5)+(width*.16)), 22+(height/11), (width/7), (height/11));
				Flee= new Rectangle((int)((width*.5)-((width*.5)*.5)+(width*.16)*2), (int)(Math.ceil(height/67.5)), (width/7), (height/11));
				PSI= new Rectangle((int)((width*.5)-((width*.5)*.5)+5), 22+(height/11), (width/7), (height/11));
		}
	
		public static void createEnemies(int EA, int e1, int e2, int e3, int e4, int e5){
			Text.line1="A Hooligan draws near. He wields a golf club like a masochist, it's a formula for trouble. What will you do?";
			Text.newDialogue();
			
			Enemies.generateEnemy(1, e1);
			Enemies.generateEnemy(2, e2);
			Enemies.generateEnemy(3, e3);
			Enemies.generateEnemy(4, e4);
			Enemies.generateEnemy(5, e5);
			//E1=Textures.getTexture(Enemies.active[e1].getEnemyIdleType(), Enemies.active[e1].getEnemyIdlePos(), 450, 500);
			Combat.Initiate(1, EA);
		}
		
		public static void keyListener(){
			if (KeyInput.isKeyPressed(KeyEvent.VK_SPACE)&&!KeyInput.isKeyReleased(KeyEvent.VK_SPACE)){
				if (cState&&Tools.Text.isComplete()){
					Reset=true;
					cState=false;
					Tools.Text.clear();
					ContinueSwitch.ContinueAdv();
				}
				else if(!Tools.Text.TextSkip){
					Tools.Text.TextSkip=true;
				}
			}
		}
		
		@Override
		public void tick(StateManager statemanager) {//remove win and lose checks from combat and place them as a tick
			
		}
		
		public void render(Graphics2D g) {
				if (Reset){
					g.setColor(Color.BLACK);
					g.fill(Tools.Text.Diaboxical);
					g.setStroke(new BasicStroke(10));
					g.draw(Tools.Text.Diaboxical);
					Reset=false;
				}
				g.setColor(Color.WHITE);
			  	g.setFont(new Font("SansSerif", Font.PLAIN, stringScale));
			  	g.drawString(String.valueOf(Player.getPlayerHP()), width/2, height-50); 
			  	
			  	Tools.Text.textAnimate(g);
		}
		
		@Override
		public String getName() {
			return "Combat";
		}
	
}

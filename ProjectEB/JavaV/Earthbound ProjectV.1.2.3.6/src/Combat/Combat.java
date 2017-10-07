package Combat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


public class Combat {
	public static int gainedxp, cao, HealthScrollTimer, nextAttacker;
	private static int Combatants;
	private static int[] Order=new int[8];
	private static Boolean Defending=false;
	
	
	public static void Initiate(int Playeramount, int EnemyAmount){
		Combatants=Playeramount+EnemyAmount;
		int Speed = Collections.max(Arrays.asList(Enemies.active[1].getEnemySPD(), Enemies.active[2].getEnemySPD(), Enemies.active[3].getEnemySPD(), Enemies.active[4].getEnemySPD(), Enemies.active[5].getEnemySPD(), Player.getPlayerSPD()));
		
		for (int i=Speed; i>0; i--){
			for(int i2=1; i2<Combatants; i2++){
				if(Speed==Enemies.active[1].getEnemySPD())
					Order[i2]=1;
				if(Speed==Enemies.active[2].getEnemySPD())
					Order[i2]=2;
				if(Speed==Enemies.active[3].getEnemySPD())
					Order[i2]=3;
				if(Speed==Enemies.active[4].getEnemySPD())
					Order[i2]=4;
				if(Speed==Enemies.active[5].getEnemySPD())
					Order[i2]=5;
				if(Speed==Player.getPlayerSPD())
					Order[i2]=6;
			}
		}
		nextAttacker=1;
		NextAttacker();
	}
	
	public static void NextAttacker(){
		ContinueSwitch.Continue=Order[nextAttacker]+10;
		nextAttacker++;
		if (nextAttacker>=Combatants)
			nextAttacker=1;
	}
	
	public static void PlayerAttack(int Enemy){
		cao=Enemy;
		Random crn = new Random();
		int crnn= crn.nextInt(100);
		int EnemyDMGTAKEN=Player.getPlayerDMG()-Enemies.active[cao].getEnemyDEF();
		
		if (CriticalHit(Player.getPlayerLCK(), crnn)==true){
			EnemyDMGTAKEN=(Player.getPlayerDMG()*2);
				if (EnemyDMGTAKEN<0)
					EnemyDMGTAKEN=0;
			Tools.Text.line1=("  ~You critically hit "+Enemies.active[cao].getEnemyName()+" for "+EnemyDMGTAKEN+"   points of damage!!!");
		}
		else{
				if (EnemyDMGTAKEN<0)
					EnemyDMGTAKEN=0;
			Tools.Text.line1=("  ~"+Enemies.active[cao].getEnemyName()+" was hit for "+EnemyDMGTAKEN+" points of damage!");
		}
		Enemies.active[cao].setEnemyHP(Enemies.active[cao].getEnemyHP()-EnemyDMGTAKEN);
		
		//when current attacked enemy dies, do...
		if(Enemies.active[cao].getEnemyHP()<=0){
			Tools.Text.line1=("  ~"+Enemies.active[cao].getEnemyName()+" stopped moving.");
			//CombatDisplay.enemy.setIcon(Textures.retrieveIcon(Enemies.active[cao].getEnemyDeath()));
			gainedxp+=Enemies.active[cao].getEnemyEXP();
			Enemies.active[cao]=null;
		}
		if (ContinueSwitch.Continue!=10&&WinCheck()==false&&LoseCheck()==false&&Enemies.active[cao]!=null)
			ContinueSwitch.Continue=0;
		else if (Enemies.active[cao]==null)
			WinCheck();
		
		Tools.Text.newDialogue();
		//CombatDisplay.Pattack.setVisible(true);
		NextAttacker();
	}
	
	public static void EnemyAttack(int Enemy){
		Random crn = new Random();
		int crnn= crn.nextInt(100);
		cao=Enemy;
		System.out.println("EAttack");
		if (Enemies.active[cao]!=null){
			if (CriticalHit(Enemies.active[cao].getEnemyLCK(), crnn)==true){
				int PlayerDMGTAKEN=(Enemies.active[cao].getEnemyDMG()*2);
				Player.setPlayerHP(Player.getPlayerHP()-PlayerDMGTAKEN);
				//HealthScroll.animateCountdown(CombatDisplay.PlayerHPCOUNT, PlayerDMGTAKEN, tValue);
			
				Tools.Text.line1=("  ~"+Enemies.active[cao].getEnemyName()+" critically hit you for \n    "+PlayerDMGTAKEN+ " points of damage!!!");
			}
			else{
				int PlayerDMGTAKEN=((Enemies.active[cao].getEnemyDMG()-Player.getPlayerDEF()));
				
				if (Defending)
					HealthScrollTimer=1000;
				else
					HealthScrollTimer=500;
				Player.setPlayerHP(Player.getPlayerHP()-PlayerDMGTAKEN);
				//HealthScroll.animateCountdown(CombatDisplay.PlayerHPCOUNT, PlayerDMGTAKEN, tValue);
				Tools.Text.line1=("  ~"+Enemies.active[cao].getEnemyName()+" hit you for "+PlayerDMGTAKEN+ " points of damage.");
				NextAttacker();
			}
		}
		else{
			WinCheck();
		}
		
	}
	
	public static boolean CriticalHit(int luckattempt, int crit){
		if (crit<=luckattempt)
			return true;
		else
			return false;
	}
	
	public static boolean WinCheck(){
		for(Enemies e : Enemies.active){
			if(e != null)
				return false;
			else{
				ContinueSwitch.Continue=45;
				return true;
			}
		}
		return false;
	}
	
	public static boolean LoseCheck(){
			if (Player.getPlayerHP()<=0){
				return true;
			}
			else
				return false;
		}

}

package Combat;

import java.util.Arrays;

public class Enemies {
	
		private String EnemyName, EnemyIcon, EnemyAttack, EnemyDeath;
		private int EnemyHP, EnemyDMG, EnemyDEF, EnemySPD, EnemyLCK, EnemyEXP, EnemyIdleType, EnemyIdlePos;
		
		public static Enemies[] active=new Enemies[10];
		static Enemies[] enemyTypes=new Enemies[50];
		
	public static void setPresets()
	{	
		enemyTypes[0] = new Enemies("", 0, 0, 0, 0, 0, 0, 0, 0);
		enemyTypes[1] = new Enemies("Hooligan", 100, 5, 1, 3, 1, 20, 4, 1);
		Arrays.fill(active, enemyTypes[0]);
	}
	
	public static void generateEnemy(int indexNo, int enemyIndex)
	{
		active[indexNo] = enemyTypes[enemyIndex];
	}

	public Enemies(String name, int HP, int DMG, int DEF, int SPD, int LCK, int EXP, int IdleType, int IdlePos){
		EnemyName=name;
		EnemyHP=HP;
		EnemyDMG=DMG;
		EnemyDEF=DEF;
		EnemySPD=SPD;
		EnemyLCK=LCK;
		EnemyEXP=EXP;
		EnemyIdleType=IdleType;
		EnemyIdlePos=IdlePos;
	}
	
	public int getEnemyHP(){
		return EnemyHP;
	}
	public int getEnemyDMG(){
		return EnemyDMG;
	}
	public int getEnemyDEF(){
		return EnemyDEF;
	}
	public int getEnemySPD(){
		return EnemySPD;
	}
	public int getEnemyLCK(){
		return EnemyLCK;
	}
	public int getEnemyEXP(){
		return EnemyEXP;
	}
	public String getEnemyName(){
		return EnemyName;
	}
	public String getEnemyIcon(){
		return EnemyIcon;
	}
	public int getEnemyIdleType(){
		return EnemyIdleType;
	}
	public int getEnemyIdlePos(){
		return EnemyIdlePos;
	}
	public String getEnemyAttack(){
		return EnemyAttack;
	}
	public String getEnemyDeath(){
		return EnemyDeath;
	}
	
	
	public void setEnemyHP(int hp){
		EnemyHP=hp;
	}

}

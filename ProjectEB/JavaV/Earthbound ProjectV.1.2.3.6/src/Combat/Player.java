package Combat;
import java.util.Random;

public class Player {

//player stats	
	private static int PlayerMAXHP=20, PlayerHP=20, PlayerDMG=3, PlayerDEF=2, PlayerSPD=10, PlayerLCK=1, PlayerLVL=1, PlayerEXP=0, LVLREQ=0;
	public static int LevelupHP, LevelupDMG, LevelupDEF, LevelupSPD, LevelupLCK;
	
//Getters
	public static int getPlayerLVL(){
		return PlayerLVL;
	}
	public static int getMaxPlayerHP(){
		return PlayerMAXHP;
	}
	public static int getPlayerHP(){
		return PlayerHP;
	}
	public static int getPlayerDMG(){
		return PlayerDMG;
	}
	public static int getPlayerDEF(){
		return PlayerDEF;
	}
	public static int getPlayerSPD(){
		return PlayerSPD;
	}
	public static int getPlayerLCK(){
		return PlayerLCK;
	}
//setters
	public static void setPlayerHP(int php) {
		PlayerHP=php;
		if (PlayerHP<0){
			PlayerHP=0;
		}
		if (PlayerHP>PlayerMAXHP){
			PlayerHP=PlayerMAXHP;
		}
	}
	
	public static void setPlayerEXP(int gxp){
		PlayerEXP+=gxp;
	}
	
	
	
//LevelUp Function
	public static boolean levelup(){
		if (PlayerEXP>=100+LVLREQ){
			Random rn = new Random();
			int HPinc= rn.nextInt(30);
			int DMGinc= rn.nextInt(10);
			int DEFinc=rn.nextInt(10);
			int SPDinc=rn.nextInt(10);
			LevelupHP=(2*PlayerLVL+HPinc);
			LevelupDMG=(DMGinc);
			LevelupDEF=(DEFinc);
			LevelupSPD=((int)((PlayerLVL/3)+SPDinc));
			LevelupLCK=(1);
			
			PlayerLVL++;
			PlayerHP=+(LevelupHP);
			PlayerMAXHP=+(LevelupHP);
			PlayerDMG=+LevelupDMG;
			PlayerDEF=+LevelupDEF;
			PlayerSPD=+(LevelupSPD);
			PlayerLCK=+(LevelupLCK);
			PlayerEXP=0;
			LVLREQ=(100*PlayerLVL);
			return true;
		}
		else
			return false;
	}
	

}

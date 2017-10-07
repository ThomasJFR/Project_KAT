package Combat;

import Tools.Text;

public class ContinueSwitch {
	public static int Continue=0, Combatstate=1, PlayerAttacking=1;
		
	
	public static void ContinueAdv(){
		if (Combatstate==0){
			Continue=50;
		}
		
		switch (Continue){
		case 0:
		case 1:break;
		case 10:
		case 11:Combat.EnemyAttack(1);break;
		case 12:Combat.EnemyAttack(2);break;
		case 13:Combat.EnemyAttack(3);break;
		case 14:Combat.EnemyAttack(4);break;
		case 15:Combat.EnemyAttack(5);break;
		case 16:Combat.PlayerAttack(PlayerAttacking);break;
		case 17:Combat.PlayerAttack(PlayerAttacking);break;
		case 18:Combat.PlayerAttack(PlayerAttacking);break;
		case 40:break;
		case 45:HealthScroll.stopHP();Text.line1=("	      YOU WON");Text.newDialogue();Continue++;break;
		case 46:Text.EXP();if(Player.levelup()){Continue++;}else{Continue=48;}break;
		case 47:Text.LVL();break;
		case 48:System.out.println("Combat Complete");break;
		}
	}
	
}


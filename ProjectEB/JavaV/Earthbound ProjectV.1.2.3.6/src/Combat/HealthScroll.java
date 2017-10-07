package Combat;

import javax.swing.JTextArea;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HealthScroll{
	
	static Timer teletypeTimer = null;
	static Timer countdownTimer=null;
	static boolean complete=false;
	@SuppressWarnings("unused")
	private static int caq;
	private static int totalDMG=0;
		
//Health scroll function Animation
	
	public static void stopHP(){
		if(countdownTimer != null && countdownTimer.isRunning())
			countdownTimer.stop();
		totalDMG=0;
	}
	
	public static void animateCountdown(final JTextArea displayArea, int DMGTaken, int rtimer)
	{
		if(totalDMG <= 0)
			totalDMG = DMGTaken;
		else{
			totalDMG=totalDMG+DMGTaken;
			countdownTimer.stop();
		}
		int nTimer=rtimer;
		
		if(countdownTimer != null && countdownTimer.isRunning())
			return;
		
		int HPO=(Player.getPlayerHP());
		
		final int[] i = new int[1];
		i[0] = HPO;
		
		countdownTimer = new Timer(nTimer, new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				displayArea.setText(String.valueOf(i[0]));
				if(i[0]!=HPO-totalDMG){
					i[0]--;
				}
				else if(i[0]==HPO-totalDMG){
					totalDMG=0;
					countdownTimer.stop();
				}
				
				if (Player.getPlayerHP()<=0)
					//Combat.fainted();
				if (Player.getPlayerHP()<=0)
					//CombatDisplay.PlayerHPCOUNT.setText("0");
				Player.setPlayerHP(i[0]);
			}
		});
		countdownTimer.start();
	}
	
}

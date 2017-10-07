package Tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class AnimationHandler{
	private static float alpha=0;
	Timer fadeout=new Timer(150, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (alpha<=.6f){
					alpha+=0.03f;
				}
				else if(alpha>=.6f){
					fadeout.stop();
					alpha=0;
				}
			}
		});
	
	public void FadeOut(){
		fadeout.start();
	}
	
	public float getAlpha(){
		return alpha;
	}

	
	
}

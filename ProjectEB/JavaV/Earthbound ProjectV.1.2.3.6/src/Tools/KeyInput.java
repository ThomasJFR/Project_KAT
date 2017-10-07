package Tools;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Combat.CombatDisplay;
import Main.Display;
 
public class KeyInput extends KeyAdapter{
 
    private static final int NUM_KEYS = 256;
    private static final boolean[] KEYS = new boolean[NUM_KEYS];
    private static final boolean[] LAST_KEYS = new boolean[NUM_KEYS];
   
    @Override
    public void keyPressed(KeyEvent ke){
        super.keyPressed(ke);
        KEYS[ke.getKeyCode()] = true;
       
        if(Display.statemanager.getCurrentState()==Display.statemanager.getState("Combat")){
        	CombatDisplay.keyListener();
        }
    }
   
    public void keyReleased(KeyEvent ke)
    {
        super.keyReleased(ke);
        KEYS[ke.getKeyCode()] = false;
    }
   
    public static void update()
    {
        for(int i = 0; i<NUM_KEYS;i++){
            LAST_KEYS[i] = KEYS[i];
        }
    }
   
    public static boolean isKeyPressed(int keyVal)
    {
        return KEYS[keyVal];
    }
   
    public static boolean isKeyReleased(int keyVal)
    {
        return !KEYS[keyVal];
    }
   
    public static boolean wasKeyPressed(int keyCode)
    {
        return isKeyPressed (keyCode) && !LAST_KEYS[keyCode];
    }
   
    public static boolean wasKeyReleased(int keyCode)
    {
        return !isKeyPressed (keyCode) && LAST_KEYS[keyCode];
    }
}

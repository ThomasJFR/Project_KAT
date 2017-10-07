package Main;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import Combat.CombatDisplay;
import Overworld.Overworld;
import Tools.KeyInput;
import Tools.Loader.Loader;


@SuppressWarnings("serial")
public class Display extends Canvas implements Runnable{

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();	
	private static int width =  (int)screenSize.getWidth(), height = (int)screenSize.getHeight();
	int fps, tps;
	public static JFrame frame;
	private boolean running;
	public static StateManager statemanager;
	
	public static Display INSTANCE;
	
		public Display() {
			INSTANCE = this;
			addKeyListener(new KeyInput());
			Display.statemanager = new StateManager();
			initialize(this);
			Display.statemanager.addState(new Overworld());
			Display.statemanager.addState(new CombatDisplay());
			
		}
		
	void initialize(Display d)
	{	
		frame = new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.add(d);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(screenSize);
		frame.setVisible(true);
		new Loader();
		}

	public void start() {
		if (running)
			return;
		
		running=true;
		new Thread(this, "mainThread").start();
		
	}
	
	public void setState(String state){
		statemanager.setState(state);
	}
	
	@Override
	public void run() {
		double tickRateTarget = 60.0;
		double nsPerTick = 1000000000.0/tickRateTarget;
		double unprocessed = 0.0;
		fps=0;
		tps=0;
	    long lastTime = System.nanoTime(), timer = System.currentTimeMillis();
	    boolean canRender = false;
	    
			while(running)
	        {
	            //Setting necessary variables
	            long now = System.nanoTime();
	            unprocessed += (now - lastTime) / nsPerTick;
	            lastTime = now;
	           
	            if(unprocessed >= 1.0){
	                //Tick and check for inputs from the mouse & keyboard
	                tick();
	                unprocessed--;
	                tps++;
	                canRender = true;
	            }else canRender = false;
	           
	            //Limit the amount of times it can render for sake of running
	            try {
	                Thread.sleep(1);
	            } catch (InterruptedException e) {
	                System.out.println("WARNING: Error in GameTitle.run()");
	                e.printStackTrace();
	            }
	           
	            if(canRender)
	            {
	                //Rendering and incrementing my FPS for debug when needed.
	                render();
	                fps++;
	            }
	           
	            if(System.currentTimeMillis() - 1000 > timer)
	            {
	                //Adding 1 second to timer, only if the above statement is met
	                timer+=1000;
	              
	                //Reported FPS
	                fps=0;
	                //Reported TPS
	                tps=0;
	            }  
	        }
		
	}
	
	public void tick(){
		statemanager.tick();
	}
	
	public void render(){
		BufferStrategy bufferstrategy = getBufferStrategy();
		if(bufferstrategy==null){
			createBufferStrategy(3);
			return;
		}
		Graphics g1=bufferstrategy.getDrawGraphics();
		Graphics2D g2=(Graphics2D)g1;
		
		statemanager.render(g2);
		
		g1.dispose();
		bufferstrategy.show();
	}
	
	public static int getScreenWidth(){
		return width;
	}
	
	public static int getScreenHeight(){
		return height;
	}
}

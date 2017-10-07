package Tools.Loader;

import java.awt.Color;
import java.awt.Image;
import Tools.Textures;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Combat.Enemies;
import Combat.HealthScroll;
import Main.Display;

public class Loader {
	
	private SplashScreen screen;
	static int width = Main.Display.getScreenWidth(), height = Main.Display.getScreenHeight();

		public Loader(){
			new Textures();
			ImageIcon splash=new ImageIcon(Textures.getTexture( 0, 1, 500, 500));
			screen = new SplashScreen(splash);
			screen.setLocationRelativeTo(null);
			screen.setMaxProgess(100);
			screen.getContentPane().setBackground(Color.BLACK);
			screen.setVisible(true);
			
			//BEGIN LOADING
			try{//Icon
				Image image = new ImageIcon(Textures.getTexture( 0, 1, 500, 500)).getImage();
				Display.frame.setIconImage(image);
				screen.setProgress(25, "Textures");
				loadTiles();
				loadPlayer();
				loadCombat();
				setPresets();
				screen.setProgress(100, "");
			}catch(Exception e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(screen, "Hi, if you're seeing this, that means the loading isn't working right. Hey, it's an alpha build quirk. Just try again or reinstall the game.", "Loading Error", 0);
				System.exit(0);
			}
			
			//FINISH LOADING
			
			screen.setVisible(false);
			screen.dispose();
		}
		
		private void loadTiles() throws Exception {
				screen.setProgress(30, "Map");
			//new Texture("TerrainTextureSheet");
		}
	
		private void loadPlayer() throws Exception{
				screen.setProgress(35, "Sprites");
			//new Texture("TrashSpriteSheet");
			//new Texture("Prototype_ScaleGun");
			//new Texture("Sprite_Standing");
			//new Texture("SpriteSheet_1");
			//new Texture("TrashSprite");
			//	screen.setProgress(0, "LOADING: Player_Sprite_NEW");
		//	new Texture("standing");
		}
		
		private void loadCombat() throws Exception{
				screen.setProgress(40, "Combat");
				new HealthScroll();
		}
		
		private void setPresets() {
			screen.setProgress(50, "Presets");
			Combat.Enemies.setPresets();
			Overworld.NPC.setPresets();
			Enemies.generateEnemy(0,0);
			
		}
}

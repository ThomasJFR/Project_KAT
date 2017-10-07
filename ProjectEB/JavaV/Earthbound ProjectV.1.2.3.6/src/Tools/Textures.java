package Tools;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Textures 
{
	static BufferedImage[][]Textures= new BufferedImage[256][256];
	static BufferedImage[]Maps= new BufferedImage[100];
	static BufferedImage BImage, newtexture;
	static ImageIcon newtextureII;
		
		public Textures(){
			addTexture("/ProjectEBLogo.png", 0, 1, 500, 500, true);
			addTexture("/Player/Player.png", 2, 1, 0, 0, true);
			addTexture("/Enemies/Hooligan(OW).png", 3, 1, 0, 0, true);
			addTexture("/Maps/Whistleton.png", 0, 1, 0, 0, false);
			addTexture("/Enemies/HooliganXGangMember(Combat).png", 4, 1, 0, 0, true);
		}
		
		public static Icon retrieveIcon(String path)
		{
			ImageIcon icon = new ImageIcon(path);
			icon.setImage(icon.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
			return icon;
		}
		
		public static Icon retrieveGif(String path, int Width, int height)
		{
			ImageIcon gif = new ImageIcon(path);
			 gif.setImage(gif.getImage().getScaledInstance(Width, height, Image.SCALE_DEFAULT));
			return gif;
		}
	
		public static ImageIcon retrieveImage(String path, int width, int height) {
			ImageIcon image = new ImageIcon(path);
			image.setImage(image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
			return image;
		}	
		
		public static BufferedImage retrieveBImage(String path, int width, int height) {
			try {
				BImage=ImageIO.read(Textures.class.getResourceAsStream(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return BImage;
		}	
		
		public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
		    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2d = dimg.createGraphics();
		    g2d.drawImage(tmp, 0, 0, null);
		    g2d.dispose();
		    return dimg;
		}
		
		public static void addTexture(String local, int type, int pos, int w, int h, boolean arraysheet){
			try {
				newtexture=ImageIO.read(Textures.class.getResourceAsStream(local));
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (arraysheet)
				Textures[type][pos]=newtexture;
			else 
				Maps[pos]=newtexture;
		}
		
		public static BufferedImage getTexture(int type, int pos, int w, int h){
			return resize(Textures[type][pos], w, h);
		}
		public static BufferedImage getMap(int pos){
			return Maps[pos];
			
		}
}
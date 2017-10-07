package Tools;
 
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
 
public class Texture {
    public static final Map<String,Texture> textureMap=new HashMap<String,Texture>();
    public static final Map<String, BufferedImage> imageMap=new HashMap<String,BufferedImage>();
    private BufferedImage image;
    private String fileName;
    private int width, height;
    
    public Texture(String fileName, String extension) {
        this.fileName = fileName;
        BufferedImage existingImage = imageMap.get(fileName);
        if (existingImage != null)
            this.image = existingImage;
        else
            try {
                System.out.println("Loading Texture: " + fileName);
                this.image = ImageIO.read(Texture.class.getResourceAsStream("/textures/" + fileName + extension));//new File("./res/textures/" + fileName + ".png"));
                imageMap.put(fileName, image);
                System.out.println("\tLoad Successful");
            } catch (Exception e) {
                System.out.println("\tUnsuccessfully Loaded.");
                e.printStackTrace();
            }
        this.width = image.getWidth();
        this.height = image.getHeight();
               
        textureMap.put(fileName, this);
    }
   
    public Texture(String fileName){
        this(fileName, ".png");
    }
 
    public Texture(Texture spritesheet, int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
        String key = spritesheet.fileName + "_" + x + "_" + y;
        BufferedImage old = imageMap.get(key);
        if (old != null)
            this.image = old;
        else
            this.image = spritesheet.image.getSubimage((x * width) - width, (y * height) - height, width, height);
    }
 
    public Texture(Texture spritesheet, int x, int y, int size) {
        this(spritesheet, x, y, size, size);
    }
 
    public static BufferedImage getTexture(String name){
        return imageMap.get(name);
    }
   
    public Texture resize(int newW, int newH) {
        Image tmp = image.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
 
        return this;
    }
 
    public void render(Graphics2D g, double x, double y) {
        g.drawImage(image, (int) x, (int) y, null);
    }
   
    public void render(Graphics2D g, double x, double y, double width, double height){
        g.drawImage(image, (int) x, (int) y, (int) width, (int) height, null);
    }
 
    public void render(Graphics2D g, double destinationX1, double destinationX2, double srcX1, double srcX2, double y){
        g.drawImage(image, (int)destinationX1, (int)y, (int)destinationX2, (int)y + height, (int)srcX1, 0, (int)srcX2, height, null);
    }
   
    public int getWidth() {
        return width;
    }
 
    public int getHeight() {
        return height;
    }
 
    public BufferedImage getImage() {
        return image;
    }
 
    public void setImage(BufferedImage image) {
        this.image = image;
    }
}

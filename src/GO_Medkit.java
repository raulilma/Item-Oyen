import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class GO_Medkit extends GameObject {

	private BufferedImage medkit_img;
	
	public GO_Medkit(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss); 
		
		medkit_img = ss.grabImage(16, 1, 32, 48);
	}
 
	public void tick() { 
		
	}
 
	public void render(Graphics g) { 
		g.drawImage(medkit_img, x, y, null);
	}
 
	public Rectangle getBounds() { 
		return new Rectangle(x, y, 32, 32);
	}

}

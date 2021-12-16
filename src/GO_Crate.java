import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class GO_Crate extends GameObject {

	private BufferedImage crate_img;
	
	public GO_Crate(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
		crate_img = ss.grabImage(9, 2, 32, 32);
	}
 
	public void tick() { 
		
	}
 
	public void render(Graphics g) { 
		g.drawImage(crate_img, x, y, null);
	}
 
	public Rectangle getBounds() { 
		return new Rectangle(x, y, 32, 32);
	}

}

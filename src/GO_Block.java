import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class GO_Block extends GameObject {

	private BufferedImage block_img;
	
	public GO_Block(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss); 
		block_img = ss.grabImage(8, 2, 32, 32);
	}
 
	public void tick() { 
		
	}
 
	public void render(Graphics g) { 
		g.drawImage(block_img, x, y, null);
	}
 
	public Rectangle getBounds() { 
		return new Rectangle(x, y, 32, 32);
	}

}

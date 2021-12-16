import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GO_Box extends GameObject 
{

	public GO_Box(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss); 
		velX = 1;
	}
 
	public void tick() { 
		x += velX;
		y += velY;
	}
 
	public void render(Graphics g) { 
		g.setColor(Color.blue);
		g.fillRect(x, y, 32, 32);
	}
 
	public Rectangle getBounds() { 
		return null;
	}
}

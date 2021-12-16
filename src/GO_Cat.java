import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GO_Cat extends GameObject {

	Handler handler;
	Game game; 
	
	private AudioPlayer catHit;
	
	private BufferedImage[] item_img = new BufferedImage[6];
	
	Animation anim;
	
	public GO_Cat(int x, int y, ID id, Handler handler, Game game, SpriteSheet ss) {
		super(x, y, id, ss); 
		this.handler = handler;
		this.game = game;
		
		item_img[0] = ss.grabImage(1, 1, 32, 48);
		item_img[1] = ss.grabImage(2, 1, 32, 48);
		item_img[2] = ss.grabImage(3, 1, 32, 48);
		item_img[3] = ss.grabImage(4, 1, 32, 48);
		item_img[4] = ss.grabImage(5, 1, 32, 48);
		item_img[5] = ss.grabImage(6, 1, 32, 48);

		anim = new Animation(6, item_img[0], item_img[1], item_img[2], item_img[3], item_img[4], item_img[5]);
	}
 
	public void tick() { 
		x += velX;
		y += velY;
		
		collision();
		
		//Item
		
		if(handler.isUp()) velY = -5;
		else if(!handler.isDown()) velY = 0;
		
		if(handler.isDown()) velY = 5;
		else if(!handler.isUp()) velY = 0;
		
		if(handler.isRight()) velX = 5;
		else if(!handler.isLeft()) velX = 0;
		
		if(handler.isLeft()) velX = -5;
		else if(!handler.isRight()) velX = 0;

		anim.runAnimation();
	}
	
	public void collision()
	{
		for(int i = 0; i < handler.object.size()-1; i++)
		{
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Block)
			{
				if(getBounds().intersects(tempObject.getBounds()))
				{
					x += velX * -1;
					y += velY * -1;
				}
			}
			if(tempObject.getId() == ID.Enemy && getBounds().intersects(tempObject.getBounds())) 
			{
				try {
					catHit = new AudioPlayer(5);
					catHit.play();
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				game.hp--;
			}
		}
	}
 
	public void render(Graphics g) { 
		if(velX == 0 && velY == 0) g.drawImage(item_img[0], x, y, null);
		else anim.drawAnimation(g, x, y, 0);
	}
 
	public Rectangle getBounds() { 
		return new Rectangle(x, y, 32, 48);
	}

}

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GO_CatOyen extends GameObject {

	Handler handler;
	Game game; 
	
	private AudioPlayer catHit;
	
	private BufferedImage[] oyen_img = new BufferedImage[6];
	
	Animation anim;
	
	public GO_CatOyen(int x, int y, ID id, Handler handler, Game game, SpriteSheet ss) {
		super(x, y, id, ss); 
		this.handler = handler;
		this.game = game;
		
		oyen_img[0] = ss.grabImage(10, 1, 32, 48);
		oyen_img[1] = ss.grabImage(11, 1, 32, 48);
		oyen_img[2] = ss.grabImage(12, 1, 32, 48);
		oyen_img[3] = ss.grabImage(13, 1, 32, 48);
		oyen_img[4] = ss.grabImage(14, 1, 32, 48);
		oyen_img[5] = ss.grabImage(15, 1, 32, 48);

		anim = new Animation(6, oyen_img[0], oyen_img[1], oyen_img[2], oyen_img[3], oyen_img[4], oyen_img[5]);
	}
 
	public void tick() { 
		x += velX;
		y += velY;
		
		collision();
		//Oyen
		
		if(handler.isUpOyen()) velY = -5;
		else if(!handler.isDownOyen()) velY = 0;
		
		if(handler.isDownOyen()) velY = 5;
		else if(!handler.isUpOyen()) velY = 0;
		
		if(handler.isRightOyen()) velX = 5;
		else if(!handler.isLeftOyen()) velX = 0;
		
		if(handler.isLeftOyen()) velX = -5;
		else if(!handler.isRightOyen()) velX = 0;

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
			
			if(tempObject.getId() == ID.Crate)
			{
				if(getBounds().intersects(tempObject.getBounds()))
				{
					game.ammo += 10;
					handler.removeObject(tempObject);
					game.ammobox--;
				}
			}
			
			if(tempObject.getId() == ID.Medkit)
			{
				if(getBounds().intersects(tempObject.getBounds()))
				{
					game.hp = 100;
					handler.removeObject(tempObject);
					game.medkit--;
				}
			}
			
			if(tempObject.getId() == ID.Enemy)
			{
				if(getBounds().intersects(tempObject.getBounds()))
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
	}
 
	public void render(Graphics g) { 
		if(velX == 0 && velY == 0) g.drawImage(oyen_img[0], x, y, null); 
		else anim.drawAnimation(g, x, y, 0); 
	}
 
	public Rectangle getBounds() { 
		return new Rectangle(x, y, 32, 48);
	}

}

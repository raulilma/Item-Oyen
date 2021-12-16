import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GO_Enemy extends GameObject {

	private Handler handler;
	Random r = new Random();
	int choose = 0;
	int hp = 150;
	
	private AudioPlayer hitEnemy, enemyDead;
	
	private BufferedImage[] enemy_img = new BufferedImage[3];
	private Game game;
	Animation anim;
	
	public GO_Enemy(int x, int y, ID id, Handler handler, SpriteSheet ss, Game game) {
		super(x, y, id, ss); 
		this.game = game;
		this.handler = handler;
		
		enemy_img[0] = ss.grabImage(7, 1, 32, 32);
		enemy_img[1] = ss.grabImage(8, 1, 32, 32);
		enemy_img[2] = ss.grabImage(9, 1, 32, 32);
		anim = new Animation(3, enemy_img[0], enemy_img[1], enemy_img[2]);
	}
 
	public void tick() { 
		x += velX;
		y += velY;
		
		choose = r.nextInt(10);
		
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Block)
			{
				if(getBoundsBig().intersects(tempObject.getBounds()))
				{
					x += (velX * 4) * -1;
					y += (velY * 4) * -1;
					velX *= -1;
					velY *= -1;
				} else if(choose == 0)
				{ 
					velX = (r.nextInt(8) + -4);
					velY = (r.nextInt(8) + -4);
				}
			}
			
			if(tempObject.getId() == ID.Bullet)
			{
				if(getBounds().intersects(tempObject.getBounds()))
				{
					try {
						hitEnemy = new AudioPlayer(3);
						hitEnemy.play();
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
					hp -= 50;
					handler.removeObject(tempObject); 
				}
			}
		} 
		anim.runAnimation();
		if(hp <= 0) {
			try {
				enemyDead = new AudioPlayer(8);
				enemyDead.play();
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
			handler.removeObject(this); game.kills++;
		}
	}
 
	public void render(Graphics g) { 
		anim.drawAnimation(g, x, y, 0);
	}
 
	public Rectangle getBounds() { 
		return new Rectangle(x, y, 32, 32);
	}
	
	public Rectangle getBoundsBig() { 
		return new Rectangle(x-32, y-32, 64, 64);
	}

}

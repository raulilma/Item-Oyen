import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public class MouseInput extends MouseAdapter {

	private Handler handler;
	private Camera camera;
	private Game game;
	private SpriteSheet ss;
	private AudioPlayer antidote;
	
	public MouseInput(Handler handler, Camera camera, Game game, SpriteSheet ss)
	{
		this.handler = handler;
		this.camera = camera;
		this.game = game;
		this.ss = ss;
	}
	
	public void mousePressed(MouseEvent e)
	{
		int mx = (int) (e.getX() + camera.getX());
		int my = (int) (e.getY() + camera.getY());
		
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.ItemPlayer && game.ammo > 0)
			{
				try {
					antidote = new AudioPlayer(4);
					antidote.play();
				} catch (UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				handler.addObject(new GO_Antidote(tempObject.getX()+16, tempObject.getY()+24, ID.Bullet, handler, mx, my, ss));
				game.ammo--;
			}
		}
	}
	
}

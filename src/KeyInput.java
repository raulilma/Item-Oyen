import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter 
{

	Handler handler;
	
	public KeyInput(Handler handler)
	{
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.ItemPlayer)
			{
				if(key == KeyEvent.VK_W) handler.setUp(true);
				if(key == KeyEvent.VK_S) handler.setDown(true);
				if(key == KeyEvent.VK_A) handler.setLeft(true);
				if(key == KeyEvent.VK_D) handler.setRight(true);
			}
			if(tempObject.getId() == ID.OyenPlayer)
			{
				if(key == KeyEvent.VK_UP) handler.setUpOyen(true);
				if(key == KeyEvent.VK_DOWN) handler.setDownOyen(true);
				if(key == KeyEvent.VK_LEFT) handler.setLeftOyen(true);
				if(key == KeyEvent.VK_RIGHT) handler.setRightOyen(true);
			}
		}
	}
	
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.ItemPlayer)
			{
				if(key == KeyEvent.VK_W) handler.setUp(false);
				if(key == KeyEvent.VK_S) handler.setDown(false);
				if(key == KeyEvent.VK_A) handler.setLeft(false);
				if(key == KeyEvent.VK_D) handler.setRight(false);
			}
			if(tempObject.getId() == ID.OyenPlayer)
			{
				if(key == KeyEvent.VK_UP) handler.setUpOyen(false);
				if(key == KeyEvent.VK_DOWN) handler.setDownOyen(false);
				if(key == KeyEvent.VK_LEFT) handler.setLeftOyen(false);
				if(key == KeyEvent.VK_RIGHT) handler.setRightOyen(false);
			}
		}
	}
	
}

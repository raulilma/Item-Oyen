import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	private boolean isRunning = false;
	public Thread thread;
	private Handler handler; 
	private Camera camera;
	private SpriteSheet ss;
	
	private BufferedImage level = null;
	private BufferedImage sprite_sheet = null;
	private BufferedImage floor = null;
	
	private UI_GameWindow gameWindow;
	
	public int ammo = 100, hp = 100, kills = 0, medkit = 0, ammobox = 0;
	public boolean gameEnd = false, gameStarted = false;
	public Game()
	{
		gameWindow = new UI_GameWindow(1000, 563, "Item Oyen", this);
		start();
		
		handler = new Handler(); 
		camera = new Camera(0, 0);
		this.addKeyListener(new KeyInput(handler));
		 
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("res/item_oyen_level2.png");
		sprite_sheet = loader.loadImage("res/level.png");
		
		ss = new SpriteSheet(sprite_sheet);
		
		floor = ss.grabImage(7, 2, 32, 32);
		
		this.addMouseListener(new MouseInput(handler, camera, this, ss));
		
		loadLevel(level);
	}
	
	public void start()
	{
		isRunning = true;
		thread = new Thread(this);
		thread.start(); 
	}
	
	public void stop()
	{
		isRunning = false;
		try
		{
			thread.join();
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public void pause()
	{
		isRunning = false;
	}
	
	public void unpause()
	{
		isRunning = true;
	}
	
	public void run() 
	{ 
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		while(isRunning)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1)
			{
				tick();
				delta--;
			}
			render();
			
			if(System.currentTimeMillis() - timer > 1000) timer += 1000;
			
			if(hp <= 0)
			{
				gameWindow.dispose();
				UI_GameOver gameOver = new UI_GameOver(this);
				gameOver.setVisible(true);
				
				isRunning = false;
			}
			
			if ((ammobox*10 + ammo) < 3*(30-kills)) 
			{
				gameWindow.dispose();
				UI_GameOver go = new UI_GameOver(this);
				go.setVisible(true);
				
				isRunning = false;
			}
			
			//System.out.print("enemy: " + Integer.toString(totalEnemy));
			if(kills == 30 && !gameEnd && gameStarted)
			{
				gameEnd = true;
				gameWindow.dispose();
				UI_WinScene winScene = new UI_WinScene(this);
				winScene.setVisible(true);
				//System.out.print("kills: "+Integer.toString(kills)+" enemy: " + Integer.toString(totalEnemy)+"\n");
			}			
		}
		stop();
	}
	
	public void tick()
	{
		for(int i = 0; i < handler.object.size(); i++)
		{
			if(handler.object.get(i).getId() == ID.ItemPlayer || handler.object.get(i).getId() == ID.OyenPlayer)
			{
				camera.tick(handler.object.get(i));
			}
		}
		
		handler.tick();
	}
	
	public void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.translate(-camera.getX(), -camera.getY());
		
		for(int xx = 0; xx < 30*72; xx += 32)
		{
			for(int yy = 0; yy < 30*72; yy += 32)
			{
				g.drawImage(floor, xx, yy, null);
			}
		}
				
		handler.render(g);
		
		g2d.translate(camera.getX(), camera.getY());
		
		g.setColor(Color.gray);
		g.fillRect(5, 5, 200, 32);
		g.setColor(Color.black);
		g.fillRect(5, 5, 200, 32);
		g.setColor(Color.green);
		g.fillRect(5, 5, hp*2, 32);
		
		g.setColor(Color.white);
		g.drawString("Ammo: " + ammo, 5, 50);
		g.drawString("Kills: " + kills, 5, 70);
		g.drawString("Medkit: " + medkit, 5, 90);
		g.drawString("Ammobox: " + ammobox, 5, 110);
		
		g.dispose();
		bs.show();
	}
	
	private void loadLevel(BufferedImage image)
	{
		int w = image.getWidth(), h = image.getHeight();
		
		for(int xx = 0; xx < w; xx++)
		{
			for(int yy = 0; yy < h; yy++)
			{
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if(red == 255 && green == 255 && blue == 0) handler.addObject(new GO_Enemy(xx*32, yy*32, ID.Enemy, handler, ss, this));
				else if (red == 255 && green == 255 && blue == 255) {handler.addObject(new GO_Medkit(xx*32,yy*48, ID.Medkit, ss)); medkit++;}
				else if(red == 0 && green == 255 && blue == 255) {handler.addObject(new GO_Crate(xx*32, yy*32, ID.Crate, ss)); ammobox++;}
				else if(red == 255) handler.addObject(new GO_Block(xx*32, yy*32, ID.Block, ss));
				else if(blue == 255) handler.addObject(new GO_Cat(xx*32, yy*32, ID.ItemPlayer, handler, this, ss));
				else if(green == 255) handler.addObject(new GO_CatOyen(xx*32, yy*32, ID.OyenPlayer, handler, this, ss));
			}
		}
		gameStarted = true;
	}

	public static void main(String[] args)
	{
		new UI_Menu();
	}
	
}

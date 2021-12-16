import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();

	private boolean up = false, down = false, right = false, left = false;
	private boolean upOyen = false, downOyen = false, rightOyen = false, leftOyen = false;
	
	public void tick()
	{
		for(int i = 0; i < object.size(); i++)
		{
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}
	
	public void render(Graphics g)
	{
		for(int i = 0; i < object.size(); i++)
		{
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject tempObject)
	{
		object.add(tempObject);
	}
	
	public void removeObject(GameObject tempObject)
	{
		object.remove(tempObject);
	}

	public boolean isUpOyen() {
		return upOyen;
	}

	public void setUpOyen(boolean upOyen) {
		this.upOyen = upOyen;
	}

	public boolean isDownOyen() {
		return downOyen;
	}

	public void setDownOyen(boolean downOyen) {
		this.downOyen = downOyen;
	}

	public boolean isRightOyen() {
		return rightOyen;
	}

	public void setRightOyen(boolean rightOyen) {
		this.rightOyen = rightOyen;
	}

	public boolean isLeftOyen() {
		return leftOyen;
	}

	public void setLeftOyen(boolean leftOyen) {
		this.leftOyen = leftOyen;
	}
	
	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}
	
}

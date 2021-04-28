
public class Character

{
	protected int x;
	protected int y;
	protected int width, height;
	
	// default constructor
	public Character()
	{
		x = 0;
		y = 0;
		width = 0;
		height = 0;
	}
	
	// call with super 
	public Character(int x1, int y1, int w, int h)
	{
		x = x1;
		y = y1;
		width = w;
		height = h;
	}

	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
	
	public void setX(int x1)
	{
		x = x1;
	}
	public void setY(int y1)
	{
		y = y1;
	}
	public void changeX(int k)
	{
		x += k;
	}
	public void changeY(int k)
	{
		y += k;
	}
	
	
}
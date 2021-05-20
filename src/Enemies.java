
public class Enemies extends Character {
	
	int x;
	int y;
	int width; 
	int height;
	private int xv;
	
	//hello
			
	// default constructor, sets all to zero
	public Enemies(String fileName, int width, int height, int x, int y){
			super(fileName, width, height, x, y);
			xv=1;
		}
		
		public String toString()
		{
			return x + " " + y;
		}
		

		/*public void move()
		{
			changeX(xv);
		}*/
				
		
}

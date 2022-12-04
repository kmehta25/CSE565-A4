import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
* This class is for creating City object and it will also draw itself and
* connected cities of the object. 
*  
* @author  Suraj Suryawanshi
* @version 2.0
* @since   2021-10-08
*/

public class City extends AbstractCity{
	
	public City(String name, int x, int y) {
		super(name, x, y);
	}
	
	/** 
	* This method is for drawing the city rectangle and name of it
	*  
	* @param g  Graphics object
	*/
	public void draw(Graphics g) {
		int x = this.bounds.x, y = this.bounds.y, 
				w = this.bounds.width, h = this.bounds.height;
		
		g.setColor(Color.GRAY);
		g.drawRect(x, y, w, h);
		g.setColor(Color.green);
		g.fillRect(x+1, y+1, w-1, h-1);
		g.setColor(Color.red);
		g.setFont(new Font("Courier", Font.PLAIN, 10));
		g.drawString(this.label, x+w, y);
	}
	
}

import java.awt.*;

public class CircleDecorator extends CityDecorator{


    public CircleDecorator(String name, int x, int y) {
        super(name, x, y);
    }

    public void draw(Graphics g){
        super.draw(g);
        g.setColor(Color.WHITE);
        g.drawOval(super.x,super.y, super.height, super.width);
        g.fillOval(super.x, super.y, super.height, super.height);
    }
}

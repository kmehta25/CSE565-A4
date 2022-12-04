import java.awt.*;

public class SquareDecorator extends CityDecorator{

    public SquareDecorator(String name, int x, int y) {
        super(name, x, y);
    }

    public void draw(Graphics g){
        super.draw(g);
        int height = super.height;
        int width = super.width;
        int xCo = super.x;
        int yCo = super.y;
        g.setColor(Color.BLACK);
        g.drawRect(xCo-height-5, yCo, height, width);
        g.fillRect(xCo-height-5, yCo, height, width);
        g.drawRect(xCo+height+5, yCo, height, width);
        g.fillRect(xCo+height+5, yCo, height, width);
        g.drawRect(xCo, yCo-height-5, height, width);
        g.fillRect(xCo, yCo-height-5, height, width);
        g.drawRect(xCo, yCo+height+5, height, width);
        g.fillRect(xCo, yCo+height+5, height, width);
    }
}

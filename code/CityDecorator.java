import java.awt.*;

public class CityDecorator extends AbstractCity{

    protected AbstractCity abstractCity;

    public CityDecorator(String name, int x, int y) {
        super(name, x, y);
    }

    public void setAbstractCity(AbstractCity abstractCity){
        this.abstractCity = abstractCity;
    }

    @Override
    public void draw(Graphics g) {
        if(abstractCity != null){
            abstractCity.draw(g);
        }
    }
}

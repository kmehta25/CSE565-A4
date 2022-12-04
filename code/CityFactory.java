public class CityFactory extends AbstractFactory{

    @Override
    public City getCity(String name, int x, int y) {
        return new City(name, x, y);
    }
}

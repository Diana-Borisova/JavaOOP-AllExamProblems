package onlineShop.models.products;

public class Laptop extends BaseComputer{
    private static final double overallPerformance = 10;

    public Laptop(int id, String manufacturer, String model, double price) {
        super(id, manufacturer, model, price, overallPerformance);
    }
}

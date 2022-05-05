package onlineShop.models.products;

public class DesktopComputer extends BaseComputer{
    private static final double overallPerformance = 15;
    public DesktopComputer(int id, String manufacturer, String model, double price) {
        super(id, manufacturer, model, price, overallPerformance);
    }
}

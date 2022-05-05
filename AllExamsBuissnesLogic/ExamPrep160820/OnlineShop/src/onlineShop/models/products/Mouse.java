package onlineShop.models.products;

public class Mouse extends BasePeripheral{
    private static final double multiplier = 1.25;
    public Mouse(int id, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        super(id, manufacturer, model, price, overallPerformance*multiplier, connectionType);
    }
}

package onlineShop.models.products;

import onlineShop.common.constants.OutputMessages;
import onlineShop.models.products.peripherals.Peripheral;

public abstract class BasePeripheral extends BaseProduct implements Peripheral {
    private String connectionType;

    public BasePeripheral(int id, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        super(id, manufacturer, model, price, overallPerformance);
        this.connectionType = connectionType;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String getManufacturer() {
        return super.getManufacturer();
    }

    @Override
    public String getModel() {
        return super.getModel();
    }

    @Override
    public double getPrice() {
        return super.getPrice();
    }

    @Override
    public double getOverallPerformance() {
        return super.getOverallPerformance();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("  Overall Performance: %.2f. Price: %.2f - %s: %s %s (Id: %d) Connection Type: %s", super.getOverallPerformance()
                , super.getPrice(), super.getClass().getSimpleName(), super.getManufacturer(),
                        super.getModel(),super.getId(), this.connectionType)).append(System.lineSeparator());
        return sb.toString();
    }

    @Override
    public String getConnectionType() {
        return this.getConnectionType();
    }
}

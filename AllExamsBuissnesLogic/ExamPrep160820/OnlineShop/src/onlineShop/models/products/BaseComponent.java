package onlineShop.models.products;


import onlineShop.common.constants.OutputMessages;
import onlineShop.models.products.components.Component;

public abstract class BaseComponent extends BaseProduct implements Component {
    private int generation;

    public BaseComponent(int id, String manufacturer, String model, double price, double overallPerformance, int generation) {
        super(id, manufacturer, model, price, overallPerformance);
        this.generation = generation;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("  Overall Performance: %.2f. Price: %.2f - %s: %s %s (Id: %d) Generation: %s", super.getOverallPerformance(),super.getPrice(), super.getClass().getSimpleName(), super.getManufacturer(),
                        super.getModel(), super.getId(), this.generation));
               return sb.toString();
}

    @Override
    public int getGeneration() {
        return this.generation;
    }
}
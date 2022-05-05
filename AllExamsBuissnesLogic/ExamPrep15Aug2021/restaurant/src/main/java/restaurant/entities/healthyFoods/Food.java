package restaurant.entities.healthyFoods;

import restaurant.entities.healthyFoods.interfaces.HealthyFood;

import static restaurant.common.ExceptionMessages.*;

public class Food implements HealthyFood {

    private String name;
    private double portion;
    private double price;

    public Food(String name, double portion, double price) {
        this.name = name;
        setPortion(portion);
        setPrice(price);
    }

    @Override
    public String getName() {
        if (this.name == null || this.name.trim().isEmpty()) {
            throw new IllegalArgumentException(INVALID_NAME);
        }
        return this.name;
    }

    public void setPortion(double portion) {
        if (portion <= 0) {
            throw new IllegalArgumentException(INVALID_PORTION);
        }
        this.portion = portion;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException(INVALID_PRICE);
        }
        this.price = price;
    }

    @Override
    public double getPortion() {
        return this.portion;
    }

    @Override
    public double getPrice() {
        return this.price;
    }
}

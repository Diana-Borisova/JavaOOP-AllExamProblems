package aquarium.entities;

import aquarium.entities.decorations.Decoration;

public abstract class BaseDecoration implements Decoration {
    private int comfort;
    private double price;

    public BaseDecoration(int comfort, double price) {
        this.comfort = comfort;
        this.price = price;
    }

    @Override
    public int getComfort() {
        return this.comfort;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    protected void setComfort(int comfort) {
        this.comfort = comfort;
    }

    protected void setPrice(double price) {
        this.price = price;
    }
}

package aquarium.entities;

public class Plant extends BaseDecoration{
    private final static int DEFAULT_COMFORT = 5;
    private final static double DEFAULT_PRICE = 10;
    public Plant() {
        super(DEFAULT_COMFORT, DEFAULT_PRICE);
    }
}

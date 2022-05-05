package aquarium.entities;

public class Ornament extends BaseDecoration{
    private final static int DEFAULT_COMFORT = 1;
    private final static double DEFAULT_PRICE = 5;
    public Ornament() {
        super(DEFAULT_COMFORT, DEFAULT_PRICE);
    }
}

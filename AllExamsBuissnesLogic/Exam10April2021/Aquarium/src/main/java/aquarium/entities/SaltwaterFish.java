package aquarium.entities;

public class SaltwaterFish extends BaseFish{
    private static final int INITIAL_SIZE = 5;
    public SaltwaterFish(String name, String species, double price) {
        super(name, species, price);
    }

    @Override
    public void eat() {
        super.setSize(this.getSize()+5);
    }
}

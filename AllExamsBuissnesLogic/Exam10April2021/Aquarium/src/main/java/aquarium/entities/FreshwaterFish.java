package aquarium.entities;

import aquarium.entities.fish.Fish;

public class FreshwaterFish extends BaseFish{
    private static final int INITIAL_SIZE = 3;
    public FreshwaterFish(String name, String species, double price) {
        super(name, species, price);
    }

    @Override
    public void eat() {
        super.setSize(this.getSize()+3);
    }
}

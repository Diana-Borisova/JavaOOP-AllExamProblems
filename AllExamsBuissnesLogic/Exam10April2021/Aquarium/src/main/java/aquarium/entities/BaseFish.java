package aquarium.entities;

import aquarium.common.ExceptionMessages;
import aquarium.entities.fish.Fish;

public abstract class BaseFish implements Fish {
    private String name;
    private String species;
    private int size;
    private double price;

    public BaseFish(String name, String species, double price) {
        this.setName(name);
        this.setSpecies(species);
        this.setPrice(price);

    }

    @Override
    public void setName(String name) {
    if (name == null|| name.trim().isEmpty()){
    throw new NullPointerException(ExceptionMessages.FISH_NAME_NULL_OR_EMPTY);
    }
    this.name = name;
    }

    @Override
    public void eat() {
        this.size +=5;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    protected void setSpecies(String species) {
        if (species==null||species.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.SPECIES_NAME_NULL_OR_EMPTY);
        }
        this.species = species;
    }

    protected void setSize(int size) {

        this.size = size;
    }

    protected void setPrice(double price) {
        if (price<=0){
            throw new IllegalArgumentException(ExceptionMessages.FISH_PRICE_BELOW_OR_EQUAL_ZERO);
        }
        this.price = price;
    }
}

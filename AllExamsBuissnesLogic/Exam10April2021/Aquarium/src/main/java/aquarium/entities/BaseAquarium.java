package aquarium.entities;

import aquarium.common.ConstantMessages;
import aquarium.common.ExceptionMessages;
import aquarium.entities.aquariums.Aquarium;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.fish.Fish;

import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseAquarium implements Aquarium {
    private String name;
    private int capacity;
    private Collection<Decoration> decorations;
    private Collection<Fish>fish;
    private Aquarium aquarium;



    public BaseAquarium(String name, int capacity) {
        this.setName(name);
        this.setCapacity(capacity);
        this.decorations = new ArrayList<>();
        this.fish = new ArrayList<>();


    }

    @Override
    public int calculateComfort() {
        return this.decorations.stream().mapToInt(Decoration::getComfort).sum();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addFish(Fish fish) {

        if (this.capacity <= this.fish.size()) {
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY);
        }

        String fishType = fish.getClass().getSimpleName().replaceAll("Fish", "");
        if (!this.getClass().getSimpleName().contains(fishType)){
            throw new IllegalStateException(ConstantMessages.WATER_NOT_SUITABLE);
        }
        this.fish.add(fish);

    }
    @Override
    public void removeFish(Fish fish) {

        if (fish.getClass().getSimpleName().equals("Freshwater")){

            this.fish.remove(fish);
        } else if (fish.getClass().getSimpleName().equals("Saltwater")){

            this.fish.remove(fish);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void addDecoration(Decoration decoration) {

        if (decoration.getClass().getSimpleName().equals("Ornament")){

            this.decorations.add(decoration);
        } else if (decoration.getClass().getSimpleName().equals("Plant")){

            this.decorations.add(decoration);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void feed() {
        for (Fish fish :this.fish) {
            fish.eat();
        }
    }

    @Override
    public String getInfo() {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s (%s):", name,this.getClass().getSimpleName())).append(System.lineSeparator());
        if (this.fish.size()==0){
            sb.append("none").append(System.lineSeparator());
        }else {
            sb.append("Fish: ");
          sb.append(this.fish.stream().map(Fish::getName).collect(Collectors.joining(" ")));

            sb.append(System.lineSeparator());
        }
        sb.append(String.format("Decorations: %d",this.decorations.size())).append(System.lineSeparator());
        sb.append(String.format("Comfort: %d", this.calculateComfort()));


        return sb.toString().trim();
    }
    @Override
    public Collection<Fish> getFish() {
        return this.fish;
    }

    @Override
    public Collection<Decoration> getDecorations() {
        return this.decorations;
    }

    protected void setName(String name) {
        if (name == null|| name.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public int getCapacity() {
        return this.capacity;
    }

    protected void setCapacity(int capacity) {
      /*  if (capacity<= this.aquariumMap.size()){
            throw new IllegalArgumentException(ConstantMessages.NOT_ENOUGH_CAPACITY);
        }*/
        this.capacity = capacity;
    }
}

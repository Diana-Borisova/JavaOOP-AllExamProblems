package aquarium.core;


import aquarium.common.ConstantMessages;
import aquarium.common.ExceptionMessages;
import aquarium.entities.*;
import aquarium.entities.aquariums.Aquarium;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ControllerImpl implements Controller {
    private  DecorationRepository	decorations;
    private Collection<Aquarium> aquariums;
    private Fish fish;

    public ControllerImpl() {
        this.decorations = new DecorationRepository();
        this.aquariums = new ArrayList<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        Aquarium aquarium=null;

        if (aquariumType.equals("FreshwaterAquarium")){
            aquarium = new FreshwaterAquarium(aquariumName);
        }else if (aquariumType.equals("SaltwaterAquarium")) {
            aquarium = new SaltwaterAquarium(aquariumName);
        }
        if (aquarium == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_AQUARIUM_TYPE);
        }
        this.aquariums.add(aquarium);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_AQUARIUM_TYPE, aquariumType);

    }

    @Override
    public String addDecoration(String type) {

    if (!type.equals("Ornament") && !type.equals("Plant")){
    throw new IllegalArgumentException(ExceptionMessages.INVALID_DECORATION_TYPE);
    }
        Decoration decoration;
    if (type.equals("Ornament")){
        decoration = new Ornament();
    } else {
        decoration= new Plant();
    }
        this.decorations.add(decoration);


        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
        if (this.decorations.getDecorations().stream().noneMatch(decoration ->
                decoration.getClass().getSimpleName().equals(decorationType))){
           throw new IllegalArgumentException(String.format(ExceptionMessages.NO_DECORATION_FOUND, decorationType));
        }
       Decoration currentDecoration = this.decorations.getDecorations().stream().filter(decoration ->
                decoration.getClass().getSimpleName().equals(decorationType)).findFirst().orElse(null);
        for (Aquarium aquarium :this.aquariums) {
            if (aquariumName.equals(aquarium.getName()) && currentDecoration !=null){
                aquarium.addDecoration(currentDecoration);
                this.decorations.remove(currentDecoration);
            }
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM, decorationType, aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {
        if (!fishType.equals("FreshwaterFish") && !fishType.equals("SaltwaterFish")){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_FISH_TYPE);
        }
        Fish fish = null;
        if (fishType.equals("FreshwaterFish")){
            for (Aquarium aquarium :this.aquariums) {
                if (aquarium.getClass().getSimpleName().contains("FreshwaterAquarium")){
                    fish = new FreshwaterFish(fishName,fishSpecies,price);
                    aquarium.addFish(fish);
                    return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName);
                }
            }

        } else if (fishType.equals("SaltwaterFish")){
            for (Aquarium aquarium :this.aquariums) {
                if (aquarium.getClass().getSimpleName().contains("SaltwaterAquarium")){
                    fish = new SaltwaterFish(fishName,fishSpecies,price);
                    aquarium.addFish(fish);
                    return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName);
                }
            }

        }



     /*   for (Aquarium aquarium : this.aquariums) {
            if (aquarium.getClass().getSimpleName().contains("FreshwaterAquarium")&& fishType.equals("FreshwaterFish")){
                fish = new FreshwaterFish(fishName,fishSpecies,price);
                aquarium.addFish(fish);
            }else if(aquarium.getClass().getSimpleName().contains("SaltwaterAquarium")&& fishType.equals("SaltwaterFish")){
                fish = new SaltwaterFish(fishName,fishSpecies,price);
                aquarium.addFish(fish);
            } else {
                throw new IllegalArgumentException(ConstantMessages.WATER_NOT_SUITABLE);
            }

        }*/

        throw new IllegalArgumentException(ConstantMessages.WATER_NOT_SUITABLE);
    }

    @Override
    public String feedFish(String aquariumName) {
        int count = 0;
        for (Aquarium aquarium :this.aquariums) {
            if (aquariumName.equals(aquarium.getName())){
                for (Fish fish :aquarium.getFish()) {
                fish.eat();
                count++;
                }
            }
        }
        return String.format(ConstantMessages.FISH_FED, count);
    }

    @Override
    public String calculateValue(String aquariumName) {
      double fishSum = 0;
        double decorationsSum = 0;
        for (Aquarium aquarium :this.aquariums) {
        if (aquariumName.equals(aquarium.getName())){
           fishSum = aquarium.getFish().stream().mapToDouble(Fish::getPrice).sum();
            decorationsSum = aquarium.getDecorations().stream().mapToDouble(Decoration::getPrice).sum();
                   }
    }


        return String.format(ConstantMessages.VALUE_AQUARIUM, aquariumName, fishSum+decorationsSum);
    }

    @Override
    public String report() {

        return this.aquariums.stream().map(Aquarium::getInfo).collect(Collectors.joining(System.lineSeparator()));
    }
}

package restaurant.repositories;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.repositories.interfaces.BeverageRepository;

import java.util.*;

public class BeverageRepositoryImpl implements BeverageRepository<Beverages> {

    private final List<Beverages> beveragesList;

    public BeverageRepositoryImpl() {
        this.beveragesList = new ArrayList<>();
    }

    @Override
    public Beverages beverageByName(String drinkName, String drinkBrand) {
        return beveragesList.stream().filter(b -> b.getName().equals(drinkName) &&
                b.getBrand().equals(drinkBrand)).findFirst().orElse(null);
    }

    @Override
    public Collection<Beverages> getAllEntities() {
        return beveragesList;
    }

    @Override
    public void add(Beverages entity) {
        beveragesList.add(entity);
    }

}
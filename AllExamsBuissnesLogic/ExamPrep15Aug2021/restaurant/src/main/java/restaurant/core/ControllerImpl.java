package restaurant.core;

import restaurant.core.interfaces.Controller;
import restaurant.entities.drinks.Fresh;
import restaurant.entities.drinks.Smoothie;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.Salad;
import restaurant.entities.healthyFoods.VeganBiscuits;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.InGarden;
import restaurant.entities.tables.Indoors;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.BeverageRepository;
import restaurant.repositories.interfaces.HealthFoodRepository;
import restaurant.repositories.interfaces.TableRepository;

import static restaurant.common.ExceptionMessages.*;
import static restaurant.common.OutputMessages.*;

public class ControllerImpl implements Controller {

    private HealthFoodRepository<HealthyFood> foods;
    private BeverageRepository<Beverages> beverages;
    private TableRepository<Table> tables;
    private double totalSum;


    public ControllerImpl(HealthFoodRepository<HealthyFood> foods,
                          BeverageRepository<Beverages> beverages, TableRepository<Table> tables) {
        this.foods = foods;
        this.beverages = beverages;
        this.tables = tables;
    }
    @Override
    public String addHealthyFood(String type, double price, String name) {

        HealthyFood food = null;
        switch (type) {
            case "Salad":
                food = new Salad(name, price);
                break;
            case "VeganBiscuits":
                food = new VeganBiscuits(name, price);
                break;
        }
        HealthyFood existed = this.foods.foodByName(name);
        if (existed != null) {
            throw new IllegalArgumentException(String.format(FOOD_EXIST, name));
        }
        this.foods.add(food);
        return String.format(FOOD_ADDED, name);
    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name) {
        Beverages drink = null;
        switch (type) {
            case "Fresh":
                drink = new Fresh(name, counter, brand);
                break;
            case "Smoothie":
                drink = new Smoothie(name, counter, brand);
                break;
        }
        Beverages existed = beverages.beverageByName(name, brand);
        if (existed != null) {
            throw new IllegalArgumentException(String.format(BEVERAGE_EXIST, name));
        }
        beverages.add(drink);
        return String.format(BEVERAGE_ADDED, type, brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table = null;
        switch (type) {
            case "Indoors":
                table = new Indoors(type, tableNumber, capacity);
                break;
            case "InGarden":
                table = new InGarden(tableNumber, capacity);
                break;
        }
        Table existed = tables.byNumber(tableNumber);
        if (existed != null) {
            throw new IllegalArgumentException(String.format(TABLE_IS_ALREADY_ADDED, tableNumber));
        }
        tables.add(table);
        return String.format(TABLE_ADDED, tableNumber);
    }

    @Override
    public String reserve(int numberOfPeople) {

        for (Table table : tables.getAllEntities()) {
            if (!table.isReservedTable() && table.getSize() >= numberOfPeople) {
                table.reserve(numberOfPeople);
                return String.format(TABLE_RESERVED, table.getTableNumber(), numberOfPeople);
            }
        }
        for (Table entity : tables.getAllEntities()) {
            if (entity == null) {
                return String.format(RESERVATION_NOT_POSSIBLE , numberOfPeople);
            }
        }
        return null;
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {
        Table table = this.tables.byNumber(tableNumber);

        if (table == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }

        HealthyFood healthyFood = this.foods.foodByName(healthyFoodName);

        if (healthyFood == null) {
            return String.format(NONE_EXISTENT_FOOD, healthyFoodName);
        }

        table.orderHealthy(healthyFood);
        return String.format(FOOD_ORDER_SUCCESSFUL, healthyFoodName, tableNumber);
    }



    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {
        Table table = this.tables.byNumber(tableNumber);
        if (table == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }
        Beverages drink = this.beverages.beverageByName(name , brand);

        if (drink == null) {
            return String.format(NON_EXISTENT_DRINK , name , brand);
        }
        table.orderBeverages(drink);
        return String.format(BEVERAGE_ORDER_SUCCESSFUL , name , tableNumber);
    }

    @Override
    public String closedBill(int tableNumber) {

        Table table = this.tables.byNumber(tableNumber);

        double bill = table.bill();
        table.clear();
        totalSum += bill;

        return String.format(BILL , tableNumber , bill);
    }


    @Override
    public String totalMoney() {
        return String.format(TOTAL_MONEY , totalSum);
    }
}
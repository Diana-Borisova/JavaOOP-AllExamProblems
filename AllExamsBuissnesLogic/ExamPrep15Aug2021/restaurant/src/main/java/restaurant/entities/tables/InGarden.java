package restaurant.entities.tables;

public class InGarden extends BaseTable{

    private static double pricePerPerson = 4.50;
    public InGarden(int number, int size) {
        super(number, size,  pricePerPerson);
    }
}
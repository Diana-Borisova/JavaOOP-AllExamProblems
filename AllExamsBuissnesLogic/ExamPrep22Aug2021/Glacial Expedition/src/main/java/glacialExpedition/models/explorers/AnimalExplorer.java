package glacialExpedition.models.explorers;

public class AnimalExplorer extends BaseExplorer{

    private static int INIT_ENERGY = 100;
    public AnimalExplorer(String name) {
        super(name, INIT_ENERGY);
    }


}
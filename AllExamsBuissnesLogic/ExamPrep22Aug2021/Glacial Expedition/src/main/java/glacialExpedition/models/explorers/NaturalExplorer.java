package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer{

    private static int INIT_ENERGY = 60;

    public NaturalExplorer(String name) {
        super(name, INIT_ENERGY);
    }



    @Override
    public void search() {
        if (this.getEnergy() <= 7) {
            this.setEnergy(0);
        } else {
            this.setEnergy(getEnergy()- 7);
        }
    }
}
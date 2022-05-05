package glacialExpedition.models.explorers;

public class GlacierExplorer extends BaseExplorer{

    private static int INIT_ENERGY = 40;

    public GlacierExplorer(String name) {
        super(name, INIT_ENERGY);
    }
}
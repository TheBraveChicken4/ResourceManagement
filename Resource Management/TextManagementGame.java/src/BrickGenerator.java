import java.util.ArrayList;

public class BrickGenerator extends Generator{

    public BrickGenerator(String name, ArrayList<Resource> constructionCost,
                          int resourceProductionRate, int numberConstructed, String type)
    {
        super(name, constructionCost, resourceProductionRate, numberConstructed, type);
    }
}
import java.util.ArrayList;

/**
 * The Generator class represents a generic resource generating item in the game.
 * Generators have a name, a construction cost, and a resource production rate.
 */
public abstract class Generator implements Score{
    private String name;
    private ArrayList<Resource> constructionCost;
    private int resourceProductionRate;
    private int numberConstructed;
    private Resource product;
    private int generatorScore;
    private String type;

    /**
     * Creates a new Generator with the given name, construction cost, and resource production rate.
     *
     * @param name                  the name of the Generator
     * @param constructionCost      the cost in resources required to construct the Generator
     * @param resourceProductionRate the rate at which the Generator produces resources per unit of time
     * @param numberConstructed     the number of units of this generator constructed at this time
     * @param product               the type of resource this generator produces
     */
    public Generator(String name, ArrayList<Resource> constructionCost, int resourceProductionRate, int numberConstructed, String type) {
        this.name = name;
        this.constructionCost = constructionCost;
        this.resourceProductionRate = resourceProductionRate;
        this.numberConstructed = numberConstructed;
        this.product = product;
        this.type = type;
    }

    /**
     * Gets the name of the Generator.
     *
     * @return the name of the Generator
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the construction cost of the Generator.
     *
     * @return the construction cost of the Generator
     */
    public ArrayList<Resource> getConstructionCost() {
        return constructionCost;
    }

    /**
     * Gets the resource production rate of the Generator.
     *
     * @return the resource production rate of the Generator
     */
    public int getResourceProductionRate() {
        return resourceProductionRate;
    }

    public String getType() {
        return this.type;
    }

    /**
     * Gets the number of units constructed of this Generator.
     *
     * @return the number of units constructed of the generator
     */
    public int getNumberConstructed() {
        return numberConstructed;
    }

    public void increaseNumConstructed() {
        numberConstructed++;
    }

    /**
     This game will keep track of score based on how many generators there are, as well as resources. The event class will decrease score naturally.
     **/

    public int scoreImpact() {
        return (int)(numberConstructed * 5);
    }

}
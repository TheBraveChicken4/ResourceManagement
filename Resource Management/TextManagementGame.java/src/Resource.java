import java.util.Random;

/**
 * The Resource class represents a generic resource in the game.
 * Resources have a name, a quantity, and a status of critical or not critical.
 */
public abstract class Resource implements Comparable<Resource>, Score {
    private String name;
    private int quantity;
    private boolean isCritical;
    private int resourceScore;

    /**
     * Creates a new Resource with the given name and initializes the quantity to 0.
     *
     * @param name the name of the resource
     */
    public Resource(String name) {
        this.name = name;
        this.quantity = 10;
        this.isCritical = true;
    }


    public int scoreImpact() {
        return (int)(getQuantity() * 0.1);
    }


    /**
     * Gets the name of the resource.
     *
     * @return the name of the resource
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the quantity of the resource.
     *
     * @return the quantity of the resource
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Reports if a resource is critical. If a rsource is critical, reaching 0 ends the game.
     *
     * @return if the resource is critical
     */
    public boolean isCritical(){
        return isCritical;
    }

    /**
     * Sets if a given resource is critical.
     *
     * @param boolean value for isCritical
     */
    public void setIsCrticial(boolean isCritical){
        this.isCritical = isCritical;
    }

    /**
     * Adds the specified amount to the quantity of the resource.
     *
     * @param amount the amount to add
     */
    public void add(int amount) {
        quantity += amount;
    }

    /**
     * Consumes the specified amount of the resource if available. Sets the resource to 0 if there is not enough to consume.
     *
     * @param amount the amount to consume
     */
    public void consume(int amount) {
        if (quantity >= amount) {
            quantity -= amount;
        } else {
            quantity = 0;
            System.out.println("Not enough " + name + " to use.");
        }
    }

    // Overriden to take in a max and min parameter too delete a random number of resources 
    // Consumes some of the resources when called
    public void consume(int max, int min) {
        Random random = new Random();
        int amount = random.nextInt(max - min) + min;
        if (quantity >= amount) {
            quantity -= amount;
        } else {
            quantity = 0;
            System.out.println("Not enough " + name + " to use.");
        }
    }

    // compareTo method can be seen when displaying resources; They are always printed
    // least to greatest
    public int compareTo(Resource o) {
        int amount = this.getQuantity();
        int otherAmount = ((Resource) o).getQuantity();

        if (amount > otherAmount) {
            return 1;
        } else if (amount < otherAmount) {
            return -1;
        } else {
            return 0;
        }
    }

}
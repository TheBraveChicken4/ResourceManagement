import java.util.Random;

/**
 * The Event class represents a generic randomly occuring event in the game.
 * Events have a name
 */
public class Event implements Score{
    private String name;

    /**
     * Creates a new Event with the given name
     *
     * @param name the name of the event
     */
    public Event(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the event.
     *
     * @return the name of the event
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int quantityToReduce(char t, int max, int min) {
        Random random = new Random();
        int randomNum;
        if (t == 's') {
            randomNum = random.nextInt(max - min) + min;
            return randomNum;
        } else if (t == 'h') {
            randomNum = random.nextInt(max - min) + min;
            return randomNum;
        }
        return 0;
    }

    public int quantityToReduce(int num) {
        return num;
    }

    public int quantityToIncrease(int num) {
        Random random = new Random();
        int randomNum = random.nextInt(num);
        return randomNum;
    }


    public int scoreImpact() {
        return 0;
    }

}
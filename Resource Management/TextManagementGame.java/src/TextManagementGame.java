import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

/**
 * The TextManagementGame class represents a text-based management game where the player manages resources and resource generators.
 */
public class TextManagementGame {
    // Define game variables
    private int round;
    private boolean gameLoop = true;
    private ArrayList<Resource> resources = new ArrayList<Resource>();
    private ArrayList<Generator> generators = new ArrayList<Generator>();

    private Steel steel = new Steel();
    private Energy energy = new Energy();
    private Brick brick = new Brick();

    private Event earthquake = new Event("Earthquake");
    private Event hurricane = new Event("Hurricane");
    private Event search = new Event("Search Party");

    ArrayList<Resource> steelConstructionCost = new ArrayList<>();

    SteelMill steelGenerator = new SteelMill("Steel Mill", steelConstructionCost, 10, 1, "Steel");
    BrickGenerator brickGenerator = new BrickGenerator("Brick Generator", steelConstructionCost, 10, 1, "Steel");
    SolarPanel solarPanel = new SolarPanel("Solar Panel", steelConstructionCost, 10, 1, "Steel");

    private int steelGens = steelGenerator.getNumberConstructed();
    private int brickGens = brickGenerator.getNumberConstructed();
    private int solarPanels = solarPanel.getNumberConstructed();


    // Define a Scanner for user input
    private Scanner scanner;
    Random random = new Random();

    /**
     * Creates a new TextManagementGame instance with initial resource and time values.
     * TODO : Add starting resources
     */
    public TextManagementGame() {
        round = 1;       // Start at time 1
        scanner = new Scanner(System.in);
    }

    /**
     Getters and Setters for my critical resources and generator instances
     **/
    public Steel getSteel() {
        return this.steel;
    }

    public Brick getBrick() {
        return this.brick;
    }

    public Energy getEnergy() {
        return this.energy;
    }

    public SteelMill getSteelGenerator() {
        return this.steelGenerator;
    }

    public SolarPanel getEnergyGenerator() {
        return this.solarPanel;
    }

    public BrickGenerator getBrickGenerator() {
        return this.brickGenerator;
    }

    /**
     * Check if a method should run with a 1 in number chance.
     *
     * @return true if the method should run, false otherwise
     */
    public boolean haveEventThisTurn(int number) {
        Random random = new Random();
        int chance = random.nextInt(number); // Generates a random number between 0 (inclusive) and number (exclusive)
        return chance == 0; // Returns true with a 1 in number chance
    }

    /**
     Implement functionality for scout for resources
     **/
    // public ArrayList

    /**
     * Prints the list of resources
     */
    public void viewResources(){
        sortResources();
        for(Resource r : resources){
            System.out.println(r.getName() + ":     " + r.getQuantity());
        }
    }

    public int calculateScore() {
        int score = 0;
        score += steel.scoreImpact();
        score += brick.scoreImpact();
        score += energy.scoreImpact();
        score += brickGenerator.scoreImpact();
        score += steelGenerator.scoreImpact();
        score += solarPanel.scoreImpact();
        return score;
    }

    public void sortResources() {
        Collections.sort(resources);
    }

    /**
     * Prints the list of Generators
     */
    public void viewGenerators(){

        for (Generator g : generators) {

            System.out.println(g.getName() + ":");
            System.out.println("            Number Constructed: " + g.getNumberConstructed());
            System.out.println("            Production Rate: " + g.getNumberConstructed()*10);

        }
    }

    /**
     * Checks if a Generator can be constructed and then adds it to the list of Generators
     * TODO : ADD LOGIC
     */

    public void constructBrickGenerator(){
        int cost = (int)(brickGenerator.getNumberConstructed() * 20);
        System.out.println("Cost: " + cost);
        if (brick.getQuantity() >= cost) {
            this.brickGenerator.increaseNumConstructed();
            this.brick.consume(cost);
            System.out.println("Successfully created a new Brick Generator");
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public void constructSteelGenerator() {
        int cost = (int)(steelGenerator.getNumberConstructed() * 20);
        System.out.println("Cost: " + cost);
        if (steel.getQuantity() >= cost) {
            this.steelGenerator.increaseNumConstructed();
            this.steel.consume(cost);
            System.out.println("Successfully created a new Steel Generator");
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public void constructEnergyGenerator() {
        int cost = (int)(solarPanel.getNumberConstructed() * 20);
        System.out.println("Cost: " + cost);
        if (energy.getQuantity() >= cost) {
            this.solarPanel.increaseNumConstructed();
            this.energy.consume(cost);
            System.out.println("Successfully created a new Energy Generator");
        } else {
            System.out.println("Insufficient funds");
        }
    }

    /**
     * Increments the time counter and then adds more resources based on what generators are present
     * TODO : Add calculations to generate resources for the next turn
     */
    public void endRound(){
        steelGens = steelGenerator.getNumberConstructed();
        brickGens = brickGenerator.getNumberConstructed();
        solarPanels = solarPanel.getNumberConstructed();

        steel.add(10 * steelGens);
        brick.add(10 * brickGens);
        energy.add(10 * solarPanels);
        round++;

    }

    /**
     * Adds a Generator object to the ArrayList of Generators.
     *
     * @param generator the Generator object to add
     */
    public void addGenerator(Generator generator) {
        generators.add(generator);
    }

    /**
     * Adds a Resource object to the ArrayList of resources.
     *
     * @param resource the Resource object to add
     */
    public void addResource(Resource resource) {
        resources.add(resource);
    }

    public void scoutForResources() {

        Random random = new Random();
        int keepGoing = 0;

        System.out.println("Cost is 100 energy and 20-50 steel / brick...\nContinue?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        int option = scanner.nextInt();

        if (option == 1) {
            if (energy.getQuantity() >= 100 && brick.getQuantity() >= 50 && steel.getQuantity() >= 50) {
                energy.consume(100);
                int brickOrSteel = random.nextInt(2);
                if (brickOrSteel == 1) {
                    steel.consume(50, 20);
                } else if (brickOrSteel == 2) {
                    brick.consume(50, 20);
                }
                keepGoing = 1;
            } else {
                System.out.println("Insufficient Funds");
            }

        } else if (option == 2) {
            System.out.println("Returning to main screen...");
        } else {
            System.out.println("Error... Please try again");
        }

        if (keepGoing == 1) {
            int foundBricks = search.quantityToIncrease(201);
            int foundSteel = search.quantityToIncrease(201);
            steel.add(foundSteel);
            brick.add(foundBricks);
            System.out.println("The search party found " + foundBricks + " Bricks and " + foundSteel + " Steel!");
        }
    }

    /**
     * Checks if we are out of any critical resources
     *
     * @return returns true if we are out of any critical resources returns false otherwise
     */
    public boolean isCriticalResourceEmpty(){
        for(Resource r : resources){
            if(r.isCritical() && r.getQuantity() == 0){
                System.out.println("critical resources depleted!");
                return true;
            }
        }
        return false;
    }

    /**
     * Starts the game and manages the game loop.
     */
    public void start() {
        System.out.println("------------Welcome to the Resource Management Game!------------");
        System.out.println("            - Upgrade generators for more resources!              ");
        System.out.println("            - End the round to collect resources!                 ");
        System.out.println("            * But watch out! Storms are dangerous! *               ");


        int oddsOfRandomStorm = 5; //a 20% chance of a random event occuring
        int oddsOfRandomHurricane = 10;


        // Main game loop
        while (gameLoop) {
            System.out.println("\nRound: " + round);

            // Protects players for the first few rounds
            if (round != 1 && round != 2 && round != 3 && round != 4 && round != 5 && round != 6) {
                if(haveEventThisTurn(oddsOfRandomStorm)){
                    System.out.println("You've been hit by an Earthquake!");

                    // Increases storm damage based on amount of generators
                    int max = 30 + ((steelGens + brickGens + solarPanels) * 20);
                    int min = 10 + ((steelGens + brickGens+ solarPanels) * 20);

                    int lostResources = earthquake.quantityToReduce('s', max, min);
                    int resourceTypeNum = random.nextInt(3) + 1;
                    switch(resourceTypeNum) {
                        case 1:
                            System.out.println("You lost " + lostResources + " steel in the earthquake!");
                            System.out.println(" - " + lostResources);
                            steel.consume(lostResources);
                            break;
                        case 2:
                            System.out.println("You lost " + lostResources + " brick in the earthquake!");
                            System.out.println(" - " + lostResources);
                            brick.consume(lostResources);
                            break;
                        case 3:
                            System.out.println("You lost " + lostResources + " energy in the earthquake!");
                            System.out.println(" - " + lostResources);
                            energy.consume(lostResources);
                            break;

                    }

                }


                if (haveEventThisTurn(oddsOfRandomHurricane)) {
                    System.out.println("You've been hit by a hurricane!");

                    // Increases hurricane damage based on amount of generators
                    int max = 65 + ((steelGens + brickGens + solarPanels) * 20);
                    int min = 45 + ((steelGens + brickGens + solarPanels) * 20);

                    int lostResources = hurricane.quantityToReduce('h', max, min);
                    int resourceTypeNum = random.nextInt(3) + 1;

                    switch(resourceTypeNum) {
                        case 1:
                            System.out.println("You lost " + lostResources + " steel in the hurricane!");
                            System.out.println(" - " + lostResources);
                            steel.consume(lostResources);
                            break;
                        case 2:
                            System.out.println("You lost " + lostResources + " brick in the hurricane!");
                            System.out.println(" - " + lostResources);
                            brick.consume(lostResources);
                            break;
                        case 3:
                            System.out.println("You lost " + lostResources + " energy in the hurricane!");
                            System.out.println(" - " + lostResources);
                            energy.consume(lostResources);
                            break;
                        default:
                            System.out.println("Error");

                    }
                }
            }

            int choice;


            System.out.println("Options:");
            System.out.println("1. View Current Resources");
            System.out.println("2. View Current Generators");
            System.out.println("3. Add a new Generator");
            System.out.println("4. End round");
            System.out.println("5. Scout for Resources");
            System.out.println("6. Quit game");


            do {

                System.out.print("Choose an option: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        viewResources();
                        break;
                    case 2:
                        viewGenerators();
                        break;
                    case 3:
                        System.out.println("Which type of generator would you like to create?");
                        System.out.println("1: Steel;    Cost: " + (int)(steelGenerator.getNumberConstructed() * 20));
                        System.out.println("2. Brick;    Cost: " + (int)(brickGenerator.getNumberConstructed() * 20));
                        System.out.println("3. Energy;    Cost: " + (int)(solarPanel.getNumberConstructed() * 20));
                        choice = scanner.nextInt();

                        if (choice == 1) {
                            constructSteelGenerator();
                            break;
                        } else if (choice == 2) {
                            constructBrickGenerator();
                            break;
                        } else if (choice == 3) {
                            constructEnergyGenerator();
                            break;
                        } else {
                            System.out.println("Error; Please try again...");
                        }
                        viewGenerators();
                        break;
                    case 4:
                        break;
                    case 5:
                        scoutForResources();
                        break;
                    case 6:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");

                        choice = scanner.nextInt();

                }
            } while (choice != 4);

            if (isCriticalResourceEmpty()) {
                gameLoop = false;
            }

            int notDead = 0;
            for (Resource r : resources) {
                if (r.getQuantity() > 0) {
                    notDead++;
                }
            }
            if (notDead == 3) {
                endRound();
            }

        }

        System.out.println("Game Over! You ran out of resources.");
        System.out.println("You played for " + round + " rounds");
        viewResources();
        System.out.println("                            Score:");
        System.out.println("                              " + calculateScore());
    }

    /**
     * Main method to run the game
     *
     * @param args the command-line arguments (not used in this game)
     */
    public static void main(String[] args) {
        TextManagementGame game = new TextManagementGame();

        // steelConstructionCost.add(energy);
        // steelConstructionCost.add(steel);


        // Construction cost is an ArrayList of type Resource so I will just list the resource
        // in the Array and then access the quantities individually when we actually create the generators
        // SolarPanel panel1 = new SolarPanel("Solar Panel", )


        game.addResource(game.getSteel());
        game.addResource(game.getEnergy());
        game.addResource(game.getBrick());
        game.addGenerator(game.getBrickGenerator());
        game.addGenerator(game.getSteelGenerator());
        game.addGenerator(game.getEnergyGenerator());

        game.start();
    }
}
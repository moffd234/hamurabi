package hammurabi.src;
import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Hammurabi {
    Random rand = new Random();  // this is an instance variable
    Scanner scanner = new Scanner(System.in);
    int year;
    int bushels;
    int population;
    int harvestRate;
    int numHarvested;
    int immigrantNum;
    int landVal;
    int numStarved;
    int cropsEatenByRats;
    int landTotal;
    int totalStarved;


    public static void main(String[] args) {
        new Hammurabi().playGame();
    }

    public Hammurabi(){
        // Setup initial values
        year = 1;
        bushels = 2800;
        population = 100;
        landVal = 19;
        harvestRate = 3;
        landTotal = 1000;
        numStarved = 0;
        immigrantNum = 5;
        numHarvested = 3000;
        cropsEatenByRats = 200;
        totalStarved = 0;
    }

    void playGame() {
        while(year <= 10) {
            printSummary();

            // Buy land and update variables
            int boughtLand = askHowManyAcresToBuy(landVal, bushels);
            landTotal = calcAddedLand(landTotal, boughtLand);
            bushels -= boughtLand * landVal;
            System.out.println("Total land " + landTotal + " Total Bushels " + bushels);

            // Sell land and update variables
            int soldLand = askHowManyAcresToSell(landTotal);
            landTotal = calcSoldLand(landTotal, soldLand);
            bushels += soldLand * landVal;
            System.out.println("Total land " + landTotal + " Total Bushels " + bushels);

            // Feed the population
            int bushelsFed = askHowMuchGrainToFeedPeople(bushels);
            bushels -= bushelsFed;
            System.out.println("Total Bushels " + bushels);

            // Plant bushels
            int bushelsPlanted = askHowManyAcresToPlant(landTotal, population, bushels);
            bushels -= bushelsPlanted;

            // Find the harvest rate between turns
            numHarvested = harvest(bushelsPlanted);
            harvestRate = calculateHarvestRate(numHarvested, bushelsPlanted);
            bushels += numHarvested;

            // Handle starvation between rounds
            numStarved = starvationDeaths(population, bushelsFed);
            population -= numStarved;
            totalStarved += numStarved;

            // Get new landVal
            landVal = newCostOfLand();

            year++;
        }
        finalSummary();
    }

    public boolean uprising(int i, int i1) {
        return false;
    }

    public int grainEatenByRats(int i) {
        return 0;
    }

    public int newCostOfLand() {
        int min = 17;
        int max = 23;
        return rand.nextInt(max - min + 1) + min;
    }

    public int immigrants(int i, int i1, int i2) {
        return 0;
    }

    public int plagueDeaths(int i) {
        return 0;
    }

    // This doesn't pass the unit test unless you take the ceiling despite the README saying we don't need to do any
    // arithmetic with floating point numbers
    public int starvationDeaths(int population, int bushelsFedToPeople) {
        int neededBushels = population * 20;
        if (neededBushels > bushelsFedToPeople){
            int remainingNeeded = neededBushels - bushelsFedToPeople;
            return (int) Math.ceil((double) remainingNeeded / 20);
        }
        return 0;
    }
    public String getSummary(){

        return "\n\nO great Hammurabi!\n" +
                "You are in year " + year + " of your ten year rule.\n" +
                "In the previous year " + numStarved + " people starved to death.\n" +
                "In the previous year " + immigrantNum + " people entered the kingdom.\n" +
                "The population is now " + population + ".\n" +
                "We harvested " + numHarvested + " bushels at " + harvestRate + " bushels per acre.\n" +
                "Rats destroyed " + cropsEatenByRats + " bushels, leaving " + bushels + " bushels in storage.\n" +
                "The city owns " + landTotal + " acres of land.\n" +
                "Land is currently worth " + landVal + " bushels per acre.\n\n";
    }
    public void printSummary(){
        System.out.println(getSummary());
    }

    int askHowManyAcresToBuy(int price, int bushels){
        int numAcres = getNumber("O great Hammurabi, how many acres shall you buy?\n");
        while(bushels < price * numAcres){
            numAcres = getNumber("O Great Hammurabi, surely you jest! We have only " + bushels + " bushels left!\n");
        }
        return numAcres;
    }
    int askHowManyAcresToSell(int acresOwned){
        int numAcres = getNumber("O great Hammurabi, how many acres shall you sell?\n");
        while(numAcres > acresOwned){
            numAcres = getNumber("O Great Hammurabi, surely you jest! We have only " + acresOwned + " acres left!\n");
        }
        return numAcres;
    }
    public int calcAddedLand(int prevTotal, int acresBought){
        return prevTotal + acresBought;
    }
    public int calcSoldLand(int prevTotal, int acresSold){
        return prevTotal - acresSold;
    }

    int askHowMuchGrainToFeedPeople(int bushels){
        int numGrainFed = getNumber("O great Hammurabi, how much grain do you wish to feed your citizens\n");
        while(numGrainFed > bushels){
            numGrainFed = getNumber("O Great Hammurabi, surely you jest! We have only " + bushels + " acres left!\n");
        }
        return numGrainFed;
    }

    int askHowManyAcresToPlant(int acresOwned, int population, int bushels){
        /*
        Each person can farm 10 grain so numPlanted / 10 can't be > population
        numPlanted can't be > bushels
        numPlanted can't be > acresOwned
         */
        int numPlanted = getNumber("O great Hammurabi, how much grain do you wish to plant\n");

        // WARNING: This does integer division so if the truncated result of numPlanted / 10 is greater than the
        //          population then it still passes the conditional. This is due to the fact that the README
        //          explicitly says "All the required arithmetic in this program should be integer.
        //          You do not need doubles."
        while(numPlanted > acresOwned || numPlanted > bushels || numPlanted / 10 > population){

            // Inform the user of the current resources
            System.out.println("Current acres " + acresOwned + "\nCurrent bushels " + bushels
                    + "\nCurrent Population " + population);
            numPlanted = getNumber("O great Hammurabi, surely you jest! We don't have enough resources for that\n");


        }
        return numPlanted;
    }

    public int genHarvestRate(){
        int min = 1;
        int max = 6;
        return rand.nextInt(max - min + 1) + min;
    }

    // README says to use harvest(int acres, int bushelsUsedAsSeed) but the test case only gives 1 argument
    public int harvest(int bushelsUsedAsSeed){
        int rate = genHarvestRate();
        return bushelsUsedAsSeed * rate;
    }

    public int calculateHarvestRate(int harvestAmount, int bushelsUsedAsSeed) {
        if(bushelsUsedAsSeed != 0) {
            return harvestAmount / bushelsUsedAsSeed;
        }
        return 0;
    }

    public void finalSummary(){
        int acresPerPerson = landTotal / population;
        System.out.println("---------FINAL SUMMARY---------\n" +
                "Number of people starved = " + totalStarved + "\n" +
                "Land per population " + acresPerPerson);
        if(numStarved <= 10){
            System.out.println("Great Job! You managed to starve less than 10% of your population");
        }
        else{
            System.out.println("You failed to provide for your population and allowed "
                    + totalStarved + "people to starve");
        }

        if(acresPerPerson > 10){
            System.out.println("Good job! Your rule marks a time of great growth in the amount of land in our kingdom");
        }
        else{
            System.out.println("You failed to manage your land efficiently and your rule has been remebered as one" +
                    "where the kingdom lost lots of land");
        }
    }

    /**
     * Prints the given message (which should ask the user for some integral
     * quantity), and returns the number entered by the user. If the user's
     * response isn't an integer, the question is repeated until the user
     * does give an integer response.
     *
     * @param message The request to present to the user.
     * @return The user's numeric response.
     */
    int getNumber(String message) {
        while (true) {
            System.out.print(message);
            try {
                return scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("\"" + scanner.next() + "\" isn't a number!");
            }
        }
    }

    // GETTERS

    public int getLandTotal() {
        return landTotal;
    }

}
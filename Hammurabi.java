package hammurabi;
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
    int peopleStarvedLastYear;
    int cropsEatenByRats;
    int totalLand;


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
        totalLand = 1000;
        peopleStarvedLastYear = 0;
        immigrantNum = 5;
        numHarvested = 3000;
        cropsEatenByRats = 200;
        printSummary();
        int boughtLand = askHowManyAcresToBuy(landVal, bushels);
        int soldLand = askHowManyAcresToSell(totalLand);
    }

    void playGame() {
    }

    public boolean uprising(int i, int i1) {
        return false;
    }

    public int grainEatenByRats(int i) {
        return 0;
    }

    public int newCostOfLand() {
        return 0;
    }

    public int harvest(int i) {
        return 0;
    }

    public int immigrants(int i, int i1, int i2) {
        return 0;
    }

    public int plagueDeaths(int i) {
        return 0;
    }

    public int starvationDeaths(int i, int i1) {
        return 0;
    }
    public String getSummary(){
        String output = "O great Hammurabi!\n" +
                "You are in year " + year + " of your ten year rule.\n" +
                "In the previous year " + peopleStarvedLastYear + " people starved to death.\n" +
                "In the previous year " + immigrantNum + " people entered the kingdom.\n" +
                "The population is now " + population + ".\n" +
                "We harvested " + numHarvested + " bushels at " + harvestRate + " bushels per acre.\n" +
                "Rats destroyed " + cropsEatenByRats + " bushels, leaving " + bushels + " bushels in storage.\n" +
                "The city owns " + totalLand + " acres of land.\n" +
                "Land is currently worth " + landVal + " bushels per acre.\n\n";

        return output;
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
    public int getYear() {
        return year;
    }

    public int getBushels() {
        return bushels;
    }

    public int getPopulation() {
        return population;
    }

    public int getHarvestRate() {
        return harvestRate;
    }

    public int getNumHarvested() {
        return numHarvested;
    }

    public int getImmigrantNum() {
        return immigrantNum;
    }

    public int getLandVal() {
        return landVal;
    }

    public int getPeopleStarvedLastYear() {
        return peopleStarvedLastYear;
    }

    public int getCropsEatenByRats() {
        return cropsEatenByRats;
    }

    public int getTotalLand() {
        return totalLand;
    }

}
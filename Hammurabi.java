package hammurabi;
import java.util.Random;
import java.util.Scanner;

public class Hammurabi {
    Random rand = new Random();  // this is an instance variable
    Scanner scanner = new Scanner(System.in);
    byte year;
    int grain;
    int population;
    int harvestRate;
    int numHarvested;
    int immigrantNum;
    int landVal;
    int starvationRate;
    int ratTotal;
    int totalLand;

    public static void main(String[] args) {
        new Hammurabi().playGame();
    }

    public Hammurabi(){

        year = 1;
        grain = 2800;
        population = 100;
        landVal = 19;
        harvestRate = 3;
        totalLand = 1000;
        starvationRate = 0;
        immigrantNum = 5;
        numHarvested = 3000;
        ratTotal = 200;

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
    public String getYearMessage(){
        String output = "O great Hammurabi!\n" +
                "You are in year " + year + " of your ten year rule.\n" +
                "In the previous year " + starvationRate + " people starved to death.\n" +
                "In the previous year " + immigrantNum + " people entered the kingdom.\n" +
                "The population is now " + population + ".\n" +
                "We harvested " + numHarvested + " bushels at " + harvestRate + " bushels per acre.\n" +
                "Rats destroyed " + ratTotal + " bushels, leaving " + grain + " bushels in storage.\n" +
                "The city owns " + totalLand + " acres of land.\n" +
                "Land is currently worth " + landVal + " bushels per acre.";

        return output;
    }
}
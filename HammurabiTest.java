package hammurabi;

import static org.junit.Assert.*;

import hammurabi.src.Hammurabi;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HammurabiTest {
    
    Hammurabi ham;
    
    boolean about(double expected, double actual) {
        return actual > 0.90 * expected && actual < 1.10 * expected;
    }

    @Before
    public void setUp() throws Exception {
        ham = new Hammurabi();
    }

    @Test
    public final void testPlagueDeaths1() {
        int number_of_plagues = 0;
        for (int i = 0; i < 10000; i++) {
            int deaths = ham.plagueDeaths(100);
            if (deaths > 0) {
                number_of_plagues += 1;
            }
        }
        int percentPlagues = number_of_plagues / 100;
        assertTrue("Number of plagues is about " + percentPlagues + ", not about 15%.",
                   about(1500, number_of_plagues));
    }

    @Test
    public final void testPlagueDeaths2() {
        int deaths = 0;
        for (int i = 0; i < 10000; i++) {
            deaths = ham.plagueDeaths(100);
            if (deaths > 0) break;
        }
        assertEquals("In a plague, " + deaths + "% of your people die, not 50%.",
                     50, deaths);
    }
    
    @Test
    public final void testStarvationDeaths() {
        int deaths = ham.starvationDeaths(100, 1639);
        assertEquals("Wrong number of starvations deaths.", 19, deaths);
        deaths = ham.starvationDeaths(100, 2500);
        if (deaths < 0) {
            fail("You starved a negative number of people!");
        }
    }

    @Test
    public final void testUprising() {
        assertTrue("Should have had an uprising!", ham.uprising(1000, 451));
        assertFalse("Should not have had an uprising!", ham.uprising(1000, 449));
    }

    @Test
    public final void testImmigrants() {
        int imm = ham.immigrants(10, 1200, 500);
        assertEquals("Wrong number of immigrants.", 25, imm);
    }

    @Test
    public final void testHarvest() {
        int[] yield = new int[7];
        for (int i = 0; i < 1000; i++) {
            int harvest = ham.harvest(1);
            assertTrue("Illegal harvest per acre: " + harvest, harvest > 0 && harvest <= 6);
            yield[harvest] += 1;
        }
        for (int j = 1; j <= 6; j++) {
            assertTrue("You never have a yield of " + j + " bushels per acre.", yield[j] > 0);
        }
    }

    @Test
    public final void testGrainEatenByRats1() {
        int infestations = 0;
        for (int i = 0; i < 1000; i++) {
            int eaten = ham.grainEatenByRats(100);
            if (eaten > 0) {
                infestations += 1;
            }
        }
        int percentInfestations = infestations / 100;
        assertTrue("Number of rat infestations is about " + percentInfestations + 
                   ", not about 40%.", about(400, infestations));
    }

    @Test
    public final void testGrainEatenByRats2() {
        int percent = 0;
        int[] counts = new int[31];
        for (int i = 0; i < 10000; i++) {
            percent = ham.grainEatenByRats(100);
            if (percent == 0) continue;
            counts[percent] += 1;
            assertTrue("Rats ate " + percent + "% of your grain, not 10% to 30%.",
                       percent >= 10 && percent <= 30);
        }
        for (int j = 11; j < 30; j++) {
            assertTrue("Rats never ate " + j + "% of your grain.", counts[j] > 0);
        }
    }

    @Test
    public final void testNewCostOfLand() {
        int[] cost = new int[24];
        for (int i = 0; i < 1000; i++) {
            int price = ham.newCostOfLand();
            assertTrue("Illegal cost of land: " + price, price >= 17 && price <= 23);
            cost[price] += 1;
        }
        for (int j = 17; j <= 23; j++) {
            assertTrue("You never have a land cost of " + j + " bushels per acre.", cost[j] > 0);
        }
    }
    @Test
    public final void testGetYearMessage(){
        String expectedOutput = "O great Hammurabi!\n" +
                "You are in year 1 of your ten year rule.\n" +
                "In the previous year 0 people starved to death.\n" +
                "In the previous year 5 people entered the kingdom.\n" +
                "The population is now 100.\n" +
                "We harvested 3000 bushels at 3 bushels per acre.\n" +
                "Rats destroyed 200 bushels, leaving 2800 bushels in storage.\n" +
                "The city owns 1000 acres of land.\n" +
                "Land is currently worth 19 bushels per acre.\n\n";

        String actual = ham.getSummary();
        Assert.assertEquals(expectedOutput, actual);
    }

    @Test
    public final void testCalcAddedLand(){
        // Given
        int expected = ham.getLandTotal() + 5;

        // When
        int actual = ham.calcAddedLand(ham.getLandTotal(), 5);

        // Then
        Assert.assertEquals(expected, actual);

    }
    @Test
    public final void testCalcSoldLand(){
        // Given
        int expected = ham.getLandTotal() - 5;

        // When
        int actual = ham.calcSoldLand(ham.getLandTotal(), 5);

        // Then
        Assert.assertEquals(expected, actual);

    }

}


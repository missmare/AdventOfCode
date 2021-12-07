package mama.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CrabTest {

    private final String smallPositions = "/home/manuela/projects/AdventOfCode/2021.07/test/mama/aoc/CrabPositionsSmall.txt";
    private final String bigPositions = "/home/manuela/projects/AdventOfCode/2021.07/test/mama/aoc/CrabPositionsBig.txt";

    @Test
    public void testMinimumFuelSmall() {
        Crab crab = new Crab();
        int minimumFuel = crab.getMinimumFuelForPositions(smallPositions);
        assertEquals(37, minimumFuel, "The small positions need 37 fuel to get to the same position");
    }

    @Test
    public void testMinimumFuelBig() {
        Crab crab = new Crab();
        int minimumFuel = crab.getMinimumFuelForPositions(bigPositions);
        assertTrue(minimumFuel > 0, "The crabs need some fuel to get to the hole position.");
    }

    @Test
    public void testMinimumFuelForCrabEnginesSmall() {
        Crab crab = new Crab();
        int minimumFuel = crab.getMinimalFuelForCrabEngines(smallPositions);
        assertEquals(168, minimumFuel, "The small positions need 123 fuel to get to the same position with crab engines");
    }

    @Test
    public void testMinimumFuelForCrabEnginesBig() {
        Crab crab = new Crab();
        int minimumFuel = crab.getMinimalFuelForCrabEngines(bigPositions);
        assertTrue(minimumFuel>0, "The crabs need some fuel to get to the hole position.");
    }



}
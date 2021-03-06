package mama.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VentLinesTest {

    private final String smallTest = "/home/manuela/projects/AdventOfCode/2021.05/test/mama/aoc/HydrothermalVentSmall.txt";
    private final String bigTest = "/home/manuela/projects/AdventOfCode/2021.05/test/mama/aoc/HydrothermalVentBig.txt";

    @Test
    public void testHydrothermalVentRiskSmall() {
        VentLines submarine = new VentLines();
        int risk = submarine.determineRisk(smallTest);
        assertEquals(5, risk);
    }

    @Test
    public void testHydrothermalVentRiskBig() {
        VentLines submarine = new VentLines();
        int risk = submarine.determineRisk(bigTest);
        System.out.println("There are " + risk + " overlapping points.");
        assertTrue(risk > 0);
    }

    @Test
    public void testHydrothermalRiskWithDiagonalSmall() {
        VentLines submarine = new VentLines();
        int risk = submarine.determineRiskInclDiagonal(smallTest);
        System.out.println("there are " + risk + " points where at least two lines overlap");
        assertEquals(12, risk);
    }

    @Test
    public void testHydrothermalVentRiskWithDiagonalBig() {
        VentLines submarine = new VentLines();
        int risk = submarine.determineRiskInclDiagonal(bigTest);
        System.out.println("There are " + risk + " overlapping points.");
        assertTrue(risk > 0);
    }


}
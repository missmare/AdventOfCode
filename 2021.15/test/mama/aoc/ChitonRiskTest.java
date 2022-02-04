package mama.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChitonRiskTest {

    String smallPath = "/home/manuela/projects/AdventOfCode/2021.15/test/mama/aoc/SmallChiton.txt";
    String bigPath = "/home/manuela/projects/AdventOfCode/2021.15/test/mama/aoc/BigChiton.txt";
    String verySmallPath = "/home/manuela/projects/AdventOfCode/2021.15/test/mama/aoc/VerySmallChiton.txt";

    @Test
    public void testCalculateLowestRiskVerySmall() {
        ChitonRisk submarine = new ChitonRisk();
        int i = submarine.calculateLowestRisk(verySmallPath);
        System.out.println("minimum risk very small: " + i);
        assertEquals(8, i);
    }

    @Test
    public void testCalculateLowestRiskSmall() {
        ChitonRisk submarine = new ChitonRisk();
        int i = submarine.calculateLowestRisk(smallPath);
        System.out.println("minimum risk small: " + i);
        assertEquals(40, i);
    }

    @Test
    public void testCalculateLowestRiskBig() {
        ChitonRisk submarine = new ChitonRisk();
        int i = submarine.calculateLowestRisk(bigPath);
        System.out.println("minimum risk big: " + i);
        assertEquals(40, i);
    }


}
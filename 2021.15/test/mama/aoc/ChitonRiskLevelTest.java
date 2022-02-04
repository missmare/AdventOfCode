package mama.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChitonRiskLevelTest {

    String verySmallPath = "/home/manuela/projects/AdventOfCode/2021.15/test/mama/aoc/VerySmallChiton.txt";
    String smallPath = "/home/manuela/projects/AdventOfCode/2021.15/test/mama/aoc/SmallChiton.txt";
    String bigPath = "/home/manuela/projects/AdventOfCode/2021.15/test/mama/aoc/BigChiton.txt";

    @Test
    public void testCalculateLowestRiskVerySmall() {
        ChitonRiskLevel submarine = new ChitonRiskLevel();
        int i = submarine.calculateRisk(verySmallPath);
        System.out.println("minimum risk very small: " + i);
        assertEquals(8, i);
    }

    @Test
    public void testCalculateLowestRiskSmall() {
        ChitonRiskLevel submarine = new ChitonRiskLevel();
        int i = submarine.calculateRisk(smallPath);
        System.out.println("minimum risk small: " + i);
        assertEquals(40, i);
    }

    @Test
    public void testCalculateLowestRiskBig() {
        ChitonRiskLevel submarine = new ChitonRiskLevel();
        int i = submarine.calculateRisk(bigPath);
        System.out.println("minimum risk big: " + i);
        assertEquals(40, i);
    }
}
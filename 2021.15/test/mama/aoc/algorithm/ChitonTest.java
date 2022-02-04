package mama.aoc.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChitonTest {

    String verySmallPath = "/home/manuela/projects/AdventOfCode/2021.15/test/mama/aoc/VerySmallChiton.txt";
    String smallPath = "/home/manuela/projects/AdventOfCode/2021.15/test/mama/aoc/SmallChiton.txt";
    String bigPath = "/home/manuela/projects/AdventOfCode/2021.15/test/mama/aoc/BigChiton.txt";

    @Test
    void calculateLowestRiskPath() {
        Chiton submarine = new Chiton();
        long l = submarine.calculateLowestRiskPath(verySmallPath);
        System.out.println("minimum risk very small: " + l);
        assertEquals(8, l);
    }

    @Test
    void calculateLowestRiskPathSmall() {
        Chiton submarine = new Chiton();
        long l = submarine.calculateLowestRiskPath(smallPath);
        System.out.println("minimum risk small: " + l);
        assertEquals(40, l);
    }

    @Test
    void calculateLowestRiskPathBig() {
        Chiton submarine = new Chiton();
        long l = submarine.calculateLowestRiskPath(bigPath);
        System.out.println("minimum risk big: " + l);
        assertEquals(8, l);
    }

}
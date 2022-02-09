package mama.aoc.trial;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChitonTest {

    String verySmallPath = "/home/manuela/projects/AdventOfCode/2021.15/test/mama/aoc/VerySmallChiton.txt";
    String smallPath = "/home/manuela/projects/AdventOfCode/2021.15/test/mama/aoc/SmallChiton.txt";
    String bigPath = "/home/manuela/projects/AdventOfCode/2021.15/test/mama/aoc/BigChiton.txt";


    @Test
    void calculateChitonCaveFirst() {
        Chiton submarine = new Chiton();
        int i = submarine.calculateLowestRisk(verySmallPath);
        System.out.println("Risk is: " + i);
        assertEquals(8, i);
    }

    @Test
    void calculateChitonCaveSmallFirst() {
        Chiton submarine = new Chiton();
        int i = submarine.calculateLowestRisk(smallPath);
        System.out.println("Risk is: " + i);
        assertEquals(40, i);
    }

    @Test
    void calculateChitonCaveBigFirst() {
        Chiton submarine = new Chiton();
        int i = submarine.calculateLowestRisk(bigPath);
        System.out.println("Risk is: " + i);
        assertEquals(696, i);
    }

    @Test
    void calculateChitonCaveSecond() {
        Chiton submarine = new Chiton();
        int i = submarine.calculateBigChitonCave(verySmallPath);
        System.out.println("Risk is: " + i);
        assertEquals(101, i);
    }

    @Test
    void calculateChitonCaveSmallSecond() {
        Chiton submarine = new Chiton();
        int i = submarine.calculateBigChitonCave(smallPath);
        System.out.println("Risk is: " + i);
        assertEquals(315, i);
    }

    @Test
    void calculateChitonCaveBigSecond() {
        Chiton submarine = new Chiton();
        int i = submarine.calculateBigChitonCave(bigPath);
        System.out.println("Risk is: " + i);
        assertEquals(2952, i);
    }
}
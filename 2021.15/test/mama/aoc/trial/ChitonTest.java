package mama.aoc.trial;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChitonTest {

    String verySmallPath = "/home/manuela/projects/AdventOfCode/2021.15/test/mama/aoc/VerySmallChiton.txt";
    String smallPath = "/home/manuela/projects/AdventOfCode/2021.15/test/mama/aoc/SmallChiton.txt";
    String bigPath = "/home/manuela/projects/AdventOfCode/2021.15/test/mama/aoc/BigChiton.txt";


    @Test
    void calculateChitonCave() {
        Chiton submarine = new Chiton();
        int i = submarine.calculateChitonCave(verySmallPath);
        System.out.println("Risk is: " + i);
        assertEquals(8, i);
    }

    @Test
    void calculateChitonCaveSmall() {
        Chiton submarine = new Chiton();
        int i = submarine.calculateChitonCave(smallPath);
        System.out.println("Risk is: " + i);
        assertEquals(40, i);
    }

    @Test
    void calculateChitonCaveBig() {
        Chiton submarine = new Chiton();
        int i = submarine.calculateChitonCave(bigPath);
        System.out.println("Risk is: " + i);
        assertEquals(696, i);
    }
}
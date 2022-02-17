package mama.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaveTest {

    String smallCave1 = "/home/manuela/projects/AdventOfCode/2021.12/test/mama/aoc/SmallCave1.txt";
    String smallCave2 = "/home/manuela/projects/AdventOfCode/2021.12/test/mama/aoc/SmallCave2.txt";
    String smallCave3 = "/home/manuela/projects/AdventOfCode/2021.12/test/mama/aoc/SmallCave3.txt";
    String bigPath = "/home/manuela/projects/AdventOfCode/2021.12/test/mama/aoc/BigCave.txt";

    @Test
    public void testSmallCave1() {
        Cave submarine = new Cave();
        int i = submarine.countNumberOfPaths(smallCave1);
        System.out.println("There are " + i + " paths through the cave");
        assertEquals(10, i);
    }

    @Test
    public void testSmallCave2() {
        Cave submarine = new Cave();
        int i = submarine.countNumberOfPaths(smallCave2);
        System.out.println("There are " + i + " paths through the cave");
        assertEquals(19, i);
    }

    @Test
    public void testSmallCave3() {
        Cave submarine = new Cave();
        int i = submarine.countNumberOfPaths(smallCave3);
        System.out.println("There are " + i + " paths through the cave");
        assertEquals(226, i);
    }


    @Test
    public void testBigCave() {
        Cave submarine = new Cave();
        int i = submarine.countNumberOfPaths(bigPath);
        System.out.println("There are " + i + " paths through the cave");
        assertEquals(3450, i);
    }
    @Test
    public void testSecondSmallCave1() {
        Cave submarine = new Cave();
        int i = submarine.countSecondPaths(smallCave1);
        System.out.println("There are " + i + " paths through the cave");
        assertEquals(36, i);
    }

    @Test
    public void testSecondSmallCave2() {
        Cave submarine = new Cave();
        int i = submarine.countSecondPaths(smallCave2);
        System.out.println("There are " + i + " paths through the cave");
        assertEquals(103, i);
    }

    @Test
    public void testSecondSmallCave3() {
        Cave submarine = new Cave();
        int i = submarine.countSecondPaths(smallCave3);
        System.out.println("There are " + i + " paths through the cave");
        assertEquals(3509, i);
    }


    @Test
    public void testSecondBigCave() {
        Cave submarine = new Cave();
        int i = submarine.countSecondPaths(bigPath);
        System.out.println("There are " + i + " paths through the cave");
        assertEquals(96528, i);
    }

}
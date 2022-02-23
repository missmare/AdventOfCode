package mama.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProbeTrajectTest {

    String smallTarget = "/home/manuela/projects/AdventOfCode/2021.17/test/mama/aoc/SmallTarget.txt";
    String bigTarget = "/home/manuela/projects/AdventOfCode/2021.17/test/mama/aoc/BigTarget.txt";


    @Test
    public void testSmallTarget() {
        ProbeTraject submarine = new ProbeTraject();
        int i = submarine.maxHeighthForTarget(smallTarget);
        System.out.println("maximum height is: " + i);
        assertEquals(45, i);
    }

    @Test
    public void testBigTarget() {
        ProbeTraject submarine = new ProbeTraject();
        int i = submarine.maxHeighthForTarget(bigTarget);
        System.out.println("maximum heigth is: " + i);
        assertEquals(2850, i);
    }

    @Test
    public void testAmountOfOptionsSmall() {
        ProbeTraject submarine = new ProbeTraject();
        int i = submarine.numberOfPossiblePaths(smallTarget);
        System.out.println("number of possible paths: " + i);
        assertEquals(112, i);
    }

    @Test
    public void testAmountOfOptionsBig() {
        ProbeTraject submarine = new ProbeTraject();
        int i = submarine.numberOfPossiblePaths(bigTarget);
        System.out.println("number of possible paths: " + i);
        assertEquals(1117, i);
    }


}
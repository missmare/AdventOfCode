package mama.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OctopusFlashTest {

    String smallPath = "/home/manuela/projects/AdventOfCode/2021.11/test/mama/aoc/smallOctopusses.txt";
    String bigPath = "/home/manuela/projects/AdventOfCode/2021.11/test/mama/aoc/bigOctopusses.txt";

    @Test
    public void testSmallOctopusses() {
        OctopusFlash submarine = new OctopusFlash();
        int i = submarine.countFlashes(smallPath);
        System.out.println("Flashed times: "+ i );
        assertEquals(1656, i);
    }
    @Test
    public void testBigOctopusses() {
        OctopusFlash submarine = new OctopusFlash();
        int i = submarine.countFlashes(bigPath);
        System.out.println("Flashed times: "+ i );
        assertEquals(1673, i);
    }

    @Test
    public void testSimultaneousSmall() {
        OctopusFlash submarine = new OctopusFlash();
        int i = submarine.getNumberOfStepsForSimultaneousFlash(smallPath);
        System.out.println("number of steps for simultaneous flashes: " + i );
        assertEquals(195, i);
    }

    @Test
    public void testSimultaneousBig() {
        OctopusFlash submarine = new OctopusFlash();
        int i = submarine.getNumberOfStepsForSimultaneousFlash(bigPath);
        System.out.println("number of steps for simultaneous flashes: " + i );
        assertEquals(279, i);
    }

}
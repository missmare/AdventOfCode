package mama.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SubmarineTest {

    public static final int[] SONAR = new int[]{199, 200, 208, 210, 200, 207, 240, 269, 260, 263};
    private final String SONAR_DEPTHS = "/home/manuela/projects/AdventOfCode/2021.01/test/mama/aoc/SonarDepths.txt";

    @Test
    public void doAdventOfCodePuzzle() {
        Submarine uboot = new Submarine();
        int increase = uboot.measureIncrease(uboot.readSonar(SONAR_DEPTHS));
        System.out.println("Increases Sonar: " + increase);
        assertTrue(increase > 0, "Should get at least 1 increasement");
    }

    @Test
    public void doAdventOfCodeSecondPuzzle() {
        Submarine uboot = new Submarine();
        int increase = uboot.measureWindowIncrease(uboot.readSonar(SONAR_DEPTHS));
        System.out.println("Increases Window: " + increase);
        assertTrue(increase > 0, "Should get at least 1 increasement");
    }

    @Test
    public void measureIncrease() {
        Submarine uboot = new Submarine();
        int numberOfIncreases = uboot.measureIncrease(SONAR);
        assertEquals(7, numberOfIncreases, "Submarine should measure 7 increasements");
    }

    @Test
    public void measureWindowIncrease() {
        Submarine uboot = new Submarine();
        int increase = uboot.measureWindowIncrease(SONAR);
        assertEquals(5, increase, "There should be 5 windows, that increase.");
    }


}
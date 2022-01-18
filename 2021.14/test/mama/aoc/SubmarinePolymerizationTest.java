package mama.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubmarinePolymerizationTest {

    String smallPath = "/home/manuela/projects/AdventOfCode/2021.14/test/mama/aoc/SmallPolyRules.txt";
    String bigPath = "/home/manuela/projects/AdventOfCode/2021.14/test/mama/aoc/BigPolyRules.txt";

    @Test
    public void applySmallRules() {
        SubmarinePolymerization submarine = new SubmarinePolymerization();
        long i = submarine.calculatePolymerization(smallPath, 10);
        assertEquals(1588, i);
    }

}
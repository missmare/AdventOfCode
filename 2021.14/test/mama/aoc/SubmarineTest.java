package mama.aoc;

import mama.aoc.trial3.SubmarinePolymerization;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubmarineTest {

    private String smallPath = "/home/manuela/projects/AdventOfCode/2021.14/test/mama/aoc/SmallPolyRules.txt";
    String bigPath = "/home/manuela/projects/AdventOfCode/2021.14/test/mama/aoc/BigPolyRules.txt";

    @Test
    public void testSmallPolymerization() {
        SubmarinePolymerization submarine = new SubmarinePolymerization();
        long l = submarine.calculatePolymerization(smallPath, 10);
        assertEquals(1588, l);
    }

    @Test
    public void testBigPolymerization() {
        SubmarinePolymerization submarine = new SubmarinePolymerization();
        long l = submarine.calculatePolymerization(bigPath, 10);
        assertEquals(2891, l);
    }

    @Test
    public void testSmallPolymerizationManyTimes() {
        SubmarinePolymerization submarine = new SubmarinePolymerization();
        long l = submarine.calculatePolymerization(smallPath, 40);
        assertEquals(2188189693529L, l);
    }

    @Test
    public void testBigPolymerizationManyTimes() {
        SubmarinePolymerization submarine = new SubmarinePolymerization();
        long l = submarine.calculatePolymerization(bigPath, 40);
        assertEquals(4607749009683L, l);
    }
}

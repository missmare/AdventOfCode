package mama.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeightSensorTest {

    private final String smallPath = "/home/manuela/projects/AdventOfCode/2021.09/test/mama/aoc/SmallHeight.txt";
    private final String bigPath = "/home/manuela/projects/AdventOfCode/2021.09/test/mama/aoc/BigHeight.txt";

    @Test
    public void testRiskOfHeightSmall() {
        HeightSensor submarine = new HeightSensor();
        int i = submarine.calculateHeightRisk(smallPath);
        assertEquals(15, i, "The risk of the small map (sum of all lowest numbers, each plus 1) is 15");
    }

    @Test
    public void testRiskOfHeightBig() {
        HeightSensor submarine = new HeightSensor();
        int i = submarine.calculateHeightRisk(bigPath);
        assertEquals(468, i, "The risk of the big map (sum of all lowest numbers, each plus 1) is 468");
    }

    @Test
    public void testBasinsSmall() {
        HeightSensor submarine = new HeightSensor();
        int i = submarine.findThreeBasins(smallPath);
        assertEquals(1134, i);
    }

    @Test
    public void testBasinsBig() {
        HeightSensor submarine = new HeightSensor();
        int i = submarine.findThreeBasins(bigPath);
        assertEquals(1280496, i);
    }

}
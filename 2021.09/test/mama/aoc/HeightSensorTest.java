package mama.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeightSensorTest {

    private String smallPath = "/home/manuela/projects/AdventOfCode/2021.09/test/mama/aoc/SmallHeight.txt";
    private String bigPath = "/home/manuela/projects/AdventOfCode/2021.09/test/mama/aoc/BigHeight.txt";

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

}
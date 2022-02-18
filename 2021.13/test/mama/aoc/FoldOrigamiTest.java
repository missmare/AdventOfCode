package mama.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoldOrigamiTest {

    String smallInstructions="/home/manuela/projects/AdventOfCode/2021.13/test/mama/aoc/SmallInstructions.txt";
    String bigInstructions="/home/manuela/projects/AdventOfCode/2021.13/test/mama/aoc/BigInstructions.txt";

    @Test
    public void testSmallInstructions() {
        FoldOrigami submarine = new FoldOrigami();
        int i = submarine.countDots(smallInstructions);
        System.out.println("The instructions result in "+ i + " dots");
        assertEquals(17, i);
    }
    @Test
    public void testBigInstructions() {
        FoldOrigami submarine = new FoldOrigami();
        int i = submarine.countDots(bigInstructions);
        System.out.println("The instructions result in "+ i + " dots");
        assertEquals(729, i);
    }

    @Test
    public void testSecondSmall() {
        FoldOrigami submarine = new FoldOrigami();
        submarine.printDots(smallInstructions);
    }
    @Test
    public void testSecondBig() {
        FoldOrigami submarine = new FoldOrigami();
        submarine.printDots(bigInstructions);
    }
}
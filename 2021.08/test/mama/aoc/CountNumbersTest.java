package mama.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountNumbersTest {

    private final String smallNumbersPath = "/home/manuela/projects/AdventOfCode/2021.08/test/mama/aoc/SmallNumbers.txt";
    private final String verySmallNumbersPath = "/home/manuela/projects/AdventOfCode/2021.08/test/mama/aoc/VerySmallNumbers.txt";
    private final String bigNumbersPath = "/home/manuela/projects/AdventOfCode/2021.08/test/mama/aoc/BigNumbers.txt";

    @Test
    public void countUniqueNumbersSmallTest() {
        CountNumbers submarine = new CountNumbers();
        int i = submarine.countUniqueDigitNumbers(smallNumbersPath);
        assertEquals(26, i);
    }

    @Test
    public void countUniqueNumbersBigTest() {
        CountNumbers submarine = new CountNumbers();
        int i = submarine.countUniqueDigitNumbers(bigNumbersPath);
        System.out.println("There are " + i + " unique digits in the big input.");
        assertTrue(i>0);
    }

    @Test
    public void countNumbersSmallTest() {
        CountNumbers submarine = new CountNumbers();
        long i = submarine.countNumbers(smallNumbersPath);
        assertEquals(61229, i);
    }

    @Test
    public void countNumbersVerySmallTest() {
        CountNumbers submarine = new CountNumbers();
        long i = submarine.countNumbers(verySmallNumbersPath);
        assertEquals(1625, i);
    }

    @Test
    public void countNumbersBigTest() {
        CountNumbers submarine = new CountNumbers();
        long i = submarine.countNumbers(bigNumbersPath);
        System.out.println("The sum of the big input is " + i);
        assertTrue(i>0);
    }

//    bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef

}
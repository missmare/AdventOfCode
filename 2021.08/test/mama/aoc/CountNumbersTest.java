package mama.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountNumbersTest {

    private String smallNumbersPath = "/home/manuela/projects/AdventOfCode/2021.08/test/mama/aoc/SmallNumbers.txt";
    private String bigNumbersPath = "/home/manuela/projects/AdventOfCode/2021.08/test/mama/aoc/BigNumbers.txt";

    @Test
    public void countNumbersSmallTest() {
        CountNumbers submarine = new CountNumbers();
        int i = submarine.countUniqueDigitNumbers(smallNumbersPath);
        assertEquals(26, i, "there are 26 unique digit numbers in the small input");
    }

    @Test
    public void countNumbersBigTest() {
        CountNumbers submarine = new CountNumbers();
        int i = submarine.countUniqueDigitNumbers(bigNumbersPath);
        assertTrue(i>0, "there are more than 0 unique digits in the big input");
    }
}
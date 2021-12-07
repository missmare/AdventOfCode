package mama.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BingoTest {

    @Test
    public void testBingoSmall() {
        Bingo bingo = new Bingo();
        int score = bingo.getFinalScore("/home/manuela/projects/AdventOfCode/2021.04/test/mama/aoc/BingoSmall.txt");
        assertEquals(4512, score, "The final score of the small set is 188 * 24 = 4512");
    }

    @Test
    public void testBingoBig() {
        Bingo bingo = new Bingo();
        int score = bingo.getFinalScore("/home/manuela/projects/AdventOfCode/2021.04/test/mama/aoc/BingoBig.txt");
        assertTrue(score > 0, "The final score of the small set is 829 * 17 = 14093");
    }



}
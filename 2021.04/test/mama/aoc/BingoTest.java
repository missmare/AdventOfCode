package mama.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BingoTest {

    private String pathToBingoBig = "/home/manuela/projects/AdventOfCode/2021.04/test/mama/aoc/BingoBig.txt";
    private String pathToBingoSmall = "/home/manuela/projects/AdventOfCode/2021.04/test/mama/aoc/BingoSmall.txt";

    @Test
    public void testBingoSmall() {
        Bingo bingo = new Bingo();
        int score = bingo.getFinalScore(pathToBingoSmall);
        assertEquals(4512, score, "The final score of the small set is 188 * 24 = 4512");
    }

    @Test
    public void testBingoBig() {
        Bingo bingo = new Bingo();
        int score = bingo.getFinalScore(pathToBingoBig);
        assertTrue(score > 0, "The final score of the big set is 829 * 17 = 14093");
    }

    @Test
    public void testLastWinningBoardSmall() {
        Bingo bingo = new Bingo();
        int score = bingo.calculateScoreOfLastWinningBoard(pathToBingoSmall);
        assertEquals(1924, score, "The final score of the small set is 148 * 13 = 1924");
    }

    @Test
    public void testLastWinningBoardBig() {
        Bingo bingo = new Bingo();
        int score = bingo.calculateScoreOfLastWinningBoard(pathToBingoBig);
        assertTrue(score > 0, "The final score of the last winning board in the big set is 483 * 36 = 17388");
    }

}
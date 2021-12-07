package mama.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BingoTest {

    @Test
    public void testBingoSmall() {
        Bingo bingo = new Bingo();
        int score = bingo.getFinalScore("/home/manuela/projects/AdventOfCode/2021.04/test/mama/aoc/BingoSmall.txt");
    }

}
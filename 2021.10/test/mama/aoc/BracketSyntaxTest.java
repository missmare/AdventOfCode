package mama.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BracketSyntaxTest {

    private final String smallPath = "/home/manuela/projects/AdventOfCode/2021.10/test/mama/aoc/SmallBrackets.txt";
    private final String bigPath = "/home/manuela/projects/AdventOfCode/2021.10/test/mama/aoc/BigBrackets.txt";

    @Test
    public void testCorruptedLines() {
        BracketSyntax submarine = new BracketSyntax();
        long i = submarine.calculateCorruption(smallPath);
        assertEquals(26397, i);
    }

    @Test
    public void testCorruptedLinesBig() {
        BracketSyntax submarine = new BracketSyntax();
        long i = submarine.calculateCorruption(bigPath);
        assertEquals(344193, i);
    }

    @Test
    public void testIncompleteLinesSmall() {
        BracketSyntax submarine = new BracketSyntax();
        long i = submarine.calculateIncomplete(smallPath);
        assertEquals(288957, i);
    }

    @Test
    public void testIncompleteLinesBig() {
        BracketSyntax submarine = new BracketSyntax();
        long i = submarine.calculateIncomplete(bigPath);
        assertEquals(3241238967L, i);
    }

}
package mama.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolymerizationTest {

    String smallPath = "/home/manuela/projects/AdventOfCode/2021.14/test/mama/aoc/SmallPolyRules.txt";
    String bigPath = "/home/manuela/projects/AdventOfCode/2021.14/test/mama/aoc/BigPolyRules.txt";

    @Test
    public void applySmallRules() {
        Polymerization submarine = new Polymerization();
        int i = submarine.applyRulesTimes(smallPath, 10);
        assertEquals(1588, i);
    }

    @Test
    public void applyBigRules() {
        Polymerization submarine = new Polymerization();
        int i = submarine.applyRulesTimes(bigPath, 10);
        assertEquals(2891, i);
    }

    @Test
    public void applySmallRulesSecondPuzzle() {
        Polymerization submarine = new Polymerization();
        long i = submarine.applyLongRulesTimes(smallPath, 40);
        assertEquals(2188189693529L, i);
    }

    @Test
    public void applySmallRulesSecondPuzzleExceedInteger() {
        Polymerization submarine = new Polymerization();
        long i = submarine.applyLongRulesTimesExceedlong(smallPath, 40);
        assertEquals(2188189693529L, i);
    }



    @Test
    public void applyBigRulesSecondPuzzle() {
        Polymerization submarine = new Polymerization();
        int i = submarine.applyRulesTimes(bigPath, 10);
        assertEquals(2891, i);
    }



}
package mama.aoc;

import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

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
        long i = submarine.applyRulesTimes(smallPath, 40);
        assertEquals(2188189693529L, i);
    }

    @Test
    public void applyBigRulesSecondPuzzle() {
        Polymerization submarine = new Polymerization();
        int i = submarine.applyRulesTimes(bigPath, 10);
        assertEquals(2891, i);
    }

    @Test
    public void testMaxLong() {
        System.out.println(Long.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE);
        long finalLenght = calculateFinalLenght(20, 40);
        System.out.println("small 40: " + finalLenght);
        System.out.println(finalLenght / Integer.MAX_VALUE);
        System.out.println("small 10: " + calculateFinalLenght(4, 10));
        System.out.println("big 10: " + calculateFinalLenght(20, 10));
        System.out.println("big 40: " + calculateFinalLenght(20, 40));
    }

    @Test
    public void testBigLenght() {
        for (int i = 0; i < 40; i++) {
            long l = calculateFinalLenght(20, i);
            double m = (l + 0.00) / Integer.MAX_VALUE;
            System.out.println("Template length after step " + i + " is " + l + ". this is " + m + " times integer.max");
        }
    }

    private long calculateFinalLenght(int templateLenght, int times) {
        long power = calculatePower(2, times);
        return power * templateLenght - (power - 1);
    }

    private long calculatePower(long base, long expontent) {
        long result = 1;
        for (int e = 0; e < expontent; e++) {
            result *= base;
        }
        return result;
    }

    @Test
    public void printOutputLength() {
        DecimalFormat format = new DecimalFormat("###,###.##");
        for (int i = 0; i < 40; i++) {
            System.out.println("step " + (i + 1) + " template of length " + format.format(calculateFinalLenght(4, i)));
            System.out.println("     " + (i + 1) + "                    " + (format.format(Integer.MAX_VALUE / 2)));
        }
    }

}
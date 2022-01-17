package mama.aoc;

public class MathematicCalculator {

    public static long calculatePower(long base, long expontent) {
        long result = 1;
        for (int e = 0; e < expontent; e++) {
            result *= base;
        }
        return result;
    }
}

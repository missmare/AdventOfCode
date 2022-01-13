package mama.aoc;

import java.util.Arrays;
import java.util.List;

public class CountNumbers {

    private final List<Integer> uniqueAmountAsList = Arrays.asList(2, 3, 4, 7);
    private int uniqueDigits = 0;

    public int countUniqueDigitNumbers(String path) {
        List<String> strings = FileReader.readFile(path);
        for (String noteEntry : strings) {
            String[] split = noteEntry.split(" \\| ");
            String[] digits = split[1].split(" ");
            countUniqueDigits(digits);
        }
        return uniqueDigits;
    }

    public long countNumbers(String path) {
        long result = 0;
        List<String> strings = FileReader.readFile(path);
        for (String noteEntry : strings) {
            String[] split = noteEntry.split(" \\| ");
            SevenSegmentDigit mapping = new SevenSegmentDigit(split[0]);
            int mappedDigit = mapping.mapDisplay(split[1]);
            result += mappedDigit;
        }
        return result;
    }

    private void countUniqueDigits(String[] digits) {
        for (String digit : digits) {
            if (uniqueAmountAsList.contains(digit.length())) {
                uniqueDigits++;
            }
        }
    }

}

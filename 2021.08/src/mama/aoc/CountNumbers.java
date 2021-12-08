package mama.aoc;

import java.util.Arrays;
import java.util.List;

public class CountNumbers {

    private List<Integer> uniqueAmountAsList = Arrays.asList(2, 3, 4, 7);
    private int uniqueDigits = 0;

    public int countUniqueDigitNumbers(String path) {
        List<String> strings = FileReader.readFile(path);
        for (String noteEntry : strings) {
            String[] split = noteEntry.split(" \\| ");
//            System.out.println("Digits displayed: " + split[1]);
            String[] digits = split[1].split(" ");
            countUniqueDigits(digits);
//            System.out.println("");
        }
        System.out.println("Unique digits: " + uniqueDigits);
        return uniqueDigits;
    }

    private void countUniqueDigits(String[] digits) {
        for (String digit :
                digits) {
//            System.out.println("Digit " + digit + "\t has length " + digit.length());
            if (uniqueAmountAsList.contains(digit.length())) {
                uniqueDigits++;
            }
        }
    }
}

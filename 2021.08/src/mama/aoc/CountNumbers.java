package mama.aoc;

import java.util.ArrayList;
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

    public int countNumbers(String path) {
        List<String> strings = FileReader.readFile(path);
        for (String noteEntry : strings) {
            String[] split = noteEntry.split(" \\| ");
            findOutMapping(split[0]);
            System.out.println("First part: " + split[0]);
            System.out.println("\tsecond part: " + split[1]);
        }
        return 0;
    }

    private void findOutMapping(String noteEntry) {
        String[] digits = noteEntry.split(" ");
        SevenSegmentDigit mapping = new SevenSegmentDigit();
        for (String digit : digits) {
            digit = sortString(digit);
            switch (digit.length()) {
                case 2:
                    mapping.setOne(digit);
                    break;
                case 3:
                    mapping.setSeven(digit);
                    break;
                case 4:
                    mapping.setFour(digit);
                    break;
                case 5:
                    mapping.fiveDigits.add(digit);
                    break;
                case 6:
                    mapping.sixDigits.add(digit);
                    break;
                case 7:
                    mapping.setEight(digit);
            }

        }
        mapping.arrangeSegments();

    }

    private String sortString(String input) {
        char[] chars = input.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }


    private class SevenSegmentDigit {
        char segment_a;
        char segment_b;
        char segment_c;
        char segment_d;
        char segment_e;
        char segment_f;
        char segment_g;


        String one;
        String seven;
        String four;
        String eight;

        StringBuilder segment_cf = new StringBuilder();
        StringBuilder segment_bd = new StringBuilder();
        StringBuilder segment_eg = new StringBuilder();

        List<String> fiveDigits = new ArrayList<>();
        List<String> sixDigits = new ArrayList<>();

        public void arrangeSegments() {
            for (char c : seven.toCharArray()) {
                if (one.contains(c + "")) {
                    segment_cf.append(c);
                } else {
                    segment_a = c;
                }
            }
            for (char c : four.toCharArray()) {
                if (!one.contains(c + "")) {
                    segment_bd.append(c);
                }
            }
            for (char c : eight.toCharArray()) {
                if (!four.contains(c + "") && segment_a != c) {
                    segment_eg.append(c);
                }
            }

            //now assign bcdefg
            CharSequence bd_one = segment_bd.subSequence(0, 1);
            CharSequence bd_two = segment_bd.subSequence(1, 2);

            CharSequence cf_one = segment_cf.subSequence(0, 1);
            CharSequence cf_two = segment_cf.subSequence(1, 2);

            CharSequence eg_one = segment_eg.subSequence(0, 1);
            CharSequence eg_two = segment_eg.subSequence(1, 2);

            for (String fiveDigit : fiveDigits) {
                if (fiveDigit.contains(eg_one) && fiveDigit.contains(eg_two)) { // this is the two
                    if (fiveDigit.contains(bd_one)) {
                        segment_b = bd_two.charAt(0);
                        segment_d = bd_one.charAt(0);
                    } else {
                        segment_b = bd_one.charAt(0);
                        segment_d = bd_two.charAt(0);
                    }

                    if (fiveDigit.contains(cf_one)) {
                        segment_c = cf_one.charAt(0);
                        segment_f = cf_two.charAt(0);
                    } else {
                        segment_c = cf_two.charAt(0);
                        segment_f = cf_one.charAt(0);
                    }

                } else if (fiveDigit.contains(bd_one) && fiveDigit.contains(bd_two)) { // this is the five
                    if (fiveDigit.contains(eg_one)) {
                        segment_e = eg_two.charAt(0);
                        segment_g = eg_one.charAt(0);
                    } else {
                        segment_e = eg_one.charAt(0);
                        segment_g = eg_one.charAt(0);
                    }

                }
            }

            System.out.println("Assignments: a=" + segment_a
                    + ", b=" + segment_b
                    + ", c=" + segment_c
                    + ", d=" + segment_d
                    + ", e=" + segment_e
                    + ", f=" + segment_f
                    + ", g=" + segment_g
            );

        }

        public char getSegment_a() {
            return segment_a;
        }

        public void setSegment_a(char segment_a) {
            this.segment_a = segment_a;
        }

        public char getSegment_b() {
            return segment_b;
        }

        public void setSegment_b(char segment_b) {
            this.segment_b = segment_b;
        }

        public char getSegment_c() {
            return segment_c;
        }

        public void setSegment_c(char segment_c) {
            this.segment_c = segment_c;
        }

        public char getSegment_d() {
            return segment_d;
        }

        public void setSegment_d(char segment_d) {
            this.segment_d = segment_d;
        }

        public char getSegment_e() {
            return segment_e;
        }

        public void setSegment_e(char segment_e) {
            this.segment_e = segment_e;
        }

        public char getSegment_f() {
            return segment_f;
        }

        public void setSegment_f(char segment_f) {
            this.segment_f = segment_f;
        }

        public char getSegment_g() {
            return segment_g;
        }

        public void setSegment_g(char segment_g) {
            this.segment_g = segment_g;
        }

        public String getOne() {
            return one;
        }

        public void setOne(String one) {
            this.one = one;
        }

        public String getSeven() {
            return seven;
        }

        public void setSeven(String seven) {
            this.seven = seven;
        }

        public String getFour() {
            return four;
        }

        public void setFour(String four) {
            this.four = four;
        }

        public String getEight() {
            return eight;
        }

        public void setEight(String eight) {
            this.eight = eight;
        }


    }
}

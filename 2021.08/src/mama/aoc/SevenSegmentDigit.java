package mama.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SevenSegmentDigit {

    private char segment_a;
    private char segment_b;
    private char segment_c;
    private char segment_d;
    private char segment_e;
    private char segment_f;
    private char segment_g;

    private String one;
    private String seven;
    private String four;
    private String eight;

    private StringBuilder segment_cf = new StringBuilder();
    private StringBuilder segment_bd = new StringBuilder();
    private StringBuilder segment_eg = new StringBuilder();

    private List<String> fiveDigits = new ArrayList<>();
    //private List<String> sixDigits = new ArrayList<>();

    public SevenSegmentDigit(String noteEntryForMapping) {
        String[] digits = noteEntryForMapping.split(" ");
//        System.out.println(noteEntryForMapping);
//        System.out.println(Arrays.toString(digits));
        for (String digit : digits) {
            switch (digit.length()) {
                case 2:
                    this.setOne(digit);
                    break;
                case 3:
                    this.setSeven(digit);
                    break;
                case 4:
                    this.setFour(digit);
                    break;
                case 7:
                    this.setEight(digit);
                    break;
                case 5:
                    this.addToFiveDigits(digit);
                    break;
                case 6:
                    //do nothing, five segment digits are enough to sort out the remaining segments
                    break;
                default:
                    throw new RuntimeException("check digit " + digit);
            }
        }
        this.arrangeSegments();
    }

    public void arrangeSegments() {
        for (char singleCharacter : seven.toCharArray()) {
            if (one.contains(singleCharacter + "")) {
                segment_cf.append(singleCharacter);
            } else {
                segment_a = singleCharacter;
            }
        }
//        System.out.println("Compare one (" + one + ") and seven (" + seven + ").\t\t cf=" + segment_cf + ", a=" + segment_a);

        for (char singleCharacter : four.toCharArray()) {
            if (!one.contains(singleCharacter + "")) {
                segment_bd.append(singleCharacter);
            }
        }
//        System.out.println("Compare one (" + one + ") and four (" + four + "). \t\t bd=" + segment_bd);

        for (char singleCharacter : eight.toCharArray()) {
            if (!four.contains(singleCharacter + "") && segment_a != singleCharacter) {
                segment_eg.append(singleCharacter);
            }
        }
//        System.out.println("Compare eight (" + eight + ") and four(" + four + ").\t eg=" + segment_eg);

        //now assign remaining segments (bcdefg)
        CharSequence bd_one = segment_bd.subSequence(0, 1);
        CharSequence bd_two = segment_bd.subSequence(1, 2);

        CharSequence cf_one = segment_cf.subSequence(0, 1);
        CharSequence cf_two = segment_cf.subSequence(1, 2);

        CharSequence eg_one = segment_eg.subSequence(0, 1);
        CharSequence eg_two = segment_eg.subSequence(1, 2);

        for (String fiveDigit : fiveDigits) {
            if (fiveDigit.contains(eg_one) && fiveDigit.contains(eg_two)) { // this is the two
                if (fiveDigit.contains(bd_one)) {  //if two contains bd_one, then this is segment d
                    segment_d = bd_one.charAt(0);
                    segment_b = bd_two.charAt(0);
                } else {                          //otherwise bd_two is segment d
                    segment_b = bd_one.charAt(0);
                    segment_d = bd_two.charAt(0);
                }

                if (fiveDigit.contains(cf_one)) {  //if two contains cf_one, then this is segment c
                    segment_c = cf_one.charAt(0);
                    segment_f = cf_two.charAt(0);
                } else {                           //otherwise cf_two is segment c
                    segment_c = cf_two.charAt(0);
                    segment_f = cf_one.charAt(0);
                }

            } else if (fiveDigit.contains(bd_one) && fiveDigit.contains(bd_two)) { // this is the five
                if (fiveDigit.contains(eg_one)) { //if five contains eg_one, then this is segment e
                    segment_e = eg_two.charAt(0);
                    segment_g = eg_one.charAt(0);
                } else {                          //otherwise eg_two is segment_e
                    segment_e = eg_one.charAt(0);
                    segment_g = eg_two.charAt(0);
                }

            }
        }
//            printAssignment();
    }

    private String sortString(String input) {
        char[] chars = input.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    private void printAssignment() {
        System.out.println("Assignments: \t a=" + segment_a
                + ", b=" + segment_b
                + ", c=" + segment_c
                + ", d=" + segment_d
                + ", e=" + segment_e
                + ", f=" + segment_f
                + ", g=" + segment_g
        );
    }

    private void setOne(String one) {
        this.one = one;
    }

    private void setSeven(String seven) {
        this.seven = seven;
    }

    private void setFour(String four) {
        this.four = four;
    }

    private void setEight(String eight) {
        this.eight = eight;
    }

    private void addToFiveDigits(String digit) {
        this.fiveDigits.add(digit);
    }

    public int mapDisplay(String input) {
        String[] digits = input.split(" ");
        StringBuilder result = new StringBuilder();
        for (String digit : digits) {
            result.append(mapDigit(digit));
        }
        return Integer.parseInt(result.toString());
    }

    private int mapDigit(String digit) {
        switch (digit.length()) {
            case 2:
                return 1;
            case 3:
                return 7;
            case 4:
                return 4;
            case 7:
                return 8;
            case 5:
                if (digit.contains(segment_c + "") && digit.contains(segment_e + "")) {
                    return 2;
                } else if (digit.contains(segment_c + "") && digit.contains(segment_f + "")) {
                    return 3;
                } else if (digit.contains(segment_b + "") && digit.contains(segment_f + "")) {
                    return 5;
                }
            case 6:
                if (!digit.contains(segment_d + "")) {
                    return 0;
                } else if (!digit.contains(segment_c + "")) {
                    return 6;
                } else if (!digit.contains(segment_e + "")) {
                    return 9;
                }
            default:
                printAssignment();
                throw new UnsupportedOperationException("check digit " + digit);
        }
    }
}

package mama.aoc;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class Brackets {

    private static final List<Character> openingCharacters = Arrays.asList('(', '[', '{', '<');
    private static final List<Character> closingCharacters = Arrays.asList(')', ']', '}', '>');
    private static final Vector<Tuple<Character>> illegalCharTable = new Vector<>();
    private static final Vector<Tuple<Character>> incompleteCharTable = new Vector<>();

    static {
        illegalCharTable.add(new Tuple<>(')', 3));
        illegalCharTable.add(new Tuple<>(']', 57));
        illegalCharTable.add(new Tuple<>('}', 1197));
        illegalCharTable.add(new Tuple<>('>', 25137));

        incompleteCharTable.add(new Tuple<>(')', 1));
        incompleteCharTable.add(new Tuple<>(']', 2));
        incompleteCharTable.add(new Tuple<>('}', 3));
        incompleteCharTable.add(new Tuple<>('>', 4));
    }

    public static boolean isOpeningCharacter(char c) {
        return openingCharacters.contains(c);
    }

    public static boolean isClosingCharacter(char c) {
        return closingCharacters.contains(c);
    }

    public static boolean checkOpeningCharacterForClose(char opening, char closing) {
        switch (opening) {
            case '(':
                return ')' == closing;
            case '[':
                return ']' == closing;
            case '{':
                return '}' == closing;
            case '<':
                return '>' == closing;
            default:
                throw new UnsupportedOperationException("check brackets");
        }
    }

    public static char getClosingBracketFor(Character opening) {
        switch (opening) {
            case '(':
                return ')';
            case '[':
                return ']';
            case '{':
                return '}';
            case '<':
                return '>';
            default:
                throw new UnsupportedOperationException("is " + opening + " really an opening bracket?");
        }
    }

    public static long getCorruptedBracketPoints(char c) {
        return illegalCharTable.stream().filter(t -> t.getKey() == c).mapToLong(Tuple::getValue).sum();
    }

    public static int getIncompleteBracketPoints(char c) {
        return incompleteCharTable.stream().filter(t -> t.getKey() == c).mapToInt(Tuple::getValue).sum();
    }
}
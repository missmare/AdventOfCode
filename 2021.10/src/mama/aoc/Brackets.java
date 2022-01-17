package mama.aoc;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class Brackets {

    private static final List<Character> openingCharacters = Arrays.asList('(', '[', '{', '<');
    private static final List<Character> closingCharacters = Arrays.asList(')', ']', '}', '>');
    private static final Vector<Tuple<Character>> illegalCharTable = new Vector<>();

    static {
        illegalCharTable.add(new Tuple<>(')', 3));
        illegalCharTable.add(new Tuple<>(']', 57));
        illegalCharTable.add(new Tuple<>('}', 1197));
        illegalCharTable.add(new Tuple<>('>', 25137));
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

    public static long getBracketPoints(char c) {
        return illegalCharTable.stream().filter(t -> t.getKey() == c).mapToLong(Tuple::getValue).sum();
    }
}
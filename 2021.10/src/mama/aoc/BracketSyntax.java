package mama.aoc;

import java.util.*;

public class BracketSyntax {


    public BracketSyntax() {

    }

    public long calculateCorruption(String path) {
        List<String> brackets = FileReader.readFile(path);
        Map<Character, Integer> corruptedValues = new HashMap<>();
        for (String bracketLine : brackets) {
            char lineCorruptedWith = isLineCorruptedWith(bracketLine);
            if (lineCorruptedWith == 'i') {
                //ignore incomplete lines
            } else if (corruptedValues.containsKey(lineCorruptedWith)) {
                int i = corruptedValues.get(lineCorruptedWith);
                corruptedValues.put(lineCorruptedWith, i + 1);
            } else {
                corruptedValues.put(lineCorruptedWith, 1);
            }

//            System.out.println(bracketLine + " :\t" + lineCorruptedWith);
        }
//        System.out.println(corruptedValues);

        long sumIllegalBracketPoints = 0L;
        for (Map.Entry<Character, Integer> entry : corruptedValues.entrySet()) {
            sumIllegalBracketPoints += Brackets.getBracketPoints(entry.getKey()) * entry.getValue();
        }

        return sumIllegalBracketPoints;
    }

    private char isLineCorruptedWith(String line) {
        Queue<Character> lifo = Collections.asLifoQueue(new ArrayDeque<>());
        for (char c : line.toCharArray()) {
            if (Brackets.isOpeningCharacter(c)) {
                lifo.add(c);
            }
            if (Brackets.isClosingCharacter(c)) {
                Character opening = lifo.remove();
                if (!Brackets.checkOpeningCharacterForClose(opening, c)) {
                    return c;
                }
            }
        }

        return 'i';
    }


}

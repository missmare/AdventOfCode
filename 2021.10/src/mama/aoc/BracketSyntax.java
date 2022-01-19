package mama.aoc;

import java.util.*;

public class BracketSyntax {

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
            sumIllegalBracketPoints += Brackets.getCorruptedBracketPoints(entry.getKey()) * entry.getValue();
        }

        return sumIllegalBracketPoints;
    }

    public long calculateIncomplete(String path) {
        List<String> brackets = FileReader.readFile(path);

        List<Long> finalizeLines = new ArrayList<>();
        int corrupted = 0;

        for (String bracketLine : brackets) {
            String completeLine = lineCompletesWith(bracketLine);
            if (!"c".equals(completeLine)) { // ignore corrupted lines
                long i = calculateBracketScore(completeLine);
                finalizeLines.add(i);
            } else {
                corrupted++;
            }
        }
        Collections.sort(finalizeLines);

        System.out.println(finalizeLines);

        return finalizeLines.get((finalizeLines.size()) / 2);
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

    private String lineCompletesWith(String line) {
        Queue<Character> lifo = Collections.asLifoQueue(new ArrayDeque<>());
        for (char c : line.toCharArray()) {
            if (Brackets.isOpeningCharacter(c)) {
                lifo.add(c);
            }
            if (Brackets.isClosingCharacter(c)) {
                Character opening = lifo.remove();
                if (!Brackets.checkOpeningCharacterForClose(opening, c)) {
                    return "c"; //corrupted line
                }
            }
        }
        StringBuilder complete = new StringBuilder();
        for (Character openingBracket : lifo) {
            complete.append(Brackets.getClosingBracketFor(openingBracket));
        }
        return complete.toString();
    }

    private long calculateBracketScore(String finalizeIncompleteLine) {
        long result = 0L;
        for (char bracket : finalizeIncompleteLine.toCharArray()) {
            long i = Brackets.getIncompleteBracketPoints(bracket);
            result = 5 * result + i;
        }
        return result;
    }

}

package mama.aoc;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bingo {

    private int winningNumber;

    public int getFinalScore(String pathToBingo) {
        List<String> strings = FileReader.readFile(pathToBingo);
        List<Integer> bingoNumbers = getBingoNumbers(strings);

        Set<BingoTable> bingoTables = readTables(strings);
        for (Integer nextNumber : bingoNumbers) {
            for (BingoTable table : bingoTables) {
                Pair<Integer, Boolean> tableSuccess = table.checkNextNumber(nextNumber);
                if (tableSuccess.getValue()) {
                    System.out.println("Table won.");
                    System.out.println(table.print());
                    System.out.println("Final score: " + tableSuccess.getKey() + " with number: " + nextNumber);
                    System.out.println("This results in " + tableSuccess.getKey() * nextNumber);
                    return tableSuccess.getKey() * nextNumber;
                }
            }
        }

        System.out.println(bingoTables.toArray()[0]);

        return 0;
    }

    private List<Integer> getBingoNumbers(List<String> strings) {
        List<Integer> bingoNumbers = StringHelper.convertToIntegerList(StringHelper.splitAndTrim(strings.get(0)));

        //the first line contains the bingo numbers, the second line is empty.
        for (int i = 0; i < 2; i++) {
            strings.remove(0);
        }
        return bingoNumbers;
    }

    public int calculateScoreOfLastWinningBoard(String pathToBingo) {
        List<String> strings = FileReader.readFile(pathToBingo);
        List<Integer> bingoNumbers = getBingoNumbers(strings);

        Set<BingoTable> bingoTables = readTables(strings);
        Set<BingoTable> copyForIteration = new HashSet<>(bingoTables);

        BingoTable lastWinningBoard = new BingoTable();
        int lastFinalScoreUnmared = 0;
        int lastBingoNumber = 0;

        for (Integer nextNumber : bingoNumbers) {
            for (BingoTable table : copyForIteration) {
                Pair<Integer, Boolean> tableSuccess = table.checkNextNumber(nextNumber);
                if (tableSuccess.getValue()) {
                    if (bingoTables.contains(table)) {
                        lastWinningBoard = table;
                        lastFinalScoreUnmared = tableSuccess.getKey();
                        lastBingoNumber = nextNumber;

                        if (bingoTables.size() == 1) {
                            System.out.println(lastWinningBoard.print());
                            System.out.println("Final score: " + lastFinalScoreUnmared + " with number: " + lastBingoNumber);
                            System.out.println("This results in " + lastFinalScoreUnmared * lastBingoNumber);

                            return lastFinalScoreUnmared * lastBingoNumber;
                        }
                        bingoTables.remove(table);

                    }
                }
            }
        }

        System.out.println(lastWinningBoard.print());
        System.out.println("Final score: " + lastFinalScoreUnmared + " with number: " + lastBingoNumber);
        System.out.println("This results in " + lastFinalScoreUnmared * lastBingoNumber);

        System.out.println(bingoTables.toArray()[0]);

        return 0;
    }

    private Set<BingoTable> readTables(List<String> input) {
        Set<BingoTable> allTables = new HashSet<>();
        for (int i = 0; i < input.size(); i = i + 6) {
            allTables.add(new BingoTable(input.subList(i, i + 5)));
        }
        return allTables;
    }

}

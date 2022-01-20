package mama.aoc;

import java.util.List;

public class BingoTable {

    private final Tuple<Integer>[][] table = new Tuple[5][5];

    BingoTable(List<String> input) {
        if (input.size() != 5) {
            throw new Error("Only support bingo tables with 5 rows");
        }
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < 5; j++) {
                int number = Integer.parseInt(input.get(i).substring(j * 3, j * 3 + 2).trim());
                table[i][j] = new Tuple<>(number, 0);
            }
        }
    }

    BingoTable() {
        //empty table;
    }

    public AdventPair<Integer, Boolean> checkNextNumber(int number) {
        dropNumber(number);
        return wins();
    }

    private AdventPair<Integer, Boolean> wins() {
        int droppedRow = 0;
        int droppedColumn = 0;
        int sumOfUnmarked = 0;
        boolean won = false;

        //check rows
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                droppedRow += table[i][j].getValue();
                droppedColumn += table[j][i].getValue();
                if (table[i][j].getValue() == 0) {
                    sumOfUnmarked += table[i][j].getKey();
                }
            }
            //after a row (or column, check if this was a winning row:
            if (droppedColumn == table.length || droppedRow == table.length) {
                won = true;
            }
            //reset row and column
            droppedColumn = 0;
            droppedRow = 0;
        }
        return new AdventPair<>(sumOfUnmarked, won);
    }

    private void dropNumber(int bingoNumber) {
        for (Tuple<Integer>[] tuples : table) {
            for (Tuple<Integer> tuple : tuples) {
                if (tuple.getKey() == bingoNumber) {
                    tuple.increaseValue();
                    break;
                }
            }
        }
    }

    public String print() {
        StringBuilder output = new StringBuilder();
        System.out.println("BingoTable: {");
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                output.append("[").append(i).append("][").append(j).append("]: ");
                output.append(table[i][j].getKey());
                output.append(": ").append(table[i][j].getValue());
                output.append("\t");
            }
            System.out.println(output);
            output = new StringBuilder();
        }
        System.out.println("}");
        return output.toString();
    }
}

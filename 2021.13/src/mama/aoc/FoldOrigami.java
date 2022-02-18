package mama.aoc;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FoldOrigami {


    public int countDots(String path) {
        List<String> strings = FileReader.readFile(path);
        int[][] dots = printDots(strings);
        dots = foldDots(strings, dots, 1);

        return countFoldedDots(dots);
    }

    public void printDots(String path) {
        List<String> strings = FileReader.readFile(path);
        int[][] dots = foldDots(strings, printDots(strings), 12);

        for (int y = 0; y < dots[0].length; y++) {
            for (int x = 0; x < dots.length - 1; x++) {
                if (dots[x][y] == 0) System.out.print(" ");
                else System.out.print("#");
            }
            System.out.println();
        }
    }

    private int[][] printDots(List<String> strings) {
        int maxX = 0;
        int maxY = 0;

        int emptyLine = strings.indexOf("");// get index of empty line
        List<String> dotInstructions = strings.subList(0, emptyLine);

        for (String string : dotInstructions) {
            String[] dotPoint = string.split(",");
            int x = Integer.parseInt(dotPoint[0]);
            int y = Integer.parseInt(dotPoint[1]);
            if (x > maxX) maxX = x;
            if (y > maxY) maxY = y;
        }

        //set dots
        int[][] dots = new int[maxX + 1][maxY + 1];
        for (String string : dotInstructions) {
            String[] dotPoint = string.split(",");
            dots[Integer.parseInt(dotPoint[0])][Integer.parseInt(dotPoint[1])]++;
        }
        return dots;
    }

    private int[][] foldDots(List<String> instructions, int[][] dots, int foldTimes) {
        int emptyLine = instructions.indexOf("");
        List<String> foldInstructions = instructions.subList(emptyLine + 1, instructions.size());
        int[][] newDots = null;
        for (int i = 0; i < Math.min(foldTimes, foldInstructions.size()); i++) {
            String foldInstruction = foldInstructions.get(i);
            foldInstruction = foldInstruction.replace("fold along ", "");
            String[] split = foldInstruction.split("=");
            if (split[0].equals("x")) {
                int line = extractFoldLine(dots, split, true);
//                System.out.println("fold at x," + line);
                //fold at x, start at end
                newDots = new int[line][dots[0].length];
                for (int x = dots.length - 1; x > line; x--) {
                    for (int y = 0; y < dots[0].length; y++) {
                        int pointX = 2 * line - x;
                        newDots[pointX][y] = Math.min(dots[x][y] + dots[pointX][y], 1);
                    }
                }

                //copy remaining x-lines:
                for (int x = 0; x < 2 * line - dots.length; x++) {
                    for (int y = 0; y < dots[0].length; y++) {
                        newDots[x][y] = dots[x][y];
                    }
                }

            } else {
                int line = extractFoldLine(dots, split, false);
//                System.out.println("fold at y," + line);
                //fold at y, start at end
                newDots = new int[dots.length][line];

                for (int x = 0; x < dots.length; x++) {
                    for (int y = dots[x].length - 1; y > line; y--) {
                        int pointY = 2 * line - y;
                        newDots[x][pointY] = Math.min(dots[x][y] + dots[x][pointY], 1);
                    }
                }
                //copy remaining y-lines:
                for (int x = 0; x < dots.length; x++) {
                    for (int y = 0; y < 2 * line - dots[0].length; y++) {
                        newDots[x][y] = dots[x][y];
                    }
                }
            }
            dots = newDots;

        }
        return newDots;
    }

    private int extractFoldLine(int[][] dots, String[] foldInstruction, boolean isDirectionX) {
        int line = Integer.parseInt(foldInstruction[1]);
        //check if line is empty:
        if (isDirectionX) {
            for (int y = 0; y < dots[line].length; y++) {
                if (dots[line][y] > 0) {
                    throw new UnsupportedOperationException("check dots at position " + line + "," + y + ". should be 0, but is{" + dots[line][y] + ".");
                }
            }
        } else {//check y direction
            for (int x = 0; x < dots.length; x++) {
                if (dots[x][line] > 0) {
                    throw new UnsupportedOperationException("check dots at position " + x + "," + line + ". Should be , but is " + dots[x][line]);
                }
            }
        }
        return line;
    }

    private int countFoldedDots(int[][] dots) {
        int numberOfDots = 0;
        for (int[] line : dots) {
            numberOfDots += Arrays.stream(line).reduce(0, (result, dot) -> result + dot);
        }
        return numberOfDots;
    }
}

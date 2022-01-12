package mama.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VentLines {

    public int determineRisk(String pathToHydrothermalMap) {
        List<String> hydrothermalMap = FileReader.readFile(pathToHydrothermalMap);
        List<HydrothermalVent> allVents = new ArrayList<>();
        int maxX = 0;
        int maxY = 0;
        HydrothermalVent vent;
        for (String ventLine : hydrothermalMap) {
            vent = new HydrothermalVent((ventLine));
            if (vent.isHorizontalOrVertical()) {
                allVents.add(vent);
                maxX = Math.max(maxX, vent.getMaxX() + 1);
                maxY = Math.max(maxY, vent.getMaxY() + 1);
            }
        }

        int[][] ventLines = new int[maxX][maxY];
        applyAllVentsToMap(ventLines, allVents);
        return countOverlappingPoints(ventLines);
    }

    public int determineRiskInclDiagonal(String pathToHydrothermalMap) {
        List<String> hydrothermalMap = FileReader.readFile(pathToHydrothermalMap);
        List<HydrothermalVent> allVents = new ArrayList<>();
        int maxX = 0;
        int maxY = 0;
        HydrothermalVent vent;
        for (String ventLine : hydrothermalMap) {
            vent = new HydrothermalVent((ventLine));
            if (vent.isValid()) {
                allVents.add(vent);
                maxX = Math.max(maxX, vent.getMaxX() + 1);
                maxY = Math.max(maxY, vent.getMaxY() + 1);
            }
        }

        int[][] ventLines = new int[maxX][maxY];
        applyAllVentsToMap(ventLines, allVents);
        return countOverlappingPoints(ventLines);
    }

    private void printVentMap(int[][] ventMap) {
        StringBuilder result = new StringBuilder();
        for (int[] ventLine : ventMap) {
            result.append(Arrays.toString(ventLine)).append("\n");
        }
        System.out.println(result);
    }

    private void applyAllVentsToMap(int[][] ventLines, List<HydrothermalVent> allVents) {
        for (HydrothermalVent vent : allVents) {
            if (vent.isHorizontal()) {
                for (int y = vent.getY1(); y < vent.getY2() + 1; y++) {
                    ventLines[vent.getX1()][y]++;
                }

            } else if (vent.isVertical()) {
                for (int x = vent.getX1(); x < vent.getX2() + 1; x++) {
                    ventLines[x][vent.getY1()]++;
                }
            } else if (vent.isDiagonal()) {
                int x, y;
                for (int i = 0; i < vent.getLenght(); i++) {
                    if (vent.isXAcrossUp()) {
                        x = vent.getX1() + i;
                    } else {
                        x = vent.getX1() - i;
                    }
                    if (vent.isYAcrossUp()) {
                        y = vent.getY1() + i;
                    } else {
                        y = vent.getY1() - i;
                    }
                    ventLines[x][y]++;
                }
            } else {
                throw new UnsupportedOperationException("Vent is neither Horizontal, Vertical, nor diagonal. Please check: " + vent);
            }

        }

    }


    private int countOverlappingPoints(int[][] ventLines) {
        int overlappingPoints = 0;
        for (int[] ventLine : ventLines) {
            for (int point : ventLine) {
                if (point > 1)
                    overlappingPoints++;
            }
        }
        return overlappingPoints;
    }
}

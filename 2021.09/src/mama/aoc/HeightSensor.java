package mama.aoc;

import java.util.List;

public class HeightSensor {

    Tuple<Integer>[][] heights;

    public int calculateHeightRisk(String path) {
        List<String> heightMap = FileReader.readFile(path);
        int lenght = heightMap.size();
        int width = heightMap.get(0).length();
        heights = new Tuple[lenght][width];

        readHeightMap(heightMap);
        calculateLowPoints();
        int risk = sumUpRisk();
        System.out.println("Risk is: " + risk);
        return risk;
    }

    private void readHeightMap(List<String> strings) {
        for (int i = 0; i < strings.size(); i++) {
            String line = strings.get(i);
            for (int j = 0; j < strings.get(i).length(); j++) {
                int currentHeight = Integer.parseInt(line.charAt(j) + "");
                heights[i][j] = new Tuple<Integer>(currentHeight, 0);
            }
        }
    }

    private void calculateLowPoints() {
        int length = heights.length;
        int deep = heights[0].length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < deep; j++) {
                boolean lowerAdjacentFound = false;
                int currentHeight = heights[i][j].getKey();
                //System.out.println("Current height: " + currentHeight + " at position [" + i + "][" + j + "]");

                int compareHeight;
                //compare upper
                if (i > 0) {
                    lowerAdjacentFound = compareToCurrent(lowerAdjacentFound, currentHeight, heights[i - 1][j].getKey());
                }
                //compare lower
                if (i < length - 1) {
                    lowerAdjacentFound = compareToCurrent(lowerAdjacentFound, currentHeight, heights[i + 1][j].getKey());
                }
                //compare left
                if (j > 0) {
                    lowerAdjacentFound = compareToCurrent(lowerAdjacentFound, currentHeight, heights[i][j - 1].getKey());
                }
                //compare right
                if (j < deep - 1) {
                    lowerAdjacentFound = compareToCurrent(lowerAdjacentFound, currentHeight, heights[i][j + 1].getKey());
                }

                // this location has no lower adjacent location
                if (!lowerAdjacentFound) {
                    System.out.println("no lower points for number " + currentHeight + " at position [" + i + "][" + j + "]");
                    heights[i][j].setValue(heights[i][j].getKey() + 1);
                }
            }
        }
    }

    private boolean compareToCurrent(boolean lowerAdjacentFound, int currentHeight, int compareHeight) {
        if (!lowerAdjacentFound && compareHeight <= currentHeight) {
            lowerAdjacentFound = true;
            //System.out.println("Compare number " + compareHeight + " is lower than current number " + currentHeight);
        }
        return lowerAdjacentFound;
    }

    private int sumUpRisk() {
        int sum = 0;
        for (Tuple<Integer>[] line : heights) {
            for (Tuple<Integer> location : line) {
                sum += location.getValue();
            }
        }
        return sum;
    }

    private void printHeightMap() {
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[i].length; j++) {
                System.out.print("[" + i + "][" + j + "]: " + heights[i][j].getKey() + ", ");
            }
            System.out.println();
        }
    }
}

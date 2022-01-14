package mama.aoc;

import java.util.*;

public class HeightSensor {

    Tuple<Integer>[][] heights;  //map of heights (0 is low, 9 is high)
    HeighSensorTuple[][] basins; //map of basins
    Integer[] basinsSize;

    public int calculateHeightRisk(String path) {
        List<String> heightMap = FileReader.readFile(path);

        readHeightMap(heightMap);
        //value is the risk of a low point (height + 1)
        calculateLowPoints();
        int risk = sumUpRisk();
        System.out.println("Total risk is: " + risk);
        return risk;
    }

    public int findThreeBasins(String path) {
        List<String> heightMap = FileReader.readFile(path);
        //get the height map from the file. a key corresponds to the height at this position.
        readHeightMap(heightMap);
        //get the low points (a low point is its height + 1, value at this tuple point).
        calculateLowPoints();

        //copy the height map and number the basins
        assignLowPointsToHeightSensorTuples();
        //get all lowest points
        List<HeighSensorTuple> allLowPoints = findAllLowPoints();

        //print the low points
//        allLowPoints.stream().forEach(System.out::print);
//        System.out.println();

        findBasinsStartingWithLowPoints(allLowPoints);

        Arrays.sort(basinsSize, Collections.reverseOrder());
        int threeBasins = basinsSize[0] * basinsSize[1] * basinsSize[2];

        System.out.println(Arrays.toString(basinsSize));
        System.out.println("the three biggest basins multiplied are: " + threeBasins);
        return threeBasins;
    }

    private void readHeightMap(List<String> heightMap) {
        //initialize height map
        int lenght = heightMap.size();
        int width = heightMap.get(0).length();
        heights = new Tuple[lenght][width];

        for (int i = 0; i < heightMap.size(); i++) {
            String line = heightMap.get(i);
            for (int j = 0; j < heightMap.get(i).length(); j++) {
                int currentHeight = Integer.parseInt(line.charAt(j) + "");
                heights[i][j] = new Tuple<>(currentHeight, 0);
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
                    heights[i][j].setValue(heights[i][j].getKey() + 1);
                }
            }
        }
    }

    private boolean compareToCurrent(boolean lowerAdjacentFound, int currentHeight, int compareHeight) {
        if (!lowerAdjacentFound && compareHeight <= currentHeight) {
            lowerAdjacentFound = true;
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
                System.out.print("[" + i + "," + j + "]: " + heights[i][j].getKey() + "," + heights[i][j].getValue() + " ; ");
            }
            System.out.println();
        }
    }

    private void printBasinMap() {
        for (int i = 0; i < basins.length; i++) {
            for (int j = 0; j < basins[i].length; j++) {
                System.out.print("[" + i + "," + j + "]: "
                        + basins[i][j].getKey() + ","
                        + basins[i][j].getBasin() + ";");
                if ((i + j) < 20) {
                    System.out.print(" ");
                    if (basins[i][j].getBasin() < 10) {
                        System.out.print(" ");
                    } else if (basins[i][j].getBasin() < 100) {
                        System.out.print(" ");
                    }
                }
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    /**
     * copy the height tuples to height sensor tuples, number the basins 1 up to x
     */
    private void assignLowPointsToHeightSensorTuples() {
        basins = new HeighSensorTuple[heights.length][heights[0].length];
        int c = 0;
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[i].length; j++) {
                Tuple<Integer> tuple = heights[i][j];
                int basinNumber = 0;
                if (tuple.getValue() > 0) {
                    basinNumber = ++c;
                }
                basins[i][j] = new HeighSensorTuple(tuple.getKey(), tuple.getValue(), i, j);
                basins[i][j].setBasin(basinNumber);
            }
        }
        basinsSize = new Integer[c];
        Arrays.fill(basinsSize, 1);
    }

    private List<HeighSensorTuple> findAllLowPoints() {
        List<HeighSensorTuple> allLowPoints = new ArrayList<>();
        for (HeighSensorTuple[] line : basins) {
            for (HeighSensorTuple location : line) {
                if (location.isLowPoint) {
                    allLowPoints.add(location);
                }
            }
        }
        return allLowPoints;
    }

    private void findBasinsStartingWithLowPoints(List<HeighSensorTuple> allLowPoints) {
        for (HeighSensorTuple startingPoint : allLowPoints) {
            LinkedList<HeighSensorTuple> fifo = new LinkedList<>();
            checkHeightForBasins(fifo, startingPoint);
            while (!fifo.isEmpty()) {
                HeighSensorTuple checkPoint = fifo.removeFirst();
                checkHeightForBasins(fifo, checkPoint);
            }
        }
    }

    private void checkHeightForBasins(LinkedList<HeighSensorTuple> fifo, HeighSensorTuple checkPoint) {
        //check upper
        if (checkPoint.getPositionX() > 0) {
            checkPoint(fifo, checkPoint.getPositionX() - 1, checkPoint.getPositionY(), checkPoint.getBasin());
        }
        //check left
        if (checkPoint.getPositionY() > 0) {
            checkPoint(fifo, checkPoint.getPositionX(), checkPoint.getPositionY() - 1, checkPoint.getBasin());
        }
        //check lower
        if (checkPoint.getPositionX() < basins.length - 1) {
            checkPoint(fifo, checkPoint.getPositionX() + 1, checkPoint.getPositionY(), checkPoint.getBasin());
        }
        //check right
        if (checkPoint.getPositionY() < basins[0].length - 1) {
            checkPoint(fifo, checkPoint.getPositionX(), checkPoint.getPositionY() + 1, checkPoint.getBasin());
        }

    }

    private void checkPoint(LinkedList<HeighSensorTuple> fifo, int positionX, int positionY, int basin) {
        HeighSensorTuple checkPoint = basins[positionX][positionY];
        if (checkPoint.isInABasin()) {
            checkPoint.setBasin(basin);
            basinsSize[basin - 1]++;
            fifo.add(checkPoint);
        }
    }

}

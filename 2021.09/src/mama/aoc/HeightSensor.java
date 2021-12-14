package mama.aoc;

import java.util.*;

public class HeightSensor {

    Tuple<Integer>[][] heights;
    HeighSensorTuple[][] basins;
    List<Integer> basinsSize = new ArrayList<>();

    public int calculateHeightRisk(String path) {
        List<String> heightMap = FileReader.readFile(path);

        readHeightMap(heightMap);
        calculateLowPoints();
        int risk = sumUpRisk();
        System.out.println("Risk is: " + risk);
        return risk;
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
//                    System.out.println("no lower points for number " + currentHeight + " at position [" + i + "][" + j + "]");
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

    public int findThreeBasins(String path) {
        List<String> heightMap = FileReader.readFile(path);
        readHeightMap(heightMap);
        //first, get the low points.
        calculateLowPoints();
        //set all basins to -1
        assignLowPointsToSensorTuples();
        //then assign basins:
        assignBasinsRecursive();

//        readHeightMap(heightMap);
//        calculateBasins();
//        basinsSize.sort(Collections.reverseOrder());
        // System.out.println(basinsSize);
        int threeBasins = basinsSize.get(0) * basinsSize.get(1) * basinsSize.get(2);
        System.out.println("the three biggest basins multiplied are: " + threeBasins);
        return threeBasins;
    }

    private void assignLowPointsToSensorTuples() {
        basins = new HeighSensorTuple[heights.length][heights[0].length];
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[i].length; j++) {
                Tuple<Integer> tuple = heights[i][j];
                basins[i][j] = new HeighSensorTuple(tuple.getKey(), tuple.getValue(), i, j);
            }
        }
    }

    private void assignBasinsRecursive() {
        LinkedList<HeighSensorTuple> fifo = new LinkedList<>();
        for (int i = 0; i < basins.length; i++) {
            for (int j = 0; j < basins[i].length; j++) {
                HeighSensorTuple lowPoint = basins[i][j];
                //low point is really a lowest point = start search
                if (lowPoint.isLowPoint) {
                    System.out.println("low point found at position [" + i + "][" + j + "]");
                    basinsSize.add(1);
                    int currentBasin = basinsSize.size();
                    checkAdjacents(fifo, i, j, currentBasin);
                }
            }
        }
        while (!fifo.isEmpty()) {
            HeighSensorTuple adjacent = fifo.removeFirst();
            System.out.println("Adjacent found. check position [" + adjacent.getPositionX() + "][" + adjacent.getPositionY() + "]");
            checkAdjacents(fifo, adjacent.getPositionX(), adjacent.getPositionY(), adjacent.getBasin());
        }
    }

    private void checkAdjacents(LinkedList<HeighSensorTuple> fifo, int i, int j, int currentBasin) {
        HeighSensorTuple adjacent;
        //check up
        if (i > 0) {
            adjacent = basins[i - 1][j];
            checkSingleAdjacent(fifo, currentBasin, adjacent);
        }
        //check low
        if (i < basins.length - 1) {
            adjacent = basins[i + 1][j];
            checkSingleAdjacent(fifo, currentBasin, adjacent);
        }
        //check left
        if (j > 0) {
            adjacent = basins[i][j - 1];
            checkSingleAdjacent(fifo, currentBasin, adjacent);
        }
        //check right
        if (j < basins[0].length - 1) {
            adjacent = basins[i][j + 1];
            checkSingleAdjacent(fifo, currentBasin, adjacent);
        }
    }

    private void checkSingleAdjacent(LinkedList<HeighSensorTuple> fifo, int basin, HeighSensorTuple adjacent) {
        if (adjacent.getKey() == 9) {
            //if it's a 9, do nothing
            System.out.println("Position " + adjacent.getPositionX() + ", " + adjacent.getPositionY() + " is a 9, don't check.");
        } else if (adjacent.getBasin() == 0) {
            //do something
            adjacent.basin = basin;
            basinsSize.set(basin - 1, basinsSize.get(basin - 1));
            fifo.add(adjacent);
        }
    }

    private void calculateBasins() {
        int length = heights.length;
        int deep = heights[0].length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < deep; j++) {
                Tuple<Integer> currentTuple = heights[i][j];

                if (currentTuple.getKey() == 9) {
                    continue;
                } else {
                    int upperBasin = 0;
                    int leftBasin = 0;
                    int currentBasin = 0;
                    Tuple<Integer> upperTuple = new Tuple<>(9, 0);
                    Tuple<Integer> leftTuple = new Tuple<>(9, 0);
                    //compare upper
                    if (i > 0) {
                        upperTuple = heights[i - 1][j];
                        if (upperTuple.getKey() < 9) {
                            upperBasin = upperTuple.getValue();
                        }
                    }
                    //compare left
                    if (j > 0) {
                        leftTuple = heights[i][j - 1];
                        if (leftTuple.getKey() < 9) {
                            leftBasin = leftTuple.getValue();
                        }
                    }
                    //check if both are the same:
                    if (upperBasin > 0 && leftBasin > 0 && upperBasin != leftBasin) {
                        System.out.println("Check basins! at position [" + i + "][" + j + "]. Upper: " + upperBasin + " vs. Left: " + leftBasin);
                        //reassign "bigger" basin
//                        if (upperBasin > leftBasin){
//                            upperTuple.setValue(leftBasin);
//                            Integer sizeOfUpperBasin = basinsSize.get(upperBasin - 1);
//                            System.out.println("Size of upper basin: " + sizeOfUpperBasin);
//                            basinsSize.set(leftBasin + 1, basinsSize.get(leftBasin + 1) + sizeOfUpperBasin);
//                            basinsSize.set(upperBasin+1, 0);
//                        } else {
//                            System.out.println("think harder");
//                        }
                    } else if (upperBasin == leftBasin) {
                        currentBasin = upperBasin;
                    }
                    //check if at least one basin is non-zero:
                    if (upperBasin + leftBasin > 0) {
                        currentBasin = upperBasin;
                        if (currentBasin == 0) {
                            currentBasin = leftBasin; // either of them should be 0, so assigning the sum should be ok
                        }
                    }
                    //check if both neighbours (up and left) are 9
                    else if (upperTuple.getKey() == 9 && leftTuple.getKey() == 9) {
                        //if yes, then check the diagonal upper to the right number
                        if (i > 0 && j < deep - 1) {
                            Tuple<Integer> adjacentBasin = heights[i - 1][j + 1];
                            Tuple<Integer> rightBasin = heights[i][j + 1];
                            if (rightBasin.getKey() < 9 && adjacentBasin.getKey() < 9) {
                                currentBasin = adjacentBasin.getValue();
                            }
                        }
                    }
                    if (currentBasin == 0) {
                        basinsSize.add(1);
                        currentBasin = basinsSize.size();
                    } else {
                        //System.out.println("Basin to search for " + (currentBasin-1));
                        Integer currentBasinSize = basinsSize.get(currentBasin - 1);
                        basinsSize.set(currentBasin - 1, currentBasinSize + 1);
                    }
                    heights[i][j].setValue(currentBasin);
//                    System.out.println("Current basin: " + currentBasin + " for number " + currentTuple.getKey()
//                            + " at position [" + i + "][" + j + "] has size " + basinsSize.get(currentBasin - 1));
                }
            }

        }
    }

    private class HeighSensorTuple extends Tuple<Integer> {

        int basin;
        int positionX;
        int positionY;
        boolean isLowPoint;

        public HeighSensorTuple(Integer key, int value, int positionX, int positionY, boolean isLowPoint) {
            super(key, value);
            this.positionX = positionX;
            this.positionY = positionY;
            this.isLowPoint = isLowPoint;
        }

        public HeighSensorTuple(Integer key, int value, int positionX, int positionY) {
            super(key, value);
            this.positionX = positionX;
            this.positionY = positionY;
            this.isLowPoint = false;
            if (value > 0) this.isLowPoint = true;
        }

        private HeighSensorTuple(Integer key, int value) {
            super(key, value);
        }

        public int getBasin() {
            return basin;
        }

        public void setBasin(int basin) {
            this.basin = basin;
        }

        public int getPositionX() {
            return positionX;
        }

        public void setPositionX(int positionX) {
            this.positionX = positionX;
        }

        public int getPositionY() {
            return positionY;
        }

        public void setPositionY(int positionY) {
            this.positionY = positionY;
        }
    }
}

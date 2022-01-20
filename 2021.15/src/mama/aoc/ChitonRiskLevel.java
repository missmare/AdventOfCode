package mama.aoc;

import java.util.*;

public class ChitonRiskLevel {

    public int calculateRisk(String path) {
        List<String> strings = FileReader.readFile(path);
        //get the chiton list
        int xSize = strings.get(0).length();
        int ySize = strings.size();
        int[][] chitons = new int[xSize][ySize];
        for (int y = 0; y < ySize; y++) {
            String string = strings.get(y);
            for (int x = 0; x < string.length(); x++) {
                int i1 = Integer.parseInt(string.substring(x, x + 1));
                chitons[x][y] = i1;
            }
        }
        //chiton risk is parsed
        for (int i = 0; i < xSize; i++) {
            System.out.println(Arrays.toString(chitons[i]));
        }

        //calculate the minimum path
        int maxTotalRisk = xSize * 9 + ySize * 9;
        int maxIterations = xSize * ySize;

        List<ChitonPathCalculator> fifo = new LinkedList<>();
        fifo.add(new ChitonPathCalculator());

        int totalRisk = maxTotalRisk;
        while (!fifo.isEmpty()) {
            ChitonPathCalculator current = fifo.remove(0);
            if (current.getTotalRisk() > maxTotalRisk || current.getIterations() > maxIterations) {
                continue; //drop this risk
            }
            if (isAtEnd(current, xSize, ySize) && current.getTotalRisk() < totalRisk) {
                totalRisk = current.getTotalRisk();
            }
            //move in all directions
            ChitonPathCalculator movedChiton;
            if (isMovingUpAllowed(current, xSize, ySize)) {
                movedChiton = current.moveUp(chitons);
                if (isPathAllowed(movedChiton, totalRisk, maxIterations)) {
                    System.out.println("move up");
                    fifo.add(movedChiton);
                }
            }
            if (isMovingDownAllowed(current, xSize, ySize)) {
                movedChiton = current.moveDown(chitons);
                if (isPathAllowed(movedChiton, totalRisk, maxIterations)) {
                    fifo.add(movedChiton);
                    System.out.println("move down");
                }
            }
            if (isMovingLeftAllowed(current, xSize, ySize)) {
                movedChiton = current.moveLeft(chitons);
                if (isPathAllowed(movedChiton, totalRisk, maxIterations)) {
                    System.out.println("move left");
                    fifo.add(movedChiton);
                }
            }
            if (isMovingRightAllowed(current, xSize, ySize)) {
                movedChiton = current.moveRight(chitons);
                if (isPathAllowed(movedChiton, totalRisk, maxIterations)) {
                    System.out.println("move right");
                    fifo.add(movedChiton);
                }
            }
        }
        return totalRisk;
    }

    private boolean isMovingUpAllowed(ChitonPathCalculator current, int maxX, int maxY) {
        int newXPosition = current.getAtPosition().getFirst();
        int newYPosition = current.getAtPosition().getSecond() - 1;
        return isMovingAllowed(current.getCameFrome(), newXPosition, newYPosition, maxX, maxY);
    }

    private boolean isMovingDownAllowed(ChitonPathCalculator current, int maxX, int maxY) {
        int newXPosition = current.getAtPosition().getFirst();
        int newYPosition = current.getAtPosition().getSecond() + 1;
        return isMovingAllowed(current.getCameFrome(), newXPosition, newYPosition, maxX, maxY);
    }

    private boolean isMovingLeftAllowed(ChitonPathCalculator current, int maxX, int maxY) {
        int newXPosition = current.getAtPosition().getFirst() - 1;
        int newYPosition = current.getAtPosition().getSecond();
        return isMovingAllowed(current.getCameFrome(), newXPosition, newYPosition, maxX, maxY);
    }

    private boolean isMovingRightAllowed(ChitonPathCalculator current, int maxX, int maxY) {
        int newXPosition = current.getAtPosition().getFirst() + 1;
        int newYPosition = current.getAtPosition().getSecond();
        return isMovingAllowed(current.getCameFrome(), newXPosition, newYPosition, maxX, maxY);
    }

    private boolean isMovingAllowed(AdventPair<Integer, Integer> cameFrom, int xPosition, int yPosition, int maxX, int maxY) {
        return (isPositionAllowed(xPosition, yPosition, maxX, maxY))
                && (cameFrom == null || !(cameFrom.getFirst() == xPosition && cameFrom.getSecond() == yPosition));
    }

    private boolean isPositionAllowed(int xPosition, int yPosition, int maxX, int maxY) {
        return (xPosition > -1 && xPosition < maxX)
                && (yPosition > -1 && yPosition < maxY);
    }

    private boolean isPathAllowed(ChitonPathCalculator current, int maxTotalRisk, int maxTotalIterations) {
        return current.getTotalRisk() < maxTotalRisk
                && current.getIterations() < maxTotalIterations;
    }

    private boolean isAtEnd(ChitonPathCalculator current, int xSize, int ySize) {
        return current.getAtPosition().getFirst() == xSize - 1 && current.getAtPosition().getSecond() == ySize - 1;
    }


}

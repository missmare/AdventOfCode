package mama.aoc.trial;

import mama.aoc.FileReader;

import java.util.*;

public class Chiton {

    private Node[][] riskMap;
    private int maxX;
    private int maxY;

    private Node origin;
    private Node destination;

    public int calculateLowestRisk(String path) {
        List<String> input = FileReader.readFile(path);

        initialize(input, 1);
        return applyAlgorithm();
    }

    public int calculateBigChitonCave(String path) {
        List<String> input = FileReader.readFile(path);
        initialize(input, 5);
        return applyAlgorithm();
    }

    private void initialize(List<String> inputs, int numberOfTiles) {
        int initialX = inputs.size();
        int initialY = inputs.get(0).length();

        maxX = initialX * numberOfTiles;
        maxY = initialY * numberOfTiles;

        riskMap = new Node[maxX][maxY];
        for (int y = 0; y < maxY; y++) {
            int tileY = y / initialY;
            char[] numbers = inputs.get(y % initialY).toCharArray();
//            System.out.println(Arrays.toString(numbers));
            for (int x = 0; x < maxX; x++) {
                //calculate the risk for, increase for the n-tile
                int tileX = x / initialX;
                int riskIncrement = tileX + tileY;
                int originalRisk = Integer.parseInt(Character.toString(numbers[x % initialX]));
                int baseRisk = originalRisk + riskIncrement;
                int modRisk = baseRisk % 10;
                int divRisk = baseRisk / 10;
                int realRisk = modRisk + divRisk;

/*                System.out.println("tileX: " + tileX + ", tileY: " + tileY
                        + ", riskIncrement: " + riskIncrement
                        + ", risk: " + originalRisk
                        + ", -> " + realRisk);*/
                riskMap[x][y] = new Node(x, y, realRisk);
            }
        }

        origin = riskMap[0][0];
        destination = riskMap[maxX - 1][maxY - 1];


    }

    private void printRiskMap() {
        for (Node[] riskArray :
                riskMap) {
            System.out.println(Arrays.toString(riskArray));
        }
    }

    private int applyAlgorithm() {
        origin.setTotalRisk(0);
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(origin);
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            currentNode.setVisited(true);
            Set<Node> adjacentNodes = getAdjacentNodes(currentNode);
            for (Node adjacentNode : adjacentNodes) {
                if (!adjacentNode.isVisited()) {
                    //cost to move from current to this node
                    int currentRisk = adjacentNode.getRisk();
                    //cost to move from origin to this node
                    int estimatedRisk = currentNode.getTotalRisk() + currentRisk;

                    if (adjacentNode.getTotalRisk() > estimatedRisk) {
                        adjacentNode.setTotalRisk(estimatedRisk);
                        queue.add(adjacentNode);
                    }
                }
            }
        }
        printRiskMap();
        return destination.getTotalRisk();
    }

    private Set<Node> getAdjacentNodes(Node node) {
        Set<Node> adjacentNodes = new HashSet<>(4);
        int x = node.getX();
        int y = node.getY();
        if (isWithinRange(x, y - 1)) { // add up
            adjacentNodes.add(riskMap[x][y - 1]);
        }
        if (isWithinRange(x, y + 1)) { // add down
            adjacentNodes.add(riskMap[x][y + 1]);
        }
        if (isWithinRange(x - 1, y)) { // add left
            adjacentNodes.add(riskMap[x - 1][y]);
        }
        if (isWithinRange(x + 1, y)) { // add right
            adjacentNodes.add(riskMap[x + 1][y]);
        }
        return adjacentNodes;
    }

    private boolean isWithinRange(int x, int y) {
        return (x >= 0 && x < maxX && y >= 0 && y < maxY);
    }

}


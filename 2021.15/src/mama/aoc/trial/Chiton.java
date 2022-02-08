package mama.aoc.trial;

import mama.aoc.FileReader;

import java.util.*;

public class Chiton {

    private Node[][] riskMap;
    private int maxX;
    private int maxY;

    private Node origin;
    private Node destination;

    public int calculateChitonCave(String path) {
        List<String> input = FileReader.readFile(path);

        initialize(input);

        int minPathLenghth = applyAlgorithm();
        return minPathLenghth;
    }

    private void initialize(List<String> inputs) {
        maxX = inputs.size();
        maxY = inputs.get(0).length();

        riskMap = new Node[maxX][maxY];
        for (int y = 0; y < maxY; y++) {
            int tileY = y / maxX;
            char[] numbers = inputs.get(y).toCharArray();
//            System.out.println(Arrays.toString(numbers));
            for (int x = 0; x < maxX; x++) {
                //calculate the risk for the n-tile
                int tileX = x / maxX;
                int riskIncrement = tileX + tileY;
                int originalRisk = Integer.parseInt(Character.toString(numbers[x]));
                int baseRisk = originalRisk + riskIncrement;
                int modRisk = baseRisk % 10;
                int divRisk = baseRisk / 10;
                int realRisk = modRisk + divRisk;

/*                System.out.println("tileX: " + tileX + ", tileY: " + tileY
                        + ", riskIncrement: " + riskIncrement
                        + ", originalRisk: " + numbers[x]
                        + ", parsed: " + originalRisk);*/
                riskMap[x][y] = new Node(x, y, realRisk);
            }
        }

        origin = riskMap[0][0];
        destination = riskMap[maxX - 1][maxY - 1];

//        System.out.println(Arrays.deepToString(riskMap));
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
        return destination.getTotalRisk();
    }

    private Set<Node> getAdjacentNodes(Node node) {
        Set<Node> adjacentNodes = new HashSet<>();
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


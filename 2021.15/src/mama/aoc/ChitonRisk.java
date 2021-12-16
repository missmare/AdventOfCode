package mama.aoc;

import javafx.util.Pair;

import java.util.*;

public class ChitonRisk {

    public int calculateLowestRisk(String path) {
        //read input into list
        List<String> strings = FileReader.readFile(path);
        List<List<Integer>> risk = StringHelper.convertToNumberDoubleList(strings);

        //destination
        int maxPositionX = risk.size() - 1;
        int maxPositionY = risk.get(0).size() - 1;

        //calculate minimum path
        List<Path> possiblePaths = new ArrayList<>();
        possiblePaths.add(new Path(0, 0, maxPositionX, maxPositionY));
        int minimumRisk = (9 * maxPositionX * maxPositionY) / 3;
        Pair<Integer, Integer> currentPosition;
        int currentX;
        int currentY;
        Path current;
        Path copy;


        while (!possiblePaths.isEmpty()) {
            current = possiblePaths.get(possiblePaths.size() - 1);
            possiblePaths.remove(current);
            //if current minimum is lower than current total risk, discard this option.
            if (current.getTotalRisk() > minimumRisk) {
                continue;
            }
            currentPosition = current.getLastPosition();
            currentX = currentPosition.getKey();
            currentY = currentPosition.getValue();

            //if possible: go right and down
            if (current.isAtEnd()) {
                if (current.getTotalRisk() < minimumRisk) {
                    minimumRisk = current.getTotalRisk();
                }
            } else {
                if (currentX < maxPositionX) {
                    copy = current.copy();
                    copy.navigate(currentX + 1, currentY, risk.get(currentX + 1).get(currentY));
                    if (copy.getTotalRisk() < minimumRisk) {
                        possiblePaths.add(copy);
                    }
                }
                if (currentY < maxPositionY) {
                    copy = current.copy();
                    copy.navigate(currentX, currentY + 1, risk.get(currentX).get(currentY + 1));
                    if (copy.getTotalRisk() < minimumRisk) {
                        possiblePaths.add(copy);
                    }
                }
            }
        }

        return minimumRisk;
    }

    private class Path {
        List<Pair<Integer, Integer>> pathList;
        Integer totalRisk;
        Pair<Integer, Integer> endPosition;

        private Path() {
            pathList = new LinkedList<>();
            totalRisk = 0;
        }

        public Path(int startX, int startY, int endX, int endY) {
            totalRisk = 0;
            pathList = new LinkedList<>();
            pathList.add(new Pair(startX, startY));
            endPosition = new Pair(endX, endY);
        }

        public int getTotalRisk() {
            return totalRisk;
        }

        public void navigate(int positionX, int positionY, int localRisk) {
            pathList.add(new Pair(positionX, positionY));
            totalRisk += localRisk;
        }

        public Pair getLastPosition() {
            Pair<Integer, Integer> lastPosition = pathList.get(pathList.size() - 1);
            return lastPosition;
        }

        public boolean isAtEnd() {
            Pair currentPosition = getLastPosition();
            if (endPosition.getKey() == currentPosition.getKey() && endPosition.getValue() == currentPosition.getValue()) {
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return "Path{" +
                    "way=" + pathList +
                    ", totalRisk=" + totalRisk +
                    '}';
        }

        public Path copy() {
            Path newPath = new Path();
            newPath.pathList = new LinkedList(this.pathList);
            newPath.totalRisk = this.totalRisk;
            newPath.endPosition = new Pair(this.endPosition.getKey(), this.endPosition.getValue());
            return newPath;
        }
    }
}

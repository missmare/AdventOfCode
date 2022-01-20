package mama.aoc;

import java.util.*;

public class ChitonRisk {

    public int calculateLowestRisk(String path) {
        //read input into list
        List<String> strings = FileReader.readFile(path);
        List<List<Integer>> chitonList = StringHelper.convertToNumberListOfLists(strings);

        //destination
        int maxPositionX = chitonList.size() - 1;
        int maxPositionY = chitonList.get(0).size() - 1;

        //calculate minimum path
        List<Path> possiblePaths = new ArrayList<>();
        possiblePaths.add(new Path(0, 0, maxPositionX, maxPositionY));
        int minimumRisk = (9 * maxPositionX * maxPositionY) / 3;
        AdventPair<Integer, Integer> currentPosition;
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
            currentX = currentPosition.getFirst();
            currentY = currentPosition.getSecond();

            //if possible: go right and down
            if (current.isAtEnd()) {
                if (current.getTotalRisk() < minimumRisk) {
                    minimumRisk = current.getTotalRisk();
                }
            } else {
                if (currentX < maxPositionX) {
                    copy = current.copy();
                    copy.navigate(currentX + 1, currentY, chitonList.get(currentX + 1).get(currentY));
                    if (copy.getTotalRisk() < minimumRisk) {
                        possiblePaths.add(copy);
                    }
                }
                if (currentY < maxPositionY) {
                    copy = current.copy();
                    copy.navigate(currentX, currentY + 1, chitonList.get(currentX).get(currentY + 1));
                    if (copy.getTotalRisk() < minimumRisk) {
                        possiblePaths.add(copy);
                    }
                }
            }
        }

        return minimumRisk;
    }

}

package mama.aoc;

import java.util.HashSet;
import java.util.Set;

public class ProbeTraject {

    TargetArea target;

    public int maxHeighthForTarget(String path) {
        String targetArea = FileReader.readSingleLine(path);
        readTarget(targetArea);
        return shootTarget();
    }

    public int numberOfPossiblePaths(String path) {
        String targetArea = FileReader.readSingleLine(path);
        readTarget(targetArea);
        Set<AdventPair<Integer, Integer>> possibleStartPositions = getPossibleStartPositions();
        return possibleStartPositions.size();
    }

    private void readTarget(String targetArea) {
        targetArea = targetArea.replace(",", "");
        String[] target = targetArea.split(" ");
        int startX = 0, startY = 0, endX = 0, endY = 0;
        for (String area : target) {
            if (area.startsWith("x")) {
                String[] split = area.substring(2).split("\\.\\.");
                startX = Integer.parseInt(split[0]);
                endX = Integer.parseInt(split[1]);
            }
            if (area.startsWith("y")) {
                String[] split = area.substring(2).split("\\.\\.");
                startY = Integer.parseInt(split[0]);
                endY = Integer.parseInt(split[1]);
            }
        }
        this.target = new TargetArea(startX, endX, startY, endY);
    }

    private int shootTarget() {
        return getMaxHeightForStarts(getPossibleStartPositions());
    }

    private Set<AdventPair<Integer, Integer>> getPossibleStartPositions() {
        Set<AdventPair<Integer, Integer>> possibleStarts = new HashSet<>();
        for (int y = Math.min(0, target.getStartY()); y <= Math.max(Math.abs(target.getStartY()), Math.abs(target.getEndY())); y++) {
            for (int x = 1; x <= Math.max(target.getStartX(), target.getEndX()); x++) {
                AdventPair<Integer, Integer> startPosition = new AdventPair<>(x, y);
                if (target.isTargetReachable(startPosition) || target.isWithinTarget(startPosition)) {
                    if (target.isWithinTarget(startPosition)) {
                        possibleStarts.add(startPosition);
                        continue;
                    }
                    int step = 1;
                    AdventPair<Integer, Integer> next = startPosition;
                    do {
                        next = executeStep(startPosition, next, step);
                        step++;
                        if (target.isWithinTarget(next)) {
                            possibleStarts.add(startPosition);
                            continue;
                        }
                    } while (target.isTargetReachable(next));
                } else {
//                    System.out.println("target not reachable from start " + startPosition);
                    break;
                }
            }
        }
        return possibleStarts;
    }

    private int getMaxHeightForStarts(Set<AdventPair<Integer, Integer>> possibleStarts) {
        int maxY = 0;
        for (AdventPair<Integer, Integer> startPosition : possibleStarts) {
            int step = 1;
            AdventPair<Integer, Integer> next = startPosition;
            while (!target.isWithinTarget(next)) {
                next = executeStep(startPosition, next, step);
                maxY = Math.max(maxY, next.getSecond());
                step++;
            }
        }
        return maxY;
    }

    private AdventPair<Integer, Integer> executeStep(AdventPair<Integer, Integer> startPosition, AdventPair<Integer, Integer> lastPosition, int stepNumber) {
        int differenceX = Math.max(0, startPosition.getFirst() - stepNumber);
        int differenceY = startPosition.getSecond() - stepNumber;
        return new AdventPair<>(lastPosition.getFirst() + differenceX, lastPosition.getSecond() + differenceY);
    }


}

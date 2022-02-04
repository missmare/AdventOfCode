package mama.aoc.algorithm;

import mama.aoc.AdventPair;

import java.util.List;

public class RiskPath {

    AdventPair<Integer, Integer> currentPosition;
    AdventPair<Integer, Integer> fromPosition;
    long totalRisk = 0L;
    long iterations = 0L;

    public RiskPath() {
        this.currentPosition = new AdventPair<>(0, 0);
    }

    public long getTotalRisk() {
        return totalRisk;
    }

    public long getIterations() {
        return iterations;
    }

    public AdventPair<Integer, Integer> getCurrentPosition() {
        return currentPosition;
    }

    public boolean isAtPosition(int positionX, int positionY) {
        return currentPosition.getFirst() == positionX && currentPosition.getSecond() == positionY;
    }

    public boolean cameFrom(int positionX, int positionY) {
        if (fromPosition == null) {
            return false;
        }
        return fromPosition.correspondsToOther(positionX, positionY);
    }

    public RiskPath moveTo(int positionX, int positionY, List<List<Integer>> localRisk) {
        RiskPath newPath = new RiskPath();
        newPath.fromPosition = this.currentPosition;
        newPath.currentPosition = new AdventPair<>(positionX, positionY);
        newPath.totalRisk = this.totalRisk + localRisk.get(positionX).get(positionY);
        newPath.iterations = this.iterations + 1;
        return newPath;
    }
}

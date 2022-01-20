package mama.aoc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Path {

    List<AdventPair<Integer, Integer>> pathList = new ArrayList<>();
    Integer totalRisk = 0;
    AdventPair<Integer, Integer> endPosition;

    private Path() {

    }

    public Path(int startX, int startY, int endX, int endY) {
        pathList = new LinkedList<>();
        pathList.add(new AdventPair<>(startX, startY));
        endPosition = new AdventPair<>(endX, endY);
    }

    public int getTotalRisk() {
        return totalRisk;
    }

    public void navigate(int positionX, int positionY, int localRisk) {
        pathList.add(new AdventPair<>(positionX, positionY));
        totalRisk += localRisk;
    }

    public AdventPair<Integer, Integer> getLastPosition() {
        AdventPair<Integer, Integer> lastPosition = pathList.get(pathList.size() - 1);
        return lastPosition;
    }

    public boolean isAtEnd() {
        AdventPair<Integer, Integer> currentPosition = getLastPosition();
        if (endPosition.getFirst() == currentPosition.getFirst() && endPosition.getSecond() == currentPosition.getSecond()) {
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
        newPath.endPosition = new AdventPair<>(this.endPosition.getFirst(), this.endPosition.getSecond());
        return newPath;
    }
}

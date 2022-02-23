package mama.aoc;

import lombok.Getter;

@Getter
public class TargetArea {

    int startX;
    int startY;
    int endX;
    int endY;

    public TargetArea(int startX, int endX, int startY, int endY) {
        this.startX = startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;
    }

    public boolean isWithinTarget(AdventPair<Integer, Integer> position) {
        return startX <= position.getFirst() && position.getFirst() <= endX
                && ((startY <= position.getSecond() && position.getSecond() <= endY)
                || (startY >= position.getSecond() && position.getSecond() >= endY));
    }

    public boolean isTargetReachable(AdventPair<Integer, Integer> position) {
        boolean xPositiv = 0 < position.getFirst();
        boolean xlessthanend = position.getFirst() <= endX;
        boolean yPositiv = endY > 0 && position.getSecond() > 0;
        boolean ynegativ = endY < 0 && position.getSecond() >= startY;
        return xPositiv && xlessthanend
                && ((yPositiv)    || ynegativ);
    }
}

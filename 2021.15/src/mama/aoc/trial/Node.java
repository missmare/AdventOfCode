package mama.aoc.trial;

public class Node implements Comparable<Node> {

    private final int x;
    private final int y;

    private final int risk;
    private int totalRisk = Integer.MAX_VALUE;
    private boolean visited = false;

    public Node(int x, int y, int risk) {
        this.x = x;
        this.y = y;
        this.risk = risk;
    }

    public void setTotalRisk(int totalRisk) {
        this.totalRisk = totalRisk;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isVisited() {
        return visited;
    }

    public int getRisk() {
        return risk;
    }

    public int getTotalRisk() {
        return totalRisk;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.totalRisk, other.getTotalRisk());
    }

    @Override
    public String toString() {
        return "#" + x + "," + y + ": " + risk ;
    }
}

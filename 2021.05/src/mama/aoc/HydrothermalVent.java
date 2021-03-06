package mama.aoc;

public class HydrothermalVent {

    protected int x1;
    protected int x2;
    protected int y1;
    protected int y2;

    public HydrothermalVent(String input) {
        if (!input.matches("^\\d+,\\d+ -> \\d+,\\d+$")) {
            throw new UnsupportedOperationException("Please check input: " + input);
        }

        String[] splits = input.split(" -> ");
        String[] startPoint = splits[0].split(",");
        x1 = Integer.parseInt(startPoint[1]);
        y1 = Integer.parseInt(startPoint[0]);
        String[] endPoint = splits[1].split(",");
        x2 = Integer.parseInt(endPoint[1]);
        y2 = Integer.parseInt(endPoint[0]);
        ensureLowerToUpper();
    }

    public boolean isHorizontalOrVertical() {
        return isHorizontal() || isVertical();
    }

    public boolean isValid() {
        return isHorizontal() || isVertical() || isDiagonalSimple() || isDiagonalReverse();
    }

    /**
     * Checks, if a vent is vertical
     *
     * @return true, if the y-values are the same
     */
    public boolean isVertical() {
        return y1 == y2;
    }

    /**
     * Checks if a vent is horizontal.
     *
     * @return true, if the x-values are the same.
     */
    public boolean isHorizontal() {
        return x1 == x2;
    }

    public boolean isDiagonal() {
        return isDiagonalSimple() || isDiagonalReverse();
    }

    private boolean isDiagonalSimple() {
        int diffX = x2 - x1;
        int diffY = y2 - y1;

        return diffX == diffY;
    }

    private boolean isDiagonalReverse() {
        int diffX = x2 - x1;
        int diffY = y2 - y1;
        return diffX == Math.negateExact(diffY);
    }

    public boolean isXAcrossUp() {
        if (isDiagonalSimple() || isVertical()) {
            return true;
        } else {
            return x2 > x1;
        }
    }

    public boolean isYAcrossUp() {
        if (isDiagonalSimple() || isHorizontal()) {
            return true;
        } else {
            return y2 > y1;
        }
    }

    public int getMaxX() {
        return Math.max(x1, x2);
    }

    public int getMaxY() {
        return Math.max(y1, y2);
    }

    private void ensureLowerToUpper() {
        if (x1 > x2) {
            swapEndpoints();
        } else if (y1 > y2) {
            swapEndpoints();
        }
    }

    private void swapEndpoints() {
        //swap x
        int x = x1;
        x1 = x2;
        x2 = x;

        //swap y
        int y = y1;
        y1 = y2;
        y2 = y;
    }

    public int getLenght() {
        if (isVertical()) {
            return y2 - y1 + 1;
        } else { //horizontal or diagonal
            return Math.abs(x2 - x1) + 1;
        }
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    @Override
    public String toString() {
        return "{" + "x1=" + x1 + ", y1=" + y1 + " -> " + "x2=" + x2 + ", y2=" + y2 + "}";
    }
}

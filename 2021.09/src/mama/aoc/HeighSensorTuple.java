package mama.aoc;

public class HeighSensorTuple extends Tuple<Integer> {

    //super key: height
    //super value: risk (low points = height + 1
    int basin;
    int positionX;
    int positionY;
    boolean isLowPoint;

    public HeighSensorTuple(Integer key, int value, int positionX, int positionY) {
        super(key, value);
        this.positionX = positionX;
        this.positionY = positionY;
        this.isLowPoint = (value > 0); //if there is a risk set as value, this is a low point
    }

    public int getBasin() {
        return basin;
    }

    public void setBasin(int basin) {
        this.basin = basin;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public boolean isInABasin() {
        return this.getKey() < 9 && this.getBasin() == 0;
    }

    @Override
    public String toString() {
        return "{[" + positionX +
                "," + positionY +
                "]: basin=" + basin +
                ", isLowPoint=" + isLowPoint +
                '}';
    }
}

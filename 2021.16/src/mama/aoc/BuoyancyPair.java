package mama.aoc;

public class BuoyancyPair extends AdventPair<Integer, Long>{

    public BuoyancyPair(Integer first, Long second) {
        super(first, second);
    }

    public int getLength() {
        return getFirst();
    }

    public long getLiteral() {
        return getSecond();
    }

    @Override
    public String toString() {
        return "Buoyancy: Length: " + getLength() + ", Literal: " + getLiteral();
    }
}

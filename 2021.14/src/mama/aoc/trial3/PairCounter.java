package mama.aoc.trial3;

import java.util.Objects;

public class PairCounter {

    private PolymerizationPair pair;
    private long counter = 0L;

    public PairCounter(PolymerizationPair pair) {
        this.pair = pair;
    }

    public PairCounter(PolymerizationPair pair, Long l) {
        this.pair = pair;
        this.counter = l;
    }

    public void increaseCounterBy(Long l) {
        this.counter += l;
    }

    public PolymerizationPair getPair() {
        return this.pair;
    }

    public long getCounter() {
        return this.counter;
    }

    public boolean correspondsToOther(PolymerizationPair otherPair) {
        if (otherPair == null) return false;
        return Objects.equals(this.pair.getLeft(), otherPair.getLeft()) && Objects.equals(this.pair.getRight(), otherPair.getRight());
    }

    @Override
    public String toString() {
        return "PairCounter{" + pair + ", counter=" + counter +
                '}';
    }
}
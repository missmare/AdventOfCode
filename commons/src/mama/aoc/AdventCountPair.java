package mama.aoc;

import java.util.Objects;

public class AdventCountPair<A> extends AdventPair<A, Long> {

    public AdventCountPair(A first, Long second) {
        super(first, second);
    }

    public void increaseCounterBy(Long l) {
        this.setSecond(this.getSecond() + l);
    }

    public boolean correspondsToOtherPair(AdventPair other) {
        if (other == null) return false;
        if (!(this.getFirst() instanceof AdventPair)) return false;
        AdventPair thisPair = (AdventPair) this.getFirst();
        return Objects.equals(thisPair.getFirst(), other.getFirst()) && Objects.equals(thisPair.getSecond(), other.getSecond());
    }


//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null || getClass() != obj.getClass()) return false;
//        AdventCountPair<?> that = (AdventCountPair<?>) obj;
//        //only compare first argument, second argument is counter and doesn't matter
//        return Objects.equals(this.getFirst(), that.getFirst());
//    }
//
//    @Override
//    public String toString() {
//        return "AdventCountPair{" + getFirst() + ", " + getSecond() + '}';
//    }
}

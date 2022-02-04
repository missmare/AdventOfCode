package mama.aoc;

import java.util.Objects;

public class AdventPair<A, B> {

    private A first;
    private B second;

    public AdventPair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return this.first;
    }

    public void setSecond(B value) {
        this.second = value;
    }

    public B getSecond() {
        return this.second;
    }

    @Override
    public String toString() {
        return "AdventPair{" + first + ", " + second + '}';
    }

    public boolean correspondsToOtherPair(AdventPair other) {
        if (other == null) return false;
        boolean equals = Objects.equals(this.first, other.first) && Objects.equals(this.second, other.second);
        boolean same = this.first.equals(other.first) && this.second.equals(other.second);
        return equals;
    }

    public boolean correspondsToOther(A otherFirst, B otherSecond) {
        return Objects.equals(this.first, otherFirst) && Objects.equals(this.second, otherSecond);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdventPair<?, ?> that = (AdventPair<?, ?>) o;
        return Objects.equals(first, that.first) && Objects.equals(second, that.second);
    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(first, second);
//    }
}

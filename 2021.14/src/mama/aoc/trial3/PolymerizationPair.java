package mama.aoc.trial3;

import mama.aoc.AdventPair;

public class PolymerizationPair {

    private AdventPair<Character, Character> pair;

    public PolymerizationPair(String pair) {
        if (pair.length() == 2) {
            this.pair = new AdventPair<>(pair.charAt(0), pair.charAt(1));
        } else {
            throw new UnsupportedOperationException("Please check polymerization pair: " + pair);
        }
    }

    public PolymerizationPair(Character left, Character right) {
        pair = new AdventPair<>(left, right);
    }

    public Character getLeft() {
        return pair.getFirst();
    }

    public Character getRight() {
        return pair.getSecond();
    }

    @Override
    public String toString() {
        return "Pair[" + pair.getFirst() + "," + pair.getSecond() + "]";
    }
}

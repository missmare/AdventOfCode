package mama.aoc;

import java.util.Objects;

public class PolymerizationPair {

    AdventPair<Character, Character> pair;
    Character insertionLetter;

    public PolymerizationPair(String rule) {
        String[] fullRule = rule.split(" -> ");
        if (fullRule.length == 2) {
            pair = new AdventPair<>(fullRule[0].charAt(0), fullRule[0].charAt(1));
            insertionLetter = fullRule[1].charAt(0);
        } else {
            System.out.println("Please check rule: " + rule);
        }
    }

    public AdventPair[] getPairsAfterRule() {
        AdventPair start = new AdventPair(pair.getFirst(), insertionLetter);
        AdventPair end = new AdventPair(insertionLetter, pair.getSecond());
        return new AdventPair[]{start, end};
    }

    public boolean isRuleForPair(AdventPair other) {
        if (other == null) return false;
        return Objects.equals(this.pair, other);
    }

    @Override
    public String toString() {
        return "{ " + pair + ", insert=" + insertionLetter +
                '}';
    }

//    /**
//     * compare only pair, insertion letter doesn't matter
//     * * @param o
//     *
//     * @return
//     */
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        PolymerizationPair that = (PolymerizationPair) o;
//        return Objects.equals(pair, that.pair);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(pair);
//    }
}

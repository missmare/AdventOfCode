package mama.aoc;

public class PolymerizationRule {

    private PolymerizationPair pair;
    private Character insertLetter;

    public PolymerizationRule(String rule) {
        String[] fullRule = rule.split(" -> ");
        if (fullRule.length == 2) {
            pair = new PolymerizationPair(fullRule[0]);
            insertLetter = fullRule[1].charAt(0);
        } else {
            System.out.println("Check rule: " + rule);
        }
    }

    public PolymerizationPair getPair() {
        return this.pair;
    }

    public PolymerizationPair[] getPairsAfterRule() {
        PolymerizationPair start = new PolymerizationPair(pair.getLeft(), insertLetter);
        PolymerizationPair end = new PolymerizationPair(insertLetter, pair.getRight());
        return new PolymerizationPair[]{start, end};
    }

    @Override
    public String toString() {
        return "{" + pair + ", insert=" + insertLetter + '}';
    }

}

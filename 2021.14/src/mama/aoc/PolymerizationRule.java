package mama.aoc;

public class PolymerizationRule {

    String pair;
    String insertLetter;

    PolymerizationRule(String rule) {
        String[] fullRule = rule.split(" ");
        if (fullRule.length == 3) {
            pair = fullRule[0];
            insertLetter = fullRule[2];
        } else {
            System.out.println("Check rule: " + rule);
        }
    }

    public boolean matchPair(String checkPair) {
        return pair.equalsIgnoreCase(checkPair);
    }

    public String getStringAfterRule() {
        return insertLetter + pair.charAt(1);
    }
}

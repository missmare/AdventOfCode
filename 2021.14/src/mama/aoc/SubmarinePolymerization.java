package mama.aoc;

import java.text.NumberFormat;
import java.util.*;

public class SubmarinePolymerization {

    private String template;
    private Map<PolymerizationPair, PolymerizationRule> polymerizationRules = new HashMap<>();

    private final NumberFormat nf = NumberFormat.getInstance();

    public SubmarinePolymerization() {
        nf.setGroupingUsed(true);
    }

    /**
     * Calculate the difference of the max and min occurring characters after having applied the rules in path to the template in path x times.
     *
     * @param path  path to template and rules
     * @param times apply the rules on the template x times
     * @return difference of max and min occurring characters in template after having applied rules x times
     */
    public long calculatePolymerization(String path, int times) {
        readInput(path);
        List<PairCounter> pairCounter = applyRules(times);
        Map<Character, Long> characterMap = countLetters(pairCounter);

        //get min and max occurring character
        char minChar = '-';
        long minCount = Long.MAX_VALUE;
        char maxChar = '+';
        long maxCount = 0;
        for (Map.Entry<Character, Long> entry : characterMap.entrySet()) {
            if (minCount > entry.getValue()) {
                minCount = entry.getValue();
                minChar = entry.getKey();
            }
            if (maxCount < entry.getValue()) {
                maxCount = entry.getValue();
                maxChar = entry.getKey();
            }
        }

        System.out.println("Max Letter: " + maxChar + ", " + maxCount);
        System.out.println("Min Letter: " + minChar + ", " + minCount);
        System.out.println("Difference: " + nf.format(maxCount - minCount));
        System.out.println("Difference: " + (maxCount - minCount));

        return maxCount - minCount;
    }

    private void readInput(String path) {
        List<String> strings = FileReader.readFile(path);
        template = (strings.get(0));

        //rules are from line 2 on
        for (int i = 2; i < strings.size(); i++) {
            PolymerizationRule rule = new PolymerizationRule(strings.get(i));
            polymerizationRules.put(rule.getPair(), rule);
        }
    }

    private List<PairCounter> applyRules(int times) {
        //first split template into pairs.
        List<PairCounter> listOfPairs = splitIntoPairs(template);

        //then apply rules x times
        for (int i = 0; i < times; i++) {
            listOfPairs = applyRulesOnPairs(listOfPairs);
            System.out.println("Length of pairs after " + (i + 1) + " iterations: " + nf.format(getTotalLenght(listOfPairs)));
        }
        return listOfPairs;
    }

    private List<PairCounter> splitIntoPairs(String template) {
        List<PairCounter> listOfPairs = new ArrayList<>();
        for (int i = 0; i < template.length() - 1; i++) {
            PolymerizationPair pair = new PolymerizationPair(template.charAt(i), template.charAt(i + 1));
            addPairxTimesToList(pair, listOfPairs, 1L);
        }
        return listOfPairs;
    }

    private List<PairCounter> applyRulesOnPairs(List<PairCounter> listOfPairsBeforeRules) {
        List<PairCounter> listOfPairsAfterRules = new ArrayList<>();

        for (PairCounter pairCounterBeforeRule : listOfPairsBeforeRules) {
            PolymerizationRule polymerizationRule = getRuleForPair(pairCounterBeforeRule.getPair());
            PolymerizationPair[] pairsAfterRule = polymerizationRule.getPairsAfterRule();
            for (PolymerizationPair polymerizationPair : pairsAfterRule) {
                addPairxTimesToList(polymerizationPair, listOfPairsAfterRules, pairCounterBeforeRule.getCounter());
            }
        }
        return listOfPairsAfterRules;
    }

    private PolymerizationRule getRuleForPair(PolymerizationPair pair) {
        for (Map.Entry<PolymerizationPair, PolymerizationRule> rule : polymerizationRules.entrySet()) {
            if (pair.toString().equals(rule.getKey().toString())) {
                return rule.getValue();
            }
        }
        return null;
    }

    private void addPairxTimesToList(PolymerizationPair pair, List<PairCounter> listOfPairs, long times) {
        boolean addNew = true;
        for (PairCounter singlePairCounter : listOfPairs) {
            if (singlePairCounter.correspondsToOther(pair)) {
                singlePairCounter.increaseCounterBy(times);
                addNew = false;
                break;
            }
        }
        if (addNew) {
            listOfPairs.add(new PairCounter(pair, times));
        }
    }

    private long getTotalLenght(List<PairCounter> listOfPairCounters) {
        return listOfPairCounters.stream().mapToLong(PairCounter::getCounter).sum() + 1L;
    }

    private Map<Character, Long> countLetters(List<PairCounter> pairCounters) {
        Map<Character, Long> letters = new HashMap<>();
        //add first character, this is otherwise not counted
        letters.put(template.charAt(0), 1L);
        //add second character from each pair
        for (PairCounter pairWithCounter : pairCounters) {
            Long counter = letters.remove(pairWithCounter.getPair().getRight());
            if (counter == null) {
                counter = pairWithCounter.getCounter();
            } else {
                counter += pairWithCounter.getCounter();
            }
            letters.put(pairWithCounter.getPair().getRight(), counter);
        }
        return letters;
    }
}

package mama.aoc;


import java.util.ArrayList;
import java.util.List;

public class SubmarinePolymerization {

    List<PolymerizationPair> polymerizationRules = new ArrayList<>();
    private String template;

    public long calculatePolymerization(String path, int times) {
        readInput(path);
        List<AdventCountPair<AdventPair<Character, Character>>> listOfPairs = applyRules(times);

        //get min and max letters
        List<AdventCountPair<Character>> countCharacters = countLetters(template, listOfPairs);
        AdventPair<Character, Long> maxCharacter = new AdventPair('+', 0L);
        AdventPair<Character, Long> minCharacter = new AdventPair('-', Long.MAX_VALUE);
        for (AdventCountPair<Character> countCharacter : countCharacters) {
            if (maxCharacter.getSecond() < countCharacter.getSecond()) {
                maxCharacter = new AdventPair<>(countCharacter.getFirst(), countCharacter.getSecond());
            }
            if (minCharacter.getSecond() > countCharacter.getSecond()) {
                minCharacter = new AdventPair<>(countCharacter.getFirst(), countCharacter.getSecond());
            }

        }
        System.out.println("Max Letter: " + maxCharacter);
        System.out.println("Min Letter: " + minCharacter);

        return maxCharacter.getSecond() - minCharacter.getSecond();
    }

    private void readInput(String path) {
        List<String> strings = FileReader.readFile(path);
        template = (strings.get(0));

        //rules are from line 2 on
        for (int i = 2; i < strings.size(); i++) {
            PolymerizationPair pair = new PolymerizationPair(strings.get(i));
//            polymerizationRules.put(pair.getPair(), pair);
            polymerizationRules.add(pair);
        }
    }

    private List<AdventCountPair<AdventPair<Character, Character>>> applyRules(int times) {
        //first split template into pairs.
        List<AdventCountPair<AdventPair<Character, Character>>> listOfPairs = splitIntoPairs(template);

        //then apply rules x times
        for (int i = 0; i < times; i++) {
            listOfPairs = applyRulesOnPairs(listOfPairs);
        }
        return listOfPairs;
    }

    private List<AdventCountPair<AdventPair<Character, Character>>> applyRulesOnPairs(List<AdventCountPair<AdventPair<Character, Character>>> listOfPairsBeforeRules) {
        List<AdventCountPair<AdventPair<Character, Character>>> listOfPairsAfterRules = new ArrayList<>();

        for (AdventCountPair<AdventPair<Character, Character>> pairBeforeRule : listOfPairsBeforeRules) {
            for (PolymerizationPair polymerizationRule : polymerizationRules) {
                if (polymerizationRule.isRuleForPair(pairBeforeRule.getFirst())) {
                    AdventPair[] pairsAfterRule = polymerizationRule.getPairsAfterRule();
                    for (AdventPair pair : pairsAfterRule) {
                        addPairToList(pair, listOfPairsAfterRules);
                    }
                    break;
                }
            }
        }
        return listOfPairsAfterRules;
    }

    private List<AdventCountPair<AdventPair<Character, Character>>> splitIntoPairs(String template) {
        List<AdventCountPair<AdventPair<Character, Character>>> listOfPairs = new ArrayList<>();
        for (int i = 0; i < template.length() - 1; i++) {
            AdventPair<Character, Character> pair = new AdventPair<>(template.charAt(i), template.charAt(i + 1));
            addPairToList(pair, listOfPairs);
        }
        return listOfPairs;
    }

    private void addPairToList(AdventPair<Character, Character> pair, List<AdventCountPair<AdventPair<Character, Character>>> listOfPairs) {
//        listOfPairs.stream()
//                .filter((AdventCountPair<AdventPair<Character, Character>> acp) -> acp.getFirst().correspondsToOtherPair(pair))
//                .findFirst()
//                .ifPresent((AdventCountPair acp) -> acp.increaseCounterBy(1L));
        AdventCountPair countedPair = getCountedPair(pair, listOfPairs);
        if (countedPair != null) {
            countedPair.increaseCounterBy(1L);
        } else {
            listOfPairs.add(new AdventCountPair<>(pair, 1L));
        }
    }

    private AdventCountPair getCountedPair(AdventPair<Character, Character> pair, List<AdventCountPair<AdventPair<Character, Character>>> listOfPairs) {
        for (AdventCountPair<AdventPair<Character, Character>> countedPair : listOfPairs) {
            if (countedPair.correspondsToOtherPair(pair)) {
                return countedPair;
            }
        }
        return null;
    }

    private List<AdventCountPair<Character>> countLetters(String template, List<AdventCountPair<AdventPair<Character, Character>>> listOfPairsWithCounter) {
        List<AdventCountPair<Character>> letters = new ArrayList<>();
        letters.add(new AdventCountPair<>(template.charAt(0), 1L));
        for (AdventCountPair<AdventPair<Character, Character>> pairWithCounter : listOfPairsWithCounter) {
            boolean foundLetter = false;
            for (AdventCountPair<Character> letterCounter : letters) {
                if (letterCounter.getFirst().equals(pairWithCounter.getFirst().getSecond())) {//take the second character
                    letterCounter.increaseCounterBy(pairWithCounter.getSecond());   //increase by number of occurrences
                    foundLetter = true;
                    break;
                }
            }
            //add new letter to count
            if (!foundLetter) {
                letters.add(new AdventCountPair<>(pairWithCounter.getFirst().getSecond(), 1L));
            }
        }
        return letters;
    }
}

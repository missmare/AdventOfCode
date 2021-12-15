package mama.aoc;

import java.math.BigInteger;
import java.util.*;

public class Polymerization {

    String template;
    Set<PolymerizationRule> polymerizationRules = new HashSet<>();

    public int applyRulesTimes(String path, int times) {
        readTemplateAndRules(path);

        applyRules(times);

        Map<Character, Long> countLetters = countLetters();
        //get min and max occurence
        Tuple<Character> maxTuple = new Tuple<>('+', 0);
        Tuple<Character> minTuple = new Tuple<>('⁻', template.length());
        for (Map.Entry<Character, Long> entry : countLetters.entrySet()) {
            if (entry.getValue() > maxTuple.getValue()) {
                maxTuple = new Tuple<>(entry.getKey(), entry.getValue().intValue());
            }
            if (entry.getValue() < minTuple.getValue()) {
                minTuple = new Tuple<>(entry.getKey(), entry.getValue().intValue());
            }
        }
        System.out.println("Max: " + maxTuple);
        System.out.println("Min: " + minTuple);

        //calculate
        System.out.println("Result: " + (maxTuple.getValue() - minTuple.getValue()));

        return maxTuple.getValue() - minTuple.getValue();
    }

    private void readTemplateAndRules(String path) {
        List<String> strings = FileReader.readFile(path);
        template = strings.remove(0);
        //remove the second line as well
        strings.remove(0);

        //next lines contain rules
        for (String ruleAsSingleString : strings) {
            PolymerizationRule rule = new PolymerizationRule(ruleAsSingleString);
            polymerizationRules.add(rule);
        }
    }

    private void applyRules(int times) {
        //max lenght
        long finalLenght = template.length();
        System.out.println("template is " + finalLenght + " chars long. " + template);
        for (int i = 0; i < times; i++) {
            finalLenght = 2 * finalLenght - 1;
            System.out.println("Apply rules for the " + (i + 1) + " time produces a string of lenght "+ finalLenght);
        }

        //cannot cast to int, number is too high
        long arraySize = (finalLenght+1) / 2L;
        System.out.println("half of the length is: " + arraySize);
        int finalArraySize = (int) arraySize;
        System.out.println("Cast to int with (brackets): " + finalArraySize);

        Vector v = new Vector();
        for (int i = 0; i < times; i++) {
            for (int j = 0; j < template.length(); j++) {

            }
        }

        for (int i = 0; i < times; i++) {

        }
    }

    private void applyRules2(int times) {
        //max lenght
        long finalLenght = template.length();
        System.out.println("template is " + finalLenght + " chars long. " + template);
        for (int i = 0; i < times; i++) {
            finalLenght = 2 * finalLenght - 1;
            System.out.println("Apply rules for the " + (i + 1) + " time produces a string of lenght "+ finalLenght);
        }

        StringBuilder newTemplate = new StringBuilder();
        newTemplate.setLength((int) finalLenght);
        String currentPair;

        for (int i = 0; i < times; i++) {
            System.out.println("Apply rules for the " + (i + 1) + " time. New String is now long: " + newTemplate.length());
            newTemplate.append(template.charAt(0));
            for (int j = 0; j < template.length() - 1; j++) {
                currentPair = template.substring(j, j + 2);
                for (PolymerizationRule rule : polymerizationRules) {
                    if (rule.matchPair(currentPair)) {
                        newTemplate.append(rule.getStringAfterRule());
                        break;
                    }
                }
            }
            template = newTemplate.toString();

            //System.out.println("   new template: " + template);
        }

        //System.out.println("Template after applying the rules for " + times + " times: " + template);
    }

    private Map<Character, Long> countLetters() {
        Map<Character, Long> letterCount = new HashMap<>();
        for (char currentLetter : template.toCharArray()) {
            long currentLetterTimes = 0;
            if (letterCount.containsKey(currentLetter)) {
                currentLetterTimes = letterCount.get(currentLetter);
            }
            currentLetterTimes++;
            letterCount.put(currentLetter, currentLetterTimes);
        }
        return letterCount;
    }

    public long applyLongRulesTimes(String path, int times) {
        readTemplateAndRules(path);
        applyRules(times);

        Map<Character, Long> countLetters = countLetters();
        //get min and max occurence
        long maxAmount = 0;
        long minAmount = template.length();
        for (Map.Entry<Character, Long> entry : countLetters.entrySet()) {
            if (entry.getValue() > maxAmount) {
                maxAmount = entry.getValue();
            }
            if (entry.getValue() < minAmount) {
                minAmount = entry.getValue();
            }
        }
        System.out.println("Max: " + maxAmount);
        System.out.println("Min: " + minAmount);

        //calculate
        System.out.println("Result: " + (maxAmount - minAmount));
        return maxAmount - minAmount;
    }

    public long applyLongRulesTimesExceedlong(String path, int times) {
        readTemplateAndRules(path);
        applyRules2(times);

        Map<Character, Long> countLetters = countLetters();
        //get min and max occurence
        long maxAmount = 0;
        long minAmount = template.length();
        for (Map.Entry<Character, Long> entry : countLetters.entrySet()) {
            if (entry.getValue() > maxAmount) {
                maxAmount = entry.getValue();
            }
            if (entry.getValue() < minAmount) {
                minAmount = entry.getValue();
            }
        }
        System.out.println("Max: " + maxAmount);
        System.out.println("Min: " + minAmount);

        //calculate
        System.out.println("Result: " + (maxAmount - minAmount));
        return maxAmount - minAmount;
    }
}

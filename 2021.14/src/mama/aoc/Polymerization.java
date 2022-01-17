package mama.aoc;

import java.util.*;
import java.util.stream.Collectors;

public class Polymerization {

    List<String> template = new ArrayList<>();
    Set<PolymerizationRule> polymerizationRules = new HashSet<>();

    public int applyRulesTimes(String path, int times) {
        readTemplateAndRules(path);

        applyRules(times);

        //count occurrence of letters
        Map<Character, Long> countLetters = countLetters();
        //get min and max occurrence
        Tuple<Character> maxTuple = new Tuple<>('+', 0);
        Tuple<Character> minTuple = new Tuple<>('⁻', Integer.MAX_VALUE);
        for (Map.Entry<Character, Long> entry : countLetters.entrySet()) {
            if (entry.getValue() > maxTuple.getValue()) {
                maxTuple = new Tuple<>(entry.getKey(), entry.getValue().intValue());
            }
            if (entry.getValue() < minTuple.getValue()) {
                minTuple = new Tuple<>(entry.getKey(), entry.getValue().intValue());
            }
        }
        System.out.println("Template lenght: " + getTotalTemplateLenght());
        System.out.println("Max: " + maxTuple);
        System.out.println("Min: " + minTuple);

        //calculate
        System.out.println("Result: " + (maxTuple.getValue() - minTuple.getValue()));

        return maxTuple.getValue() - minTuple.getValue();
    }

    private void readTemplateAndRules(String path) {
        List<String> strings = FileReader.readFile(path);
        template.add(strings.remove(0));
        //remove the second line as well
        strings.remove(0);

        //next lines contain rules
        for (String ruleAsSingleString : strings) {
            PolymerizationRule rule = new PolymerizationRule(ruleAsSingleString);
            polymerizationRules.add(rule);
        }
    }

    private void applyRules(int times) {
        //calculate max lenght
//        long templateLength = getTotalTemplateLenght();
//        System.out.println("when starting is template " + templateLength + " chars long: " + template);
//        long power = MathematicCalculator.calculatePower(2, times);
//        long finalLength = power * templateLength - (power - 1);
//        System.out.println("Apply rules for " + times + " times, produces a string of length " + finalLength);

        StringBuilder newTemplate = new StringBuilder();
        String currentPair;
        List<String> templatesAfterRule = new LinkedList<>();
        char remainingChar;
        for (int i = 0; i < times; i++) { //iterate x-times to apply the rules
            remainingChar = template.get(0).charAt(0);
            newTemplate.append(remainingChar);
            for (String singleTemplate : template) { //iterate over a part of the template
                //for second line of template: add remaining char to check.
                if (template.size() > 1 && !template.get(0).equals(singleTemplate)) {
                    currentPair = String.copyValueOf(new char[]{remainingChar, singleTemplate.charAt(0)});
                    checkRule(currentPair, newTemplate);
                }
                //iterate over the characters of this part
                for (int j = 0; j < singleTemplate.length() - 1; j++) {
                    currentPair = singleTemplate.substring(j, j + 2);
                    checkRule(currentPair, newTemplate);
                }
                int imax = Integer.MAX_VALUE / 2;
                remainingChar = singleTemplate.charAt(singleTemplate.length() - 1);
                newTemplate.delete(0, newTemplate.length()-1); //reset new template to empty
                if (newTemplate.length() > imax) {
                    templatesAfterRule.add(newTemplate.substring(0, imax));
                    templatesAfterRule.add(newTemplate.substring(imax));
                } else {
                    templatesAfterRule.add(newTemplate.toString());
                }
            }
            //ensure size (and not only capacity) of list of templates
            for (int j = template.size(); j < templatesAfterRule.size(); j++) {
                template.add("");
            }
            Collections.copy(template, templatesAfterRule);
            System.out.println("after step " + (i+1) + " template is " + getTotalTemplateLenght() + " long and has "+ template.size() + " elements.");
//            template.stream().map((String s) -> s.length() + ",").forEach(System.out::print);
//            System.out.println();
//            System.out.println("  after step " + (i + 1) + " template is: '" + printFullTemplate() + "'");
            templatesAfterRule.clear();
        }
    }

    private String printFullTemplate() {
        return template.stream().collect(Collectors.joining(","));
    }

    private void checkRule(String currentPair, StringBuilder newTemplate) {
        for (PolymerizationRule rule : polymerizationRules) { //apply the rules
            if (rule.matchPair(currentPair)) {
                newTemplate.append(rule.getStringAfterRule());
                break;
            }
        }
    }

    private Map<Character, Long> countLetters() {
        Map<Character, Long> letterCount = new HashMap<>();
        for (String partOfTemplate : template) {
            for (char currentLetter : partOfTemplate.toCharArray()) {
                long currentLetterTimes = 0;
                if (letterCount.containsKey(currentLetter)) {
                    currentLetterTimes = letterCount.get(currentLetter);
                }
                currentLetterTimes++;
                letterCount.put(currentLetter, currentLetterTimes);
            }
        }
        return letterCount;
    }

    private long getTotalTemplateLenght() {
        return template.stream().mapToLong(String::length).sum();
    }

}

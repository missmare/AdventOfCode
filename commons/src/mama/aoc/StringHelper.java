package mama.aoc;

import java.util.ArrayList;
import java.util.List;

public class StringHelper {

    /**
     * Split a single line by comma (,) and trim the result.
     *
     * @param content list as a single string, separated by ,
     * @return a list of strings
     */
    public static List<String> splitAndTrim(String content) {
        List<String> result = new ArrayList<>();
        for (String item : content.split(",")) {
            result.add(item.trim());
        }
        return result;
    }

    /**
     * Convert a list of Strings to a list of Integers.
     *
     * @param stringList a list of strings
     * @return a list of integers
     */
    public static List<Integer> convertToIntegerList(List<String> stringList) {
        List<Integer> intList = new ArrayList<>(stringList.size());
        for (String s : stringList) {
            intList.add(Integer.parseInt(s));
        }
        return intList;
    }

    /**
     * Convert a list of Strings, which are basically numbers, to a double list (=list of list) of Integers.
     *
     * @param stringList a list of strings containing numbers
     * @return a list of list of numbers (similar to Integer[][])
     */
    public static List<List<Integer>> convertToNumberDoubleList(List<String> stringList) {
        List<List<Integer>> intMap = new ArrayList<>(stringList.size());
        for (int i = 0; i < stringList.size(); i++) {
            String line = stringList.get(i);
            intMap.add(new ArrayList<>(line.length()));
            for (Character character : line.toCharArray()) {
                intMap.get(i).add(Integer.parseInt(character + ""));
            }
        }
        return intMap;
    }
}

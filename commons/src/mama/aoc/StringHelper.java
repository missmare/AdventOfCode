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
}

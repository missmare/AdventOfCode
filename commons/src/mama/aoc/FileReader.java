package mama.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {

    /**
     * Read file, line by line.
     *
     * @param path Path to file
     * @return a list of Strings, an item per line
     */
    public static List<String> readFile(String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            System.out.println("Failed to read file: " + path);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Read a file, assume that the list is now separated by comma (,) on a single line.
     * Split the line by commas and return a list of the items.
     * @param path path to file with a single line-list, separated by commas
     * @return a list of items
     */
    public static List<String> readListFromFile(String path) {
        List<String> singleLine = FileReader.readFile(path);
        if (singleLine != null && singleLine.size() == 1) {
            String content = singleLine.get(0);
            List<String> result = new ArrayList<>();
            for (String item : content.split(",")) {
                result.add(item.trim());
            }
            return result;
        } else {
            return singleLine;
        }
    }

    public static List<Integer> convertToIntegerList(List<String> stringList) {
        List<Integer> intList = new ArrayList<>(stringList.size());
        for (String s : stringList) {
            intList.add(Integer.parseInt(s));
        }
        return intList;
    }
}
